package com.mosh.trbox.ui.main.music;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.models.SlideModel;
import com.mosh.trbox.R;
import com.mosh.trbox.databinding.FeedBinding;
import com.mosh.trbox.model.FeedCategory;
import com.mosh.trbox.util.HelperRegistry;

import java.util.ArrayList;
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
            case GENRE:
                holder.binding.album.setVisibility(View.GONE);
                holder.binding.subMenu.setVisibility(View.VISIBLE);
                holder.binding.slider.setVisibility(View.GONE);

                holder.binding.menuTitle.setText(holder.item.getTitle());

                holder.binding.subMenuList.setAdapter(new SubMenuRecyclerViewAdapter(holder.item.getCategoryItems()));

                break;

            case ALBUM:
            case SONG:
                holder.binding.album.setVisibility(View.VISIBLE);
                holder.binding.subMenu.setVisibility(View.GONE);
                holder.binding.slider.setVisibility(View.GONE);

                holder.binding.albumTitle.setText(holder.item.getTitle());
                holder.binding.albumList.setAdapter(new SongRecyclerViewAdapter(holder.item.getSongItems()));
                break;
            case SLIDER:
                holder.binding.album.setVisibility(View.GONE);
                holder.binding.subMenu.setVisibility(View.GONE);
                holder.binding.slider.setVisibility(View.VISIBLE);

                List<SlideModel> imageList = new ArrayList<>();
// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title
// imageList.add(SlideModel("String Url" or R.drawable, "title", true) Also you can add centerCrop scaleType for this image
                imageList.add(new SlideModel("https://i0.wp.com/media.premiumtimesng.com/wp-content/files/2017/05/skalesfront-album-cover-new-1-720x720-e1495296648958.jpg"));
                imageList.add(new SlideModel("https://is3-ssl.mzstatic.com/image/thumb/Music49/v4/8c/4d/7c/8c4d7cb6-4232-2f1b-0d2f-d6423f92e403/source/1200x1200bb.jpg"));
                imageList.add(new SlideModel("https://cdn.smehost.net/rcarecordscom-usrcaprod/wp-content/uploads/2019/07/blow-my-mind-ndew-561x561.jpg"));
                imageList.add(new SlideModel("http://jaguda.com/wp-content/uploads/2019/07/burna-boy-African-Giant-album-.jpg"));

                holder.binding.imageSlider.setImageList(imageList,true);
                holder.binding.imageSlider.startSliding(30000);
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
