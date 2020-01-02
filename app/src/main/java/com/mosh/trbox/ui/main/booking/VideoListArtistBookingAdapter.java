package com.mosh.trbox.ui.main.booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mosh.trbox.R;
import com.mosh.trbox.model.response.artistdetails.VideoArtist;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Gideon on 27/08/19.
 */

public class VideoListArtistBookingAdapter extends RecyclerView.Adapter<VideoListArtistBookingAdapter.MovieViewHolder> {

    private List<VideoArtist> songArtistList;
    private Context context;
    int selected_position = -1;

    private ClickListner clickListner;

    public VideoListArtistBookingAdapter(Context context, ClickListner listner) {
        this.context = context;
        songArtistList = new ArrayList<>();
        this.clickListner = listner;
    }

    private void add(VideoArtist item) {
        songArtistList.add(item);
        notifyItemInserted(songArtistList.size() - 1);
    }

    public void addAll(List<VideoArtist> movieDatas) {
        for (VideoArtist movieData : movieDatas) {
            add(movieData);
        }
    }

    public void remove(VideoArtist item) {
        int position = songArtistList.indexOf(item);
        if (position > -1) {
            songArtistList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public VideoArtist getItem(int position) {
        return songArtistList.get(position);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_artist, parent, false);
        final MovieViewHolder movieViewHolder = new MovieViewHolder(view);

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        final VideoArtist movieData = songArtistList.get(position);
        holder.bind(movieData);
    }

    @Override
    public int getItemCount() {
        return songArtistList.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView videoTitle;
        ImageView imageView;
        View view;

        public MovieViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            videoTitle = itemView.findViewById(R.id.txt_video_name);
            imageView = itemView.findViewById(R.id.imageView_profile);
        }

        public void bind(final VideoArtist model) {
            videoTitle.setText(model.getTitle());
            if (model.getImage() != null){
                Glide.with(context)
                        .load(model.getImage())
                        .into(imageView);
            }

        }


    }

    public interface ClickListner {
        void onItemClick(VideoArtist model, int position);
    }

}
