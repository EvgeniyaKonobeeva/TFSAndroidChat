package com.example.evgenia.tfsandroidchat;

import android.support.v4.app.Fragment;

/**
 * Created by User on 06.04.2017.
 */

public interface OnNavigationActionBar {
    void setDrawerToggleEnabled(boolean enabled);
    void addFragment(Fragment fragment, boolean addToBackStack);
}
