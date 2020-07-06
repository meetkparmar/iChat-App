package com.meetkparmar.ichatapp.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meetkparmar.ichatapp.R;
import com.meetkparmar.ichatapp.models.Chats;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SentChatViewHolder extends RecyclerView.ViewHolder {

    View view;
    TextView tv_send_receive;
    TextView tv_send_receive_time;
    ImageView double_check;
    ImageView single_check;

    public SentChatViewHolder(@NonNull View view) {
        super(view);
        this.view = view;
        tv_send_receive = view.findViewById(R.id.tv_message_send);
        tv_send_receive_time = view.findViewById(R.id.tv_message_send_time);
        single_check = view.findViewById(R.id.iv_single_check);
        double_check = view.findViewById(R.id.iv_double_check);
    }

    public void bind(Chats chats) {
        tv_send_receive.setText(chats.getMessage());
        tv_send_receive_time.setText(getDate(chats.getUpdated_at()));
        if (chats.getCreated_at() != null) {
            single_check.setVisibility(View.GONE);
            double_check.setVisibility(View.VISIBLE);
        } else {
            single_check.setVisibility(View.VISIBLE);
            double_check.setVisibility(View.GONE);
        }
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
