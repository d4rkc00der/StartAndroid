package com.soho.evgeny.servicebackpendingintent;

import android.app.PendingIntent;
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
        int time  = intent.getIntExtra(MainActivity.PARAM_TIME,0);
        PendingIntent pendingIntent = intent.getParcelableExtra(MainActivity.PARAM_PINTENT);

        MyRun myRun = new MyRun(time,startId,pendingIntent);
        executorService.execute(myRun);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyRun implements Runnable {
        int time;
        int startId;
        PendingIntent pendingIntent;

        public MyRun(int time, int startId, PendingIntent pendingIntent) {
            Log.d(LOG_TAG,"MyRun #"+startId+" created");
            this.time = time;
            this.startId = startId;
            this.pendingIntent = pendingIntent;
        }



        @Override
        public void run() {
            Log.d(LOG_TAG,"MyRun#"+startId+" time = "+time);
            try {
                pendingIntent.send(MainActivity.STATUS_START);
                TimeUnit.SECONDS.sleep(3);

                Intent intent = new Intent().putExtra(MainActivity.PARAM_RESULT,time * 100);
                pendingIntent.send(MyService.this,MainActivity.STATUS_FINISH,intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (PendingIntent.CanceledException e){
                e.printStackTrace();
            }
            stop();
        }

        public void stop(){
            Log.d(LOG_TAG,"MyRun#" + startId + " time = "
                    + time +" finished with result:"+stopSelfResult(startId));
        }
    }

}
