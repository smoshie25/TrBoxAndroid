package com.mosh.trbox.ui.main.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.mosh.trbox.R;
import com.mosh.trbox.databinding.ActivityArtistBinding;
import com.mosh.trbox.model.response.ArtistItem;
import com.mosh.trbox.ui.main.music.SongRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import dagger.android.support.DaggerAppCompatActivity;

public class ArtistActivity extends DaggerAppCompatActivity {

    ActivityArtistBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist);

        ArtistItem artist = (ArtistItem) getIntent().getSerializableExtra("ITEM");

        if(artist!=null){
            Picasso.get().load(artist.getImage()).into(binding.image);
            binding.name.setText(artist.getName());
            binding.profile.setText(artist.getProfile());

            setSupportActionBar(binding.toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");

            SongRecyclerViewAdapter adapter = new SongRecyclerViewAdapter(artist.getSongs());

            binding.songList.setLayoutManager(new GridLayoutManager(this,3));

            //binding.songList.setAdapter(adapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
