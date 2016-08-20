package com.soho.evgeny.onclickbuttons;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnOk = (Button)findViewById(R.id.btnkOk);
        Button btnCancel = (Button)findViewById(R.id.btnCancel);
        final TextView tvOut = (TextView)findViewById(R.id.tvOut);

        View.OnClickListener oclbtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvOut.setText("Button ok pressed");
            }
        };
        btnOk.setOnClickListener(oclbtnOk);

        View.OnClickListener oclbtnCancel = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvOut.setText("Button cancel pressed");
            }
        };
        btnCancel.setOnClickListener(oclbtnCancel);
    }

}
