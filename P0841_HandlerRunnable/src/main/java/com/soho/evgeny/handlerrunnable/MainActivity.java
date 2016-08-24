package com.soho.evgeny.handlerrunnable;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ProgressBar pbCount;
    TextView tvInfo;
    CheckBox chbInfo;
    int count;

    final String LOG_TAG = "MyLog";
    final int max = 100;

    Handler myHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHandler = new Handler();

        pbCount = (ProgressBar)findViewById(R.id.pbCount);
        pbCount.setMax(max);
        pbCount.setProgress(0);

        tvInfo = (TextView)findViewById(R.id.tvInfo);
        chbInfo = (CheckBox)findViewById(R.id.chbInfo);

        chbInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    tvInfo.setVisibility(View.VISIBLE);
                    myHandler.post(showInfo);
                }else {
                    tvInfo.setVisibility(View.GONE);
                    myHandler.removeCallbacks(showInfo);
                }
            }
        });

        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(count = 1; count < max ; count++){
                        TimeUnit.MILLISECONDS.sleep(100);
                        myHandler.post(updateProgress);

                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        myThread.start();
    }


    Runnable updateProgress = new Runnable() {
        @Override
        public void run() {
            pbCount.setProgress(count);
        }
    };


    Runnable showInfo = new Runnable() {
        @Override
        public void run() {
            Log.d(LOG_TAG,"showinfo");
            tvInfo.setText("Count = "+ count);
            myHandler.postDelayed(showInfo,1000);
        }
    };
}
