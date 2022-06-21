package com.example.delivery1912.model;

public class Menu {
    private String id, nameMen, hargaMen,descMen;


    public Menu(){

    }

    public Menu(String nameMen, String hargaMen, String descMen) {
        this.nameMen = nameMen;
        this.hargaMen = hargaMen;
        this.descMen = descMen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameMen() {
        return nameMen;
    }

    public void setNameMen(String nameMen) {
        this.nameMen = nameMen;
    }

    public String getDescMen() {
        return descMen;
    }

    public void setDescMen(String descMen) {
        this.descMen = descMen;
    }

    public String getHargaMen() {
        return hargaMen;
    }

    public void setHargaMen(String hargaMen) {
        this.hargaMen = hargaMen;
    }


}

