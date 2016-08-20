package com.soho.evgeny.progressdialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ProgressDialog pd;
    Handler hd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclick(View view) {
        switch(view.getId()){
            case R.id.btnDefault:
                pd = new ProgressDialog(this);
                pd.setTitle("Default");
                pd.setMessage("Message");
                pd.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                pd.show();
                break;
            case R.id.btnHoriz:
                pd = new ProgressDialog(this);
                pd.setTitle("Horizontal");
                pd.setMessage("Message");
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.setMax(2148);
                pd.setIndeterminate(true);
                pd.show();
                hd = new Handler(){
                  public void handleMessage(Message msg){
                      pd.setIndeterminate(false);
                      if(pd.getProgress()<pd.getMax()){
                          pd.incrementProgressBy(50);
                          pd.incrementSecondaryProgressBy(75);
                          hd.sendEmptyMessageDelayed(0,100);
                      }
                      else {
                          pd.dismiss();
                      }
                  }
                };
                hd.sendEmptyMessageDelayed(0,2000);
                break;
            default:
                break;
        }
    }
}
