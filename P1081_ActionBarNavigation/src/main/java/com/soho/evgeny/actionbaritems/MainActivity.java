package com.soho.evgeny.actionbaritems;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements android.app.ActionBar.TabListener {

    final String LOG_TAG = "MyLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.app.ActionBar bar = getActionBar();
        bar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_TABS);

        android.app.ActionBar.Tab tab = bar.newTab();
        tab.setText("tab1");
        tab.setTabListener(this);
        bar.addTab(tab);

        tab = bar.newTab();
        tab.setText("tab2");
        tab.setTabListener(this);
        bar.addTab(tab);

    }




    @Override
    public void onTabSelected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
        Log.d(LOG_TAG,"Selection tab " + tab.getText());
    }

    @Override
    public void onTabUnselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
        Log.d(LOG_TAG,"Unselection tab " + tab.getText());
    }

    @Override
    public void onTabReselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
        Log.d(LOG_TAG,"Reselection tab " + tab.getText());
    }
}
