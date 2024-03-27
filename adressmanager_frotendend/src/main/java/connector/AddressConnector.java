package connector;


import model.Address;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

// this class will be used to connect with server
public final class AddressConnector {
    public static JSONArray getAllAddresses() throws IOException {
        JSONArray jsonArray = new JSONArray();
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
            jsonArray = new JSONArray(strJson);
        } else {
            System.out.println("GET request failed!");
        }
        conn.disconnect();
        return jsonArray;
    }



    public static String saveAddress(Address address) throws IOException {
        StringBuilder response = new StringBuilder();
        URL obj = new URL(UrlsConstants.SAVE_URL);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json"); // Optional, can be set based on server needs
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        JSONObject jsonObject =  convertAddressToJson(address);
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonObject.toString() .getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // Successful GET request
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response);
            }
        } else {
            System.out.println("GET request failed!");
        }
        conn.disconnect();
        return response.toString();
    }


    public static String deleteAddress(Address address) throws IOException {
        StringBuilder response = new StringBuilder();
        URL obj = new URL(UrlsConstants.DELETE_ID_URL+ address.getId());
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("DELETE");
        conn.setRequestProperty("Content-Type", "application/json"); // Optional, can be set based on server needs
        conn.setRequestProperty("Accept", "application/json");
        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // Successful GET request
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response);
            }
        } else {
            System.out.println("GET request failed!");
        }
        conn.disconnect();
        return response.toString();
    }


    public static String deleteAllAddresses() throws IOException {
        StringBuilder response = new StringBuilder();
        URL obj = new URL(UrlsConstants.DELETE_ALL_URL);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("DELETE");
        conn.setRequestProperty("Content-Type", "application/json"); // Optional, can be set based on server needs
        conn.setRequestProperty("Accept", "application/json");
        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // Successful GET request
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response);
            }
        } else {
            System.out.println("GET request failed!");
        }
        conn.disconnect();
        return response.toString();
    }


    private static JSONObject convertAddressToJson(Address address) {
        return new JSONObject(address.toJsonString());
    }



    public static void main(String[] args) {
      Address address =   UrlsConstants.getAddressExample();
        try {
            AddressConnector.saveAddress(address);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}