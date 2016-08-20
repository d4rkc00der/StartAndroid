package com.soho.evgeny.alertdialogitemssingle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "MyLog";

    final int DIALOG_ITEMS = 1;
    final int DIALOG_ADAPTER = 2;
    final int DIALOG_CURSOR = 3;

    DB db;
    Cursor cursor;

    String data[] = { "one", "two", "tree", "four"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DB(this);
        db.open();
        cursor = db.getAllData();
        startManagingCursor(cursor);
    }

    public void onclick(View v) {
        switch (v.getId()){
            case R.id.btnAdapter:
                showDialog(DIALOG_ADAPTER);
                break;
            case R.id.btnCursor:
                showDialog(DIALOG_CURSOR);
                break;
            case R.id.btnItems:
                showDialog(DIALOG_ITEMS);
                break;
            default:
                break;
        }
    }

    protected Dialog onCreateDialog(int id){
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        switch(id) {
            case DIALOG_ITEMS:
                adb.setTitle(R.string.items);
                adb.setSingleChoiceItems(data,-1,myClickListener);
                break;
            case DIALOG_ADAPTER:
                adb.setTitle(R.string.adapter);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice,data);
                adb.setSingleChoiceItems(adapter,-1,myClickListener);
                break;
            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor);
                adb.setSingleChoiceItems(cursor,-1,DB.COLUMN_TEXT,myClickListener);
                break;
        }
        adb.setPositiveButton(R.string.ok,myClickListener);
        return adb.create();
    }

   DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
       @Override
       public void onClick(DialogInterface dialogInterface, int i) {
           ListView lv = ((AlertDialog)dialogInterface).getListView();
           if(i == Dialog.BUTTON_POSITIVE) {
               Log.d(LOG_TAG,"pos = "+ lv.getCheckedItemPosition());
           }
           else
               Log.d(LOG_TAG,"which = "+i);

       }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();;
    }
}
