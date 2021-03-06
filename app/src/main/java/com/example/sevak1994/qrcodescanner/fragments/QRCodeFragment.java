package com.example.sevak1994.qrcodescanner.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sevak1994.qrcodescanner.FragmentManager;
import com.example.sevak1994.qrcodescanner.activities.HomeActivity;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.interfaces.BackKeyListener;
import com.example.sevak1994.qrcodescanner.interfaces.BottomNavigationItemSelect;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * Created by Admin on 9/7/2017.
 */

public class QRCodeFragment extends Fragment implements BackKeyListener {

    private ImageView qrCodeIV;
    private HomeActivity activity;
    private BottomNavigationItemSelect bottomNavigationItemSelect;

    public QRCodeFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bottomNavigationItemSelect = (HomeActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentRootView = inflater.inflate(R.layout.fragment_qrcode, container, false);
        qrCodeIV = fragmentRootView.findViewById(R.id.qr_code);

        return fragmentRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (HomeActivity) getActivity();
        activity.inNormalMode(false);
        activity.setToolbarTitle(getResources().getString(R.string.title_qr_code));

        activity.setBackKeyListener(this);

        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = TextToImageEncode("Hi my name is Sevag");

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        qrCodeIV.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();

    }

    private Bitmap TextToImageEncode(String textValue) {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    textValue,
                    BarcodeFormat.QR_CODE,
                    650, 650, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {
            return null;
        } catch (WriterException writeException) {
            return null;
        }

        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        return barcodeEncoder.createBitmap(bitMatrix);
    }

    @Override
    public void onBackPressed() {
        bottomNavigationItemSelect.selectNavigationItem(R.id.navigation_home);
    }
}
