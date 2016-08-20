package com.soho.evgeny.getintentaction;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

public class DateTimeShow extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_show);

        TextView tvInfo = (TextView)findViewById(R.id.tvInfo);
        Intent intent = getIntent();

        String action = intent.getAction();

        String format ="", textInfo ="";

        if(action.equals("com.soho.evgeny.getintentaction.showtime")) {
            format = "HH:mm:ss";
            textInfo = "Time:";

        }
        else if (action.equals("com.soho.evgeny.getintentaction.showdate")) {
            format = "dd.MM.yyyy";
            textInfo = "Date:";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String datetime = dateFormat.format(new Date(System.currentTimeMillis()));

        tvInfo.setText(datetime);

    }
}
