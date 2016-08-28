package com.soho.evgeny.simpleservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    final String LOG_TAG = "MyLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    void onClickStart(View view){
        startService(new Intent(this,MyService.class));
    }

    void onClickStop(View view) {
        stopService(new Intent(this,MyService.class));
    }
}
