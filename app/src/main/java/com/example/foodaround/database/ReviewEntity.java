package com.example.foodaround.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "review_table")
public class ReviewEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "nama")
    private String nama;

    @NonNull
    @ColumnInfo(name = "alamat")
    private String alamat;

    @NonNull
    @ColumnInfo(name = "namaresto")
    private String namaresto;

    @NonNull
    @ColumnInfo(name = "review")
    private String review;

    public ReviewEntity(@NonNull String nama, @NonNull String alamat, @NonNull String namaresto, @NonNull String review) {
        this.nama = nama;
        this.alamat = alamat;
        this.namaresto = namaresto;
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNama() {
        return nama;
    }

    public void setNama(@NonNull String nama) {
        this.nama = nama;
    }

    @NonNull
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(@NonNull String alamat) {
        this.alamat = alamat;
    }

    @NonNull
    public String getNamaresto() {
        return namaresto;
    }

    public void setNamaresto(@NonNull String namaresto) { this.namaresto = namaresto;
    }

    @NonNull
    public String getReview() {
        return review;
    }

    public void setReview(@NonNull String review) {
        this.review = review;
    }
}

