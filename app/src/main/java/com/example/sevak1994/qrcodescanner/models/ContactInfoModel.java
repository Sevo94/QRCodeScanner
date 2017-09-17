package com.example.sevak1994.qrcodescanner.models;

/**
 * Created by Sevak1994 on 9/17/2017.
 */

public class ContactInfoModel {

    private String name;
    private String job;
    private int imageUrl;

    public ContactInfoModel(String name, String job, int imageUrl) {
        this.name = name;
        this.job = job;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public int getImageUrl() {
        return imageUrl;
    }
}
