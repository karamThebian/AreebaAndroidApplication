package com.example.areebaclientcontrolsystem;

public class Post {
    private long id;
    private String name;
    private String phoneNumber;
    private String address;

    public Post(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }


}
