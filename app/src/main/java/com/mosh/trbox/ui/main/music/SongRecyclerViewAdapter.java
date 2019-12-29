package com.mosh.trbox.ui.main.music;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mosh.trbox.R;
import com.mosh.trbox.databinding.AlbumBinding;
import com.mosh.trbox.databinding.SubMenuBinding;
import com.mosh.trbox.model.response.CategoryItem;
import com.mosh.trbox.model.response.SongItem;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class SongRecyclerViewAdapter extends RecyclerView.Adapter<SongRecyclerViewAdapter.ViewHolder> {

    private final List<SongItem> items;


    public SongRecyclerViewAdapter(List<SongItem> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AlbumBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.album, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = items.get(position);
        //holder.profilePic.setText(items.get(position).getOperator());

        holder.binding.name.setText(holder.item.getTitle());

        Picasso.get().load(holder.item.getImage()).into(holder.binding.image);

    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AlbumBinding binding;
        public SongItem item;

        public ViewHolder(AlbumBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
