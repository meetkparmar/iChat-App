package com.meetkparmar.ichatapp;

import com.meetkparmar.ichatapp.models.UserDetails;

import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    String API_BASE_URL = "https://assignment.medimetry.in/";

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.client(httpClient.build()).build();

    RetrofitApis retrofitApis = retrofit.create(RetrofitApis.class);


    public MutableLiveData<UserDetails> getUserDetails() {
        final MutableLiveData<UserDetails> userDetailsMutableLiveData = new MutableLiveData<>();
        Call<UserDetails> call = retrofitApis.getUserDetails();
        call.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                if (response.isSuccessful()) {
                    userDetailsMutableLiveData.postValue(response.body());
                } else {
                    userDetailsMutableLiveData.postValue(new UserDetails(-1, "Something Went Wrong!"));
                }
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                userDetailsMutableLiveData.postValue(new UserDetails(-1, "Something Went Wrong!\nFailure on request."));
            }
        });
        return userDetailsMutableLiveData;
    }

}
