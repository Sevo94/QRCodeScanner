package com.example.sevak1994.qrcodescanner.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.sevak1994.qrcodescanner.AWSUtil;
import com.example.sevak1994.qrcodescanner.Constants;
import com.example.sevak1994.qrcodescanner.GlideWrapper;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.UserInfo;
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
    private HomeActivity activity;

    private ImageView blurProfilePicture;
    private CircleImageView companyLogo;
    private CircleImageView profileImage;
    private GlideWrapper glideWrapper;

    private TextView emailTV;

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
        activity.setBackKeyListener(this);
        initFragmentUi();

        activity.setBackKeyListener(this);

        glideWrapper = new GlideWrapper(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(final Bitmap resource, GlideAnimation glideAnimation) {
                final Handler handler = new Handler();

                profileImage.setImageBitmap(resource);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = BitmapUtils.fastblur(resource, 0.5f, 5); //0.2   //5
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                blurProfilePicture.setImageBitmap(bitmap);
                            }
                        });
                    }
                }).start();
            }
        });

        if (!SharedPreferenceHelper.loadBooleanFromPreference(Constants.USER_LOGIN, false)) {
            downloadProfilePicture();
        } else {
            glideWrapper.loadImageWithGlide(getContext(), Constants.PROFILE_PATH + "/test.jpg");
        }
    }

    private void initFragmentUi() {
        blurProfilePicture = fragmentRootView.findViewById(R.id.blur_pro_pic);
        companyLogo = fragmentRootView.findViewById(R.id.company_logo);
        profileImage = fragmentRootView.findViewById(R.id.profile_image);
        emailTV = fragmentRootView.findViewById(R.id.email_tv);

        emailTV.setText(UserInfo.getInstance().getEmail());

        companyLogo.setColorFilter(getActivity().getResources().getColor(R.color.toolbar_text_color));
    }

    private void downloadProfilePicture() {
        TransferUtility transferUtility = AWSUtil.getTransferUtility(getContext());
        File file = new File(Constants.PROFILE_PATH, "test.jpg");

        TransferObserver observer = transferUtility.download(
                Constants.BUCKET_NAME,
                UserInfo.getInstance().getUserID(),
                file
        );

        observer.setTransferListener(this);
    }

    @Override
    public void onStateChanged(int id, TransferState state) {
        Log.d("Sevag", "onStateChanged");
        if (state.name().equals("COMPLETED")) {
            glideWrapper.loadImageWithGlide(getContext(), Constants.PROFILE_PATH + "/test.jpg");
            SharedPreferenceHelper.storeBooleanInPreference(Constants.USER_LOGIN, true);
            SharedPreferenceHelper.storeStringInPreference(Constants.PROFILE_PATH + "/test.jpg", null);
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
        getActivity().finish();
    }
}
