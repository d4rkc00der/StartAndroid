package com.soho.evgeny.datepickerdialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int DIALOG_DATE =1;
    int myYear = 1985;
    int myMonth = 5;
    int myDay = 15;

    TextView tvDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDate = (TextView)findViewById(R.id.tvDate);
    }

    void onclick(View v) {
        showDialog(DIALOG_DATE);
    }

    protected Dialog onCreateDialog(int id) {
        if(id  == DIALOG_DATE) {
            DatePickerDialog dpd = new DatePickerDialog(this,myCallBack, DateFormat.getDateFormat(this).getCalendar().getWeekYear(),myMonth,myDay);
            return dpd;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            myYear = year;
            myMonth = month;
            myDay = day;

            tvDate.setText("Today is " + myDay + "/" + myMonth + "/" + myYear);
        }
    };
}
