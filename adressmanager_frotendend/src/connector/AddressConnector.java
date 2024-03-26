package connector;


import model.Address;
import model.AddressModel;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// this class will be used to connect with server
public class AddressConnector {
    public static void loadConnect() throws IOException {
        URL obj = new URL(UrlsConstants.GET_ALL_URL);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json"); // Optional, can be set based on server needs
        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // Successful GET request
            StringBuilder response = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            String strJson = response.toString();
            System.out.println(strJson);
        } else {
            System.out.println("GET request failed!");
        }
        conn.disconnect();
    }


    public static void main(String[] args) {
        try {
            AddressConnector.loadConnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}