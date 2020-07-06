package com.meetkparmar.ichatapp.adapter;

import android.view.View;
import android.widget.TextView;

import com.meetkparmar.ichatapp.R;
import com.meetkparmar.ichatapp.models.Chats;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SentChatViewHolder extends RecyclerView.ViewHolder {

    View view;
    TextView tv_send_receive;
    TextView tv_send_receive_time;

    public SentChatViewHolder(@NonNull View view) {
        super(view);
        this.view = view;
        tv_send_receive = view.findViewById(R.id.tv_message_send);
        tv_send_receive_time = view.findViewById(R.id.tv_message_send_time);
    }

    public void bind(Chats chats){
        tv_send_receive.setText(chats.getMessage());
        tv_send_receive_time.setText(chats.getUpdated_at());
    }
}
