package com.example.delivery1912.model;

public class User {
    private String id,namaUs,emailUs, telepon;

    public User(){

    }

    public User( String namaUs, String nimUs, String telepon) {

        this.namaUs = namaUs;
        this.emailUs = nimUs;
        this.telepon = telepon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaUs() {
        return namaUs;
    }

    public void setNamaUs(String namaUs) {
        this.namaUs = namaUs;
    }

    public String getNimUs() {
        return emailUs;
    }

    public void setNimUs(String nimUs) {
        this.emailUs = nimUs;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
}
