package com.example.delivery1912.model;

public class KeranjangModel {

    String Name,Harga,Desk, Jumlah;
    int hargaTotal;

    public String getJumlah() {
        return Jumlah;
    }

    public void setJumlah(String jumlah) {
        Jumlah = jumlah;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getDesk() {
        return Desk;
    }

    public void setDesk(String desk) {
        Desk = desk;
    }

    public int getHargaTotal() {
        return hargaTotal;
    }

    public void setHargaTotal(int hargaTotal) {
        this.hargaTotal = hargaTotal;
    }

    public KeranjangModel() {
    }

    public KeranjangModel(String name, String harga, String desk, int hargaTotal) {
        Name = name;
        Harga = harga;
        Desk = desk;
        this.hargaTotal = hargaTotal;
    }
}
