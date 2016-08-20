package com.soho.evgeny.saveinstancestate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String LOG_TAG ="MyLog";
    int cnt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trace("onCreate()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        trace("onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        trace("onPause()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        trace("onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        trace("onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        trace("onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        trace("onStop()");
    }

    void trace(String msg){
        Log.d(LOG_TAG,msg);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        trace("onSaveInstanceState()");
        outState.putInt("count",cnt);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        trace("onRestoreInstanceState()");
        cnt = savedInstanceState.getInt("count");
    }

    public void onclick(View v){
        Toast.makeText(this, "Count = " + ++cnt, Toast.LENGTH_SHORT).show();
    }
}
