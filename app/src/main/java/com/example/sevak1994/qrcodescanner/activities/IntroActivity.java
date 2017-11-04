package com.example.sevak1994.qrcodescanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.amazonaws.SDKGlobalConfiguration;
import com.example.sevak1994.qrcodescanner.BissApplication;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.helper.SharedPreferenceHelper;

import static com.example.sevak1994.qrcodescanner.Constants.USER_ID;


public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        System.setProperty(SDKGlobalConfiguration.ENFORCE_S3_SIGV4_SYSTEM_PROPERTY, "true");

        //TODO change preference key
        if (!SharedPreferenceHelper.loadStringFromPreference(USER_ID).isEmpty()) {

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        } else {
            long delay = 0;

            if (BissApplication.getInstance().isFirstTimeLaunch()) {
                delay = 2000;
                BissApplication.getInstance().setFirstTimeLaunch(false);
            }

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), AuthenticateActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }, delay);
        }
    }
}
