package com.mosh.trbox.model;

import com.mosh.trbox.model.response.CategoryItem;
import com.mosh.trbox.model.response.SongItem;

import java.util.List;

public class FeedCategory {

    private CategoryType type;

    private String title;

    private List<CategoryItem> categoryItems;

    private List<SongItem> songItems;


    public FeedCategory(CategoryType type, String title) {
        this.type = type;
        this.title = title;
    }

    public void setCategoryItems(List<CategoryItem> categoryItems) {
        this.categoryItems = categoryItems;
    }

    public List<SongItem> getSongItems() {
        return songItems;
    }

    public void setSongItems(List<SongItem> songItems) {
        this.songItems = songItems;
    }

    public CategoryType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public List<CategoryItem> getCategoryItems() {
        return categoryItems;
    }
}
