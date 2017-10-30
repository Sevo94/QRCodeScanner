package com.example.sevak1994.qrcodescanner.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.sevak1994.qrcodescanner.BissApplication;
import com.example.sevak1994.qrcodescanner.Constants;
import com.example.sevak1994.qrcodescanner.FragmentManager;
import com.example.sevak1994.qrcodescanner.GlideWrapper;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.activities.HomeActivity;
import com.example.sevak1994.qrcodescanner.helper.SharedPreferenceHelper;
import com.example.sevak1994.qrcodescanner.interfaces.BackKeyListener;
import com.example.sevak1994.qrcodescanner.interfaces.BottomNavigationItemSelect;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 9/7/2017.
 */

public class SettingsFragment extends Fragment implements View.OnClickListener, BackKeyListener {

    private View fragmentRootView;
    private HomeActivity activity;
    private LinearLayout contactInfo;
    private LinearLayout balanceInfo;
    private LinearLayout companyInfo;

    private CircleImageView profileImage;
    private BottomNavigationItemSelect bottomNavigationItemSelect;

    public SettingsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bottomNavigationItemSelect = (HomeActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentRootView = inflater.inflate(R.layout.fragment_settings, container, false);

        return fragmentRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = (HomeActivity) getActivity();

        activity.inNormalMode(false);
        activity.setToolbarTitle(getResources().getString(R.string.title_settings));
        activity.setBackKeyListener(this);
        initFragmentUI();

        GlideWrapper glideWrapper = new GlideWrapper(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                profileImage.setImageBitmap(resource);
            }
        }, true);

        if (!SharedPreferenceHelper.loadStringFromPreference(Constants.PROFILE_PATH).isEmpty()) {
            glideWrapper.loadImageWithGlide(getContext(), SharedPreferenceHelper.loadStringFromPreference(Constants.PROFILE_PATH));
        } else {
            glideWrapper.loadImageWithGlide(getContext(), Constants.PROFILE_PATH + "/test.jpg");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contact_info:
                FragmentManager.getInstance().startContactInfoFragment(activity, R.anim.enter_from_left, R.anim.exit_to_right);
                break;
            case R.id.company_info:
                FragmentManager.getInstance().startCompanyInfoFragment(activity, R.anim.enter_from_left, R.anim.exit_to_right);
                break;
            case R.id.balance_info:
                FragmentManager.getInstance().startBalanceFragment(activity, R.anim.enter_from_left, R.anim.exit_to_right);
                break;
        }
    }

    private void initFragmentUI() {
        contactInfo = fragmentRootView.findViewById(R.id.contact_info);
        balanceInfo = fragmentRootView.findViewById(R.id.balance_info);
        companyInfo = fragmentRootView.findViewById(R.id.company_info);
        profileImage = fragmentRootView.findViewById(R.id.profile_image);
        setOnClickListener();
    }

    private void setOnClickListener() {
        contactInfo.setOnClickListener(this);
        balanceInfo.setOnClickListener(this);
        companyInfo.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        bottomNavigationItemSelect.selectNavigationItem(R.id.navigation_home);
    }
}
