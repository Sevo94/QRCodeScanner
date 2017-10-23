package com.example.sevak1994.qrcodescanner;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

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
        if (resizeBitmap) {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .dontAnimate()
                    .override(300, 200)
                    .placeholder(R.drawable.default_photo)
                    .into(simpleTarget);
        } else {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .dontAnimate()
                    .placeholder(R.drawable.default_photo)
                    .into(simpleTarget);
        }
    }
}
