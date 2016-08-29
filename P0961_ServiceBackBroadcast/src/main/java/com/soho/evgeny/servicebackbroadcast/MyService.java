package com.soho.evgeny.servicebackbroadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgeny on 29.08.16.
 */
public class MyService extends Service {

    final String LOG_TAG = "MyLog";
    ExecutorService executorService;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG,"onCreate");
        executorService = Executors.newFixedThreadPool(2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,"onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG,"onStartCommand");
        int time = intent.getIntExtra(MainActivity.PARAM_TIME,0);
        int task = intent.getIntExtra(MainActivity.PARAM_TASK,0);

        MyRun myRun = new MyRun(startId,task,time);

        executorService.execute(myRun);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyRun implements Runnable{

        int time;
        int startId;
        int task;

        public MyRun(int time, int startId, int task) {
            this.time = time;
            this.startId = startId;
            this.task = task;
            Log.d(LOG_TAG,"MyRun#" + startId + " created");
        }

        @Override
        public void run() {
            Intent intent = new Intent(MainActivity.BROADCAST_ACTION);
            Log.d(LOG_TAG, "MyRun#" + startId + " start, time = " + time);

            try {
                intent.putExtra(MainActivity.PARAM_TASK, task);
                intent.putExtra(MainActivity.PARAM_STATUS, MainActivity.STATUS_START);
                sendBroadcast(intent);

                TimeUnit.SECONDS.sleep(5);

                intent.putExtra(MainActivity.PARAM_STATUS, MainActivity.STATUS_FINISH);
                intent.putExtra(MainActivity.PARAM_RESULT, time * 100);
                sendBroadcast(intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            stop();
        }

        void stop(){
            Log.d(LOG_TAG,"MyRun#" + startId + " stoped with result:"
                    + stopSelfResult(startId));
        }
    }
}
