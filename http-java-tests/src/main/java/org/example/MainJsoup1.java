package org.example;

import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

public class MainJsoup1 {
    public static void main(String[] args) throws IOException {
//        String getSinglePostURL = "https://jsonplaceholder.typicode.com/posts/16353453";
//
//        String text = Jsoup.connect(getSinglePostURL)
//                .ignoreContentType(true)
//                .get()
//                .body()
//                .text();
//
//        Post post = new Gson().fromJson(text, Post.class);
//
//        System.out.println(post);

        String putSinglePostURL = "https://jsonplaceholder.typicode.com/posts";

        Post updated = new Post();
        updated.setUserId(1);
        updated.setTitle("My title");
        updated.setBody("My body");

        Connection.Response response = Jsoup
                .connect(putSinglePostURL)
                .ignoreContentType(true)
                .requestBody(new Gson().toJson(updated))
                .headers(Map.of(
                        "Content-Type", "application/json"
                ))
                .method(Connection.Method.POST)
                .execute();

        System.out.println("response.body() = " + response.body());
    }
}