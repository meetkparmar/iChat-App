package com.meetkparmar.ichatapp.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.meetkparmar.ichatapp.ItemClicked;
import com.meetkparmar.ichatapp.R;
import com.meetkparmar.ichatapp.adapter.UserDetailsAdapter;
import com.meetkparmar.ichatapp.models.UserDetails;
import com.meetkparmar.ichatapp.models.Users;
import com.meetkparmar.ichatapp.viewmodels.HomePageActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomePageActivity extends AppCompatActivity implements ItemClicked {

    private ProgressDialog progressDialog;
    private RecyclerView rvUserList;
    private HomePageActivityViewModel viewModel;
    private List<Users> users = new ArrayList<>();
    private Button button;
    UserDetailsAdapter adapter;
    private Observer<UserDetails> UserDetailsObserver = new Observer<UserDetails>() {
        @Override
        public void onChanged(UserDetails userDetails) {
            if (userDetails != null) {
                hideLoadingDialog();
                if (userDetails.success != 1) {
                    openDialogBox();
                    Toast.makeText(HomePageActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                } else {
                    users = userDetails.getUsers();
                    adapter.setData(users);
                }
            }
        }
    };

    private void openDialogBox() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomePageActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_box, null);
        alertDialog.setView(customLayout);
        final AlertDialog alert = alertDialog.create();
        alert.show();
        button = customLayout.findViewById(R.id.btn_refresh);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(HomePageActivity.this);
                showLoadingDialog("Loading...");
                viewModel.getUserDetails();
                alert.dismiss();
            }
        });
    }

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
        adapter = new UserDetailsAdapter(usersList, this);
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

    @Override
    public void onClickedListener(int position) {
        String name = users.get(position).getName();
        String image = users.get(position).getImage();
        int id = users.get(position).getId();
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("image", image);
        startActivity(intent);
    }
}