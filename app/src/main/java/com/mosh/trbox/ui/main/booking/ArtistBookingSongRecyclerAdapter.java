package com.mosh.trbox.ui.main.booking;

import android.content.Context;
import android.support.v4.media.MediaMetadataCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.mosh.trbox.R;

import java.util.ArrayList;

public class ArtistBookingSongRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "PlaylistRecyclerAdapter";

    private ArrayList<MediaMetadataCompat> mMediaList = new ArrayList<>();
    private Context mContext;
    private IMediaSelector mIMediaSelector;
    private int mSelectedIndex;

    public ArtistBookingSongRecyclerAdapter(Context context, ArrayList<MediaMetadataCompat> mediaList, IMediaSelector mediaSelector) {
        Log.d(TAG, "PlaylistRecyclerAdapter: called.");
        this.mMediaList = mediaList;
        this.mContext = context;
        this.mIMediaSelector = mediaSelector;
        mSelectedIndex = -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_song_list_artist_booking, null);
        ViewHolder vh = new ViewHolder(view, mIMediaSelector);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//        ((ViewHolder)viewHolder).title.setText(mMediaList.get(i).getDescription().getTitle());
//        ((ViewHolder)viewHolder).artist.setText(mMediaList.get(i).getDescription().getSubtitle());
        Glide.with(mContext)
                .load(mMediaList.get(i).getDescription().getIconUri())
                .into(((ViewHolder)viewHolder).imageView);
//        if(i == mSelectedIndex){
//            ((ViewHolder)viewHolder).title.setTextColor(ContextCompat.getColor(mContext, R.color.green));
//        }
//        else{
//            ((ViewHolder)viewHolder).title.setTextColor(ContextCompat.getColor(mContext, R.color.black));
//        }
    }

    @Override
    public int getItemCount() {
        return mMediaList.size();
    }

    public void setSelectedIndex(int index){
        mSelectedIndex = index;
        notifyDataSetChanged();
    }

    public int getSelectedIndex(){
        return mSelectedIndex;
    }

    public int getIndexOfItem(MediaMetadataCompat mediaItem){
        for(int i = 0; i<mMediaList.size(); i++ ){
            if(mMediaList.get(i).getDescription().getMediaId().equals(mediaItem.getDescription().getMediaId())){
                return i;
            }
        }
        return -1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title, artist;
        private ImageView imageView;
        private IMediaSelector iMediaSelector;

        public ViewHolder(@NonNull View itemView, IMediaSelector categorySelector) {
            super(itemView);
//            title = itemView.findViewById(R.id.media_title);
//            artist = itemView.findViewById(R.id.media_artist);
//            artist = itemView.findViewById(R.id.media_artist);
            imageView = itemView.findViewById(R.id.imageView_profile);

            iMediaSelector = categorySelector;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            iMediaSelector.onMediaSelected(getAdapterPosition());
        }
    }

    public interface IMediaSelector{
        void onMediaSelected(int position);
    }

}