package com.company;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class IPLocationJSON {

    public String getData (String ip) {
        String responseJSON = "";
        //API for getting json file
        String urlString = "https://ipinfo.io";

        if (ip.equals("My IP") || ip.equals("")) {
            urlString += "/json";
        } else {
            urlString += "/" + ip + "/json";
        }

        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                responseJSON += inputLine;
            }

            bufferedReader.close();

        } catch (MalformedURLException e) {
            System.out.println("Invalid URL");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("URL Connection issue");
            e.printStackTrace();
        }

        return responseJSON;
    }


    public static void main(String[] args) {

        /*
        JSON response file:
        {
             "ip": "8.8.8.8",
             "hostname": "dns.google",
             "city": "Mountain View",
             "region": "California",
             "country": "US",
             "loc": "37.3860,-122.0838",
             "org": "AS15169 Google LLC",
             "postal": "94035",
             "timezone": "America/Los_Angeles",
             "readme": "https://ipinfo.io/missingauth"
        }  
        */

        IPLocationJSON ipLocationJSON = new IPLocationJSON();
        String googleIP = "8.8.8.8";
        String myIP = "My IP";
        String mapLink = "https://www.google.com/maps/?q=";

        String locationJSON = ipLocationJSON.getData(googleIP);
        JSONObject jsonObject = new JSONObject(locationJSON);

        System.out.println("Approximate IP location: ");
        System.out.println("City: " + jsonObject.getString("city"));
        System.out.println("Region: " + jsonObject.getString("region"));
        System.out.println("Country: " + jsonObject.getString("country"));
        System.out.println("Link to google Maps location: " + (mapLink + jsonObject.getString("loc")));



    }
}
