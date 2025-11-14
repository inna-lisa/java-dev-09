package org.example;

import java.io.IOException;
import java.util.Scanner;

public class HttpImageStatusCli {

    public void askStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter HTTP status code ");

        while (true) {
            if (scanner.hasNextInt()) {
                int code = scanner.nextInt();

                try {
                    HttpStatusImageDownloader httpStatusImageDownloader = new HttpStatusImageDownloader();
                    httpStatusImageDownloader.downloadStatusImage(code);

                } catch (IOException e) {
                    System.out.println("There is not image for HTTP status " + code);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    scanner.close();
                }
                break;

            } else {
                scanner.next();
                System.out.println("Please enter valid number ");
            }
        }
    }
}
