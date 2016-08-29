package com.soho.evgeny.servicebackpendingintent;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG ="MyLog";

    final int TASK1_CODE = 1;
    final int TASK2_CODE = 2;
    final int TASK3_CODE = 3;

    final static int STATUS_START = 100;
    final static int STATUS_FINISH = 200;

    final static String PARAM_TIME = "time";
    final static String PARAM_PINTENT = "pendingIntent";
    final static String PARAM_RESULT = "result";

    TextView tvTask1;
    TextView tvTask2;
    TextView tvTask3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTask1 = (TextView)findViewById(R.id.tvTask1);
        tvTask1.setText("Task1");
        tvTask2 = (TextView)findViewById(R.id.tvTask2);
        tvTask2.setText("Task2");
        tvTask3 = (TextView)findViewById(R.id.tvTask3);
        tvTask3.setText("Task3");
    }

    public void onClickStart(View view) {
        PendingIntent pi;
        Intent intent = new Intent(this,MyService.class);

        // Создаем PendingIntent для Task1
        pi = createPendingResult(TASK1_CODE,intent, 0);
        // Создаем Intent для вызова сервиса, кладем туда параметр времени
        // и созданный PendingIntent
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 7)
                .putExtra(PARAM_PINTENT, pi);
        // стартуем сервис
        startService(intent);

        pi = createPendingResult(TASK2_CODE, intent, 0);
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 4)
                .putExtra(PARAM_PINTENT, pi);
        startService(intent);

        pi = createPendingResult(TASK3_CODE, intent, 0);
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 6)
                .putExtra(PARAM_PINTENT, pi);
        startService(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(LOG_TAG,"Request code:"+requestCode+" Result code:"+resultCode);

        if(resultCode== STATUS_START){
            switch(requestCode){
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

        if(resultCode == STATUS_FINISH){
            int result = data.getIntExtra(PARAM_RESULT,0);
            switch (requestCode){
                case TASK1_CODE:
                    tvTask1.setText("Task1 finished with result:"+result);
                    break;
                case TASK2_CODE:
                    tvTask2.setText("Task2 finished with result:"+result);
                    break;
                case TASK3_CODE:
                    tvTask3.setText("Task3 finished with result:"+result);
                    break;
            }
        }
    }
}
