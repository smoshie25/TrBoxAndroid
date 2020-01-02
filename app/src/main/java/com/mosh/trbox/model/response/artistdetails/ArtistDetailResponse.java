package com.mosh.trbox.model.response.artistdetails;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ArtistDetailResponse implements Serializable {

	@SerializedName("image")
	private String image;

	@SerializedName("value")
	private List<SongArtist> songs;

	@SerializedName("profile")
	private String profile;

	@SerializedName("name")
	private String name;

	@SerializedName("genre")
	private Object genre;

	@SerializedName("href")
	private Object href;

	@SerializedName("id")
	private String id;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setSongs(List<SongArtist> songs){
		this.songs = songs;
	}

	public List<SongArtist> getSongs(){
		return songs;
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

	public void setGenre(Object genre){
		this.genre = genre;
	}

	public Object getGenre(){
		return genre;
	}

	public void setHref(Object href){
		this.href = href;
	}

	public Object getHref(){
		return href;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"ArtistDetailResponse{" + 
			"image = '" + image + '\'' +
			",profile = '" + profile + '\'' + 
			",name = '" + name + '\'' + 
			",genre = '" + genre + '\'' + 
			",href = '" + href + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}