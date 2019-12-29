package com.mosh.trbox.model.response;

import com.google.gson.annotations.SerializedName;

public class SongItem {

	@SerializedName("image")
	private String image;

	@SerializedName("artist")
	private ArtistItem artist;

	@SerializedName("genre")
	private Genre genre;

	@SerializedName("href")
	private Object href;

	@SerializedName("id")
	private String id;

	@SerializedName("media")
	private String media;

	@SerializedName("title")
	private String title;

	public String getImage(){
		return image;
	}

	public ArtistItem getArtist(){
		return artist;
	}

	public Genre getGenre(){
		return genre;
	}

	public Object getHref(){
		return href;
	}

	public String getId(){
		return id;
	}

	public String getMedia(){
		return media;
	}

	public String getTitle(){
		return title;
	}
}