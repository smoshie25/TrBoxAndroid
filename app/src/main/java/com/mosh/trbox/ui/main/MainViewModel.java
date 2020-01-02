package com.mosh.trbox.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mosh.trbox.di.network.main.MainApi;
import com.mosh.trbox.model.request.RegisterRequest;
import com.mosh.trbox.model.response.ArtistResponse;
import com.mosh.trbox.model.response.CategoryResponse;
import com.mosh.trbox.model.response.GenreResponse;
import com.mosh.trbox.model.response.LoginResponse;
import com.mosh.trbox.model.response.SongResponse;
import com.mosh.trbox.model.response.artistdetails.ArtistDetailResponse;
import com.mosh.trbox.model.response.artistdetails.VideoArtistResponse;
import com.mosh.trbox.util.Constants;

import org.json.JSONObject;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mosh.trbox.util.Constants.NETWORK_ERROR;
import static com.mosh.trbox.util.Constants.SYSTEM_ERROR;

public class MainViewModel extends ViewModel {

    private final MainApi mainApi;

    @Inject
    public MainViewModel(MainApi mainApi){
        this.mainApi = mainApi;
    }

    public LiveData<CategoryResponse> getCategory() {
        final MutableLiveData<CategoryResponse> responseApi = new MutableLiveData<>();
        Call call = mainApi.getCategory();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                try {

                    if(response.isSuccessful())
                        responseApi.setValue(response.body());
                    else{
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        //responseApi.setValue(new LoginResponse(jObjError.getString("error_description")));
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    //responseApi.setValue(new LoginResponse(SYSTEM_ERROR));
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //responseApi.setValue(new LoginResponse(NETWORK_ERROR ));
            }
        });

        return responseApi;
    }

    public LiveData<GenreResponse> getGenre() {
        final MutableLiveData<GenreResponse> responseApi = new MutableLiveData<>();
        Call call = mainApi.getGenre();
        call.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                try {

                    if(response.isSuccessful())
                        responseApi.setValue(response.body());
                    else{
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        //responseApi.setValue(new LoginResponse(jObjError.getString("error_description")));
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    //responseApi.setValue(new LoginResponse(SYSTEM_ERROR));
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //responseApi.setValue(new LoginResponse(NETWORK_ERROR ));
            }
        });

        return responseApi;
    }

    public LiveData<SongResponse> getSongs() {
        final MutableLiveData<SongResponse> responseApi = new MutableLiveData<>();
        Call call = mainApi.getSong();
        call.enqueue(new Callback<SongResponse>() {
            @Override
            public void onResponse(Call<SongResponse> call, Response<SongResponse> response) {
                try {

                    if(response.isSuccessful())
                        responseApi.setValue(response.body());
                    else{
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        //responseApi.setValue(new LoginResponse(jObjError.getString("error_description")));
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    //responseApi.setValue(new LoginResponse(SYSTEM_ERROR));
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //responseApi.setValue(new LoginResponse(NETWORK_ERROR ));
            }
        });

        return responseApi;
    }

    public LiveData<ArtistResponse> getArtist() {
        final MutableLiveData<ArtistResponse> responseApi = new MutableLiveData<>();
        Call call = mainApi.getArtist();
        call.enqueue(new Callback<ArtistResponse>() {
            @Override
            public void onResponse(Call<ArtistResponse> call, Response<ArtistResponse> response) {
                try {

                    if(response.isSuccessful())
                        responseApi.setValue(response.body());
                    else{
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        //responseApi.setValue(new LoginResponse(jObjError.getString("error_description")));
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    //responseApi.setValue(new LoginResponse(SYSTEM_ERROR));
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //responseApi.setValue(new LoginResponse(NETWORK_ERROR ));
            }
        });

        return responseApi;
    }

    public LiveData<ArtistDetailResponse> getArtistBookingSong(int page, String id) {
        String url = Constants.BASE_URL + "artist/" + id + "?limit=20&"+"offset="+page;
        final MutableLiveData<ArtistDetailResponse> responseApi = new MutableLiveData<>();
        Call call = mainApi.getArtistBookingDetailsSong(url);
        call.enqueue(new Callback<ArtistDetailResponse>() {
            @Override
            public void onResponse(Call<ArtistDetailResponse> call, Response<ArtistDetailResponse> response) {
                try {

                    if(response.isSuccessful())
                        responseApi.setValue(response.body());
                    else{
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        //responseApi.setValue(new LoginResponse(jObjError.getString("error_description")));
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    //responseApi.setValue(new LoginResponse(SYSTEM_ERROR));
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //responseApi.setValue(new LoginResponse(NETWORK_ERROR ));
            }
        });

        return responseApi;
    }

    public LiveData<VideoArtistResponse> fetchVideoOfArtistBooking(int page, String id) {
        String url =  Constants.BASE_URL+ "video/" + id ;
        final MutableLiveData<VideoArtistResponse> responseApi = new MutableLiveData<>();
        Call call = mainApi.getArtistVideo(url);
        call.enqueue(new Callback<VideoArtistResponse>() {
            @Override
            public void onResponse(Call<VideoArtistResponse> call, Response<VideoArtistResponse> response) {
                try {

                    if(response.isSuccessful())
                        responseApi.setValue(response.body());
                    else{
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        //responseApi.setValue(new LoginResponse(jObjError.getString("error_description")));
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    //responseApi.setValue(new LoginResponse(SYSTEM_ERROR));
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //responseApi.setValue(new LoginResponse(NETWORK_ERROR ));
            }
        });

        return responseApi;
    }

}
