package com.example.eatfragment.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.eatfragment.database.ReviewDao;
import com.example.eatfragment.database.ReviewDatabase;
import com.example.eatfragment.database.ReviewEntity;

import java.util.List;

public class ReviewRepository {
    private ReviewDao reviewDao;
    private LiveData<List<ReviewEntity>> allReviews;

    public ReviewRepository(Application application) {
        ReviewDatabase database = ReviewDatabase.getInstance(application);
        reviewDao = database.reviewDao();
        allReviews = reviewDao.getAllReviews();
    }

    public LiveData<List<ReviewEntity>> getAllReviews() {
        return allReviews;
    }

    public void insert(ReviewEntity review) {
        ReviewDatabase.databaseWriteExecutor.execute(() -> {
            reviewDao.insert(review);
        });
    }

    public void delete(ReviewEntity review) {
        ReviewDatabase.databaseWriteExecutor.execute(() -> {
            reviewDao.delete(review);
        });
    }
}

