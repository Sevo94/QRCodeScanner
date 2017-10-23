package com.example.sevak1994.qrcodescanner.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.sevak1994.qrcodescanner.Constants;
import com.example.sevak1994.qrcodescanner.FragmentManager;
import com.example.sevak1994.qrcodescanner.GlideWrapper;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.activities.HomeActivity;
import com.example.sevak1994.qrcodescanner.helper.SharedPreferenceHelper;
import com.example.sevak1994.qrcodescanner.interfaces.BackKeyListener;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 9/7/2017.
 */

public class SettingsFragment extends Fragment implements View.OnClickListener, BackKeyListener, GlideWrapper.GlideCallbacks {

    private View fragmentRootView;
    private HomeActivity activity;
    private LinearLayout contactInfo;
    private LinearLayout balanceInfo;
    private LinearLayout companyInfo;

    private CircleImageView profileImage;

    public SettingsFragment() {
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

        if (!SharedPreferenceHelper.loadStringFromPreference(Constants.PROFILE_PATH).isEmpty()) {
            GlideWrapper glideWrapper = new GlideWrapper(this, true);
            glideWrapper.loadImageWithGlide(getContext(), SharedPreferenceHelper.loadStringFromPreference(Constants.PROFILE_PATH));
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
    public void onResourceReady(Bitmap resource) {
        profileImage.setImageBitmap(resource);
    }

    @Override
    public void onBackPressed() {

    }
}
