package com.example.foodaround.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodaround.R;
import com.example.foodaround.database.ReviewEntity;

public class ReviewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayout layAddReview;
    private EditText etNama, etAlamat, etNamaresto, etReview;
    private Button btnClear, btnSubmit;

    private ReviewViewModel reviewViewModel;
    private ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        //navbar
        ImageButton btnHome = findViewById(R.id.btn_home);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(ReviewActivity.this, com.example.foodaround.ui.MainActivity.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.rv_review);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        layAddReview = findViewById(R.id.layout_add);
        etNama = findViewById(R.id.et_nama);
        etAlamat = findViewById(R.id.et_alamat);
        etNamaresto = findViewById(R.id.et_namaresto);
        etReview = findViewById(R.id.et_review);
        btnClear = findViewById(R.id.btn_clear);
        btnSubmit = findViewById(R.id.btn_submit);

        reviewViewModel = new ViewModelProvider(this).get(ReviewViewModel.class);

        btnClear.setOnClickListener(v -> clearData());

        btnSubmit.setOnClickListener(v -> {
            if (etNama.getText().toString().equals("") ||
                    etAlamat.getText().toString().equals("") ||
                    etNamaresto.getText().toString().equals("") ||
                    etReview.getText().toString().equals("")) {
                Toast.makeText(this, "Please fill in the entire form", Toast.LENGTH_SHORT).show();
            } else {
                ReviewEntity review = new ReviewEntity(
                        etNama.getText().toString(),
                        etAlamat.getText().toString(),
                        etNamaresto.getText().toString(),
                        etReview.getText().toString()
                );
                reviewViewModel.insert(review);
                clearData();
            }
        });

        reviewAdapter = new ReviewAdapter();
        recyclerView.setAdapter(reviewAdapter);

        reviewViewModel.getAllReviews().observe(this, reviews -> {
            reviewAdapter.setReviews(reviews);
            reviewAdapter.setOnItemClickListener(new ReviewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(ReviewEntity review) {
                    Intent intent = new Intent(ReviewActivity.this, DetailReviewActivity.class);
                    intent.putExtra("cnama", review.getNama());
                    intent.putExtra("calamat", review.getAlamat());
                    intent.putExtra("cnamaresto", review.getNamaresto());
                    intent.putExtra("creview", review.getReview());
                    startActivity(intent);
                }

                @Override
                public void onDeleteClick(ReviewEntity review) {
                    reviewViewModel.delete(review);
                    Toast.makeText(ReviewActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void clearData() {
        etNama.setText("");
        etAlamat.setText("");
        etNamaresto.setText("");
        etReview.setText("");
    }
}

