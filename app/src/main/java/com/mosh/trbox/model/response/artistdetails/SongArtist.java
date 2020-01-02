package com.mosh.trbox.model.response.artistdetails;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SongArtist implements Serializable {

	@SerializedName("image")
	private String image;

	@SerializedName("artist")
	private Artist artist;

	@SerializedName("href")
	private Object href;

	@SerializedName("media")
	private String media;

	@SerializedName("title")
	private String title;

	@SerializedName("id")
	private String songId;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setArtist(Artist artist){
		this.artist = artist;
	}

	public Artist getArtist(){
		return artist;
	}

	public void setHref(Object href){
		this.href = href;
	}

	public Object getHref(){
		return href;
	}

	public void setMedia(String media){
		this.media = media;
	}

	public String getMedia(){
		return media;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setSongId(String songId){
		this.songId = songId;
	}

	public String getSongId(){
		return songId;
	}

	@Override
 	public String toString(){
		return 
			"SongArtist{" + 
			"image = '" + image + '\'' + 
			",artist = '" + artist + '\'' + 
			",href = '" + href + '\'' + 
			",media = '" + media + '\'' + 
			",title = '" + title + '\'' + 
			",songId = '" + songId + '\'' + 
			"}";
		}
}