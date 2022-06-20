package com.example.delivery1912.model;

public class User {
    private String id,namaUs,emailUs,passwordUs, telepon;

    public User(){

    }

    public User( String namaUs, String emailUs,String passwordUs, String telepon) {

        this.namaUs = namaUs;
        this.emailUs = emailUs;
        this.telepon = telepon;
        this.passwordUs = passwordUs;
    }

    public String getEmailUs() {
        return emailUs;
    }

    public void setEmailUs(String emailUs) {
        this.emailUs = emailUs;
    }

    public String getPasswordUs() {
        return passwordUs;
    }

    public void setPasswordUs(String passwordUs) {
        this.passwordUs = passwordUs;
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

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
}
