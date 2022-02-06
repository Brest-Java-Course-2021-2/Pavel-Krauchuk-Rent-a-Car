package com.epam.rest.model;

public class Customer {

    private Integer customerId;
    private String firstName;
    private String lastName;
    private String address;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Customer(Integer cusomerId, String firstName, String lastName, String address) {
        this.customerId = cusomerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }


    public Integer getCusomerId() {
        return customerId;
    }

    public void setCusomerId(Integer cusomerId) {
        this.customerId = cusomerId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
