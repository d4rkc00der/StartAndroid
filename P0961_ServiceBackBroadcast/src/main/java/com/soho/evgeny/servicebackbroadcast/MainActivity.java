package com.soho.evgeny.servicebackbroadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    final String LOG_TAG = "MyLog";

    final int TASK1_CODE = 1;
    final int TASK2_CODE = 2;
    final int TASK3_CODE = 3;

    public final static String PARAM_TIME = "time";
    public final static String PARAM_TASK = "task";
    public final static String PARAM_RESULT = "result";
    public final static String PARAM_STATUS = "status";

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    public final static String BROADCAST_ACTION = "com.soho.evgeny.servicebackbroadcast";

    TextView tvTask1;
    TextView tvTask2;
    TextView tvTask3;

    BroadcastReceiver broadcastReceiver;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTask1 = (TextView)findViewById(R.id.tvTask1);
        tvTask1.setText("Task1");
        tvTask2 = (TextView)findViewById(R.id.tvTask2);
        tvTask2.setText("Task2");
        tvTask3 = (TextView)findViewById(R.id.tvTask3);
        tvTask3.setText("Task3");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int task = intent.getIntExtra(PARAM_TASK, 0);
                int status = intent.getIntExtra(PARAM_STATUS, 0);
                Log.d(LOG_TAG, "Task: " + task
                        + "Status: " + status);

                if (status == STATUS_START) {
                    switch (task) {
                        case TASK1_CODE:
                            tvTask1.setText("Task1 start");
                            break;
                        case TASK2_CODE:
                            tvTask2.setText("Task2 start");
                            break;
                        case TASK3_CODE:
                            tvTask3.setText("Task3 start");
                            break;
                    }
                }

                if (status == STATUS_FINISH) {
                    int result = intent.getIntExtra(PARAM_RESULT, 0);
                    switch (task) {
                        case TASK1_CODE:
                            tvTask1.setText("Task1 finished result: " + result);
                            break;
                        case TASK2_CODE:
                            tvTask2.setText("Task2 finished result: " + result);
                            break;
                        case TASK3_CODE:
                            tvTask3.setText("Task3 finished result: " + result);
                            break;
                    }
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    public void onClickStart(View view){
    Log.d(LOG_TAG,"onClickStart");
        Intent intent;
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 1)
                .putExtra(PARAM_TASK, TASK1_CODE);
        startService(intent);

        intent = new Intent(this,MyService.class).putExtra(PARAM_TIME,2)
                .putExtra(PARAM_TASK,TASK2_CODE);
        startService(intent);

        intent = new Intent(this,MyService.class).putExtra(PARAM_TIME,3)
                .putExtra(PARAM_TASK,TASK3_CODE);
        startService(intent);
    }
}
