package com.example.tubes_kelg_c;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class Penyewa implements Serializable {

    private String namaPenyewa, noTelp, jenisMotor;
    private int idPenyewa,harga;

    public Penyewa(int idPenyewa, String namaPenyewa, String noTelp, String jenisMotor, int harga) {
        this.idPenyewa = idPenyewa;
        this.namaPenyewa = namaPenyewa;
        this.noTelp = noTelp;
        this.jenisMotor = jenisMotor;
        this.harga = harga;
    }

    public Penyewa(String namaPenyewa, String noTelp, String jenisMotor, int harga) {
        this.namaPenyewa = namaPenyewa;
        this.noTelp = noTelp;
        this.jenisMotor = jenisMotor;
        this.harga = harga;
    }


    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public void setNamaPenyewa(String namaPenyewa) {
        this.namaPenyewa = namaPenyewa;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getJenisMotor() {
        return jenisMotor;
    }

    public void setJenisMotor(String jenisMotor) {
        this.jenisMotor = jenisMotor;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getStringHarga(){
        return String.valueOf(harga);
    }

    public void setStringharga(String harga){
        this.harga = Integer.parseInt(harga);
    }

    public int getIdPenyewa() {
        return idPenyewa;
    }

    public void setIdPenyewa(int idPenyewa) {
        this.idPenyewa = idPenyewa;
    }
}
