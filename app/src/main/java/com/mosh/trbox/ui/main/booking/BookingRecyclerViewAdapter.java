package com.mosh.trbox.ui.main.booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.models.SlideModel;
import com.mosh.trbox.R;
import com.mosh.trbox.databinding.ArtistBinding;
import com.mosh.trbox.databinding.BookingBinding;
import com.mosh.trbox.model.BookingCategory;
import com.mosh.trbox.model.FeedCategory;
import com.mosh.trbox.model.response.ArtistItem;
import com.mosh.trbox.ui.main.music.SongRecyclerViewAdapter;
import com.mosh.trbox.ui.main.music.SubMenuRecyclerViewAdapter;
import com.mosh.trbox.util.AppCoordinator;

import java.util.ArrayList;
import java.util.List;

public class BookingRecyclerViewAdapter extends RecyclerView.Adapter<BookingRecyclerViewAdapter.ViewHolder> implements ArtistClickListener {

    private final List<BookingCategory> items;
    private final Context context;
    private final AppCoordinator coordinator;


    public BookingRecyclerViewAdapter(List<BookingCategory> items, Context context , AppCoordinator coordinator) {
        this.items = items;
        this.context = context;
        this.coordinator = coordinator;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BookingBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.booking, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = items.get(position);
        //holder.profilePic.setText(items.get(position).getOperator());

        switch (holder.item.getType()){
            case ARTIST:
                holder.binding.subMenu.setVisibility(View.VISIBLE);
                holder.binding.slider.setVisibility(View.GONE);

                holder.binding.menuTitle.setText(holder.item.getTitle());
                holder.binding.subMenuList.setAdapter(new ArtistRecyclerViewAdapter(holder.item.getArtistItems(),this));
                break;
            case SLIDER:
                holder.binding.subMenu.setVisibility(View.GONE);
                holder.binding.slider.setVisibility(View.VISIBLE);

                List<SlideModel> imageList = new ArrayList<>();
// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title
// imageList.add(SlideModel("String Url" or R.drawable, "title", true) Also you can add centerCrop scaleType for this image
                imageList.add(new SlideModel("https://ichef.bbci.co.uk/news/320/cpsprodpb/11723/production/_97495417_olamide.jpg"));
                imageList.add(new SlideModel("https://cdn-www.konbini.com/ng/files/2017/10/9ice-1.jpeg"));
                imageList.add(new SlideModel("https://www.konbini.com/ng/files/2018/06/screen-shot-2018-06-01-at-09.39.01.jpg"));

                holder.binding.imageSlider.setImageList(imageList,true);
                holder.binding.imageSlider.startSliding(30000);
                break;
        }

    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onArtistClick(ArtistItem item) {
        coordinator.launchArtistActivity(context,item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private BookingBinding binding;
        public BookingCategory item;

        public ViewHolder(BookingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
