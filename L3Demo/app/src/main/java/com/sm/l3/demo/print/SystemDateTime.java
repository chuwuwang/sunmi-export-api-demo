package com.sm.l3.demo.print;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemDateTime {

    public static String getDateByStamp(long stamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(stamp);
        return simpleDateFormat.format(date);
    }

    public static String getTimeByStamp(long stamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(stamp);
        return simpleDateFormat.format(date);
    }

    public static String getMMdd() {
        long timeMillis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMdd");
        Date curDate = new Date(timeMillis);
        return simpleDateFormat.format(curDate);
    }

    public static String getYYYY() {
        long timeMillis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Date curDate = new Date(timeMillis);
        return simpleDateFormat.format(curDate);
    }

    public static String getHHMMss() {
        long timeMillis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm" + "ss");
        Date curDate = new Date(timeMillis);
        return simpleDateFormat.format(curDate);
    }

    public static int getCurrentYYYYMMDD() {
        long timeMillis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String snow = simpleDateFormat.format(timeMillis);
        return Integer.parseInt(snow);
    }


}