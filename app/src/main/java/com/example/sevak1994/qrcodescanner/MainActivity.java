package com.example.sevak1994.qrcodescanner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private FragmentManager fragmentManager;
    private FragmentActivity fragmentActivity;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (!item.isChecked()) {
                        mTextMessage.setText(R.string.title_home);
                        fragmentManager.startHomeFragment(fragmentActivity);
                    }
                    return true;
                case R.id.navigation_contacts:
                    if (!item.isChecked()) {
                        mTextMessage.setText(R.string.title_contacts);
                        fragmentManager.startContactsFragment(fragmentActivity);
                    }
                    return true;
                case R.id.navigation_qr_scanner:
                    if (!item.isChecked()) {
                        mTextMessage.setText(R.string.title_qr_scanner);
                        fragmentManager.startQRScannerFragment(fragmentActivity);
                    }
                    return true;
                case R.id.navigation_qr_code:
                    if (!item.isChecked()) {
                        mTextMessage.setText(R.string.title_qr_code);
                        fragmentManager.startQRCodeFragment(fragmentActivity);
                    }
                    return true;
                case R.id.navigation_settings:
                    if (!item.isChecked()) {
                        mTextMessage.setText(R.string.title_settings);
                        fragmentManager.startSettigsFragment(fragmentActivity);
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentActivity = this;
        fragmentManager = FragmentManager.getInstance();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager.getInstance().startHomeFragment(this);
    }
}
