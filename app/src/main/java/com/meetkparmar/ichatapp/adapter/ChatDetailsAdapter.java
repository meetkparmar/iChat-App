package com.meetkparmar.ichatapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meetkparmar.ichatapp.R;
import com.meetkparmar.ichatapp.models.Chats;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatDetailsAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private Context context;
    private List<Chats> chatsList;
    private int userId;

    public ChatDetailsAdapter(Context context, List<Chats> chatsList, int userId) {
        this.context = context;
        this.chatsList = chatsList;
        this.userId = userId;
    }

    @Override
    public int getItemViewType(int position) {
        Chats chats = (Chats) chatsList.get(position);

        if (chats.getId() != userId) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_mesage_sent, parent, false);
            return new SentChatViewHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceiveChatViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chats chats = (Chats) chatsList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentChatViewHolder) holder).bind(chats);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceiveChatViewHolder) holder).bind(chats);
        }
    }

    @Override
    public int getItemCount() {
        return chatsList.size();
    }
}
