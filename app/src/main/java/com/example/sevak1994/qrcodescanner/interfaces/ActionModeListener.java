package com.example.sevak1994.qrcodescanner.interfaces;

/**
 * Created by Sevak1994 on 9/19/2017.
 */

public interface ActionModeListener {

    void inActionMode();

    void inNormalMode(boolean deletedItems);

    void moreItemSelected(int position);

    void lessItemSelected(int position);
}
