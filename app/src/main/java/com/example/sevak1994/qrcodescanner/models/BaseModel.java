package com.example.sevak1994.qrcodescanner.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 10/25/2017.
 */

public class BaseModel {

    @SerializedName("err")
    @Expose
    private boolean error;

    @SerializedName("errMsg")
    @Expose
    private String errorMsg;

    public boolean isError() {
        return error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
