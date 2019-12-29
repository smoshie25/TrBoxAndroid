package com.mosh.trbox.ui.main.booking;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mosh.trbox.R;
import com.mosh.trbox.databinding.AlbumBinding;
import com.mosh.trbox.databinding.ArtistBinding;
import com.mosh.trbox.model.response.ArtistItem;
import com.mosh.trbox.model.response.SongItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtistRecyclerViewAdapter extends RecyclerView.Adapter<ArtistRecyclerViewAdapter.ViewHolder> {

    private final List<ArtistItem> items;


    public ArtistRecyclerViewAdapter(List<ArtistItem> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArtistBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.artist, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = items.get(position);
        //holder.profilePic.setText(items.get(position).getOperator());


        Picasso.get().load(holder.item.getImage()).into(holder.binding.image);

    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ArtistBinding binding;
        public ArtistItem item;

        public ViewHolder(ArtistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
