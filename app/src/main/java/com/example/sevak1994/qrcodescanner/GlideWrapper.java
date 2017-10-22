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

    private GlideCallbacks glideCallbacks;

    private SimpleTarget simpleTarget = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(final Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            if (glideCallbacks != null) {
                glideCallbacks.onResourceReady(resource);
            }
        }
    };

    public GlideWrapper(GlideCallbacks glideCallbacks) {
        this.glideCallbacks = glideCallbacks;
    }

    public void loadImageWithGlide(Context context, String url) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .dontAnimate()
                .placeholder(R.drawable.default_photo)
                .into(simpleTarget);
    }

    public interface GlideCallbacks {
        void onResourceReady(Bitmap resource);
    }
}
