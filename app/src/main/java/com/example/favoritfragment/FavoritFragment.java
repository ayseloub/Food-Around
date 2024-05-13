package com.example.favoritfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favoritfragment.R;
import com.example.favoritfragment.RecyclerViewAdapter;
import com.example.favoritfragment.RestoItem;

import java.util.ArrayList;

public class FavoritFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ArrayList<RestoItem> restoItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorit2, container, false);

        // Initialize RecyclerView
        recyclerView = rootView.findViewById(R.id.rvFavorit);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize ArrayList for RecyclerView data
        restoItems = new ArrayList<>();
        // Add sample data
        restoItems.add(new RestoItem("Ayam Lalapan", "The Most delicious fried chicken in town", R.drawable.resto, 5.0f));
        restoItems.add(new RestoItem("Es Coklat", "Solution Of your thirsty", R.drawable.resto, 4.0f));
        restoItems.add(new RestoItem("Katsu", "Original Japanesse Katsu", R.drawable.resto, 4.5f));
        restoItems.add(new RestoItem("Sushi", "All ingredients is fresh", R.drawable.resto, 4.0f));
        restoItems.add(new RestoItem("Nasi Goreng", "The one and only fried rice 24 hours", R.drawable.resto, 4.0f));
        restoItems.add(new RestoItem("Pop Ice", "5000 for all items", R.drawable.resto, 3.5f));
        // Add more items as needed

        // Initialize RecyclerView Adapter
        adapter = new RecyclerViewAdapter(restoItems);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}