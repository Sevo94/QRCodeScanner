package com.example.sevak1994.qrcodescanner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.sevak1994.qrcodescanner.fragments.ContactInfoEditFragment;
import com.example.sevak1994.qrcodescanner.fragments.ContactInfoFragment;
import com.example.sevak1994.qrcodescanner.fragments.ContactsFragment;
import com.example.sevak1994.qrcodescanner.fragments.HomeFragment;
import com.example.sevak1994.qrcodescanner.fragments.QRCodeFragment;
import com.example.sevak1994.qrcodescanner.fragments.QRScannerFragment;
import com.example.sevak1994.qrcodescanner.fragments.SettingsFragment;
import com.example.sevak1994.qrcodescanner.interfaces.ActionModeListener;

/**
 * Created by Admin on 9/8/2017.
 */

public class FragmentManager {

    private static FragmentManager instance;

    private FragmentManager() {
    }

    public static FragmentManager getInstance() {
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

    public void startContactsFragment(FragmentActivity fragmentActivity, ActionModeListener actionModeListener) {
        ContactsFragment contactsFragment = new ContactsFragment();
        contactsFragment.setActionModeListener(actionModeListener);
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

    public void startSettingsFragment(FragmentActivity fragmentActivity) {
        SettingsFragment settingsFragment = new SettingsFragment();
        startFragmentTransaction(fragmentActivity, settingsFragment);
    }

    public void startSettingsFragment(FragmentActivity fragmentActivity, int enterAnim, int exitAnim) {
        SettingsFragment settingsFragment = new SettingsFragment();
        startFragmentTransaction(fragmentActivity, settingsFragment, enterAnim, exitAnim);
    }

    public void startContactInfoFragment(FragmentActivity fragmentActivity, int enterAnim, int exitAnim) {
        ContactInfoFragment contactInfoFragment = new ContactInfoFragment();
        startFragmentTransaction(fragmentActivity, contactInfoFragment, enterAnim, exitAnim);
    }

    public void startContactInfoEditFragment(FragmentActivity fragmentActivity, int enterAnim, int exitAnim) {
        ContactInfoEditFragment contactInfoEditFragment = new ContactInfoEditFragment();
        startFragmentTransaction(fragmentActivity, contactInfoEditFragment, enterAnim, exitAnim);
    }

    private void startFragmentTransaction(FragmentActivity fragmentActivity, Fragment fragment) {
        fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
    }

    private void startFragmentTransaction(FragmentActivity fragmentActivity, Fragment fragment, int enterAnim, int exitAnim) {
        FragmentTransaction fragmentTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(enterAnim, exitAnim);
        fragmentTransaction.replace(R.id.content, fragment).commit();
    }
}
