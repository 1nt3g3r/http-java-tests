package org.example.webserver;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestParser {

    public static HttpRequest parse(String rawRequest) {
        HttpRequest httpRequest = new HttpRequest();
        String[] lines = rawRequest.split("\n"); // Split by CRLF
        String[] requestLine = lines[0].split(" ");

        httpRequest.setMethod(requestLine[0]);
        httpRequest.setPath(requestLine[1]);
        httpRequest.setHttpVersion(requestLine[2]);

        Map<String, String> headers = new HashMap<>();

        for (int i = 1; i < lines.length; i++) {
            if (lines[i].isEmpty()) {
                // headers end, body begins (if present)
                StringBuilder bodyBuilder = new StringBuilder();
                for (int j = i + 1; j < lines.length; j++) {
                    bodyBuilder.append(lines[j]).append("\r\n");
                }
                httpRequest.setBody(bodyBuilder.toString());
                break;
            } else {
                String[] headerParts = lines[i].split(": ", 2);
                headers.put(headerParts[0], headerParts[1]);
            }
        }

        httpRequest.setHeaders(headers);

        return httpRequest;
    }
}
