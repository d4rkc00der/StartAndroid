package ru.elitfit.sqlitequery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    void trace(String message){
        Log.d("MyLog",message);
    }

    String name[] = {
            "Китай", "США", "Бразилия", "Россия", "Япония",
            "Германия", "Египет", "Италия", "Франция", "Канада"
    };
    int people[] = { 1400, 311, 195, 142, 128, 82, 80, 60, 66, 35 };
    String region[] = {
            "Азия", "Америка", "Америка", "Европа", "Азия",
            "Европа", "Африка", "Европа", "Европа", "Америка"
    };

    DBHelper dbHelper;
    SQLiteDatabase database;

    Button btnAll, btnFunc, btnPeople, btnSort, btnGroup, btnHaving;
    EditText etFunc, etPeople, etRegionPeople;
    RadioGroup rgSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAll = (Button) findViewById(R.id.btnAll);
        btnAll.setOnClickListener(this);

        btnFunc = (Button) findViewById(R.id.btnFunc);
        btnFunc.setOnClickListener(this);

        btnPeople = (Button) findViewById(R.id.btnPeople);
        btnPeople.setOnClickListener(this);

        btnSort = (Button) findViewById(R.id.btnSort);
        btnSort.setOnClickListener(this);

        btnGroup = (Button) findViewById(R.id.btnGroup);
        btnGroup.setOnClickListener(this);

        btnHaving = (Button) findViewById(R.id.btnHaving);
        btnHaving.setOnClickListener(this);

        etFunc = (EditText) findViewById(R.id.etFunc);
        etPeople = (EditText) findViewById(R.id.etPeople);
        etRegionPeople = (EditText) findViewById(R.id.etRegionPeople);

        rgSort = (RadioGroup) findViewById(R.id.rgSort);

        dbHelper = new DBHelper(this);

        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query("mytable",null,null,null,null,null,null);

        if(cursor.getCount()==0) {
            ContentValues contentValues = new ContentValues();

            for(int i =0; i<10; i++) {
                contentValues.put("name",name[i]);
                contentValues.put("people",people[i]);
                contentValues.put("region",region[i]);
                trace("id"+database.insert("mytable",null,contentValues));
            }
        }
        cursor.close();
        dbHelper.close();
        onClick(btnAll);
    }

    @Override
    public void onClick(View view) {
        database = dbHelper.getWritableDatabase();

        String sFunc = etFunc.getText().toString();
        String sPeople = etPeople.getText().toString();
        String sRegionPeople = etRegionPeople.getText().toString();

        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor cursor = null;

        switch(view.getId()) {

            case R.id.btnAll:
                trace("Все записи");
                cursor = database.query("mytable",null,null,null,null,null,null);
                break;
            case R.id.btnFunc:
                trace("Function"+sFunc+"---");
                columns = new String[]  { sFunc };
                cursor = database.query("mytable",columns,null,null,null,null,null);
                break;
            case R.id.btnHaving:
                trace("--- Регионы с населением больше " + sRegionPeople);

                columns = new String[] { "region", "sum(people) as people" };
                groupBy = "region";
                having = "sum(people) > " + sRegionPeople;
                cursor = database.query("mytable", columns, null, null, groupBy, having, null);
                break;
            case R.id.btnGroup:
                trace("Население по региону");
                columns = new String[] {"region","sum(people) as people"};
                groupBy = "region";
                having = "sum(people) > " + sRegionPeople;
                cursor = database.query("mytable", columns, null, null, groupBy, having, null);
                break;
            case R.id.btnPeople:
                trace("Население больше"+people+"----");
                selection = "people > ?";
                selectionArgs = new String[] {sPeople};
                cursor = database.query("mytable", null, selection, selectionArgs, null, null,
                        null);
                break;

            case R.id.btnSort:

                switch(rgSort.getCheckedRadioButtonId()) {
                    case R.id.rName:
                        trace("Сортировка по наименованию");
                        orderBy = "name";
                        break;
                    case R.id.rPeople:
                        trace("--- Сортировка по населению ---");
                        orderBy = "people";
                        break;
                    // регион
                    case R.id.rRegion:
                        trace("--- Сортировка по региону ---");
                        orderBy = "region";
                        break;
                }
                cursor = database.query("mytable", null, null, null, null, null, orderBy);
                break;
            }
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        String str;
                        do {
                            str = "";
                            for (String cn : cursor.getColumnNames()) {
                                str = str.concat(cn + " = "
                                        + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                            }
                            trace(str);

                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                } else
                    trace("Cursor is null");

                dbHelper.close();
            }
        }



    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            Log.d("MyLog","--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement," + "name text,"
                    + "people integer," + "region text" + ");");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }



