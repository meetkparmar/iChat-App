package com.meetkparmar.ichatapp.viewmodels;

import android.app.Application;

import com.meetkparmar.ichatapp.RetrofitManager;
import com.meetkparmar.ichatapp.models.MessageBody;
import com.meetkparmar.ichatapp.models.MessageBodyResponse;
import com.meetkparmar.ichatapp.models.UserChatDetails;
import com.meetkparmar.ichatapp.models.UserDetails;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class ChatActivityViewModel extends AndroidViewModel {

    private Application application;
    private RetrofitManager retrofitManager = new RetrofitManager();
    private MediatorLiveData<UserChatDetails> userChatDetailsMediatorLiveData = new MediatorLiveData<>();
    private MediatorLiveData<MessageBodyResponse> messageBodyResponseMediatorLiveData = new MediatorLiveData<>();

    public ChatActivityViewModel(Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<UserChatDetails> userChatDetails(){
        return userChatDetailsMediatorLiveData;
    }

    public void getUserChatDetails(int user) {
        userChatDetailsMediatorLiveData.addSource(retrofitManager.getUserChatDetails(user), new Observer<UserChatDetails>() {
            @Override
            public void onChanged(UserChatDetails userChatDetails) {
                if (userChatDetails != null) {
                    userChatDetailsMediatorLiveData.setValue(userChatDetails);
                }
            }
        }) ;
    }

    public LiveData<MessageBodyResponse> sentMessage(){
        return messageBodyResponseMediatorLiveData;
    }

    public void sendMessage(MessageBody messageBody){
        messageBodyResponseMediatorLiveData.addSource(retrofitManager.sendMessage(messageBody), new Observer<MessageBodyResponse>() {
            @Override
            public void onChanged(MessageBodyResponse messageBodyResponse) {
                if (messageBodyResponse != null){
                    messageBodyResponseMediatorLiveData.setValue(messageBodyResponse);
                }
            }
        });
    }


}
