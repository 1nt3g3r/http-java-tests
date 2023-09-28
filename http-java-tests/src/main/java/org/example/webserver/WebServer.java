package org.example.webserver;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringJoiner;

public class WebServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(9090);


        while (true) {
            Socket socket = serverSocket.accept();

            new Thread(() -> {
                try {
                    handleRequest(socket);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        }

    }

    private static void handleRequest(Socket socket) throws IOException {
        //Read request
        InputStream is = socket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringJoiner requestText = new StringJoiner("\n");
        String line;
        while ((line = in.readLine()) != null && !line.isEmpty()) {
            requestText.add(line);
        }

        HttpRequest request = HttpRequestParser.parse(requestText.toString());

        String filename = null;
        if (request.getPath().equals("/")) {
            filename = "./www/index.html";
        } else {
            filename = "./www" + request.getPath();
        }
        System.out.println("request = " + request);

        //Craft & write response
        HttpResponse response = new HttpResponse();
        response.getHeaders().put("Content-Type", "text/html");
        response.setBody(Files.readString(Path.of(filename)));

        OutputStream os = socket.getOutputStream();
        os.write(response.toString().getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();
    }
}
