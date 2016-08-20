package com.soho.evgeny.layoutinflaterlist;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[] name = {"Петр","Виктор","Сергей","Анна","Светлана","Нататья","Андрей","Михаил","Анатолий","Игорь","Артем","Владимир","Станислав"};
    String[] position = {"Директор","Бухгалтер","Программист","Менеджер","Охранник","Юрист","Курьер","Уборщик","Менеджер","Охранник","Юрист","Курьер","Уборщик"};
    int[] salary = {50000,30000,40000,25000,10000,32000,15000,12000,25000,10000,32000,15000,12000};
    int[] colors = new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colors[0] = Color.parseColor("#559966CC");
        colors[1] = Color.parseColor("#55336699");

        LinearLayout linLayout = (LinearLayout)findViewById(R.id.linLayout);

        LayoutInflater ltInflater = getLayoutInflater();

        for(int i =0; i< name.length;i++) {
            Log.d("myLogs", "i = " + i);
            View item = ltInflater.inflate(R.layout.item, linLayout, false);
            TextView tvName = (TextView) item.findViewById(R.id.tvName);
            tvName.setText(name[i]);
            TextView tvPosition = (TextView) item.findViewById(R.id.tvPosition);
            tvPosition.setText("Должность: " + position[i]);
            TextView tvSalary = (TextView) item.findViewById(R.id.tvSalary);
            tvSalary.setText("Оклад: " + String.valueOf(salary[i]));
            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            item.setBackgroundColor(colors[i % 2]);
            linLayout.addView(item);

        }
    }
}
