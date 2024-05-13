package com.example.foodaround.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foodaround.R;
import com.example.foodaround.database.ReviewEntity;

public class ReviewFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayout layAddReview;
    private EditText etNama, etAlamat, etNamaresto, etReview;
    private Button btnClear, btnSubmit, btnHome;

    private ReviewViewModel reviewViewModel;
    private ReviewAdapter reviewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_review, container, false);

        recyclerView = rootView.findViewById(R.id.rv_review);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        layAddReview = rootView.findViewById(R.id.layout_add);
        etNama = rootView.findViewById(R.id.et_nama);
        etAlamat = rootView.findViewById(R.id.et_alamat);
        etNamaresto = rootView.findViewById(R.id.et_namaresto);
        etReview = rootView.findViewById(R.id.et_review);
        btnClear = rootView.findViewById(R.id.btn_clear);
        btnSubmit = rootView.findViewById(R.id.btn_submit);

        reviewViewModel = new ViewModelProvider(requireActivity()).get(ReviewViewModel.class);

        btnClear.setOnClickListener(v -> clearData());

        btnSubmit.setOnClickListener(v -> {
            if (etNama.getText().toString().equals("") ||
                    etAlamat.getText().toString().equals("") ||
                    etNamaresto.getText().toString().equals("") ||
                    etReview.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Please fill in the entire form", Toast.LENGTH_SHORT).show();
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

        reviewViewModel.getAllReviews().observe(getViewLifecycleOwner(), reviews -> {
            reviewAdapter.setReviews(reviews);
            reviewAdapter.setOnItemClickListener(new ReviewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(ReviewEntity review) {
                    Intent intent = new Intent(getActivity(), DetailReviewActivity.class);
                    intent.putExtra("cnama", review.getNama());
                    intent.putExtra("calamat", review.getAlamat());
                    intent.putExtra("cnamaresto", review.getNamaresto());
                    intent.putExtra("creview", review.getReview());
                    startActivity(intent);
                }

                @Override
                public void onDeleteClick(ReviewEntity review) {
                    reviewViewModel.delete(review);
                    Toast.makeText(getActivity(), "Item deleted", Toast.LENGTH_SHORT).show();
                }
            });
        });

        return rootView;
    }

    private void clearData() {
        etNama.setText("");
        etAlamat.setText("");
        etNamaresto.setText("");
        etReview.setText("");
    }
}



