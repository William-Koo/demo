package com.example.util;

import com.example.model.DataRow;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * 描述: JsonUtil
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: William-Koo
 * 版本: 1.0
 * 创建日期: 2017/10/7 21:14
 */
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();


    /**
     * 字符串 json 转 DataRow
     * @param s
     * @param ignore
     * @return
     * @throws IOException
     * @throws JsonParseException
     * @throws JsonMappingException
     */
    public static DataRow strToJson(String s, String ...ignore) throws IOException,
            JsonParseException, JsonMappingException {

        DataRow dataRow = new DataRow();
        boolean isIgnore = ignore != null && ignore.length > 0;

        Map<String, Object> obj = MAPPER.readValue(s, Map.class);

        dataRow.putAll(obj);
        //去掉忽略属性值
        if(isIgnore){
            for (int i = 0, len = ignore.length; i < len; i++) {
                dataRow.remove(ignore[i]);
            }
        }

        return dataRow;
    }


    /**
     * 对象转字符串 json（打印日志）
     * @param o
     * @return
     * @throws JsonProcessingException
     */
    public static String objToJson(Object o) throws JsonProcessingException{

        return MAPPER.writeValueAsString(o);
    }




    public static void main(String[] args) throws Exception{

        String s = "{\"birthday\":\"2016-01-01\", \"name\":\"\u674e\u56db\", \"money\":120000}\n";

        System.err.println(strToJson(s, new String[]{"name"}));

        Result result = new Result();
        DataRow dataRow = new DataRow();
          dataRow.set("name", "xx");
          dataRow.set("abc", 123);

          result.setErr_no(0);
          result.setErr_info("测试");
          result.setData(dataRow);

        System.out.println(objToJson(result));


    }



}
