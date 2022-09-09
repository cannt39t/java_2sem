package ru.kpfu.itis.kaznacheev.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class NetSample {

    public static void main(String[] args) {



        try{

            URL url = new URL("https://gorest.co.in/public/v2/users/14");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            String token = "09de836b512febc411a6e208ebf21fad0f382449fc5964993506556de29375b5";

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            try (BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream())
                    )) {

                StringBuilder content = new StringBuilder();
                String input;

                while ((input = reader.readLine()) != null){
                    content.append(input).append("\n");
                }

                System.out.println(content);

            }

            connection.disconnect();


        } catch (Exception e){
            e.printStackTrace();
        }






//        try {
//            URL postUrl = new URL("https://gorest.co.in/public/v1/users");
//            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
//            String token = "09de836b512febc411a6e208ebf21fad0f382449fc5964993506556de29375b5";
//
//            postConnection.setRequestMethod("POST");
//
//            postConnection.setRequestProperty("Content-Type", "application/json");
//            postConnection.setRequestProperty("Accept", "application/json");
//            postConnection.setRequestProperty("Authorization", "Bearer" + token);
//
//            postConnection.setDoOutput(true);
//
//            String jsonInputString = "{\"name\":\"Tenali Ramakrishna\", \"gender\":\"male\", \"email\":\"tenali.ishna2@gmail.com\", \"status\":\"active\"}";
//
//            try (OutputStream outputStream = postConnection.getOutputStream()) {
//                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
//                outputStream.write(input, 0, input.length);
//            }
//
//            System.out.println(postConnection.getResponseCode());
//
//            try (BufferedReader reader =
//                         new BufferedReader(
//                                 new InputStreamReader(postConnection.getInputStream())
//                         )) {
//                StringBuilder content = new StringBuilder();
//                String input;
//                while ((input = reader.readLine()) != null) {
//                    content.append(input);
//                }
//                System.out.println(content);
//            }
//
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }



        try {
            URL patchUrl = new URL("https://gorest.co.in/public/v2/users/14");
            HttpURLConnection patchConnection = (HttpURLConnection) patchUrl.openConnection();
            String token = "09de836b512febc411a6e208ebf21fad0f382449fc5964993506556de29375b5";

            patchConnection.setRequestMethod("PUT");

            patchConnection.setRequestProperty("Content-Type", "application/json");
            patchConnection.setRequestProperty("Accept", "application/json");
            patchConnection.setRequestProperty("Authorization", "Bearer " + token);

            patchConnection.setDoOutput(true);
            patchConnection.connect();

            String jsonInputString = "{\"name\":\"Herbert Steuber\",\"email\":\"herbert_steuber@corkery.net\",\"status\":\"active\"}";
            try (OutputStream outputStream = patchConnection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            System.out.println(patchConnection.getResponseCode());

            try (BufferedReader reader =
                         new BufferedReader(
                                 new InputStreamReader(patchConnection.getInputStream(), StandardCharsets.UTF_8)
                         )) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                System.out.println(content);
            }

            patchConnection.disconnect();

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
