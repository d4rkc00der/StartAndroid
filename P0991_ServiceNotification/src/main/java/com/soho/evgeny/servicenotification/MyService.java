package com.soho.evgeny.servicenotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.concurrent.TimeUnit;

/**
 * Created by evgeny on 01.09.16.
 */
public class MyService extends Service {

    NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        sendNotif();
        return super.onStartCommand(intent, flags, startId);
    }

    void sendNotif(){
        Notification myNotify = new Notification(R.mipmap.ic_launcher,"This is myservice",
                System.currentTimeMillis());

        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(MainActivity.FILENAME, "somefile");
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        myNotify.setLatestEventInfo(this,"Title text","BodyText",pendingIntent);
        myNotify.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(1,myNotify);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
