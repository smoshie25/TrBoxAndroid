package com.mosh.trbox.di.network.main;

import com.mosh.trbox.model.response.AlbumResponse;
import com.mosh.trbox.model.response.ArtistResponse;
import com.mosh.trbox.model.response.CategoryResponse;
import com.mosh.trbox.model.response.GenreResponse;
import com.mosh.trbox.model.response.SongResponse;
import com.mosh.trbox.model.response.artistdetails.ArtistDetailResponse;
import com.mosh.trbox.model.response.artistdetails.VideoArtistResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface MainApi {

    @GET("category")
    Call<CategoryResponse> getCategory();


    @GET("genre")
    Call<GenreResponse> getGenre();

    @GET("song")
    Call<SongResponse> getSong();

    @GET("songalbum")
    Call<AlbumResponse> getAlbum();

    @GET("artist")
    Call<ArtistResponse> getArtist();

    @GET
    Call<ArtistDetailResponse> getArtistBookingDetailsSong(@Url String url);

    @GET
    Call<VideoArtistResponse> getArtistVideo(@Url String url);


}
