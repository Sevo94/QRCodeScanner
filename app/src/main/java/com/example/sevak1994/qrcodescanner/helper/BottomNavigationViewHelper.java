package com.example.sevak1994.qrcodescanner.helper;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.example.sevak1994.qrcodescanner.R;

import java.lang.reflect.Field;

/**
 * Created by Sevak1994 on 9/8/2017.
 */

public class BottomNavigationViewHelper {
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);

                item.setShiftingMode(false);

                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

    public static void changeCenterIconSize(BottomNavigationView navigation, Context context) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);

        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View itemView = menuView.getChildAt(i);

            if (itemView.getId() == R.id.navigation_qr_scanner) {
                final View iconView = itemView.findViewById(android.support.design.R.id.icon);
                //iconView.setPadding(0, convertDpToPixel(1, context), 0, 0);
                final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
                final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();

                layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, displayMetrics); //24 by default

                layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, displayMetrics); //24 by default
                iconView.setLayoutParams(layoutParams);
            }
        }
    }

    public static int convertDpToPixel(int dp, Context context) {
        int pixel;
        Resources r = context.getResources();
        pixel = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );

        return pixel;
    }
}
