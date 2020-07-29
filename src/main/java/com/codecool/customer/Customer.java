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

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }


    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String newPasswordHash) {
        passwordHash = newPasswordHash;
    }
}
