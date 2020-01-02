package com.mosh.trbox.model;

public class NameTest {
    private String media_id;
    private String media_url;
    private String artist;
    private String date_added;
    private String description;
    private String title;
    private String image;

    public NameTest(String media_id, String media_url, String artist, String date_added, String description, String title, String image) {
        this.media_id = media_id;
        this.media_url = media_url;
        this.artist = artist;
        this.date_added = date_added;
        this.description = description;
        this.title = title;
        this.image = image;
    }


    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
