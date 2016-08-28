package com.soho.evgeny.servicestop;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgeny on 28.08.16.
 */
public class MyService extends Service {

    final String LOG_TAG = "MyLog";
    ExecutorService ex;
    Object someRes;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG,"onCreate");
        ex = Executors.newFixedThreadPool(10);
        someRes = new Object();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,"onDestroy");
        someRes = null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG,"onStartCommand");
        int time = intent.getIntExtra("time",1);
        MyRun myRun = new MyRun(time,startId);
        ex.execute(myRun);
        return super.onStartCommand(intent, flags, startId);
        }

    class MyRun implements Runnable{

        int time;
        int startId;


        public MyRun(int time, int startId) {
            this.time = time;
            this.startId = startId;
            Log.d(LOG_TAG,"MyRun #" + this.startId +" created");
        }
        @Override
        public void run() {
            Log.d(LOG_TAG, "MyRun#" + startId + " start, time = " + time);
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Log.d(LOG_TAG,"MyRun startId#"+startId+" SomeRes "
                        +someRes.getClass()+" run");
            } catch (NullPointerException e) {
                Log.d(LOG_TAG, "MyRun#" + startId + " error, null pointer");
            }

            stop();
        }

        public void stop(){
            Log.d(LOG_TAG, "MyRun#" + startId + " end, stopSelf(" + startId + ")" + " result is:"+stopSelfResult(startId));

        }
    }
}
