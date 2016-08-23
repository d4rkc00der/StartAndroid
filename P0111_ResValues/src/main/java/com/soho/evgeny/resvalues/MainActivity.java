package com.soho.evgeny.resvalues;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvOut = (TextView) findViewById(R.id.tvBottom);
        Button  btnBottom = (Button) findViewById(R.id.btnBottom);
        LinearLayout llBotom = (LinearLayout) findViewById(R.id.llBottom);

        tvOut.setText(getResources().getString(R.string.tvBottomText));
        btnBottom.setText(getResources().getString(R.string.btnBottomText));
        llBotom.setBackgroundColor(getResources().getColor(R.color.llBottomColor));
    }
    //
}
