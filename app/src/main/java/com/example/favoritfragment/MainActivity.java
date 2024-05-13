package com.example.favoritfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.favoritfragment.R;
import com.example.favoritfragment.RecyclerViewAdapter;
import com.example.favoritfragment.RestoItem;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ArrayList<RestoItem> restoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize FragmentManager for managing Fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Handle button click event
        ImageButton btnBookmark = findViewById(R.id.ibBookmark);
        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Begin FragmentTransaction
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new FavoritFragment());
                // Add transaction to the back stack
                fragmentTransaction.addToBackStack(null);
                // Commit the transaction
                fragmentTransaction.commit();
            }
        });
    }
}
