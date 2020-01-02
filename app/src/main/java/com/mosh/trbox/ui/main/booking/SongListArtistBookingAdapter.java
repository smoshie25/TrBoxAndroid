package com.mosh.trbox.ui.main.booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.mosh.trbox.R;
import com.mosh.trbox.model.response.artistdetails.SongArtist;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Gideon on 27/08/19.
 */

public class SongListArtistBookingAdapter extends RecyclerView.Adapter<SongListArtistBookingAdapter.MovieViewHolder> {

    private List<SongArtist> songArtistList;
    private Context context;
    private ClickListner clickListner;

    public SongListArtistBookingAdapter(Context context, ClickListner listner) {
        this.context = context;
        songArtistList = new ArrayList<>();
        this.clickListner = listner;
    }

    private void add(SongArtist item) {
        songArtistList.add(item);
        notifyItemInserted(songArtistList.size() - 1);
    }

    public void addAll(List<SongArtist> movieDatas) {
        for (SongArtist movieData : movieDatas) {
            add(movieData);
        }
    }

    public void remove(SongArtist item) {
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

    public SongArtist getItem(int position) {
        return songArtistList.get(position);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song_artist_booking, parent, false);
        final MovieViewHolder movieViewHolder = new MovieViewHolder(view);

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        final SongArtist movieData = songArtistList.get(position);
        holder.bind(movieData);
    }

    @Override
    public int getItemCount() {
        return songArtistList.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView txtArtistName;
        TextView txtSongTitle;
        CircleImageView imageView;
        View view;

        public MovieViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtArtistName = itemView.findViewById(R.id.artist_name);
            txtSongTitle = itemView.findViewById(R.id.song_title);
            imageView = itemView.findViewById(R.id.imageView_profile);
        }

        public void bind(final SongArtist model) {
            txtArtistName.setText(model.getArtist().getName());
            txtSongTitle.setText(model.getTitle());
            if (model.getImage() != null){
                Glide.with(context)
                        .load(model.getImage())
                        .into(imageView);
            }

        }


    }

    public interface ClickListner {
        void onItemClick(SongArtist model, int position);
    }

}
