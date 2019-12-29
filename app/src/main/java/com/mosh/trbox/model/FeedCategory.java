package com.mosh.trbox.model;

import com.mosh.trbox.model.response.CategoryItem;

import java.util.List;

public class FeedCategory {

    private CategoryType type;

    private String title;
    private List<CategoryItem> categoryItems;


    public FeedCategory(CategoryType type, String title,List<CategoryItem> categoryItems) {
        this.type = type;
        this.title = title;
        this.categoryItems = categoryItems;
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
