package com.meetkparmar.ichatapp.models;

import java.util.List;

public class UserDetails {
    public int success;
    public String message;
    private List<Users> users;

    public UserDetails() {
    }

    public UserDetails(int success, String message) {
        this.success = success;
        this.message = message;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public List<Users> getUsers() {
        return users;
    }

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
