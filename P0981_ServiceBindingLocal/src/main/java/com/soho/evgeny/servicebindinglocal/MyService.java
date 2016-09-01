package com.soho.evgeny.servicebindinglocal;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by evgeny on 01.09.16.
 */
public class MyService extends Service  {

    final String LOG_TAG = "MyLog";

    MyBinder myBinder = new MyBinder();
    Timer timer;
    TimerTask timerTask;
    long interval = 10000;

    public void onCreate(){
        Log.d(LOG_TAG,"onCreate");
        timer = new Timer();
        schedule();
    }

    void schedule() {
        if (timerTask != null) timerTask.cancel();
        if (interval > 0) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    Log.d(LOG_TAG, "run");
                }
            };
            timer.schedule(timerTask, 1000, interval);
        }
    }

    long upInterval(long gap){

        Log.d(LOG_TAG,"upInterval from " + interval + " to "
        + (interval+gap));
        interval = interval+gap;
        schedule();

        return interval;
    }

    long downInterval(long gap) {
        if(gap>interval) return 0;

        Log.d(LOG_TAG,"upInterval from " + interval + " to "
                + (interval-gap));
        interval = interval-gap;
        schedule();
        return interval;
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG,"MyService onBind");
        return myBinder;
    }

    class MyBinder extends Binder {
        MyService getService(){
            return MyService.this;
        }
    }
}
