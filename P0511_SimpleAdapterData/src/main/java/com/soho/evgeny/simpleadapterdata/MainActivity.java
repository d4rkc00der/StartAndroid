package com.soho.evgeny.simpleadapterdata;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int CM_DELETE_ID = 1;
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    ListView lvSimple;
    SimpleAdapter sAdapter;
    ArrayList<Map<String,Object>> data;
    Map<String,Object> m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<Map<String,Object>>();
        for(int i = 0; i < 5; i++) {
            m = new HashMap<String,Object>();
            m.put(ATTRIBUTE_NAME_TEXT,"Sometext "+i);
            m.put(ATTRIBUTE_NAME_IMAGE,R.mipmap.ic_launcher);
            data.add(m);
        }

        String[] from = {ATTRIBUTE_NAME_TEXT,ATTRIBUTE_NAME_IMAGE};
        int[] to = {R.id.tvText,R.id.ivImg};

        sAdapter = new SimpleAdapter(this,data,R.layout.item,from,to);

        lvSimple = (ListView)findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);
        registerForContextMenu(lvSimple);
    }

    public void onButtonClick(View view) {
        m = new HashMap<String,Object>();
        m.put(ATTRIBUTE_NAME_TEXT,"Sometext "+ data.size() +1);
        m.put(ATTRIBUTE_NAME_IMAGE,R.mipmap.ic_launcher);
        data.add(m);
        sAdapter.notifyDataSetChanged();
    }

    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo info) {
        super.onCreateContextMenu(menu,view,info);
        menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
    }

    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId() == CM_DELETE_ID) {
            AdapterView.AdapterContextMenuInfo acmi  = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            data.remove(acmi.position);
            sAdapter.notifyDataSetChanged();
            return  true;
        }
        return false;
    }

}
