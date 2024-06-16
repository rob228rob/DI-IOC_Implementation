package org.example.Mappers;

import org.example.ApplicationContext;
import org.example.Dispatcher.RequestMappingType;
import org.example.Handlers.Handler;
import org.example.MyAnnotation.Mapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class CommonMappingProvider implements MappingProvider{

    private final Handler handler;

    private final ApplicationContext applicationContext;

    public CommonMappingProvider(Handler handler, ApplicationContext applicationContext) {
        this.handler = handler;
        this.applicationContext = applicationContext;
    }

    public void init()
    {
        final var MappingMethods = applicationContext.getAllInstances().stream()
                .filter(it -> it.getClass().isAnnotationPresent(Mapping.class)).toList();

        for (var obj: MappingMethods)
        {
            RequestMappingType type = obj.getClass().getAnnotation(Mapping.class).value();
            String requestURL = obj.getClass().getAnnotation(Mapping.class).path();

        }
    }

    @Override
    public void getMapping(HttpServletRequest req, HttpServletResponse res) {

    }

    @Override
    public void postMapping(HttpServletRequest req, HttpServletResponse res) {

    }
}
