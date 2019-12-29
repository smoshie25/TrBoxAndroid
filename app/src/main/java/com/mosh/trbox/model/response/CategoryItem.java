package com.mosh.trbox.model.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CategoryItem {

	@SerializedName("artists")
	private List<ArtistItem> artists;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private Object description;

	@SerializedName("href")
	private Object href;

	@SerializedName("id")
	private String id;

	public List<ArtistItem> getArtists(){
		return artists;
	}

	public String getName(){
		return name;
	}

	public Object getDescription(){
		return description;
	}

	public Object getHref(){
		return href;
	}

	public String getId(){
		return id;
	}
}