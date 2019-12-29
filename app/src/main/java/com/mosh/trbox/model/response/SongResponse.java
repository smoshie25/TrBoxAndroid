package com.mosh.trbox.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SongResponse{

	@SerializedName("offset")
	private int offset;

	@SerializedName("size")
	private int size;

	@SerializedName("rel")
	private List<String> rel;

	@SerializedName("limit")
	private int limit;

	@SerializedName("href")
	private String href;

	@SerializedName("value")
	private List<SongItem> value;


	public int getOffset(){
		return offset;
	}

	public int getSize(){
		return size;
	}


	public List<String> getRel(){
		return rel;
	}

	public int getLimit(){
		return limit;
	}

	public String getHref(){
		return href;
	}

	public List<SongItem> getValue(){
		return value;
	}

}