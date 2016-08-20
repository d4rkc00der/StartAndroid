package com.soho.evgeny.twoactivitystate;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    final String TAG = "States";
    Button btnActTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnActTwo = (Button)findViewById(R.id.btnActTwo);
        btnActTwo.setOnClickListener(this);
        Log.d(TAG,"MainActivity onCreate()");
    }

    @Override
    protected void onPause() {
        Log.d(TAG,"MainActivity onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"MainActivity onStop()");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.d(TAG,"MainActivity onResume()");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG,"MainActivity onRestart()");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"MainActivity onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,ActivityTwo.class);
        startActivity(intent);
    }
}
