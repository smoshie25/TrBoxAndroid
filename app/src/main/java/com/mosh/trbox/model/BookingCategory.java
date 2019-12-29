package com.mosh.trbox.model;

import com.mosh.trbox.model.response.ArtistItem;
import com.mosh.trbox.model.response.CategoryItem;
import com.mosh.trbox.model.response.SongItem;

import java.util.List;

public class BookingCategory {

    private BookingCategoryType type;

    private String title;

    List<ArtistItem> artistItems;


    public BookingCategory(BookingCategoryType type, String title) {
        this.type = type;
        this.title = title;
    }

    public List<ArtistItem> getArtistItems() {
        return artistItems;
    }

    public void setArtistItems(List<ArtistItem> artistItems) {
        this.artistItems = artistItems;
    }

    public BookingCategoryType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

}
