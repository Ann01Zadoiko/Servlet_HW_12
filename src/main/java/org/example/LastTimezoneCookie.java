package org.example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public class LastTimezoneCookie {

    private LastTimezoneCookie(){}

    public static String showLastTimezone(Cookie cookie){

        if(cookie == null){
            TimezoneParser.simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
            return TimezoneParser.simpleDateFormat.format(new Date());
        } else {
            String lastTimezone = TimezoneParser.writeTimezone(Integer.parseInt(cookie.getValue()));
            TimezoneParser.simpleDateFormat.setTimeZone(TimeZone.getTimeZone(lastTimezone));
            return TimezoneParser.simpleDateFormat.format(new Date());
        }
    }

    public static Cookie setCookie(HttpServletRequest req, HttpServletResponse resp){
        Map<String, String[]> params = req.getParameterMap();

        for (String name : params.keySet()) {
            String value = params.get(name)[0];
            resp.addHeader("Set-Cookie", name + "=" + value);
        }

        Cookie[] cookies = req.getCookies();
        return cookies[cookies.length-1];
    }
}
