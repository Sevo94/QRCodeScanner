package com.example.sevak1994.qrcodescanner.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.sevak1994.qrcodescanner.FragmentManager;
import com.example.sevak1994.qrcodescanner.activities.HomeActivity;
import com.example.sevak1994.qrcodescanner.R;

/**
 * Created by Admin on 9/7/2017.
 */

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private View fragmentRootView;
    private HomeActivity activity;
    private LinearLayout contactInfo;

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
        initFragmentUI();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contact_info:
                FragmentManager.getInstance().startContactInfoFragment(activity, R.anim.enter_from_left, R.anim.exit_to_right);
                break;
        }
    }

    private void initFragmentUI() {
        contactInfo = fragmentRootView.findViewById(R.id.contact_info);
        setOnClickListener();
    }

    private void setOnClickListener() {
        contactInfo.setOnClickListener(this);
    }
}
