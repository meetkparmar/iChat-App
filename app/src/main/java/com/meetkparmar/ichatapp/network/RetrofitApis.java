package com.meetkparmar.ichatapp.network;

import com.meetkparmar.ichatapp.models.MessageBody;
import com.meetkparmar.ichatapp.models.MessageBodyResponse;
import com.meetkparmar.ichatapp.models.UserChatDetails;
import com.meetkparmar.ichatapp.models.UserDetails;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitApis {

    @GET("/api/v1/users/get")
    Call<UserDetails> getUserDetails();

    @GET("api/v1/users/{userId}/chats")
    Call<UserChatDetails> getUserChats(
            @Path("userId") int user
    );

    @POST("api/v1/users/chat")
    Call<MessageBodyResponse> sendMessage(@Body MessageBody messageBody);
}
