package com.mosh.trbox.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mosh.trbox.di.network.main.MainApi;
import com.mosh.trbox.model.request.RegisterRequest;
import com.mosh.trbox.model.response.CategoryResponse;
import com.mosh.trbox.model.response.LoginResponse;

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

}
