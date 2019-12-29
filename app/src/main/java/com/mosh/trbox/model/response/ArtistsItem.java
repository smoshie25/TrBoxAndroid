package com.mosh.trbox.model.response;

import com.google.gson.annotations.SerializedName;

public class ArtistsItem{

	@SerializedName("image")
	private String image;

	@SerializedName("profile")
	private String profile;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("categoryId")
	private String categoryId;

	public String getImage(){
		return image;
	}

	public String getProfile(){
		return profile;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public String getCategoryId(){
		return categoryId;
	}
}