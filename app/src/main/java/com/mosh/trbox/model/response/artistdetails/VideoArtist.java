package com.mosh.trbox.model.response.artistdetails;

import com.google.gson.annotations.SerializedName;

public class VideoArtist {

	@SerializedName("image")
	private String image;

	@SerializedName("artist")
	private Artist artist;

//	@SerializedName("genre")
//	private Genre genre;

	@SerializedName("videoId")
	private String videoId;

	@SerializedName("href")
	private String href;

	@SerializedName("media")
	private String media;

	@SerializedName("video")
	private Object video;

	@SerializedName("title")
	private String title;

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

	public void setVideoId(String videoId){
		this.videoId = videoId;
	}

	public String getVideoId(){
		return videoId;
	}

	public void setHref(String href){
		this.href = href;
	}

	public String getHref(){
		return href;
	}

	public void setMedia(String media){
		this.media = media;
	}

	public String getMedia(){
		return media;
	}

	public void setVideo(Object video){
		this.video = video;
	}

	public Object getVideo(){
		return video;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"VideoArtist{" +
			"image = '" + image + '\'' + 
			",artist = '" + artist + '\'' +
			",videoId = '" + videoId + '\'' + 
			",href = '" + href + '\'' + 
			",media = '" + media + '\'' + 
			",video = '" + video + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}