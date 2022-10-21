package com.sport.net.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class temproteruClient {

    static ObjectMapper mapper = new ObjectMapper();

    public static String apiKey = "6faded995f07a67bd6431a5176bb4640";

    public static Object getTempreture(String city){
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey);
            // URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Kazan&appid=7f279da3bbdd7b43aacff0d975a78e5d");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            try (BufferedReader reader =
                         new BufferedReader(
                                 new InputStreamReader(connection.getInputStream())
                         )) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }

                // convert JSON string to Map
                JsonNode node = mapper.readTree(String.valueOf(content));
//                HashMap<String,Object> o = mapper.readValue(node, Map.class);
                String res = String.valueOf(node.get("main").get("temp"));
                return res;
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
