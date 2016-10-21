package com.example.penghong.multilanguagetestflight1;

import android.preference.PreferenceActivity;

import java.util.List;

/**
 * Created by penghong on 20/10/16.
 */

public class SettingPreferenceActivity extends PreferenceActivity {

    @Override
    public void onBuildHeaders(List<Header> target) {
//        super.onBuildHeaders(target);

        loadHeadersFromResource(R.xml.preference_header, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
//        return super.isValidFragment(fragmentName);

        return SettingsPreferenceFragment.class.getName().equals(fragmentName);
    }
}
