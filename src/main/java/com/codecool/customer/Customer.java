package com.codecool.customer;

public class Customer {
    private String customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String city;
    private String street;
    private String passwordHash;

    public Customer(String customerId, String firstName, String lastName, String phoneNumber,
                    String emailAddress, String city, String street, String passwordHash) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.city = city;
        this.street = street;
        this.passwordHash = passwordHash;
    }

    public String getCustomerId() {
        return customerId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String newPasswordHash) {
        passwordHash = newPasswordHash;
    }
}
