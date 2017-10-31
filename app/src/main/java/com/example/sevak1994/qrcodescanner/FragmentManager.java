package com.example.sevak1994.qrcodescanner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.sevak1994.qrcodescanner.fragments.BalanceFragment;
import com.example.sevak1994.qrcodescanner.fragments.CompanyInfoFragment;
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
    private Fragment mFragment;
    private int fragmentID;

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

    public Fragment getmFragment() {
        return mFragment;
    }

    public int getLastTransactionFragmentID() {
        return fragmentID;
    }

    public void startHomeFragment(FragmentActivity fragmentActivity) {
        fragmentID = R.id.navigation_home;
        mFragment = new HomeFragment();
        startFragmentTransaction(fragmentActivity, mFragment);
    }

    public void startContactsFragment(FragmentActivity fragmentActivity, ActionModeListener actionModeListener) {
        fragmentID = R.id.navigation_contacts;
        mFragment = new ContactsFragment();
        ((ContactsFragment) mFragment).setActionModeListener(actionModeListener);
        startFragmentTransaction(fragmentActivity, mFragment);
    }

    public void startQRScannerFragment(FragmentActivity fragmentActivity) {
        fragmentID = R.id.navigation_qr_scanner;
        mFragment = new QRScannerFragment();
        startFragmentTransaction(fragmentActivity, mFragment);

    }

    public void startQRCodeFragment(FragmentActivity fragmentActivity) {
        fragmentID = R.id.navigation_qr_code;
        mFragment = new QRCodeFragment();
        startFragmentTransaction(fragmentActivity, mFragment);
    }

    public void startSettingsFragment(FragmentActivity fragmentActivity) {
        fragmentID = R.id.navigation_settings;
        mFragment = new SettingsFragment();
        startFragmentTransaction(fragmentActivity, mFragment);
    }

    public void startSettingsFragment(FragmentActivity fragmentActivity, int enterAnim, int exitAnim) {
        fragmentID = R.id.navigation_settings;
        mFragment = new SettingsFragment();
        startFragmentTransaction(fragmentActivity, mFragment, enterAnim, exitAnim);
    }

    public void startContactInfoFragment(FragmentActivity fragmentActivity, int enterAnim, int exitAnim) {
        mFragment = new ContactInfoFragment();
        startFragmentTransaction(fragmentActivity, mFragment, enterAnim, exitAnim);
    }

    public void startBalanceFragment(FragmentActivity fragmentActivity, int enterAnim, int exitAnim) {
        mFragment = new BalanceFragment();
        startFragmentTransaction(fragmentActivity, mFragment, enterAnim, exitAnim);
    }

    public void startCompanyInfoFragment(FragmentActivity fragmentActivity, int enterAnim, int exitAnim) {
        mFragment = new CompanyInfoFragment();
        startFragmentTransaction(fragmentActivity, mFragment, enterAnim, exitAnim);
    }

    public void startContactInfoEditFragment(FragmentActivity fragmentActivity, int enterAnim, int exitAnim) {
        mFragment = new ContactInfoEditFragment();
        startFragmentTransaction(fragmentActivity, mFragment, enterAnim, exitAnim);
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
