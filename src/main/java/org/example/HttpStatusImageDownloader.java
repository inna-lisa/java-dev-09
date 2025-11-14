package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class HttpStatusImageDownloader {
   public void downloadStatusImage(int code) throws IOException, InterruptedException {

       String url;
       HttpResponse<InputStream> response;
       try {
           HttpStatusChecker httpStatusChecker = new HttpStatusChecker();
           url = httpStatusChecker.getStatusImage(code);

           HttpClient client = HttpClientProvider.getClient();
           HttpRequest request = HttpRequest.newBuilder()
                   .uri(URI.create(url))
                   .GET()
                   .build();
           response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

       } catch (IOException | InterruptedException e) {
           throw e;
       }

       String [] fileName = url.split("/");
       Path currentDir = Paths.get(System.getProperty("user.dir"));

       if (!(Files.exists(currentDir))) {
           Files.createDirectories(currentDir);
       }
       Path outputPath = currentDir.resolve(fileName[fileName.length - 1]);

       InputStream is = response.body();
       Files.copy(is, outputPath, StandardCopyOption.REPLACE_EXISTING);
   }
}
