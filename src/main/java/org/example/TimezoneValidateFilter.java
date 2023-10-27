package org.example;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/time")
public class TimezoneValidateFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String timezoneParameter = req.getParameter("timezone");

        if ((Integer.parseInt(timezoneParameter) > -15 && Integer.parseInt(timezoneParameter) < 13)){
            chain.doFilter(req, res);
        } else {
            res.sendError(400, "Invalid timezone");
        }
    }
}
