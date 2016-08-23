package com.soho.evgeny.handlermessagemanage;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by evgeny on 23.08.16.
 */
public class TaskManager {
    private Handler myHandler;
    private final String LOG_TAG = "MyLog";

    public TaskManager(){
        myHandler = new Handler(myHandlerCallback);
    }

    public void putMessage(int message){
        myHandler.sendEmptyMessage(message);
        Log.d(LOG_TAG,"putMessage("+message+") called.");
    }

    public void putMessageDelayed(int message, int delayTimeMilisecond){
        myHandler.sendEmptyMessageDelayed(message,delayTimeMilisecond);
        Log.d(LOG_TAG,"putEmptyMessageDelayed("+message+","+delayTimeMilisecond+") called.");
    }

    public void getMessageList(){

    }
    private Handler.Callback myHandlerCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            return true;
        }
    };
}
