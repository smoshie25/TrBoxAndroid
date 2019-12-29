package com.mosh.trbox.ui.main.music;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mosh.trbox.R;
import com.mosh.trbox.databinding.FeedBinding;
import com.mosh.trbox.model.FeedCategory;
import com.mosh.trbox.util.HelperRegistry;

import java.util.List;

public class MusicRecyclerViewAdapter extends RecyclerView.Adapter<MusicRecyclerViewAdapter.ViewHolder> {

    private final List<FeedCategory> items;


    public MusicRecyclerViewAdapter(List<FeedCategory> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FeedBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.feed, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = items.get(position);
        //holder.profilePic.setText(items.get(position).getOperator());

        switch (holder.item.getType()){
            case MENU:
                holder.binding.album.setVisibility(View.GONE);
                holder.binding.subMenu.setVisibility(View.VISIBLE);
                holder.binding.slider.setVisibility(View.GONE);

                holder.binding.menuTitle.setText(holder.item.getTitle());

                holder.binding.subMenuList.setAdapter(new SubMenuRecyclerViewAdapter(holder.item.getCategoryItems()));

                break;
            case ALBUM:
                holder.binding.album.setVisibility(View.VISIBLE);
                holder.binding.subMenu.setVisibility(View.GONE);
                holder.binding.slider.setVisibility(View.GONE);
                break;
            case SLIDER:
                holder.binding.album.setVisibility(View.GONE);
                holder.binding.subMenu.setVisibility(View.GONE);
                holder.binding.slider.setVisibility(View.VISIBLE);
                break;
        }

    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private FeedBinding binding;
        public FeedCategory item;

        public ViewHolder(FeedBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
