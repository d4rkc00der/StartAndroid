package com.soho.evgeny.handler;

import android.annotation.SuppressLint;
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

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView)findViewById(R.id.tvInfo);
        btnStart = (Button)findViewById(R.id.btnStart);
        // Основной поток
        myHandler = new Handler(){
            public void handleMessage(android.os.Message message){
                tvInfo.setText("Закачано файлов:"+message.what);
                if(message.what==10) btnStart.setEnabled(true);
            };
        };
    }

    void onclick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                btnStart.setEnabled(false);
                // Отдельный поток
                Thread t = new Thread(new Runnable() {
                public void run() {
                    for (int i = 1; i <= 10; i++) {
                        // долгий процесс
                        downloadFile();
                        // отправляем в основной поток данные
                        myHandler.sendEmptyMessage(i);
                        Log.d(LOG_TAG, "i = " + i);
                    }
                }
            });
            t.start();
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
            TimeUnit.SECONDS.sleep(1);
        }
        catch (InterruptedException e) {
            Log.d(LOG_TAG,e.getMessage());
        }
    }
}
