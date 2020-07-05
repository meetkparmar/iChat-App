package com.meetkparmar.ichatapp.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meetkparmar.ichatapp.CircleTransform;
import com.meetkparmar.ichatapp.R;
import com.meetkparmar.ichatapp.models.Users;
import com.squareup.picasso.Picasso;

import androidx.recyclerview.widget.RecyclerView;

public class UserDetailsViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    ImageView image;

    public UserDetailsViewHolder(View view) {
        super(view);
        name = view.findViewById(R.id.tv_user_name);
        image = view.findViewById(R.id.iv_user_image);
    }

    public void bind(Users users, Integer position) {
        name.setText(users.getName());
        Picasso.get().load(Uri.parse(users.getImage())).centerInside().fit().transform(new CircleTransform()).into(image);
    }
}
