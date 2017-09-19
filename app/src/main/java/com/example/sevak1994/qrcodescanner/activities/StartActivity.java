package com.example.sevak1994.qrcodescanner.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.sevak1994.qrcodescanner.BissApplication;
import com.example.sevak1994.qrcodescanner.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //((ImageView) findViewById(R.id.app_logo)).setColorFilter(getResources().getColor(R.color.first_page_color), PorterDuff.Mode.SRC_IN);

        long delay = 0;

        if (BissApplication.getInstance().isFirstTimeLaunch()) {
            delay = 2000;
            BissApplication.getInstance().setFirstTimeLaunch(false);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }, delay);
    }
}