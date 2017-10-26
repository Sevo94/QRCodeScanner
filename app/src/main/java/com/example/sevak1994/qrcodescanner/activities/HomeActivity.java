package com.example.sevak1994.qrcodescanner.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sevak1994.qrcodescanner.FragmentManager;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.helper.BottomNavigationViewHelper;
import com.example.sevak1994.qrcodescanner.interfaces.ActionModeListener;
import com.example.sevak1994.qrcodescanner.interfaces.BackKeyListener;
import com.example.sevak1994.qrcodescanner.interfaces.BottomNavigationItemSelect;

import static com.example.sevak1994.qrcodescanner.R.id.navigation_home;

public class HomeActivity extends AppCompatActivity implements ActionModeListener, BottomNavigationItemSelect {

    private FragmentManager fragmentManager;
    private FragmentActivity fragmentActivity;
    private BottomNavigationView navigation;
    private Toolbar toolbar;

    private BackKeyListener backKeyListener;

    private boolean isInActionMode;

    private TextView counterTV;
    private int selectedItemsCount;

    private ActionModeListener actionModeListener = this;

    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            navItemClicked(item.getItemId());

            switch (item.getItemId()) {
                case navigation_home:
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
                        checkForCameraPermission();
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0) {
                    fragmentManager.startQRScannerFragment(fragmentActivity);
                } else {
                    //TODO handle permission denial case
                }
                break;
            }
        }
    }

    private void checkForCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                //TODO handle never ask again case
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
            }
        } else {
            fragmentManager.startQRScannerFragment(fragmentActivity);
        }
    }

    private void navItemClicked(int Id) {
        for (int i = 0; i < navigation.getMenu().size(); i++) {
            if (navigation.getMenu().getItem(i).getItemId() == R.id.navigation_qr_code
                    || navigation.getMenu().getItem(i).getItemId() == R.id.navigation_qr_scanner) {
                continue;
            }

            MenuItem menuItem = navigation.getMenu().getItem(i);
            boolean isChecked = menuItem.getItemId() == Id;

            if (isChecked) {
                navigation.getMenu().findItem(menuItem.getItemId()).getIcon().setColorFilter(getResources().getColor(R.color.active_icon), PorterDuff.Mode.SRC_IN);
            } else {
                navigation.getMenu().findItem(menuItem.getItemId()).getIcon().setColorFilter(getResources().getColor(R.color.inactive_icon), PorterDuff.Mode.SRC_IN);
            }
        }
    }

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

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            Drawable drawable = toolbar.getNavigationIcon();
            if (drawable != null) {
                drawable.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
            }
        }

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        BottomNavigationViewHelper.changeCenterIconSize(navigation, getApplicationContext());
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        selectHomeItem();
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

    //TODO make proper functionality for back button press
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
    public void inNormalMode(boolean deletedItems) {
        toolbar.getMenu().clear();
        isInActionMode = false;
        counterTV.setText(getResources().getString(R.string.title_contacts));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        if (deletedItems && selectedItemsCount > 0) {
            if (selectedItemsCount == 1) {
                Toast.makeText(this, selectedItemsCount + " item deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, selectedItemsCount + " items deleted", Toast.LENGTH_SHORT).show();
            }
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

    @Override
    public void selectHomeItem() {
        navigation.setSelectedItemId(R.id.navigation_home);
    }
}


