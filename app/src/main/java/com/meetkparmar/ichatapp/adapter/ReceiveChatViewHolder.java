package com.meetkparmar.ichatapp.adapter;

import android.view.View;
import android.widget.TextView;

import com.meetkparmar.ichatapp.R;
import com.meetkparmar.ichatapp.models.Chats;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        tv_message_receive_time.setText(getDate(chats.getUpdated_at()));
    }

    private String getDate(String initialDate) {
        if (initialDate == null) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sourceDate = null;
        try {
            sourceDate = dateFormat.parse(initialDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return initialDate;
        }

        SimpleDateFormat targetFormat = new SimpleDateFormat("dd MMM");
        return targetFormat.format(sourceDate);
    }
}
