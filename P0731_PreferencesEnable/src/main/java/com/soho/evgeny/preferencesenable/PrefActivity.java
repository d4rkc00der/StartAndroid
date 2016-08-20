package com.soho.evgeny.preferencesenable;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;

public class PrefActivity extends PreferenceActivity {

    CheckBoxPreference checkBoxPreference;
    PreferenceCategory preferenceCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);

        checkBoxPreference = (CheckBoxPreference)findPreference("chb3");
        preferenceCategory = (PreferenceCategory)findPreference("categ2");
        preferenceCategory.setEnabled(checkBoxPreference.isChecked());

        preferenceCategory.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                preferenceCategory.setEnabled(checkBoxPreference.isChecked());
                return false;
            }
        });
    }
}
