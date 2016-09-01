package com.soho.evgeny.servicebindinglocal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "MyLog";
    boolean isBound = false;
    ServiceConnection connection;
    Intent intent;
    MyService myService;
    TextView tvInternal;
    long interval;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInternal = (TextView)findViewById(R.id.tvInterval);

        intent = new Intent(this,MyService.class);

        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder myBinder) {
                Log.d(LOG_TAG,"MainActivity onServiceConnected");
                myService = ((MyService.MyBinder)myBinder).getService();
                isBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(LOG_TAG,"MainActivity onServiceDisconnected");
                isBound = false;
            }
        };
    }

    protected void onStart(){
        super.onStart();
        bindService(intent,connection,0);
    }

    protected void onStop() {
        super.onStop();
        if(!isBound) return;
        unbindService(connection);
        isBound = false;
    }

    public void onClickStart(View view){
        startService(intent);
    }

    public void onClickUp(View view){
        if(!isBound) return;
        interval = myService.upInterval(500);
        tvInternal.setText("Interval:"+interval);
    }

    public void onClickDown(View view){
        if(!isBound) return;
        interval = myService.downInterval(500);
        tvInternal.setText("Interval:"+interval);
    }
}
