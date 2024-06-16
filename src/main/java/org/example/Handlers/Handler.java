package org.example.Handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public interface Handler extends HttpHandler {

    public void handlePost(HttpExchange exchange) throws IOException;

    public void handleGet(HttpExchange exchange) throws IOException;
}
