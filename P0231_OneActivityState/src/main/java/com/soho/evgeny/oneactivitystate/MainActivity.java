package com.soho.evgeny.oneactivitystate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG","onCreate()");
        //
    }

    @Override
    protected void onPause() {
        Log.d("TAG","onPause()");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.d("TAG","onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d("TAG","onResume()");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d("TAG","onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("TAG","onDestroy()");
        super.onDestroy();
    }
}
