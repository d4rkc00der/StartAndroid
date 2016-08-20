package com.soho.evgeny.simpleintents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    Button btnWeb, btnMap, btnCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCall = (Button)findViewById(R.id.btnCall);
        btnMap = (Button)findViewById(R.id.btnMap);
        btnWeb = (Button)findViewById(R.id.btnWeb);

        btnCall.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnWeb.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnCall:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:89160967505"));
                startActivity(intent);
                break;
            case R.id.btnMap:
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:51.544,37.2232"));
                startActivity(intent);
                break;
            case R.id.btnWeb:
                intent  = new Intent(Intent.ACTION_VIEW, Uri.parse("http://vk.com"));
                startActivity(intent);
                break;
        }
    }
}
