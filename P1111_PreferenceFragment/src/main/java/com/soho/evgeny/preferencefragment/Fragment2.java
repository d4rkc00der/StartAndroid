package com.soho.evgeny.preferencefragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by evgeny on 21.09.16.
 */
public class Fragment2 extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref2);
    }
}
