package com.meetkparmar.ichatapp.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.meetkparmar.ichatapp.R;
import com.meetkparmar.ichatapp.adapter.UserDetailsAdapter;
import com.meetkparmar.ichatapp.models.UserDetails;
import com.meetkparmar.ichatapp.models.Users;
import com.meetkparmar.ichatapp.viewmodels.HomePageActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomePageActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private RecyclerView rvUserList;
    private HomePageActivityViewModel viewModel;
    private List<Users> users = new ArrayList<>();
    UserDetailsAdapter adapter;
    private Observer<UserDetails> UserDetailsObserver = new Observer<UserDetails>() {
        @Override
        public void onChanged(UserDetails userDetails) {
            if (userDetails != null) {
                hideLoadingDialog();
                if (userDetails.success != 1) {
                    Toast.makeText(HomePageActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                } else {
                    users = userDetails.getUsers();
                    adapter.setData(users);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        viewModel = new ViewModelProvider(this).get(HomePageActivityViewModel.class);
        progressDialog = new ProgressDialog(this);
        rvUserList = findViewById(R.id.rv_user_list);
        initAdapter(users);

        showLoadingDialog("Loading...");
        viewModel.getUserDetails();
    }

    private void initAdapter(List<Users> usersList) {
        rvUserList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new UserDetailsAdapter(usersList);
        rvUserList.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.userDetails().observe(this, UserDetailsObserver);
    }

    private void showLoadingDialog(String message){
        if (!progressDialog.isShowing()) {
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    private void hideLoadingDialog(){
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }
}