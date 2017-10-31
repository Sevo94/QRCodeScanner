package com.example.sevak1994.qrcodescanner;

import android.content.Context;
import android.os.SystemClock;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.signature.StringSignature;

/**
 * Created by Sevak1994 on 10/22/2017.
 */

public class GlideWrapper {

    private boolean resizeBitmap;
    private SimpleTarget simpleTarget;

    public GlideWrapper(SimpleTarget simpleTarget, boolean resizeBitmap) {
        this.simpleTarget = simpleTarget;
        this.resizeBitmap = resizeBitmap;
    }

    public void loadImageWithGlide(Context context, String url) {
        if (context != null) {
            if (resizeBitmap) {
                Glide.with(context)
                        .load(url)
                        .asBitmap()
                        .dontAnimate()
                        .override(300, 200)
                        .signature(new StringSignature(String.valueOf(SystemClock.currentThreadTimeMillis())))
                        .placeholder(R.drawable.default_photo)
                        .into(simpleTarget);
            } else {
                Glide.with(context)
                        .load(url)
                        .asBitmap()
                        .dontAnimate()
                        .override(300, 200)
                        .signature(new StringSignature(String.valueOf(SystemClock.currentThreadTimeMillis())))
                        .placeholder(R.drawable.default_photo)
                        .into(simpleTarget);
            }
        } else {
            Glide.clear(simpleTarget);
        }
    }
}
