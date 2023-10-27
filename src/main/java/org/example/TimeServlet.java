package org.example;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    private TemplateEngine engine;


    @Override
    public void init() throws ServletException {
        engine = new TemplateEngine();

        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("C:/1/Java Core 13/12 Servlet pr.2/Servlet_HW_12/src/main/webapp/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(engine.getTemplateResolvers().size());
        resolver.setCacheable(false);

        engine.addTemplateResolver(resolver);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String timezoneParameter = req.getParameter("timezone");

        Map<String, Object> param = new HashMap<>();

        param.put("resultTime",TimezoneParser.showTimezone(timezoneParameter));

        Cookie cookie = LastTimezoneCookie.setCookie(req, resp);
        param.put("lastTimezone", LastTimezoneCookie.showLastTimezone(cookie));

        Context simpleContext = new Context(req.getLocale());
        simpleContext.setVariables(param);

        engine.process("time", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}