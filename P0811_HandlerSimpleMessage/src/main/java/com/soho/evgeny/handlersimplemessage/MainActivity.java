package com.soho.evgeny.handlersimplemessage;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG="MyLog";

    final int STATUS_NONE = 0;
    final int STATUS_CONNECTING = 1;
    final int STATUS_CONNECTED = 2;

    Handler myHandler;

    TextView tvStatus;
    ProgressBar pbConnect;
    Button btnConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = (TextView)findViewById(R.id.tvStatus);
        pbConnect = (ProgressBar)findViewById(R.id.pbConnect);
        btnConnect = (Button)findViewById(R.id.btnConnect);

        myHandler = new Handler(){
            public void handleMessage(android.os.Message message){
                switch(message.what){
                    case STATUS_NONE:
                        btnConnect.setEnabled(true);
                        tvStatus.setText("Not connected");
                        break;
                    case STATUS_CONNECTING:
                        btnConnect.setEnabled(false);
                        tvStatus.setText("Connecting");
                        pbConnect.setVisibility(View.VISIBLE);
                        break;
                    case STATUS_CONNECTED:
                        tvStatus.setText("Connected");
                        pbConnect.setVisibility(View.GONE);
                        break;
                }
            }
        };

        myHandler.sendEmptyMessage(STATUS_NONE);
    }

    public void onclick(View view){

            Thread myThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        myHandler.sendEmptyMessage(STATUS_CONNECTING);
                        TimeUnit.SECONDS.sleep(2);
                        myHandler.sendEmptyMessage(STATUS_CONNECTED);
                        TimeUnit.SECONDS.sleep(3);
                        myHandler.sendEmptyMessage(STATUS_NONE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            });
        myThread.start();
    }

}

