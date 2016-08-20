package com.soho.evgeny.preferencescode;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class PrefActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(this);
        setPreferenceScreen(screen);

        CheckBoxPreference chb1 = new CheckBoxPreference(this);
        chb1.setKey("chb1");
        chb1.setTitle("CheckBox 1");
        chb1.setSummaryOn("Description of checkbox 1 on");
        chb1.setSummaryOff("Description of checkbox 1 off");

        screen.addPreference(chb1);

        ListPreference list = new ListPreference(this);
        list.setKey("list");
        list.setTitle("List");
        list.setSummary("Description of list");
        list.setEntries(R.array.entries);
        list.setEntryValues(R.array.entry_values);
        screen.addPreference(list);

        list.setDependency("chb1");

    }
}
