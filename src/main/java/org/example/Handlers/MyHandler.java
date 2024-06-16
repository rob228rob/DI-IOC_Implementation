package org.example.Handlers;

import com.sun.net.httpserver.HttpExchange;
import org.example.SupportPackage.SupportManager;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MyHandler implements Handler {

    private final SupportManager supportManager;

    public MyHandler(SupportManager supportManager) {
        this.supportManager = supportManager;
    }

    @Override
    public void handleGet(HttpExchange httpExchange) throws IOException {
        String response = supportManager.getSupportPhrase();
        httpExchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    @Override
    public void handlePost(HttpExchange httpExchange) throws IOException {
        byte[] requestBody = httpExchange.getRequestBody().readAllBytes();
        String newPhrase = new String(requestBody, StandardCharsets.UTF_8);
        supportManager.addSupportPhrase(newPhrase);
        String response = "Phrase added";
        httpExchange.sendResponseHeaders(201, response.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        if ("GET".equalsIgnoreCase(method)) {
            handleGet(httpExchange);
        } else if ("POST".equalsIgnoreCase(method)) {
            handlePost(httpExchange);
        } else {
            String response = "Method not supported";
            httpExchange.sendResponseHeaders(405, response.getBytes(StandardCharsets.UTF_8).length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes(StandardCharsets.UTF_8));
            os.close();
        }
    }
}