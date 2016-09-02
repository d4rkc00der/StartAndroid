package com.soho.evgeny.simplenotes;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by evgeny on 02.09.16.
 */
public class NoteService extends Service {
    final String LOG_TAG = "MyLog";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG,"Service created");
        Toast.makeText(this,"Service created",Toast.LENGTH_LONG);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
