package com.soho.evgeny.simpleservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(8082);

                    Socket clientSocket = serverSocket.accept();
                    Log.d(LOG_TAG,clientSocket.getInputStream().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        myThread.start();
    }
}
