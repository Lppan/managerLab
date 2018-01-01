package com.laboratory.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 时间毫秒值格式转换
 * Created by shipan on 2018/1/1.
 */
public class DateConversionUtils {
    public static String valueToDate(Long mirSecond){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        date.setTime(mirSecond);
        String format = sdf.format(date);
        return format;
    }

}
