package com.example.tubes_kelg_c.Model;

import java.io.Serializable;

public class Review implements Serializable {
    private String nama, jenis, review;
    private int idReview;

    public Review(int idReview, String nama, String jenis, String review) {
        this.idReview = idReview;
        this.nama = nama;
        this.jenis = jenis;
        this.review = review;
    }


    public int getIdReview(){ return idReview;}

    public String getNamaUser() {
        return nama;
    }

    public void setNamaUser(String nama) {
        this.nama = nama;
    }

    public String getJenisMotor() {
        return jenis;
    }

    public void setJenisMotor(String jenis) {
        this.jenis = jenis;
    }

    public String getReviewMotor() {
        return review;
    }

    public void setReviewMotor(String review) {
        this.review = review;
    }
}
