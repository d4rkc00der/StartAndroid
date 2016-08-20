package com.soho.evgeny.alertdialogprepare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    final static String LOG_TAG ="MyLog";
    final int DIALOG = 1;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    void onclick(View v) {
        showDialog(DIALOG);
    }

    protected Dialog onCreateDialog(int id){
        Log.d(LOG_TAG,"onCreateDialog() called");
        if(id == DIALOG){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Текущее время");
            builder.setMessage(sdf.format(new Date(System.currentTimeMillis())));
            return builder.create();
        }
        return super.onCreateDialog(id);
    }

    protected void onPrepareDialog(int id, Dialog dialog){
        super.onPrepareDialog(id,dialog);
        Log.d(LOG_TAG,"onPrepareDialog() called");
        if(id == DIALOG){
            ((AlertDialog)dialog).setMessage(sdf.format(new Date(System.currentTimeMillis())));
        }

    }
}
