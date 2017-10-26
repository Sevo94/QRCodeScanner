package com.example.sevak1994.qrcodescanner.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.TransferStateChangeListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.sevak1994.qrcodescanner.AWSUtil;
import com.example.sevak1994.qrcodescanner.Constants;
import com.example.sevak1994.qrcodescanner.GlideWrapper;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.activities.HomeActivity;
import com.example.sevak1994.qrcodescanner.helper.BitmapUtils;
import com.example.sevak1994.qrcodescanner.helper.SharedPreferenceHelper;
import com.example.sevak1994.qrcodescanner.interfaces.BackKeyListener;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 9/7/2017.
 */

public class HomeFragment extends Fragment implements BackKeyListener, TransferListener {

    private View fragmentRootView;

    private ImageView blurProfilePicture;
    private CircleImageView companyLogo;
    private CircleImageView profileImage;
    private GlideWrapper glideWrapper;

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
        activity.setBackKeyListener(this);
        initFragmentUi();

        glideWrapper = new GlideWrapper(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(final Bitmap resource, GlideAnimation glideAnimation) {
                final Handler handler = new Handler();

                profileImage.setImageBitmap(resource);
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
        }, false);
        //glideWrapper.loadImageWithGlide(getContext(), SharedPreferenceHelper.loadStringFromPreference(Constants.PROFILE_PATH));

        downloadImage();
    }

    private void initFragmentUi() {
        blurProfilePicture = fragmentRootView.findViewById(R.id.blur_pro_pic);
        companyLogo = fragmentRootView.findViewById(R.id.company_logo);
        profileImage = fragmentRootView.findViewById(R.id.profile_image);

        companyLogo.setColorFilter(getActivity().getResources().getColor(R.color.toolbar_text_color));
    }

    private void downloadImage() {
        TransferUtility transferUtility = AWSUtil.getTransferUtility(getContext());
        File file = new File("data/data/com.example.sevak1994.qrcodescanner", "test.jpg");

        TransferObserver observer = transferUtility.download(
                Constants.BUCKET_NAME,
                Constants.UPLOAD_KEY,
                file
        );

        observer.setTransferListener(this);
    }

    @Override
    public void onStateChanged(int id, TransferState state) {
        Log.d("Sevag", "onStateChanged");
        if (state.name().equals("COMPLETED")) {
            glideWrapper.loadImageWithGlide(getContext(), "data/data/com.example.sevak1994.qrcodescanner/test.jpg");
        }
    }

    @Override
    public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
        Log.d("Sevag", "onProgressChanged");
    }

    @Override
    public void onError(int id, Exception ex) {
        Log.d("Sevag", "error");
    }

    @Override
    public void onBackPressed() {

    }
}
