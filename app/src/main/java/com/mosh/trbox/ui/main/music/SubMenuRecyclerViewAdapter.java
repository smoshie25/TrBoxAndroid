package com.mosh.trbox.ui.main.music;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mosh.trbox.R;
import com.mosh.trbox.databinding.FeedBinding;
import com.mosh.trbox.databinding.SubMenuBinding;
import com.mosh.trbox.model.FeedCategory;
import com.mosh.trbox.model.response.CategoryItem;
import com.mosh.trbox.util.HelperRegistry;

import java.util.List;

public class SubMenuRecyclerViewAdapter extends RecyclerView.Adapter<SubMenuRecyclerViewAdapter.ViewHolder> {

    private final List<CategoryItem> items;


    public SubMenuRecyclerViewAdapter(List<CategoryItem> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SubMenuBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.sub_menu, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = items.get(position);
        //holder.profilePic.setText(items.get(position).getOperator());

        holder.binding.name.setText(holder.item.getName());

    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SubMenuBinding binding;
        public CategoryItem item;

        public ViewHolder(SubMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
