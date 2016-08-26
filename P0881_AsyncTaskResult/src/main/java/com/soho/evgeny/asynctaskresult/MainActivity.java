package com.soho.evgeny.asynctaskresult;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "MyLog";
    MyTask myTask;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView)findViewById(R.id.tvInfo);
    }

    void onclick(View view){
        switch (view.getId()){
            case R.id.btnStart:
                myTask = new MyTask();
                myTask.execute();
                break;
            case R.id.btnGet:
                showResult();
                break;
            default:
                break;
        }
    }

    private void showResult(){
        if(myTask==null) return;
        int result = -1;
        try {
            Log.d(LOG_TAG,"Try to get result");
            result = myTask.get(1,TimeUnit.SECONDS);
            Log.d(LOG_TAG,"Get returns"+ result);
            Toast.makeText(this,"Get returns"+result,Toast.LENGTH_LONG);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e){
            Log.d(LOG_TAG,"Result by timeout:"+result);
        }

    }

    class MyTask extends AsyncTask<Void,Void,Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Begin");
            Log.d(LOG_TAG,"Begin");
        }



        @Override
        protected Integer doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(10);
            }  catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100500;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            tvInfo.setText("Results:" + result);
            Log.d(LOG_TAG,"Results: "+ result);

        }
    }

}
