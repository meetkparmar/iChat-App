package com.meetkparmar.ichatapp.adapter;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meetkparmar.ichatapp.CircleTransform;
import com.meetkparmar.ichatapp.ItemClicked;
import com.meetkparmar.ichatapp.R;
import com.meetkparmar.ichatapp.models.Users;
import com.squareup.picasso.Picasso;

import androidx.recyclerview.widget.RecyclerView;

public class UserDetailsViewHolder extends RecyclerView.ViewHolder {

    View view;
    TextView name;
    ImageView image;

    public UserDetailsViewHolder(View view) {
        super(view);
        this.view = view;
        name = view.findViewById(R.id.tv_user_name);
        image = view.findViewById(R.id.iv_user_image);
    }

    public void bind(Users users, final Integer position, final ItemClicked itemClickedListener) {
        name.setText(users.getName());
        Picasso.get().load(Uri.parse(users.getImage())).centerInside().fit().transform(new CircleTransform()).into(image);
        this.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickedListener.onClickedListener(position);
            }
        });
    }
}
