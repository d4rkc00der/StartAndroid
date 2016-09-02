package com.soho.evgeny.simplenotes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by evgeny on 02.09.16.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    final String LOG_TAG = "MyLog";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(LOG_TAG,"onReceive action: "+ intent.getAction());
        context.startService(new Intent(context,NoteService.class));
    }
}
