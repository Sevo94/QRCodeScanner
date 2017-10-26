package com.example.sevak1994.qrcodescanner.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 10/25/2017.
 */

public class UserModel {

    @SerializedName("_id")
    @Expose
    private String _ID;

    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("__v")
    @Expose
    private int __v;

    @SerializedName("tokens")
    @Expose
    private List<String> tokens;


    public String get_ID() {
        return _ID;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int get__v() {
        return __v;
    }

    public List<String> getTokens() {
        return tokens;
    }
}
