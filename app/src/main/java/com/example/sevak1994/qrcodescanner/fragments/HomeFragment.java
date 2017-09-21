package com.example.sevak1994.qrcodescanner.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.activities.HomeActivity;
import com.example.sevak1994.qrcodescanner.helper.BitmapUtils;

/**
 * Created by Admin on 9/7/2017.
 */

public class HomeFragment extends Fragment {

    private View fragmentRootView;
    private HomeActivity activity;

    private LinearLayout mobileLayout;
    private LinearLayout emailLayout;
    private LinearLayout addressLayout;

    private ImageView blurProfilePicture;
    private Bitmap bitmap;

    private ImageView miniProIV;
    private ImageView websiteIV;

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
        activity = (HomeActivity) getActivity();

        activity.inNormalMode(false);
        activity.setToolbarTitle(getResources().getString(R.string.title_home));
        initFragmentUi();

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile);
        blurProfilePicture.setImageBitmap(BitmapUtils.fastblur(bitmap, 0.04f, 5));
    }

    private void initFragmentUi() {
        mobileLayout = fragmentRootView.findViewById(R.id.mobile);
        emailLayout = fragmentRootView.findViewById(R.id.email);
        addressLayout = fragmentRootView.findViewById(R.id.address);
        blurProfilePicture = fragmentRootView.findViewById(R.id.blur_pro_pic);
        miniProIV = fragmentRootView.findViewById(R.id.mini_pro_iv);
        websiteIV = fragmentRootView.findViewById(R.id.website_iv);

        miniProIV.setColorFilter(getResources().getColor(R.color.white));
        websiteIV.setColorFilter(getResources().getColor(R.color.white));

        mobileLayout.findViewById(R.id.icon_image_view).setBackgroundResource(R.drawable.ic_call_white_24dp);
        emailLayout.findViewById(R.id.icon_image_view).setBackgroundResource(R.drawable.ic_chat_bubble_white_24dp);
        addressLayout.findViewById(R.id.icon_image_view).setBackgroundResource(R.drawable.ic_location_on_white_24dp);
    }
}
