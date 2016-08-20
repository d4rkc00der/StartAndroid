package com.soho.evgeny.intentfilter;

import android.app.Activity;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

public class ActivityDateExt extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.yyyy.dd");
        String date = dateFormat.format(new Date(System.currentTimeMillis()));

        TextView tvDate = (TextView)findViewById(R.id.tvDate);
        tvDate.setText(date);
    }
}