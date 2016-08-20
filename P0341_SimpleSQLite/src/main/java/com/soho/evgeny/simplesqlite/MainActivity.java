package com.soho.evgeny.simplesqlite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "MyLog";
    Button btnAdd, btnRead, btnClear,btnUpd,btnDel;
    EditText etName, etEmail, etID;

    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = (Button)findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        etName = (EditText)findViewById(R.id.etName);
        etEmail = (EditText)findViewById(R.id.etEmail);

        btnUpd = (Button)findViewById(R.id.btnUpd);
        btnUpd.setOnClickListener(this);

        btnDel = (Button)findViewById(R.id.btnDel);
        btnDel.setOnClickListener(this);

        etID = (EditText) findViewById(R.id.etID);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View view) {
        ContentValues values = new ContentValues();

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String id = etID.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        switch(view.getId()) {
            case R.id.btnAdd:
                trace("Insert in contacts table");
                values.put("name",name);
                values.put("email",email);
                long rowID = 0;
                rowID = database.insert("contacts", null, values);
                Log.d(LOG_TAG,"row inserted id = "+rowID);
                break;
            case R.id.btnRead:
                trace("Rows in contacts table");
                Cursor cursor = database.query("contacts",null,null,null,null,null,null);

                if(cursor.moveToFirst()) {

                    int idColIndex = cursor.getColumnIndex("id");
                    int nameColIndex = cursor.getColumnIndex("name");
                    int emailColIndex = cursor.getColumnIndex("email");

                    do {
                        trace(
                                "ID = " + cursor.getInt(idColIndex) +
                                ", name = " + cursor.getString(nameColIndex) +
                                ", email = " + cursor.getString(emailColIndex)
                        );
                    } while (cursor.moveToNext());

                }
                else
                    trace("0 rows");
                cursor.close();
                break;
            case R.id.btnUpd:
                if(id.equalsIgnoreCase("")){
                    break;
                }
                trace("Update contacts");
                values.put("name",name);
                values.put("email",email);

                int updCount = database.update("contacts",values,"id = ?", new String[] { id });
                trace("Update row counts:"+updCount);
                break;
            case R.id.btnClear:
                trace("Clear contacts");
                try {
                    int clearCount = database.delete("contacts", null, null);
                    trace("Deleted rows count = " + clearCount);
                }
                catch (Exception e) {
                    trace(e.getMessage());
                }
                    break;
            case R.id.btnDel:
                if(id.equalsIgnoreCase("")){
                    break;
                }
                trace("Delete contacts table records");
                int delCount = database.delete("contacts","id = "+id,null);
                trace("Deleted rows:"+delCount);
                break;
        }
        try {
            database.close();
        }
        catch (Exception e) {
            trace(e.getMessage());
        }
    }

    void trace(String message) {
        Log.d(LOG_TAG,message);
    }
    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context,"organizer",null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            trace("DBHelper onCreate() called");
            try {
                sqLiteDatabase.execSQL("create table contacts ("
                        + "id integer primary key autoincrement,"
                        + "name text,"
                        + "email text" + ");");
                trace("table contacts created");
            }
            catch (Exception e) {
                trace(e.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            trace("DBHelper onUpgrade() called");
        }
    }
}


