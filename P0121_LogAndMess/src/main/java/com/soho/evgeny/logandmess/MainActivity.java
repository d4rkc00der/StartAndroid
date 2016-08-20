package com.soho.evgeny.logandmess;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    Button btnOk;
    Button btnCancel;
    TextView tvOut;
    private static final String TAG = "MyLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOk = (Button)findViewById(R.id.btnOk);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        tvOut = (TextView)findViewById(R.id.tvOut);
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnOk:
                tvOut.setText("Button ok pressed");
                Log.d(TAG,"Button ok pressed");
                Toast.makeText(this, "Button ok pressed", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.btnCancel:
                tvOut.setText("Button cancel pressed");
                Log.d(TAG,"Button cancel pressed");
                Toast.makeText(this,"Button cancel pressed",Toast.LENGTH_SHORT).show();
                break;

        }
    };
}
