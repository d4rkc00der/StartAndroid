package com.soho.evgeny.intentfilter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btntime = (Button)findViewById(R.id.btnTime);
        Button btnDate = (Button)findViewById(R.id.btnDate);

        btntime.setOnClickListener(this);
        btnDate.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()) {
            case R.id.btnTime:
                intent = new Intent("com.soho.evgeny.intentfilter.showtime");
                startActivity(intent);
                break;
            case R.id.btnDate:
                intent = new Intent("com.soho.evgeny.intentfilter.showdate");
                startActivity(intent);
                break;
        }
    }
}
