package com.soho.evgeny.asynctaskrotate;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    MyTask myTask;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MyLog","create MainActivity "+this.hashCode());
        tv = (TextView)findViewById(R.id.tv);

        myTask = (MyTask)getLastNonConfigurationInstance();
        if(myTask==null){
            myTask = new MyTask();
            myTask.execute();
        }
        myTask.link(this);
        Log.d("MyLog","create MyTask "+myTask.hashCode());

    }

    public Object onRetainNonConfigurationInstance() {
        myTask.unlink();
        return myTask;
    }

    static class MyTask extends AsyncTask<String,Integer,Void> {
        MainActivity mainActivity;

        void link(MainActivity act){
            mainActivity = act;
        }

        void unlink(){
            mainActivity = null;
        }
        @Override
        protected Void doInBackground(String... strings) {
            try {
                for(int i = 0; i <= 10; i++){
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(i);
                    Log.d("MyLog", "i = " + i
                            + ", MyTask: " + this.hashCode()
                            + ", MainActivity: " + mainActivity.hashCode());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mainActivity.tv.setText("i = "+values[0]);
        }
    }
}
