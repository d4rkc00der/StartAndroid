package com.soho.evgeny.asynctaskstatus;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    MyTask myTask;
    TextView tvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView)findViewById(R.id.tvInfo);
    }

    void onclick(View view){
        switch(view.getId()){
            case R.id.btnStart:
                startTask();
                break;
            case R.id.btnStatus:
                showStatus();
                break;
            default:
                break;
        }
    }

    void startTask(){
        myTask = new MyTask();
        myTask.execute();
        myTask.cancel(false);
    }

    void showStatus(){
        if(myTask!=null){
            Toast.makeText(this,"Status:"+myTask.getStatus().toString(),Toast.LENGTH_LONG).show();
        }
    }
    class MyTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Begin");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                for(int i = 0; i < 5 ; i++){
                    if(isCancelled()) return null;
                    TimeUnit.SECONDS.sleep(1);
                }
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
            super.onCancelled();
            tvInfo.setText("Cancel");
        }
    }
}


