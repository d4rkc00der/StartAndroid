package com.soho.evgeny.dynamiclayout2;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import java.sql.BatchUpdateException;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{
    LinearLayout llMain;
    RadioGroup rgGravity;
    EditText etName;
    Button btnCreate;
    Button btnClear;

    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llMain = (LinearLayout) findViewById(R.id.llMain);
        rgGravity = (RadioGroup) findViewById(R.id.rgGravity);
        etName = (EditText) findViewById(R.id.etName);

        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btnCreate:
                LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(wrapContent,wrapContent);
                int btnGravity = Gravity.CENTER;

                switch(rgGravity.getCheckedRadioButtonId())
                {
                    case R.id.rbCenter:
                        btnGravity = Gravity.CENTER;
                        break;
                    case R.id.rbLeft:
                        btnGravity = Gravity.LEFT;
                        break;
                    case R.id.rbRight:
                        btnGravity = Gravity.RIGHT;
                        break;
                }

                lParams.gravity = btnGravity;



                    Button btn = new Button(this);
                    btn.setText(etName.getText().toString());

                    llMain.addView(btn,lParams);

                break;
            case R.id.btnClear:
                llMain.removeAllViews();
                break;
        }
    }
}
