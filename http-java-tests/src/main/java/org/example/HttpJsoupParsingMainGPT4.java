package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class HttpJsoupParsingMainGPT4 {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://jsonplaceholder.typicode.com/guide/").get();

        // Select the text of the element where the year is located
        Element footerElement = doc.select("footer div:containsOwn(©)").first();
        
        if (footerElement != null) {
            String text = footerElement.text();
            String year = text.replaceAll("[^\\d]", ""); // Extract numbers only
            
            System.out.println("Year: " + year);
        } else {
            System.out.println("Year not found.");
        }
    }
}
