package com.example.penghong.multilanguagetestflight1;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
//import android.support.v7.preference.PreferenceFragmentCompat;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompatDividers;

import java.util.Locale;

/**
 * Created by penghong on 19/10/16.
 */

public class SettingsPreferenceFragment extends PreferenceFragmentCompatDividers/*extends PreferenceFragmentCompat*/ {
    @Override
    public void onCreatePreferencesFix(Bundle bundle, String rootKey) {
        // Load the Preferences from the XML file
//        setPreferencesFromResource(R.xml.app_preference, rootKey);
        setPreferencesFromResource(R.xml.preference_language, rootKey);

        changeLanguage();
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        try {
//            return super.onCreateView(inflater, container, savedInstanceState);
//        } finally {
//            // Uncomment this if you want to change the dividers' style
//            // setDividerPreferences(DIVIDER_PADDING_CHILD | DIVIDER_CATEGORY_AFTER_LAST | DIVIDER_CATEGORY_BETWEEN);
//        }
//    }

    private void changeLanguage() {
        final Context context = getPreferenceManager().getContext();
        final PreferenceScreen screen = (PreferenceScreen) findPreference("language_setting");
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        Preference prefEn = findPreference("language_en");
        Preference prefZhrCn = findPreference("language_zh_rCN");
        Preference prefZhrTw = findPreference("language_zh_rTW");

        if (prefEn != null) {
            prefEn.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    setLocale(Locale.ENGLISH, "en");
                    createNewLanguagePreference();

//                    Toast.makeText(context, "English", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
        if (prefZhrCn != null) {
            prefZhrCn.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    setLocale(Locale.SIMPLIFIED_CHINESE, "zh-rCN");
                    createNewLanguagePreference();

//                    Toast.makeText(context, "简体", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
        if (prefZhrTw != null) {
            prefZhrTw.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    setLocale(Locale.TRADITIONAL_CHINESE, "zh-rTW");
                    createNewLanguagePreference();

//                    Toast.makeText(context, "繁體", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
    }

    private void setLocale(Locale locale, String lang) {
        final Resources resources = getResources();
        final Configuration configuration = resources.getConfiguration();
//        if (!configuration.locale.equals(locale)) {
//            configuration.setLocale(locale);

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getPreferenceManager().getContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("language", lang);
            editor.commit();

            configuration.locale = locale;
            resources.updateConfiguration(configuration, null);
//        }
    }

    private void createNewLanguagePreference() {
//        removePreviousLanguagePreference();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment newFragment = new SettingsPreferenceFragment();
        ft.replace(R.id.fragment, newFragment,"settingsPreferenceFragment");
//        ft.addToBackStack(newFragment.getClass().getName());
//        getFragmentManager().popBackStack();
        ft.commit();
    }

//    private void removePreviousLanguagePreference() {
//        Fragment prevFrag = getFragmentManager().findFragmentByTag("settingsPreferenceFragment");
//        if (prevFrag != null) {
//            Toast.makeText(getPreferenceManager().getContext(), "Remove", Toast.LENGTH_SHORT).show();
//            getFragmentManager().popBackStack();
//            getFragmentManager().beginTransaction().remove(prevFrag).commit();
//        }
//    }
}
