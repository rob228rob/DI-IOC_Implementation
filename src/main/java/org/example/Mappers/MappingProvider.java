package org.example.Mappers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MappingProvider {

    public void getMapping(HttpServletRequest request, HttpServletResponse response);

    public void postMapping(HttpServletRequest request, HttpServletResponse response);
}
