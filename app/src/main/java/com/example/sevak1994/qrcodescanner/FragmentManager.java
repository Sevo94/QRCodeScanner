package com.example.sevak1994.qrcodescanner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.example.sevak1994.qrcodescanner.fragments.ContactsFragment;
import com.example.sevak1994.qrcodescanner.fragments.HomeFragment;
import com.example.sevak1994.qrcodescanner.fragments.QRCodeFragment;
import com.example.sevak1994.qrcodescanner.fragments.QRScannerFragment;
import com.example.sevak1994.qrcodescanner.fragments.SettingsFragment;

/**
 * Created by Admin on 9/8/2017.
 */

public class FragmentManager {

    private static FragmentManager instance;

    private FragmentManager() {
    }

    static FragmentManager getInstance() {
        if (instance == null) {
            synchronized (FragmentManager.class) {
                if (instance == null) {
                    instance = new FragmentManager();
                }
            }
        }
        return instance;
    }

    public void startHomeFragment(FragmentActivity fragmentActivity) {
        HomeFragment homeFragment = new HomeFragment();
        startFragmentTransaction(fragmentActivity, homeFragment);

    }

    public void startContactsFragment(FragmentActivity fragmentActivity) {
        ContactsFragment contactsFragment = new ContactsFragment();
        startFragmentTransaction(fragmentActivity, contactsFragment);

    }

    public void startQRScannerFragment(FragmentActivity fragmentActivity) {
        QRScannerFragment qrScannerFragment = new QRScannerFragment();
        startFragmentTransaction(fragmentActivity, qrScannerFragment);

    }

    public void startQRCodeFragment(FragmentActivity fragmentActivity) {
        QRCodeFragment qrCodeFragment = new QRCodeFragment();
        startFragmentTransaction(fragmentActivity, qrCodeFragment);

    }

    public void startSettigsFragment(FragmentActivity fragmentActivity) {
        SettingsFragment settingsFragment = new SettingsFragment();
        startFragmentTransaction(fragmentActivity, settingsFragment);

    }

    private void startFragmentTransaction(FragmentActivity fragmentActivity, Fragment fragment) {
        fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
    }
}
