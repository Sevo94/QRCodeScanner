package com.example.sevak1994.qrcodescanner.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sevak1994.qrcodescanner.MainActivity;
import com.example.sevak1994.qrcodescanner.R;

/**
 * Created by Admin on 9/7/2017.
 */

public class SettingsFragment extends Fragment {

    private View fragmentRootView;
    private MainActivity activity;

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
        activity = (MainActivity) getActivity();

        activity.setToolbarTitle(getResources().getString(R.string.title_home));
    }
}
