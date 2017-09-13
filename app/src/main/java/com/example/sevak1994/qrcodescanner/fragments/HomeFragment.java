package com.example.sevak1994.qrcodescanner.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sevak1994.qrcodescanner.BitmapUtils;
import com.example.sevak1994.qrcodescanner.MainActivity;
import com.example.sevak1994.qrcodescanner.R;

/**
 * Created by Admin on 9/7/2017.
 */

public class HomeFragment extends Fragment {

    private View fragmentRootView;
    private MainActivity activity;

    private LinearLayout mobileLayout;
    private LinearLayout emailLayout;
    private LinearLayout addressLayout;

    private ImageView blurProfilePictrue;
    private Bitmap bitmap;

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
        activity = (MainActivity) getActivity();

        activity.setToolbarTitle(getResources().getString(R.string.title_home));
        initFragmentUi();

//        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile);    //scale 0.2f radius = 10;
//        scaleBitmap(0.2f);
//
//        blurProfilePictrue.setImageBitmap(BitmapUtils.fastblur(bitmap, 0.2f, 5));
    }

    private void scaleBitmap(float scale) {
        int width = Math.round(bitmap.getWidth() * scale);
        int height = Math.round(bitmap.getHeight() * scale);
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
    }

    private void initFragmentUi() {
        mobileLayout = fragmentRootView.findViewById(R.id.mobile);
        emailLayout = fragmentRootView.findViewById(R.id.email);
        addressLayout = fragmentRootView.findViewById(R.id.address);
        blurProfilePictrue = fragmentRootView.findViewById(R.id.blur_pro_pic);

        mobileLayout.findViewById(R.id.icon_image_view).setBackgroundResource(R.drawable.ic_call_white_24dp);
        emailLayout.findViewById(R.id.icon_image_view).setBackgroundResource(R.drawable.ic_chat_bubble_white_24dp);
        addressLayout.findViewById(R.id.icon_image_view).setBackgroundResource(R.drawable.ic_location_on_white_24dp);
    }
}
