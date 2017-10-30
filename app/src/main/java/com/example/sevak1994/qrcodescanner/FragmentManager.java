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

    public int getLastTransactionFragmentID() {
        return fragmentID;
    }

    public void startHomeFragment(FragmentActivity fragmentActivity) {
        fragmentID = R.id.navigation_home;
        HomeFragment homeFragment = new HomeFragment();
        startFragmentTransaction(fragmentActivity, homeFragment);
    }

    public void startContactsFragment(FragmentActivity fragmentActivity, ActionModeListener actionModeListener) {
        fragmentID = R.id.navigation_contacts;
        ContactsFragment contactsFragment = new ContactsFragment();
        contactsFragment.setActionModeListener(actionModeListener);
        startFragmentTransaction(fragmentActivity, contactsFragment);
    }

    public void startQRScannerFragment(FragmentActivity fragmentActivity) {
        fragmentID = R.id.navigation_qr_scanner;
        QRScannerFragment qrScannerFragment = new QRScannerFragment();
        startFragmentTransaction(fragmentActivity, qrScannerFragment);

    }

    public void startQRCodeFragment(FragmentActivity fragmentActivity) {
        fragmentID = R.id.navigation_qr_code;
        QRCodeFragment qrCodeFragment = new QRCodeFragment();
        startFragmentTransaction(fragmentActivity, qrCodeFragment);
    }

    public void startSettingsFragment(FragmentActivity fragmentActivity) {
        fragmentID = R.id.navigation_settings;
        SettingsFragment settingsFragment = new SettingsFragment();
        startFragmentTransaction(fragmentActivity, settingsFragment);
    }

    public void startSettingsFragment(FragmentActivity fragmentActivity, int enterAnim, int exitAnim) {
        fragmentID = R.id.navigation_settings;
        SettingsFragment settingsFragment = new SettingsFragment();
        startFragmentTransaction(fragmentActivity, settingsFragment, enterAnim, exitAnim);
    }

    public void startContactInfoFragment(FragmentActivity fragmentActivity, int enterAnim, int exitAnim) {
        ContactInfoFragment contactInfoFragment = new ContactInfoFragment();
        startFragmentTransaction(fragmentActivity, contactInfoFragment, enterAnim, exitAnim);
    }

    public void startBalanceFragment(FragmentActivity fragmentActivity, int enterAnim, int exitAnim) {
        BalanceFragment balanceFragment = new BalanceFragment();
        startFragmentTransaction(fragmentActivity, balanceFragment, enterAnim, exitAnim);
    }

    public void startCompanyInfoFragment(FragmentActivity fragmentActivity, int enterAnim, int exitAnim) {
        CompanyInfoFragment companyInfoFragment = new CompanyInfoFragment();
        startFragmentTransaction(fragmentActivity, companyInfoFragment, enterAnim, exitAnim);
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
