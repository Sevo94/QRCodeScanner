package com.example.sevak1994.qrcodescanner.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.activities.HomeActivity;
import com.example.sevak1994.qrcodescanner.helper.BitmapUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 9/7/2017.
 */

public class HomeFragment extends Fragment {

    private View fragmentRootView;

    private ImageView blurProfilePicture;
    private CircleImageView companyLogo;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentRootView = inflater.inflate(R.layout.fragment_home, container, false);

        return fragmentRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomeActivity activity = (HomeActivity) getActivity();

        activity.inNormalMode(false);
        activity.setToolbarTitle(getResources().getString(R.string.title_home));
        initFragmentUi();

        SimpleTarget simpleTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(final Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                final Handler handler = new Handler();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = BitmapUtils.fastblur(resource, 0.2f, 5);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                blurProfilePicture.setImageBitmap(bitmap);
                            }
                        });
                    }
                }).start();
            }
        };

        Glide.with(activity)
                .load(R.drawable.profile)
                .asBitmap()
                .dontAnimate()
                .placeholder(R.drawable.default_photo)
                .into(simpleTarget);
    }

    private void initFragmentUi() {
        blurProfilePicture = fragmentRootView.findViewById(R.id.blur_pro_pic);
        companyLogo = fragmentRootView.findViewById(R.id.company_logo);

        companyLogo.setColorFilter(getActivity().getResources().getColor(R.color.toolbar_text_color));
    }
}
