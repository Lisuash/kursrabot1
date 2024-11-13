package com.example.kursrabot;

public class Secdata {

    private String address;
    private String price;
    private String name;


    public Secdata(String address, String price, String name) {
        this.address = address;
        this.price = price;
        this.name = name;

    }

    public String getAddress() {
        return this.address;
    }

    public String getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }


}



