package com.example.sevak1994.qrcodescanner.helper;

import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * Created by Admin on 11/3/2017.
 */

public class CommonHelper {

    public static void shakeAnimation(View view) {
        android.view.animation.Animation shake = new TranslateAnimation(0, 8, 0, 0);
        shake.setInterpolator(new CycleInterpolator(5));
        shake.setDuration(600);
        view.startAnimation(shake);
    }
}
