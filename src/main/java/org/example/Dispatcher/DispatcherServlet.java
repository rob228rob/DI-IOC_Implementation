package org.example.Dispatcher;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ApplicationContext;
import org.example.Handlers.Handler;
import org.example.Handlers.MyHandler;
import org.example.MyAnnotation.Mapping;
import org.example.SupportPackage.SupportManager;
import org.example.SupportPackage.SupportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.runtime.ObjectMethods;


public class DispatcherServlet extends HttpServlet {

    private final Handler handler;

    private ObjectMapper objectMapper;

    private ApplicationContext applicationContext;

    public DispatcherServlet(Handler handler, ApplicationContext applicationContext, ObjectMapper objectMapper) {
        this.handler = handler;
        this.applicationContext = applicationContext;
        this.objectMapper = objectMapper;
    }

    @Mapping(value = RequestMappingType.GET, path = "/req/support")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(objectMapper.writeValueAsString(handler));
    }

    @Mapping(value = RequestMappingType.POST, path = "/req/support")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(objectMapper.writeValueAsString(handler));
    }
}
