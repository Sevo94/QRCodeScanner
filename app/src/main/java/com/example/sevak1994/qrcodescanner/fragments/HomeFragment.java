package com.example.sevak1994.qrcodescanner.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sevak1994.qrcodescanner.Constants;
import com.example.sevak1994.qrcodescanner.GlideWrapper;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.activities.HomeActivity;
import com.example.sevak1994.qrcodescanner.helper.BitmapUtils;
import com.example.sevak1994.qrcodescanner.helper.SharedPreferenceHelper;
import com.example.sevak1994.qrcodescanner.interfaces.BackKeyListener;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 9/7/2017.
 */

public class HomeFragment extends Fragment implements BackKeyListener, GlideWrapper.GlideCallbacks {

    private View fragmentRootView;

    private ImageView blurProfilePicture;
    private CircleImageView companyLogo;
    private CircleImageView profileImage;

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

        GlideWrapper glideWrapper = new GlideWrapper(this);
        glideWrapper.loadImageWithGlide(getContext(), SharedPreferenceHelper.loadStringFromPreference(Constants.PROFILE_PATH));
    }

    @Override
    public void onResourceReady(final Bitmap resource) {
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

    private void initFragmentUi() {
        blurProfilePicture = fragmentRootView.findViewById(R.id.blur_pro_pic);
        companyLogo = fragmentRootView.findViewById(R.id.company_logo);
        profileImage = fragmentRootView.findViewById(R.id.profile_image);

        companyLogo.setColorFilter(getActivity().getResources().getColor(R.color.toolbar_text_color));
    }

    @Override
    public void onBackPressed() {

    }
}
