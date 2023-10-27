package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimezoneParser {
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz");

    private TimezoneParser(){}

    public static String writeTimezone(int timezoneParameter){
        String timezone = "GMT";

        if (timezoneParameter > -1 && timezoneParameter < 13){
            timezone += "+" + timezoneParameter;
        }
        return timezone;
    }

    public static String showTimezone(String timezoneParameter){
        String timezone = writeTimezone(Integer.parseInt(timezoneParameter));
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timezone));

        return simpleDateFormat.format(new Date());
    }
}
