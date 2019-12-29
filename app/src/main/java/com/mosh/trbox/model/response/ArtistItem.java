package com.mosh.trbox.model.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ArtistItem {

	@SerializedName("image")
	private String image;

	@SerializedName("phone")
	private String phone;

	@SerializedName("songs")
	private List<Object> songs;

	@SerializedName("profile")
	private String profile;

	@SerializedName("name")
	private String name;

	@SerializedName("href")
	private String href;

	@SerializedName("id")
	private String id;

	@SerializedName("category")
	private CategoryItem category;

	public String getImage(){
		return image;
	}

	public String getPhone(){
		return phone;
	}

	public List<Object> getSongs(){
		return songs;
	}

	public String getProfile(){
		return profile;
	}

	public String getName(){
		return name;
	}

	public String getHref(){
		return href;
	}

	public String getId(){
		return id;
	}

	public CategoryItem getCategory(){
		return category;
	}
}