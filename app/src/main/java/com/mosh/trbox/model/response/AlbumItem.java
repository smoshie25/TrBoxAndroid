package com.mosh.trbox.model.response;

import com.google.gson.annotations.SerializedName;

public class AlbumItem {

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private Object description;

	@SerializedName("href")
	private Object href;

	@SerializedName("id")
	private String id;

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