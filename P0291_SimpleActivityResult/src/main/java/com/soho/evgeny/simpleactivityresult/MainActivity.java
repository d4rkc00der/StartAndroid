package com.soho.evgeny.simpleactivityresult;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

    Button btnInput;
    TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInput = (Button)findViewById(R.id.btnName);
        btnInput.setOnClickListener(this);
        tvName = (TextView)findViewById(R.id.tvName);


    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, NameActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data==null)
            return;
        String name = data.getStringExtra("name");
        tvName.setText("Yoour name is "+name);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
