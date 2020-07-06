package com.meetkparmar.ichatapp.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.meetkparmar.ichatapp.CircleTransform;
import com.meetkparmar.ichatapp.R;
import com.meetkparmar.ichatapp.models.MessageBody;
import com.meetkparmar.ichatapp.models.MessageBodyResponse;
import com.meetkparmar.ichatapp.models.UserChatDetails;
import com.meetkparmar.ichatapp.viewmodels.ChatActivityViewModel;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ChatActivity extends AppCompatActivity {

    private int userId;
    private String name;
    private String image;
    private TextView userName;
    private ImageView icon;
    private ImageView back;
    private EditText etChatBox;
    private ImageView btnSend;
    private Button button;
    private MessageBody messageBody;
    private ChatActivityViewModel viewModel;
    private ProgressDialog progressDialog;

    private Observer<UserChatDetails> UserChatDetailsObserver = new Observer<UserChatDetails>() {
        @Override
        public void onChanged(UserChatDetails userChatDetails) {
            if (userChatDetails != null) {
                hideLoadingDialog();
                if (userChatDetails.success != 1) {
                    openDialogBox();
                } else {

                }
            }
        }
    };

    private Observer<MessageBodyResponse> sentMessageResponseObserver = new Observer<MessageBodyResponse>() {
        @Override
        public void onChanged(MessageBodyResponse messageBodyResponse) {
            if (messageBodyResponse != null) {
                hideLoadingDialog();
                if (messageBodyResponse.success != 1) {
                    Toast.makeText(ChatActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        }
    };

    private void openDialogBox() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ChatActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_box, null);
        alertDialog.setView(customLayout);
        final AlertDialog alert = alertDialog.create();
        alert.show();
        button = customLayout.findViewById(R.id.btn_refresh);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(ChatActivity.this);
                showLoadingDialog("Loading...");
                viewModel.getUserChatDetails(userId);
                alert.dismiss();
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        View view = getSupportActionBar().getCustomView();

        userName = view.findViewById(R.id.tv_name);
        icon = view.findViewById(R.id.iv_image);
        back = view.findViewById(R.id.btn_back);

        etChatBox = view.findViewById(R.id.et_chatbox);
        btnSend = view.findViewById(R.id.button_send);

        Intent intent = getIntent();
        userId = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        image = intent.getStringExtra("image");

        userName.setText(name);
        Picasso.get().load(Uri.parse(image)).centerInside().fit().transform(new CircleTransform()).into(icon);

        viewModel = new ViewModelProvider(this).get(ChatActivityViewModel.class);
        progressDialog = new ProgressDialog(this);
        showLoadingDialog("Loading...");
        viewModel.getUserChatDetails(userId);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message;
                message = etChatBox.getText().toString();
                messageBody = new MessageBody();
                messageBody.setMessage(message);
                messageBody.setId(userId);
                viewModel.sendMessage(messageBody);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.userChatDetails().observe(this, UserChatDetailsObserver);
        viewModel.sentMessage().observe(this, sentMessageResponseObserver);
    }

    private void showLoadingDialog(String message) {
        if (!progressDialog.isShowing()) {
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    private void hideLoadingDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }
}
