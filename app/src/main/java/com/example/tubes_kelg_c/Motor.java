package com.example.tubes_kelg_c;

public class Motor {

    public String nama;
    public String jenis;
    public String harga;


    public Motor (String nama, String jenis, String harga){
        this.nama = nama;
        this.jenis = jenis;
        this.harga = harga;
    }

    public String getNama() { return nama;}

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() { return jenis;}

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getharga() { return harga;}

    public void setharga(String harga) {
        this.harga = harga;
    }

}