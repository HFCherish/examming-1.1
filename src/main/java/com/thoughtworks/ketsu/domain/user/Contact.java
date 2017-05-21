package com.thoughtworks.ketsu.domain.user;

/**
 * Created by pzzheng on 5/21/17.
 */
public class Contact {
    private String name;
    private String address;
    private String phone;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    private Contact(){}

    public Contact(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
