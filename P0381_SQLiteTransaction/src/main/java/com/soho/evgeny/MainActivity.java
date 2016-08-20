package com.soho.evgeny;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    DBHelper dbh;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MyLog","OnCreate ");
        dbh = new DBHelper(this);
        myActions();
    }

    void myActions() {
        db = dbh.getWritableDatabase();
        delete(db,"mytable");
        insert(db,"mytable","val1");
        read(db,"mytable");
        dbh.close();
    }

    void insert(SQLiteDatabase db, String table, String value) {
        Log.d("MyLog", "Insert in table " + table + " value = " + value);
        ContentValues cv = new ContentValues();
        cv.put("val",value);
        db.insert(table,null,cv);
    }

    void read(SQLiteDatabase db, String table) {
        Log.d("MyLog", "Read table " + table);

        Cursor c = db.query("table",null,null,null,null,null,null);

        if(c!=null) {
            Log.d("MyLog","Record cound = "+c.getCount());
            if(c.moveToFirst()){
                do {
                    Log.d("MyLog",c.getString(c.getColumnIndex("val")));
                }while (c.moveToNext());
            }
        }
    }

    void delete(SQLiteDatabase db, String table) {
        Log.d("MyLog", "Delete table " + table);
        db.delete(table,null,null);
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context,"myDB4",null,1);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d("MyLog", "--- onCreate database ---");

            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "val text"
                    + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
