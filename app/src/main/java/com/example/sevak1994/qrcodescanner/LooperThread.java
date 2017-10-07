package com.example.sevak1994.qrcodescanner;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Sevak1994 on 10/8/2017.
 */

public class LooperThread extends Thread {

    public Handler nonUIHandler;
    public Handler UIHandler;

    public LooperThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        Looper.prepare();

        UIHandler = new Handler(Looper.getMainLooper());
        nonUIHandler = new Handler(Looper.myLooper());

        Looper.loop();
    }
}
