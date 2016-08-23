package com.soho.evgeny.handler;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG ="MyLog";

    Handler myHandler;
    TextView tvInfo;
    Button btnStart;
    Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView)findViewById(R.id.tvInfo);
    }

    void onclick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                for (int i = 0; i < 10; i++) {
                    downloadFile();
                    tvInfo.setText("Закачано файлов:"+i);

                    Log.d(LOG_TAG,"Закачано файлов:"+i);
                }
                break;
            case R.id.btnTest:
                Log.d(LOG_TAG,"Test");
                break;
            default:
                break;
        }
    }

    void downloadFile(){
        try {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (InterruptedException e) {
            Log.d(LOG_TAG,e.getMessage());
        }
    }
}
