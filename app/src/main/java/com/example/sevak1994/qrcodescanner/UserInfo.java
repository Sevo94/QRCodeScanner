package com.example.sevak1994.qrcodescanner;

/**
 * Created by Admin on 11/3/2017.
 */

public class UserInfo {

    private static UserInfo instance;

    private String userID;
    private String email;

    private UserInfo() {
    }

    public static UserInfo getInstance() {
        if (instance == null) {
            synchronized (UserInfo.class) {
                if (instance == null) {
                    instance = new UserInfo();
                }
            }
        }
        return instance;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }
}
