package com.example.sevak1994.qrcodescanner.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.sevak1994.qrcodescanner.FragmentManager;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.helper.BottomNavigationViewHelper;
import com.example.sevak1994.qrcodescanner.interfaces.ActionModeListener;
import com.example.sevak1994.qrcodescanner.interfaces.BackKeyListener;

public class HomeActivity extends AppCompatActivity implements ActionModeListener {

    private FragmentManager fragmentManager;
    private FragmentActivity fragmentActivity;
    private BottomNavigationView navigation;
    private Toolbar toolbar;

    private BackKeyListener backKeyListener;

    private boolean isInActionMode;

    private TextView counterTV;
    private int selectedItemsCount;

    private ActionModeListener actionModeListener = this;

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
                        fragmentManager.startContactsFragment(fragmentActivity, actionModeListener);
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

    public boolean isInActionMode() {
        return isInActionMode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fabric.with(this, new Crashlytics());

        setContentView(R.layout.activity_home);

        counterTV = (TextView) findViewById(R.id.item_counter);

        fragmentActivity = this;
        fragmentManager = FragmentManager.getInstance();
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setTitle(getResources().getString(R.string.title_home));
//        }

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        BottomNavigationViewHelper.changeCenterIconSize(navigation, getApplicationContext());
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(null);

        FragmentManager.getInstance().startHomeFragment(this);
    }

    public void setToolbarTitle(String title) {
        counterTV.setVisibility(View.VISIBLE);
        counterTV.setText(title);
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

    @Override
    public void inActionMode() {
        toolbar.getMenu().clear();
        isInActionMode = true;
        toolbar.inflateMenu(R.menu.menu_action_mode);
        counterTV.setText(getResources().getString(R.string.items_selected));
        counterTV.setVisibility(View.VISIBLE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        selectedItemsCount = 0;
    }

    @Override
    public void inNormalMode() {
        toolbar.getMenu().clear();
        isInActionMode = false;
        counterTV.setText(getResources().getString(R.string.title_contacts));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        selectedItemsCount = 0;
    }

    @Override
    public void moreItemSelected(int position) {
        counterTV.setText(String.valueOf(++selectedItemsCount) + " " + getResources().getString(R.string.item_selected));
    }

    @Override
    public void lessItemSelected(int position) {
        counterTV.setText(String.valueOf(--selectedItemsCount) + " " + getResources().getString(R.string.item_selected));
    }
}
