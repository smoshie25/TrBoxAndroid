package com.mosh.trbox.model.response.artistdetails;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Artist implements Serializable {

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

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setProfile(String profile){
		this.profile = profile;
	}

	public String getProfile(){
		return profile;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	@Override
 	public String toString(){
		return 
			"Artist{" + 
			"image = '" + image + '\'' + 
			",profile = '" + profile + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",categoryId = '" + categoryId + '\'' + 
			"}";
		}
}