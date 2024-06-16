package org.example.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.MyAnnotation.Config;
import org.example.MyAnnotation.Instance;
import org.example.SupportPackage.SupportManager;
import org.example.SupportPackage.SupportService;
import org.example.SupportPackage.SupportServlet;

@Config
public class SupportConfiguration {

    @Instance
    public SupportManager supportManager() {
        return new SupportManager(new SupportService());
    }

    @Instance
    public SupportServlet supportServlet() {
        return new SupportServlet();
    }

    @Instance
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }


}
