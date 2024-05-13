package com.example.foodaround.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodaround.R;

public class HomepageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //navbar
        ImageButton btnNotif = findViewById(R.id.btn_notif);
        btnNotif.setOnClickListener(v -> {
            Intent intent = new Intent(HomepageActivity.this, ReviewActivity.class);
            startActivity(intent);
        });
    }
}
