package com.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @描述: 时间工具类
 * @版权: Copyright(C) 2017
 * @公司:
 * @作者: William-Koo
 * @版本: 1.0
 * @创建日期: 2017-11-18
 * @创建时间: 21:28
 */
public class DateUtil {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";


    public static String getDate(String format){

       return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
//       return new SimpleDateFormat(format).format(date);
    }


    public static void main(String[] args) {
        System.out.println(getDate(YYYY_MM_DD_HH_MM_SS));
    }

}
