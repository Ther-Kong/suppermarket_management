package com.example.supermarket_management.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
    public static java.sql.Date strToDate(String str){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try{
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date result = new java.sql.Date(date.getTime());
        return result;
    }
}
