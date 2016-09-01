package com.soho.evgeny.servicebindclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    final String LOG_TAG = "MyLog";

    ServiceConnection connection;
    Intent intent;
    boolean bound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent("com.soho.evgeny.servicebindserver.SimpleService");
        intent.setPackage("com.soho.evgeny.servicebindserver");
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(LOG_TAG,"onServiceConnected");
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(LOG_TAG,"onServiceDisconnected");
                bound = false;
            }
        };
    }

    public void onClickStart(View view){
        try {
            startService(intent);
        } catch (Exception e){
            Log.d(LOG_TAG,e.getMessage());
        }
    }

    public void onClickStop(View view) {
        stopService(intent);
    }

    public void onClickBind(View view) {
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    public void onClickUnBind(View view) {
        if (!bound) return;
        unbindService(connection);
        bound = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onClickUnBind(null);
    }
}
