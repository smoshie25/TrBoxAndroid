package com.mosh.trbox.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumResponse{


	@SerializedName("rel")
	private List<String> rel;

	@SerializedName("href")
	private Object href;

	@SerializedName("value")
	private List<AlbumItem> value;


	public List<String> getRel(){
		return rel;
	}

	public Object getHref(){
		return href;
	}

	public List<AlbumItem> getValue(){
		return value;
	}
}