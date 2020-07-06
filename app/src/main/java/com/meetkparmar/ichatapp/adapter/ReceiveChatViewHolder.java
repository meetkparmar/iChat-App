package com.meetkparmar.ichatapp.adapter;

import android.view.View;
import android.widget.TextView;

import com.meetkparmar.ichatapp.R;
import com.meetkparmar.ichatapp.models.Chats;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReceiveChatViewHolder extends RecyclerView.ViewHolder {

    View view;
    TextView tv_message_receive;
    TextView tv_message_receive_time;

    public ReceiveChatViewHolder(@NonNull View view) {
        super(view);
        this.view = view;
        tv_message_receive = view.findViewById(R.id.tv_message_receive);
        tv_message_receive_time = view.findViewById(R.id.tv_message_receive_time);
    }

    public void bind(Chats chats) {
        tv_message_receive.setText(chats.getMessage());
        tv_message_receive_time.setText(chats.getUpdated_at());
    }
}
