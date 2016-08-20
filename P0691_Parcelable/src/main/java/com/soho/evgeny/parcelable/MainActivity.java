package com.soho.evgeny.parcelable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final String LOG_LAT ="MyLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void onclick(View v){
        MyObject obj1 = new MyObject("string",1);
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra(MyObject.class.getCanonicalName(),obj1);
        Log.d(LOG_LAT,"StartActivity");
        startActivity(intent);
    }
}
