package com.epam.brest.model;

public class Customer {

    private Integer cusomerId;
    private String firstName;
    private String lastName;
    private Integer numberDriverLicense;
    private Integer phoneNumber;
    private String address;

    public Customer(Integer cusomerId, String firstName, String lastName, Integer numberDriverLicense, Integer phoneNumber, String address) {
        this.cusomerId = cusomerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberDriverLicense = numberDriverLicense;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Integer getCusomerId() {
        return cusomerId;
    }

    public void setCusomerId(Integer cusomerId) {
        this.cusomerId = cusomerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getNumberDriverLicense() {
        return numberDriverLicense;
    }

    public void setNumberDriverLicense(Integer numberDriverLicense) {
        this.numberDriverLicense = numberDriverLicense;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
