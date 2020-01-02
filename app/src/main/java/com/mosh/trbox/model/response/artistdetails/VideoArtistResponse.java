package com.mosh.trbox.model.response.artistdetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoArtistResponse{

	@SerializedName("limit")
	private int limit;

	@SerializedName("href")
	private String href;

	@SerializedName("value")
	private List<VideoArtist> videoList;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<VideoArtist> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<VideoArtist> videoList) {
		this.videoList = videoList;
	}
}