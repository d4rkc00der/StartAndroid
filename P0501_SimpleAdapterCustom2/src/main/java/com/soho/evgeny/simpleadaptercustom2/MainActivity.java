package com.soho.evgeny.simpleadaptercustom2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_PB = "pb";
    final String ATTRIBUTE_NAME_LL = "ll";

    ListView lvSimple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int load[] = { 41, 48, 22, 35, 30, 67, 51, 88 };

        ArrayList<Map<String,Object>> data = new ArrayList<Map<String,Object>>(load.length);
        Map<String,Object> map;
        for(int i = 0; i < load.length; i++) {
            map = new HashMap<String,Object>();
            map.put(ATTRIBUTE_NAME_TEXT,"Day " + (i+1) + ". Load: " + load[i] + "%");
            map.put(ATTRIBUTE_NAME_LL,load[i]);
            map.put(ATTRIBUTE_NAME_PB,load[i]);
            data.add(map);
        }

        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_PB,
                ATTRIBUTE_NAME_LL };

        int[] to = { R.id.tvLoad, R.id.pbLoad, R.id.llLoad };

        SimpleAdapter adapter = new SimpleAdapter(this,data,R.layout.item,from,to);
        adapter.setViewBinder(new MyBinder());
        lvSimple = (ListView)findViewById(R.id.lvSimple);
        lvSimple.setAdapter(adapter);

    }

    class MyBinder implements SimpleAdapter.ViewBinder {

        int red = getResources().getColor(R.color.Red);
        int orange = getResources().getColor(R.color.Orange);
        int green = getResources().getColor(R.color.Green);

        @Override
        public boolean setViewValue(View view, Object data, String s) {
            int i = 0;
            switch(view.getId()) {
                case R.id.llLoad:
                    i = ((Integer) data).intValue();
                    if (i < 40) view.setBackgroundColor(green); else
                    if (i < 70) view.setBackgroundColor(orange); else
                        view.setBackgroundColor(red);
                    return true;
                case R.id.pbLoad:
                    i = ((Integer) data).intValue();
                    ((ProgressBar)view).setProgress(i);
                    return true;
            }
            return false;
        }
    }

}
