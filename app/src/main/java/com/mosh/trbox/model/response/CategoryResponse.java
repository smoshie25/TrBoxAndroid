package com.mosh.trbox.model.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse{


	@SerializedName("rel")
	private List<String> rel;

	@SerializedName("href")
	private String href;

	@SerializedName("value")
	private List<CategoryItem> value;

	public List<String> getRel(){
		return rel;
	}

	public String getHref(){
		return href;
	}

	public List<CategoryItem> getValue(){
		return value;
	}
}