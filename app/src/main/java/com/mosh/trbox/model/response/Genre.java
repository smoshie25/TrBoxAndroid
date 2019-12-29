package com.mosh.trbox.model.response;

import com.google.gson.annotations.SerializedName;

public class Genre{

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private Object description;

	@SerializedName("href")
	private String href;

	public String getName(){
		return name;
	}

	public Object getDescription(){
		return description;
	}

	public String getHref(){
		return href;
	}
}