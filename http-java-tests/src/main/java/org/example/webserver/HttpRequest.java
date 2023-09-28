package org.example.webserver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HttpRequest {
    private String method;
    private String path;
    private String httpVersion;
    private Map<String, String> headers;
    private String body;
}
