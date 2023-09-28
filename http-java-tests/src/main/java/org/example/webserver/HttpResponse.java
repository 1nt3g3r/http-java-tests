package org.example.webserver;

import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

@Data
public class HttpResponse {
    private String protocol = "HTTP/1.1";
    private int code = 200;
    private String status = "OK";

    private Map<String, String> headers = new LinkedHashMap<>();

    private String body;

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner("\r\n");

        //Start line
        result.add(protocol + " " + code + " " + status);

        //Manually calculate content-length
        int bodyLength = body.getBytes(StandardCharsets.UTF_8).length;
        headers.put("Content-Length", Integer.toString(bodyLength));

        //Headers
        headers.forEach((key, value) -> {
            result.add(key + ": " + value);
        });

        //Body
        result.add("");
        result.add(body);

        return result.toString();
    }
}
