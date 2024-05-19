package com.example.eatfragment.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReviewDao {

    @Insert
    void insert(ReviewEntity review);

    @Delete
    void delete(ReviewEntity review);

    @Query("SELECT * FROM review_table")
    LiveData<List<ReviewEntity>> getAllReviews();
}
