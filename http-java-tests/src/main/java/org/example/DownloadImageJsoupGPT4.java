package org.example;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DownloadImageJsoupGPT4 {
    public static void main(String[] args) {
        String imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ed/At_Wikimedia_Hackathon_Athens_%28MP%29_2023_001_%28cropped%29.jpg/1920px-At_Wikimedia_Hackathon_Athens_%28MP%29_2023_001_%28cropped%29.jpg";
        String outputFileName = "example.jpg";

        try {
            // Connect to the image URL
            Connection connection = Jsoup.connect(imageUrl).ignoreContentType(true);

            // Get the byte stream from the connection's response
            InputStream inputStream = connection.execute().bodyStream();

            // Use FileOutputStream to save the image to the specified file
            OutputStream outputStream = new FileOutputStream(outputFileName);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Close streams to free resources
            outputStream.close();
            inputStream.close();

            System.out.println("Image downloaded and saved as " + outputFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
