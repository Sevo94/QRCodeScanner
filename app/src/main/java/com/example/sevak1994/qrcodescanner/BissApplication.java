package com.example.sevak1994.qrcodescanner;

import android.app.Application;

/**
 * Created by Sevak1994 on 9/16/2017.
 */

public class BissApplication extends Application {

    private static BissApplication instance;
    private boolean firstTimeLaunch = true;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BissApplication getInstance() {
        return instance;
    }

    public boolean isFirstTimeLaunch() {
        return firstTimeLaunch;
    }

    public void setFirstTimeLaunch(boolean firstTimeLaunch) {
        this.firstTimeLaunch = firstTimeLaunch;
    }
}
