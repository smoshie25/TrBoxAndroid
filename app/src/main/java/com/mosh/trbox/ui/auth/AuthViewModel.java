package com.mosh.trbox.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mosh.trbox.di.network.auth.AuthApi;
import com.mosh.trbox.model.request.RegisterRequest;
import com.mosh.trbox.model.response.LoginResponse;

import org.json.JSONObject;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mosh.trbox.util.Constants.NETWORK_ERROR;
import static com.mosh.trbox.util.Constants.SYSTEM_ERROR;

public class AuthViewModel extends ViewModel {

    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi){
        this.authApi = authApi;
    }

    public LiveData<LoginResponse> login(String username,String password) {
        final MutableLiveData<LoginResponse> responseApi = new MutableLiveData<>();
        Call call = authApi.login("password",username,password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                try {

                    if(response.isSuccessful())
                        responseApi.setValue(response.body());
                    else{
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        responseApi.setValue(new LoginResponse(jObjError.getString("error_description")));
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    responseApi.setValue(new LoginResponse(SYSTEM_ERROR));
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                responseApi.setValue(new LoginResponse(NETWORK_ERROR ));
            }
        });

        return responseApi;
    }

    public LiveData<LoginResponse> register(RegisterRequest request) {
        final MutableLiveData<LoginResponse> responseApi = new MutableLiveData<>();
        Call call = authApi.register(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                try {

                    if(response.isSuccessful())
                        responseApi.setValue(new LoginResponse("Sign up successful"));
                    else{
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        responseApi.setValue(new LoginResponse(jObjError.getString("detail")));
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    responseApi.setValue(new LoginResponse(SYSTEM_ERROR));
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                responseApi.setValue(new LoginResponse(NETWORK_ERROR ));
            }
        });

        return responseApi;
    }
}
