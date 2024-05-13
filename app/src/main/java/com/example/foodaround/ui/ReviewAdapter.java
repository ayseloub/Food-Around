package com.example.foodaround.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodaround.R;
import com.example.foodaround.database.ReviewEntity;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<ReviewEntity> reviews = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ReviewEntity review);
        void onDeleteClick(ReviewEntity review);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        ReviewEntity review = reviews.get(position);
        holder.tvNama.setText(review.getNama());
        holder.tvAlamat.setText(review.getAlamat());
        holder.tvNamaresto.setText(review.getNamaresto());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(review);
            }
        });
        holder.tvDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(review);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama;
        private TextView tvAlamat;
        private TextView tvNamaresto;
        private TextView tvDelete;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvNamaresto = itemView.findViewById(R.id.tv_namaresto);
            tvDelete = itemView.findViewById(R.id.tv_delete);
        }
    }
}




