package com.meetkparmar.ichatapp.viewmodels;

import android.app.Application;

import com.meetkparmar.ichatapp.RetrofitManager;
import com.meetkparmar.ichatapp.models.UserDetails;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class HomePageActivityViewModel extends AndroidViewModel {
    private Application application;
    private RetrofitManager retrofitManager = new RetrofitManager();
    private MediatorLiveData<UserDetails> userDetailsResponseMediatorLiveData = new MediatorLiveData<>();

    public HomePageActivityViewModel(Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<UserDetails> userDetails(){
        return userDetailsResponseMediatorLiveData;
    }

    public void getUserDetails() {
        userDetailsResponseMediatorLiveData.addSource(retrofitManager.getUserDetails(), new Observer<UserDetails>() {
            @Override
            public void onChanged(UserDetails userDetails) {
                if (userDetails != null) {
                    userDetailsResponseMediatorLiveData.setValue(userDetails);
                }
            }
        }) ;
    }
}
