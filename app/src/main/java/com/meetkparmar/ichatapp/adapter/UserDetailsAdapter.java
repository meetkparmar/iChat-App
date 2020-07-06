package com.meetkparmar.ichatapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meetkparmar.ichatapp.R;
import com.meetkparmar.ichatapp.models.Users;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsViewHolder> {

    public List<Users> data;
    public ItemClicked itemClickedListener;

    public void setData(List<Users> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public UserDetailsAdapter(List<Users> data, ItemClicked itemClickedListener) {
        this.data = data;
        this.itemClickedListener = itemClickedListener;
    }

    @NonNull
    @Override
    public UserDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_detail_item, parent, false);
        return new UserDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDetailsViewHolder holder, int position) {
        Users item = data.get(position);
        holder.bind(item, position, itemClickedListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
