package com.soho.evgeny.headerfooter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public String LOG_TAG = "MyLog";
    String[] data = {"one","two","three","four","five"};
    ListView lvMain;
    ArrayAdapter<String> adapter;

    View header1;
    View header2;
    View footer1;
    View footer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMain = (ListView)findViewById(R.id.lvMain);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);

        header1 = createHeader("header 1");
        header2 = createHeader("header 2");
        footer1 = createFooter("footer 1");
        footer2 = createFooter("footer 2");

        fillList();
    }

    void fillList() {
        try {
            lvMain.setAdapter(adapter);
            lvMain.addHeaderView(header1);
        } catch (Exception e) {
            Log.d(LOG_TAG,e.getMessage());
        }
    }
    public void onClick(View view) {

    }

    View createHeader(String text){
        View v = getLayoutInflater().inflate(R.layout.header,null);
        ((TextView)v.findViewById(R.id.tvText)).setText(text);
        return v;
    }

    View createFooter(String text) {
        View v = getLayoutInflater().inflate(R.layout.footer,null);
        ((TextView)v.findViewById(R.id.tvText)).setText(text);
        return v;
    }
}
