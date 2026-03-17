package com.wmata;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HelloWorld {

    public static void main(String[] args) {
        try {
            String urlString = "https://api.wmata.com/Rail.svc/json/jLines";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //Request headers
            connection.setRequestProperty("Cache-Control", "no-cache");

            connection.setRequestProperty("api_key", "2b82adaba418491e8729b685469478f7");

            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();
            System.out.println(status);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            System.out.println(content);

            connection.disconnect();
        } catch (Exception ex) {
            System.out.print("exception:" + ex.getMessage());
        }
    }
}