package com.soho.evgeny.parcelable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {
    final String LOG_LAT ="MyLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        Log.d(LOG_LAT,"getParcelableExtra");

        MyObject obj = (MyObject)getIntent().getParcelableExtra(MyObject.class.getCanonicalName());
        Log.d(LOG_LAT,"obj: "+obj.s + ","+ obj.i);
    }
}
