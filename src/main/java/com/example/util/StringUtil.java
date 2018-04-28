package com.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 版权: Copyright (c) 2017
 * 公司:
 * @author : William-Koo
 * 版本: 1.0
 * 创建日期: 2017/8/26 10:02
 */
public class StringUtil {

    private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

    private static final String UNDEFINED = "undefined";
    private static final String NULL = "null";

    /**
     * 判断是否为空
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o){
        if(o == null || "".equals(o)){
            return true;
        }
        String s = trim(o.toString());
        if("".equals(s) ||s.length() == 0 || UNDEFINED.equalsIgnoreCase(s) ||NULL.equalsIgnoreCase(s)){
            return true;
        }
        return false;
    }


    /**
     *
     * @param o
     * @return
     */
    public static boolean isNotEmpty(Object o){
        return !isEmpty(o);
    }




    /**
     * 字符串编码
     * @param str
     * @param code
     * @return
     */
    public static String encode(String str, String code) {
        try {
            return URLEncoder.encode(str, code);
        } catch (Exception ex) {
            logger.error("encode error: ", ex);
            return "";
        }
    }



    /**
     * 字符串解码
     * @param str
     * @param code
     * @return
     */
    public static String decode(String str, String code) {
        try {
            return URLDecoder.decode(str, code);
        } catch (Exception ex) {
            logger.error("decode error: ", ex);
            return "";
        }
    }


    /**
     * 判断一个字符串是否都为数字
     * @param s
     * @return
     */
    public static boolean isDigit(String s) {

        return s.matches("[0-9]{1,}");
    }





    /**
     * <b>功能说明</b>
     * 半角汉字转全角汉字
     * @param s  半角
     * @return  全角字符串
     * <p>
     * <b>例如：</b><br>
     * 使用例子说明
     * String a = BQchange("赞同")
     * </p>
     */
    public static final String BQchange(String s) {
        StringBuilder outStr = new StringBuilder();
        String Tstr = "";
        byte[] b = null;

        try {
            for (int i = 0, len = s.length(); i < len; i++) {
                Tstr = s.substring(i, i + 1);
                b = Tstr.getBytes("unicode");

                if (b[3] != -1) {
                    b[2] = (byte) (b[2] - 32);
                    b[3] = -1;
                    outStr.append(new String(b, "unicode"));
                } else{
                    outStr.append(Tstr);
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("半角汉字转全角汉字 异常：", e);
        }

        return outStr.toString();
    }



    /**
     * <b>功能说明</b>
     * 全角转半角
     * @param s  全角字符串
     * @return
     * <p>
     * <b>例如：</b><br>
     * 使用例子说明
     * String a = ToDBC("ｒｅ")
     * </p>
     */
    public static String ToDBC(String s) {
        char[] c = s.toCharArray();
        for (int i = 0, len = c.length; i < len; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375){
                c[i] = (char) (c[i] - 65248);
            }
        }

        return new String(c);
    }


    /**
     * 字符串去空格
     * @param s
     * @return
     */
    public static String trim(String s){
        if(s == null || "".equals(s)){
            return "";
        }
        return s.trim().replaceAll("[\\s\\u00A0]+$", "");
    }


    /**
     * 指定返回获取任意数字
     * @param min
     * @param max
     * @return
     */
    public static int random(int min, int max){

        return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
    }


    /**
     * 10位任意字符串
     * @return
     */
    public static String random(){

        return String.valueOf(Math.random()).substring(2, 10);
    }


    /**
     * 50%可能
     * @return
     */
    public static boolean halfChange(){
        boolean half [] = {true, false};

        return half[random(0, 1)];
    }


    /**
     * 生成任意数字和字母字符串【可用作公共 id 生成方法】
     * @param length
     * @return
     */
    public static String random(int length){

        return  random(length, false);
    }



    /**
     * 生成任意数字和字母字符串【可用作公共 id 生成方法】
     * @param length
     * @param isCapCase 字符串是否需要包含大写字符
     * @return
     */
    public static String random(int length, boolean isCapCase){
        StringBuilder result = new StringBuilder();

        while(result.length() < length){
            String value = changeRadix(random(0, Character.MAX_RADIX), Character.MAX_RADIX);
            //是否需要混合大写字母
            if(isCapCase && halfChange()){
                result.append(value.toUpperCase());
            }else{
                result.append(value);
            }
        }

        return result.substring(0, length);
    }


    /**
     * 将指定数字按照进制转换
     * @param num
     * @param radix 2-36
     * @return
     */
    public static String changeRadix(long num, int radix){

        return Long.toString(num, radix);
    }


    /**
     * 将 double 转化， 防止数值过大， 以科学计数法显示
     * @param d
     * @param precision
     * @return
     */
    public static String doubleFormat(String d, int precision){
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(precision);
        nf.setMinimumFractionDigits(precision);
        String format = nf.format(Double.valueOf(d)).replaceAll("[.]", "");
        return format;
    }


    /**
     * 字符串反序
     * @param s
     * @return
     */
    public static String reverse(String s){

        return new StringBuilder(s).reverse().toString();
    }


    /**
     *
     * @param errorNo
     * @return
     */
    public static int getErrorNo(int errorNo) {
       StringBuilder result = new StringBuilder(errorNo);
       for (int i = 0; i < errorNo; i++){
           result.append(random(0, 10));
       }

       return -Integer.parseInt(result.toString());
    }



    public static long getCurrentTime(){

        return System.currentTimeMillis();
    }



    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<>();
//
//          map.put("abc", new Date());
//
//
//          Date date = (Date) map.get("abc");
//        System.err.println(date);




    }

}
