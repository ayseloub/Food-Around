package com.example.eatfragment.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eatfragment.R;
import com.example.eatfragment.database.Note;
import com.example.eatfragment.helper.NoteDiffCallback;

import com.example.eatfragment.databinding.ItemNoteBinding;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends
        RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private final ArrayList<Note> listNotes = new ArrayList<>();
    void setListNotes(List<Note> listNotes) {
        final NoteDiffCallback diffCallback = new NoteDiffCallback(this.listNotes, listNotes);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.listNotes.clear();
        this.listNotes.addAll(listNotes);
        diffResult.dispatchUpdatesTo(this);
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNoteBinding binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NoteViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull final NoteViewHolder holder, int position) {
        holder.bind(listNotes.get(position));
        Note currentNote = listNotes.get(position);
        if (currentNote.getImagePath() != null && !currentNote.getImagePath().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(Uri.parse(currentNote.getImagePath()))
                    .into(holder.binding.imageGambar);
        } else {
            holder.binding.imageGambar.setImageResource(R.drawable.ic_launcher_background);
        }
    }
    @Override
    public int getItemCount() {
        return listNotes.size();
    }
    static class NoteViewHolder extends RecyclerView.ViewHolder {
        final ItemNoteBinding binding;

        NoteViewHolder(ItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Note note) {
            binding.tvItemTitle.setText(note.getTitle());
            binding.tvItemDate.setText(note.getDate());
            binding.tvItemDescription.setText(note.getDescription());
            if (note.getImagePath() != null && !note.getImagePath().isEmpty()) {
                Glide.with(binding.getRoot().getContext())
                        .load(Uri.parse(note.getImagePath()))
                        .into(binding.imageGambar);
            } else {
                binding.imageGambar.setImageResource(R.drawable.ic_launcher_background);
            }

            binding.cvItemNote.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), InsertUpdateActivity.class);
                intent.putExtra(InsertUpdateActivity.EXTRA_NOTE, note);
                v.getContext().startActivity(intent);
            });
        }
    }

}

