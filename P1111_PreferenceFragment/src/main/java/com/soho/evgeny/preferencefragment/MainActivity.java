package com.soho.evgeny.preferencefragment;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import java.util.List;

public class MainActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_head,target);
    }
}
