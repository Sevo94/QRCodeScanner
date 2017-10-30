package com.example.sevak1994.qrcodescanner.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sevak1994.qrcodescanner.CustomZXIngScannerView;
import com.example.sevak1994.qrcodescanner.FragmentManager;
import com.example.sevak1994.qrcodescanner.activities.HomeActivity;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.interfaces.BackKeyListener;
import com.example.sevak1994.qrcodescanner.interfaces.BottomNavigationItemSelect;
import com.google.zxing.Result;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Admin on 9/7/2017.
 */

public class QRScannerFragment extends Fragment implements ZXingScannerView.ResultHandler, BackKeyListener {

    private View fragmentRootView;
    private HomeActivity activity;
    private ZXingScannerView scannerView;
    private LinearLayout scannerViewLayout;
    private CustomZXIngScannerView customZXingScannerView;
    private BottomNavigationItemSelect bottomNavigationItemSelect;

    public QRScannerFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bottomNavigationItemSelect = (HomeActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentRootView = inflater.inflate(R.layout.fragment_qrscanner, container, false);
        scannerViewLayout = fragmentRootView.findViewById(R.id.scanner_view_layout);

        return fragmentRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (HomeActivity) getActivity();
        activity.inNormalMode(false);
        activity.setToolbarTitle(getResources().getString(R.string.title_qr_scanner));

        activity.setBackKeyListener(this);

        customZXingScannerView = new CustomZXIngScannerView(getContext());
        customZXingScannerView.setBorderColor(getResources().getColor(R.color.white));

        scannerView = new ZXingScannerView(getContext()) {
            @Override
            protected IViewFinder createViewFinderView(Context context) {
                return customZXingScannerView;
            }
        };

        scannerViewLayout.addView(scannerView);
        scannerView.setResultHandler(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void onResume() {
        super.onResume();
        scannerView.startCamera(0);
    }

    @Override
    public void handleResult(Result result) {
        if (result != null) {
            Toast.makeText(activity, "Scanned " + result.getText(), Toast.LENGTH_SHORT).show();
            scannerView.resumeCameraPreview(this);
        }
    }

    @Override
    public void onBackPressed() {
        bottomNavigationItemSelect.selectNavigationItem(R.id.navigation_home);
    }
}
