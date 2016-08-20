package com.soho.evgeny.alertdialogitemsmulti;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "MyLog";
    final int DIALOG_ITEMS =1;
    final  int DIALOG_CURSOR = 3;

    DB db;
    Cursor cursor;

    String data[] = {"one","two","three","four"};
    boolean chkd[] = {false,true,true,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        db = new DB(this);
        db.open();
        cursor = db.getAllData();
        startManagingCursor(cursor);
    }


    public void onclick(View view) {
        switch(view.getId()) {
            case R.id.btnItems:
                showDialog(DIALOG_ITEMS);
                break;
            case R.id.btnCursor:
                showDialog(DIALOG_CURSOR);
                break;
            default:
                break;
        }
    }

    protected Dialog onCreateDialog(int id){
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        switch (id) {
            case DIALOG_ITEMS:
                adb.setTitle(R.string.items);
                adb.setMultiChoiceItems(data,chkd,myItemsMultiClickListener);
                break;
            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor);
                adb.setMultiChoiceItems(cursor,DB.COLUMN_CHK,DB.COLUMN_TXT,myCursorMultiClickListener);
                break;
        }
        adb.setPositiveButton(R.string.ok, myBtnClickListener);
        return adb.create();
    };

    DialogInterface.OnMultiChoiceClickListener myItemsMultiClickListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
            Log.d(LOG_TAG,"which = "+ which + ", isChecked = "+isChecked);
        }
    };

    DialogInterface.OnMultiChoiceClickListener myCursorMultiClickListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int which, boolean isCheked) {
            ListView lv = ((AlertDialog)dialogInterface).getListView();
            Log.d(LOG_TAG,"which = "+which + ",isChecked = "+isCheked);
            db.changeRec(which,isCheked);
            cursor.requery();
        }
    };

    DialogInterface.OnClickListener myBtnClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            SparseBooleanArray sbArray = ((AlertDialog)dialog).getListView().getCheckedItemPositions();
            for (int i = 0; i<sbArray.size();i++){
                int key = sbArray.keyAt(i);
                if(sbArray.get(key)){
                    Log.d("MyLog","checked: "+key);
                }
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
