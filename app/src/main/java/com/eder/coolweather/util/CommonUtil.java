package com.eder.coolweather.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CommonUtil {
    public static String timeFormat(int millisecond) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String time = dateFormat.format(new Date(millisecond));
        return time;
    }
}
