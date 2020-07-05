package com.meetkparmar.ichatapp;

import com.meetkparmar.ichatapp.models.UserDetails;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApis {

    @GET("/api/v1/users/get")
    Call<UserDetails> getUserDetails();
}
