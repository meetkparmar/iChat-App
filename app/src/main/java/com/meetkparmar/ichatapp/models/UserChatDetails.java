package com.meetkparmar.ichatapp.models;

import java.util.List;

public class UserChatDetails {

    public int success;
    public String message;
    private Users user;
    private List<Chats> chats;

    public UserChatDetails(int success, String message) {
        this.success = success;
        this.message = message;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Chats> getChats() {
        return chats;
    }

    public void setChats(List<Chats> chats) {
        this.chats = chats;
    }
}
