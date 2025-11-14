package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpStatusChecker {

    String url = "https://http.cat/";

    public String getStatusImage(int code) throws IOException, InterruptedException {

        try {

            HttpClient client = HttpClientProvider.getClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url + code))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return url + code + ".jpg";
            } else {
                throw new IOException();
            }

        } catch (IOException | InterruptedException e) {
            throw e;
        }
    }
}
