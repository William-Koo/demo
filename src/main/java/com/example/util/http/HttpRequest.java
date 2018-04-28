package com.example.util.http;

import com.example.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

/**
 * 描述:
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: William-Koo
 * 版本: 1.0
 * 创建日期: 2017/8/26 10:05
 */
public class HttpRequest {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequest.class);

    private static int TIME_OUT = 30000;

    /**
     * get 请求
     * @param url
     * @param params
     * @param charSet 参数不需要编码， 可以传 null
     * @return
     */
    public static String sendGet(String url, Map<String, String> params, CharSet charSet) {
        logger.info("------ 发送 GET 请求：------");
        String data = "";
        try {
            //拼接参数
            String joinParam = joinParam(params, charSet);
            //创建链接对象
            URLConnection conn = getConn(url, RequestMethod.GET, joinParam);

            // 建立实际的连接
            conn.connect();
            // 输入流读取URL的响应
            data = getData(conn);

            logger.info("发送 GET 请求成功！");
        } catch (Exception e) {
            logger.error("发送GET请求出现异常！" + e);
        }

        return data;
    }

    /**
     * post 请求
     * @param url
     * @param params
     * @param charSet 参数不需要编码， 可以传 null
     * @return
     */
    public static String sendPost(String url, Map<String, String> params, CharSet charSet) {
        logger.info("------ 发送 POST 请求：------");
        PrintWriter out = null;
        String data = "";

        try {
            //创建链接对象
            URLConnection conn = getConn(url, RequestMethod.POST, null);

            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //拼接参数
            String joinParam = joinParam(params, charSet);
            // 发送请求参数
            out.print(joinParam);
            out.flush();
            // 输入流读取URL的响应
            data = getData(conn);
            logger.info("发送 POST 请求成功！");
        } catch (Exception e) {
            logger.error("发送 POST 请求出现异常！"+e);
        }finally{
            if(out != null){
                out.close();
            }
        }
        return data;
    }


    /**
     * 拼接请求参数
     * @param params
     * @param charSet 参数是否需要编码(不需要传null)
     * @return
     */
    private static String joinParam(Map<String, String> params, CharSet charSet){
        StringBuilder param = new StringBuilder();
        boolean flag = charSet == null? false: true;
        if(params != null && !params.isEmpty()){
            Set<Map.Entry<String, String>> entry = params.entrySet();
            for (Map.Entry<String, String> e : entry) {
                String key = e.getKey();
                String value = e.getValue();
                if(flag){
                    param.append(key + "=" + StringUtil.encode(value, charSet.getCode()) + "&");
                }else{
                    param.append(key + "=" + value + "&");
                }
            }
            param.deleteCharAt(param.length()-1);
        }

        return param.toString();
    }



    /**
     * 获取请求数据
     * @param conn
     * @return
     */
    private static String getData(URLConnection conn){
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            logger.error("解析响应数据异常：", e);

        }finally{
            try {
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                logger.error("关闭响应流异常：", e);
            }
        }

        return result.toString();
    }



    /**
     * 内部获取连接方法
     * @param urlStr
     * @param method
     * @param joinParam
     *
     * @return
     */
    private static URLConnection getConn(String urlStr, RequestMethod method, String joinParam){
        URL realUrl = null;
        // 打开和URL之间的连接
        URLConnection conn = null;
        try {
            if(RequestMethod.POST.equals(method)){
                realUrl = new URL(urlStr);
                conn = realUrl.openConnection();
                // 发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
            }else if(RequestMethod.GET.equals(method)){
                realUrl = new URL(urlStr +"?"+ joinParam);
                conn = realUrl.openConnection();
            }

            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            conn.setConnectTimeout(TIME_OUT);
            conn.setReadTimeout(TIME_OUT);
        } catch (MalformedURLException e) {
            logger.error("获取连接异常：", e);
        } catch (IOException e) {
            logger.error("获取连接异常：", e);
        }

        return conn;
    }

    /**
     * 请求类型
     */
    private enum RequestMethod{
        GET,POST
    }


    public enum CharSet {
        GBK("GBK"),
        UTF8("UTF-8"),
        GB2312("GB2312")

        ;

        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        private CharSet(String code){
            this.code = code;
        }
    }

}
