package com.soho.evgeny.servicenotification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String FILENAME = "filname";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView)findViewById(R.id.tv);
        Intent intent = getIntent();
        String filename = intent.getStringExtra(FILENAME);
        if(!TextUtils.isEmpty(filename)){
            tv.setText(filename);
        }
    }

    public void onClickStart(View view){
        startService(new Intent(this,MyService.class));
    }

    public void onClickStop(View view){
        stopService(new Intent(this,MyService.class));
    }
}
