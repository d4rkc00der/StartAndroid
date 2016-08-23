package com.soho.evgeny.listener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnOk = (Button)findViewById(R.id.btnOk);
        Button btnCancel = (Button)findViewById(R.id.btnCancel);
        final TextView tvOut = (TextView)findViewById(R.id.tvOut);

        View.OnClickListener oclbtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId())
                {
                    case R.id.btnOk:
                        tvOut.setText("Button ok pressed");
                        break;
                    case R.id.btnCancel:
                        tvOut.setText("Button cancel pressed");
                        break;
                }
            }
        };
        btnOk.setOnClickListener(oclbtn);
        btnCancel.setOnClickListener(oclbtn);
        //
    }



}
