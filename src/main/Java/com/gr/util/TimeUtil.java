package com.gr.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-25 12:26
 */
public class TimeUtil {
    /**
     * 根据今天的时间得到昨天的时间
     *
     * @return
     * @throws ParseException
     */
    public static Date oldtime(Integer day) throws ParseException {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - day);
        return dft.parse(dft.format(date.getTime()));
    }
}
