package connector;

import model.Address;

public class UrlsConstants {

    public final static String GET_ALL_URL = "http://localhost:8080/api/v1/address";

    // POST Method
    public final static String SAVE_URL = "http://localhost:8080/api/v1/address/save";

    public final static String UPDATE_URL = "";

    // DELETE Method
    public final static String DELETE_ALL_URL = "http://localhost:8080/api/v1/address/delete";

    // DELETE Method with /idNumber
    public final static String DELETE_ID_URL = "http://localhost:8080/api/v1/address/delete/";


    public static Address getAddressExample(){
      //  public Address(String firstname, String lastname, String street, String city, String telNumber, long zip) {
        return new Address("Mohammad", "Assaf", "laloStr", "laloCity", "93482346", 23413);
    }
}
