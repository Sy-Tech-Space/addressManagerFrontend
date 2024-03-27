package model;


public class AddressModel {

    private long id;
    private String firstname;
    private String lastname;
    private String street;
    private String city;
    private String telNumber;
    private long zip;

    public long getId() {
        return id;
    }

    public AddressModel() {

    }

    public AddressModel(String firstname, String lastname, String street, String city, String telNumber, long zip) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.street = street;
        this.city = city;
        this.telNumber = telNumber;
        this.zip = zip;
    }

    public AddressModel(long id, String firstname, String lastname, String street, String city, String telNumber, long zip) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.street = street;
        this.city = city;
        this.telNumber = telNumber;
        this.zip = zip;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public long getZip() {
        return zip;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public void setZip(long zip) {
        this.zip = zip;
    }
}
