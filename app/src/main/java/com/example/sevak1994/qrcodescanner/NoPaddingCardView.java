package com.example.sevak1994.qrcodescanner;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by Admin on 9/25/2017.
 */

public class NoPaddingCardView extends CardView {

    private int padding;

    public NoPaddingCardView(Context context) {
        super(context);
    }

    public NoPaddingCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoPaddingCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setElevation(float elevation) {
        super.setElevation(elevation);
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {

        if (params instanceof MarginLayoutParams) {
            MarginLayoutParams layoutParams = (MarginLayoutParams) params;
            layoutParams.bottomMargin -= (getPaddingBottom() - getContentPaddingBottom()) / 2;
            layoutParams.leftMargin -= (getPaddingLeft() - getContentPaddingLeft()) / 2;
            layoutParams.rightMargin -= (getPaddingRight() - getContentPaddingRight()) / 2;

            padding = (getPaddingTop() - getContentPaddingTop()) / 2;
        }

        super.setLayoutParams(params);
    }
}
