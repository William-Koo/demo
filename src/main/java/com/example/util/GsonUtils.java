package com.example.util;

import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @描述: JSON工具类
 * @版权: Copyright(C) 2017
 * @公司:
 * @作者: William-Koo
 * @版本: 1.0
 * @创建日期: 2017-11-19
 * @创建时间: 17:08
 */
public class GsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(GsonUtils.class);

    private static Gson gson;


    /**
     * 对象转换为json，带日期格式
     * @param ts
     * @param dateformat
     * @return
     */
    public static String objToJson(Object ts, final String dateformat){
        String jsonStr=null;
        gson = new GsonBuilder().registerTypeHierarchyAdapter(Date.class, new JsonSerializer<Date>() {
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                SimpleDateFormat format = new SimpleDateFormat(dateformat);
                return new JsonPrimitive(format.format(src));
            }
        }).setDateFormat(dateformat).create();
        if(gson!=null){
            jsonStr=gson.toJson(ts);
        }
        LOGGER.info(jsonStr);
        return jsonStr;
    }


    /**
     * 不带时间的对象转json
     * @param ts
     * @return
     */
    public static String objToJson(Object ts){
        if(ts == null){
            return "";
        }
        final String dateformat = "yyyyMMdd HH:mm:ss SSS";
        String jsonStr=null;
        gson=new GsonBuilder().registerTypeHierarchyAdapter(Date.class, new JsonSerializer<Date>() {
            public JsonElement serialize(Date src, Type typeOfSrc,JsonSerializationContext context) {
                SimpleDateFormat format = new SimpleDateFormat(dateformat);
                return new JsonPrimitive(format.format(src));
            }
        }).setDateFormat(dateformat).create();
        if(gson!=null){
            jsonStr=gson.toJson(ts);
        }
        return jsonStr;
    }

    /**
     * json转换为对象，带日期格式
     * @param jsonStr
     * @param cl
     * @param pattern
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T  jsonToObj(final String jsonStr,final Class<T> cl, final String pattern){
        Object obj=null;
        gson=new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context)
                    throws JsonParseException {
                SimpleDateFormat format=new SimpleDateFormat(pattern);
                String dateStr=json.getAsString();
                try {
                    return format.parse(dateStr);
                } catch (ParseException e) {
                    LOGGER.warn("【json转换为对象，带日期格式】错误,json:"+jsonStr+",对象的类："+cl.getClass().getName(),e);
                }
                return null;
            }
        }).setDateFormat(pattern).create();
        if(gson!=null){
            obj=gson.fromJson(jsonStr, cl);
        }
        return (T)obj;
    }


    /**
     * json转换为对象，带日期格式
     * @param jsonStr
     * @param type
     * @param pattern
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T  jsonToList(final String jsonStr, final Type type, final String pattern){
        Object obj=null;
        gson=new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context)
                    throws JsonParseException {
                SimpleDateFormat format=new SimpleDateFormat(pattern);
                String dateStr=json.getAsString();
                try {
                    return format.parse(dateStr);
                } catch (ParseException e) {
                    LOGGER.warn("【json转换为对象，带日期格式】错误,json:"+jsonStr+",对象的类：" +pattern,e);
                }
                return null;
            }
        }).setDateFormat(pattern).create();
        if(gson!=null){
            obj=gson.fromJson(jsonStr, type);
        }
        return (T)obj;
    }


    /**
     *
     * @param jsonStr
     * @param type
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T  jsonToList(final String jsonStr, final Type type){
        final String pattern = "yyyyMMdd HH:mm:ss SSS";
        Object obj=null;
        gson=new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context)
                    throws JsonParseException {
                SimpleDateFormat format=new SimpleDateFormat(pattern);
                String dateStr=json.getAsString();
                try {
                    return format.parse(dateStr);
                } catch (ParseException e) {
                    LOGGER.warn("【json转换为对象，带日期格式】错误,json:"+jsonStr+",对象的类：",e);
                }
                return null;
            }
        }).setDateFormat(pattern).create();
        if(gson!=null){
            obj=gson.fromJson(jsonStr, type);
        }
        return (T)obj;
    }
}
