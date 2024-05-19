package com.example.eatfragment.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eatfragment.R;
import com.example.eatfragment.databinding.FragmentProfilBinding;
import com.example.eatfragment.helper.ViewModelFactory;


public class ProfilFragment extends Fragment {

    private FragmentProfilBinding binding;
    private NoteAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfilBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        adapter = new NoteAdapter();
        binding.rvNotes.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvNotes.setHasFixedSize(true);
        binding.rvNotes.setAdapter(adapter);

        binding.fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(requireActivity(), InsertUpdateActivity.class);
            startActivity(intent);
        });

        NoteMainViewModel noteMainViewModel = obtainViewModel((AppCompatActivity) requireActivity());
        // Perhatikan perubahan disini, menggunakan getViewLifecycleOwner() untuk observasi pada siklus hidup fragment
        noteMainViewModel.getAllNotes().observe(getViewLifecycleOwner(), notes -> {
            if (notes != null) {
                adapter.setListNotes(notes);
            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Metode yang sama seperti yang ada di MainActivity
    private static NoteMainViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(NoteMainViewModel.class);
    }
}