package com.soho.evgeny.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    MyTask mt;
    TextView tvInfo;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView)findViewById(R.id.tvInfo);
        pb = (ProgressBar)findViewById(R.id.pb);
        pb.setEnabled(false);
        pb.setVisibility(View.GONE);
    }

    void startTask(View view){
        mt = new MyTask();
        mt.execute();

    }

    void stopTask(View view) {
        if((mt!= null) && (!mt.isCancelled())) {
            mt.cancel(true);
        }
        else Toast.makeText(this,"Task not started",Toast.LENGTH_SHORT).show();
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Begin");
            pb.setEnabled(true);
            pb.setVisibility(View.VISIBLE);
        }

        protected Void doInBackground (Void ...params){
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tvInfo.setText("End");
        }

        @Override
        protected void onCancelled() {
            pb.setEnabled(false);
            pb.setVisibility(View.GONE);
            super.onCancelled();

        }
    }
}
