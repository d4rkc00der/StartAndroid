package com.soho.evgeny.simpleactivityresult;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NameActivity extends Activity implements View.OnClickListener{

    EditText etName;
    Button btnOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        etName = (EditText)findViewById(R.id.etName);
        btnOk = (Button)findViewById(R.id.btnOK);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("name",etName.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }
}
