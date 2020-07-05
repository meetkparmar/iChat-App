package com.meetkparmar.ichatapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meetkparmar.ichatapp.CircleTransform;
import com.meetkparmar.ichatapp.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {

    String name;
    String image;
    TextView userName;
    ImageView icon;
    ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        View view =getSupportActionBar().getCustomView();

        userName = view.findViewById(R.id.tv_user_name);
        icon = view.findViewById(R.id.iv_user_image);
        back = view.findViewById(R.id.btn_back);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        image = intent.getStringExtra("image");

        userName.setText(name);
        Picasso.get().load(Uri.parse(image)).centerInside().fit().transform(new CircleTransform()).into(icon);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
