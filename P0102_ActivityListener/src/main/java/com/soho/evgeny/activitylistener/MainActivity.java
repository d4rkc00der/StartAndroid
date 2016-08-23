package com.soho.evgeny.activitylistener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
public class MainActivity extends Activity implements OnClickListener {
    TextView tvOut;
    Button btnOk;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOut = (TextView)findViewById(R.id.tvOut);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnOk.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnOk:
                tvOut.setText("Ok Button pressed");
                break;
            case R.id.btnCancel:
                tvOut.setText("Cancel button pressed");
                break;
        }
        //
    }


}
