package com.example.eatfragment.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.eatfragment.database.ReviewEntity;
import com.example.eatfragment.repository.ReviewRepository;

import java.util.List;

public class ReviewViewModel extends AndroidViewModel {
    private ReviewRepository repository;
    private LiveData<List<ReviewEntity>> allReviews;

    public ReviewViewModel(@NonNull Application application) {
        super(application);
        repository = new ReviewRepository(application);
        allReviews = repository.getAllReviews();
    }

    public LiveData<List<ReviewEntity>> getAllReviews() {
        return allReviews;
    }

    public void insert(ReviewEntity review) {
        repository.insert(review);
    }

    public void delete(ReviewEntity review) {
        repository.delete(review);
    }
}


