package com.soho.evgeny.asynctaskrotate;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by evgeny on 26.08.16.
 */
public class MyService extends Service {

    final String LOG_TAG = "MyLog";



    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG,"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG,"onStart()");
        SomeTask();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,"onDestroy()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG,"onBind()");
        return null;
    }

    void SomeTask(){

    }
}
