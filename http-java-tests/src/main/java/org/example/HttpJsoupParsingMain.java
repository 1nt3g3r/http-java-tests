package org.example;

import org.jsoup.Jsoup;

import java.io.IOException;

public class HttpJsoupParsingMain {
    public static void main(String[] args) throws IOException {
        String url = "https://jsonplaceholder.typicode.com/guide/";
        String yearSelector = "div.mb-one:nth-child(2)";

        String text = Jsoup
                .connect(url)
                .get()
                .body()
                .select(yearSelector)
                .html();

        String[] yearParts = text.strip().split(" ");

        int year = Integer.parseInt(yearParts[yearParts.length - 1]);

        System.out.println("year = " + year);
    }
}
