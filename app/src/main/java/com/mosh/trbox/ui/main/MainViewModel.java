package com.mosh.trbox.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mosh.trbox.di.network.main.MainApi;
import com.mosh.trbox.model.request.RegisterRequest;
import com.mosh.trbox.model.response.CategoryResponse;
import com.mosh.trbox.model.response.GenreResponse;
import com.mosh.trbox.model.response.LoginResponse;
import com.mosh.trbox.model.response.SongResponse;

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

}
