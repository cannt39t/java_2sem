package com.kaznacheev.net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.SplittableRandom;

//{
//        "args": {
//        "foo1": "bar1",
//        "foo2": "bar2"
//        },
//        "headers": {
//        "x-forwarded-proto": "https",
//        "host": "postman-echo.com",
//        "accept": "*/*",
//        "accept-encoding": "gzip, deflate",
//        "cache-control": "no-cache",
//        "postman-token": "5c27cd7d-6b16-4e5a-a0ef-191c9a3a275f",
//        "user-agent": "PostmanRuntime/7.6.1",
//        "x-forwarded-port": "443"
//        },
//        "url": "https://postman-echo.com/get?foo1=bar1&foo2=bar2"
//        }


public class Client implements HttpClient {


    private String formatParam(Map<String, String> params){
        StringBuilder res = new StringBuilder();
        params.forEach((k, v) -> res.append((k + "=" + v + "&")));
        return String.valueOf(res.delete(res.length()-1,res.length()-1));
    }


    private HttpURLConnection setConnectionProperties(String method, URL url, Map<String, String> headers) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        headers.forEach(connection::setRequestProperty);
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        return connection;
    }

    private String getResponse(HttpURLConnection connection) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(connection.getInputStream())
                     )) {
            String input;
            while ((input = reader.readLine()) != null) {
                content.append(input);
            }
        }
        return String.valueOf(content);
    }


    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> params) {
        String res = "";
        try {
            String tempUrl = url + "?" + formatParam(params);

            URL urlget = new URL(url);

            HttpURLConnection connection = setConnectionProperties("GET", urlget, headers);

            res = res + getResponse(connection);

            connection.disconnect();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return res;

    }

    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> params) {
        String res = "";
        try {
            String tempUrl = url + "?" + formatParam(params);

            URL urlget = new URL(url);

            HttpURLConnection connection = setConnectionProperties("POST", urlget, headers);

            connection.setDoOutput(true);

            res = res + getResponse(connection);

            connection.disconnect();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return res;
    }
}
