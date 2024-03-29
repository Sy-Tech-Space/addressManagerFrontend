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
    private static HttpURLConnection getConnection(String Url, String RequestMethod, boolean doOutPut) throws IOException {
        URL obj = new URL(Url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod(RequestMethod);
        conn.setRequestProperty("Content-Type", "application/json"); // Optional, can be set based on server needs
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(doOutPut);
        return conn;
    }
    public static JSONArray getAllAddresses() throws IOException {
        JSONArray jsonArray = new JSONArray();
        HttpURLConnection conn = getConnection(UrlsConstants.GET_ALL_URL, "GET", false);
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
    public static String deleteAddress(Address address) throws IOException {
        HttpURLConnection conn = getConnection(UrlsConstants.DELETE_ID_URL + address.getId(), "DELETE", false);
        return perform(conn);
    }
    public static String deleteAllAddresses() throws IOException {
        HttpURLConnection conn = getConnection(UrlsConstants.DELETE_ALL_URL, "DELETE", false);
        return perform(conn);
    }
    public static String saveAddress(Address address) throws IOException {
        HttpURLConnection conn = getConnection(UrlsConstants.SAVE_URL, "POST", true);
        return perform(address, conn);
    }

    public static String updateAddress(Address address) throws IOException {
        HttpURLConnection conn = getConnection(UrlsConstants.UPDATE_URL + address.getId(), "PUT", true);
        return perform(address, conn);
    }
    private static String perform(HttpURLConnection conn) throws IOException {
        StringBuilder response = new StringBuilder();
        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // Successful  request
            try (BufferedReader br = new BufferedReader(
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

    private static String perform(Address address, HttpURLConnection conn) throws IOException {
        StringBuilder response = new StringBuilder();
        JSONObject jsonObject = convertAddressToJson(address);
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // Successful request
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response);
            }
        } else {
            System.out.println("request failed!");
        }
        conn.disconnect();
        return response.toString();
    }


    private static JSONObject convertAddressToJson(Address address) {
        return new JSONObject(address.toJsonString());
    }

    public static void main(String[] args) {
        Address address = UrlsConstants.getAddressExample();
        address.setId(1);
        try {
          //  AddressConnector.saveAddress(address);
            String arr =       AddressConnector.updateAddress(address);
            System.out.println(arr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}