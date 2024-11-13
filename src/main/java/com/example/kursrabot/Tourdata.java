package com.example.kursrabot;

public class Tourdata {


    private int id;
    private String data;
    private String ochki;



    public Tourdata(int id ,String data, String ochki) {
        this.id = id;
        this.data = data;
        this.ochki = ochki;


    }
    public int getId(){return this.id;}

    public String getData() {
        return this.data;
    }

    public String getOchki() {
        return this.ochki;
    }


}
