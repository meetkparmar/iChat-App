package com.meetkparmar.ichatapp.network;

import com.meetkparmar.ichatapp.models.MessageBody;
import com.meetkparmar.ichatapp.models.MessageBodyResponse;
import com.meetkparmar.ichatapp.models.UserChatDetails;
import com.meetkparmar.ichatapp.models.UserDetails;

import java.util.concurrent.TimeUnit;

import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    String API_BASE_URL = "https://assignment.medimetry.in/";

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS);

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

    public MutableLiveData<UserChatDetails> getUserChatDetails(int user) {
        final MutableLiveData<UserChatDetails> userChatDetailsMutableLiveData = new MutableLiveData<>();
        Call<UserChatDetails> call = retrofitApis.getUserChats(user);
        call.enqueue(new Callback<UserChatDetails>() {
            @Override
            public void onResponse(Call<UserChatDetails> call, Response<UserChatDetails> response) {
                if (response.isSuccessful()) {
                    userChatDetailsMutableLiveData.postValue(response.body());
                } else {
                    userChatDetailsMutableLiveData.postValue(new UserChatDetails(-1, "Something Went Wrong!"));
                }
            }

            @Override
            public void onFailure(Call<UserChatDetails> call, Throwable t) {
                userChatDetailsMutableLiveData.postValue(new UserChatDetails(-1, "Something Went Wrong!\nFailure on request."));
            }
        });
        return userChatDetailsMutableLiveData;
    }

    public MutableLiveData<MessageBodyResponse> sendMessage(MessageBody messageBody) {
        final MutableLiveData<MessageBodyResponse> messageBodyResponseMutableLiveData = new MutableLiveData<>();
        Call<MessageBodyResponse> call = retrofitApis.sendMessage(messageBody);
        call.enqueue(new Callback<MessageBodyResponse>() {
            @Override
            public void onResponse(Call<MessageBodyResponse> call, Response<MessageBodyResponse> response) {
                if (response.isSuccessful()) {
                    messageBodyResponseMutableLiveData.postValue(response.body());
                } else {
                    messageBodyResponseMutableLiveData.postValue(new MessageBodyResponse(-1, "Something Went Wrong!"));
                }
            }

            @Override
            public void onFailure(Call<MessageBodyResponse> call, Throwable t) {
                messageBodyResponseMutableLiveData.postValue(new MessageBodyResponse(-1, "Something Went Wrong!\nFailure on request."));
            }
        });
        return messageBodyResponseMutableLiveData;
    }

}
