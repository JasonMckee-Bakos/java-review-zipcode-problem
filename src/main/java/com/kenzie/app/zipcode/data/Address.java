package com.kenzie.app.zipcode.data;

public class Address {
    // Properties
    private String name;
    private String street;
    private String city;
    private String state; // TODO: Enum for the state
    private String zipcode;

    // Constructors
    public Address() {
        this.name = "";
        this.street = "";
        this.city = "";
        this.state = "";
    }

    public Address(String name, String street, String city, String state) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public Address(String name, String street, String city, String state, String zipcode) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    // Getters/Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
