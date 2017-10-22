package com.example.sevak1994.qrcodescanner.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sevak1994.qrcodescanner.BissApplication;

/**
 * Created by Sevak1994 on 10/22/2017.
 */

public class SharedPreferenceHelper {

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences("SHARED_PREF", context.MODE_PRIVATE);
    }

    public static void storeStringInPreference(String key, String value) {
        SharedPreferences mySharedPreferences = getPreferences(BissApplication.getInstance().getApplicationContext());
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String loadStringFromPreference(String key) {
        SharedPreferences mySharedPreferences = getPreferences(BissApplication.getInstance().getApplicationContext());
        String result = mySharedPreferences.getString(key, "");
        return result;
    }

    public static String loadStringFromPreferenceDefault(String key) {
        SharedPreferences mySharedPreferences = getPreferences(BissApplication.getInstance().getApplicationContext());
        String result = mySharedPreferences.getString(key, "077");
        return result;
    }

    public static void storeIntInPreference(String key, int value) {
        SharedPreferences mySharedPreferences = getPreferences(BissApplication.getInstance().getApplicationContext());
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static Integer loadIntFromPreference(String key) {
        SharedPreferences mySharedPreferences = getPreferences(BissApplication.getInstance().getApplicationContext());
        Integer result = mySharedPreferences.getInt(key, 0);
        return result;
    }

    public static Integer loadIntFromPreference(String key, int defValue) {
        SharedPreferences mySharedPreferences = getPreferences(BissApplication.getInstance().getApplicationContext());
        Integer result = mySharedPreferences.getInt(key, defValue);
        return result;
    }

    public static void storeLongInPreference(String key, long value) {
        SharedPreferences mySharedPreferences = getPreferences(BissApplication.getInstance().getApplicationContext());
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static long loadLongFromPreference(String key) {
        SharedPreferences mySharedPreferences = getPreferences(BissApplication.getInstance().getApplicationContext());
        long result = mySharedPreferences.getLong(key, -1);
        return result;
    }

    public static void storeBooleanInPreference(String key, Boolean value) {
        SharedPreferences mySharedPreferences = getPreferences(BissApplication.getInstance().getApplicationContext());
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static Boolean loadBooleanFromPreference(String key, Boolean defValue) {
        SharedPreferences mySharedPreferences = getPreferences(BissApplication.getInstance().getApplicationContext());
        Boolean result = mySharedPreferences.getBoolean(key, defValue);
        return result;
    }

    public static void storeDoubleInPreference(String key, double value) {
        SharedPreferences mySharedPreferences = getPreferences(BissApplication.getInstance().getApplicationContext());
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putLong(key, Double.doubleToRawLongBits(value));
        editor.commit();
    }

    public static Double loadDoubleFromPreference(String key, double defValue) {
        SharedPreferences mySharedPreferences = getPreferences(BissApplication.getInstance().getApplicationContext());
        Double result = Double.longBitsToDouble(mySharedPreferences.getLong(key, Double.doubleToLongBits(defValue)));
        return result;
    }

    public static void clearData() {
        SharedPreferences mySharedPreferences = getPreferences(BissApplication.getInstance().getApplicationContext());
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
