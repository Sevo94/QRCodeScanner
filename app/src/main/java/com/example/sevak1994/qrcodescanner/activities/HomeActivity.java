package com.example.sevak1994.qrcodescanner.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.example.sevak1994.qrcodescanner.FragmentManager;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.helper.BottomNavigationViewHelper;
import com.example.sevak1994.qrcodescanner.interfaces.BackKeyListener;

public class HomeActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentActivity fragmentActivity;
    private BottomNavigationView navigation;
    private Toolbar toolbar;

    private BackKeyListener backKeyListener;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (!item.isChecked()) {
                        fragmentManager.startHomeFragment(fragmentActivity);
                    }
                    return true;
                case R.id.navigation_contacts:
                    if (!item.isChecked()) {
                        fragmentManager.startContactsFragment(fragmentActivity);
                    }
                    return true;
                case R.id.navigation_qr_scanner:
                    if (!item.isChecked()) {
                        fragmentManager.startQRScannerFragment(fragmentActivity);
                    }
                    return true;
                case R.id.navigation_qr_code:
                    if (!item.isChecked()) {
                        fragmentManager.startQRCodeFragment(fragmentActivity);
                    }
                    return true;
                case R.id.navigation_settings:
                    if (!item.isChecked()) {
                        fragmentManager.startSettingsFragment(fragmentActivity);
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fabric.with(this, new Crashlytics());

        setContentView(R.layout.activity_home);

        fragmentActivity = this;
        fragmentManager = FragmentManager.getInstance();
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.title_home));
        }

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        BottomNavigationViewHelper.changeCenterIconSize(navigation, getApplicationContext());
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(null);

        FragmentManager.getInstance().startHomeFragment(this);
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    public void setBackKeyListener(BackKeyListener backKeyListener) {
        this.backKeyListener = backKeyListener;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            if (backKeyListener != null) {
                backKeyListener.onBackPressed();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (backKeyListener != null) {
            backKeyListener.onBackPressed();
        }
    }
}
