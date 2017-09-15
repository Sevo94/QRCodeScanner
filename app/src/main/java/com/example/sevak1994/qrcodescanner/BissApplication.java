package com.example.sevak1994.qrcodescanner;

import android.app.Application;

/**
 * Created by Sevak1994 on 9/16/2017.
 */

public class BissApplication extends Application {

    private static BissApplication instance = new BissApplication();
    private boolean LaunchFirstTime = true;

    public static BissApplication getInstance() {
        return instance;
    }

    public boolean isLaunchFirstTime() {
        return LaunchFirstTime;
    }

    public void setLaunchFirstTime(boolean launchFirstTime) {
        LaunchFirstTime = launchFirstTime;
    }
}
