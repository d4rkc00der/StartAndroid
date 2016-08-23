package com.soho.evgeny.twoactivity;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    Button btnActTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActTwo = (Button)findViewById(R.id.btnActTwo);
        btnActTwo.setOnClickListener(this);
    }
    //
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnActTwo:
                //
                Intent intent = new Intent(this,ActivityTwo.class);
                startActivityForResult(intent,0);
                break;
            default:
                break;
        }
    }
}
