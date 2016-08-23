package com.soho.evgeny.simplecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    Button btnAdd,btnSub,btnDiv,btnMult;
    EditText etNum1,etNum2;
    TextView tvResult;
    String oper = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnSub = (Button)findViewById(R.id.btnSub);
        btnDiv = (Button)findViewById(R.id.btnDiv);
        btnMult = (Button)findViewById(R.id.btnMult);
        etNum1 = (EditText)findViewById(R.id.etNum1);
        etNum2 = (EditText)findViewById(R.id.etNum2);
        tvResult = (TextView)findViewById(R.id.tvResult);

        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        //
    }

    @Override
    public void onClick(View view) {
        float num1,num2,result = 0;
        if(TextUtils.isEmpty(etNum1.getText().toString())
                || TextUtils.isEmpty(etNum2.getText().toString())) {
            return;
        }

        num1 = Float.parseFloat(etNum1.getText().toString());
        num2 = Float.parseFloat(etNum2.getText().toString());

        switch (view.getId())
        {
            case R.id.btnAdd:
                result = num1 + num2;
                oper = "+";
                break;
            case R.id.btnSub:
                result = num1 - num2;
                oper = "-";
                break;
            case R.id.btnMult:
                result = num1 * num2;
                oper = "*";
                break;
            case R.id.btnDiv:
                if(num2!=0){
                    result = num1 / num2;
                    oper = "/";
                }
                else {
                    tvResult.setText("");
                    Toast.makeText(this,"Devide by zero incorrect",Toast.LENGTH_SHORT).show();
                    return;
                }
                break;

        }
        tvResult.setText(num1 + " " + oper + " " + num2 + "=" + String.valueOf(result));

    }
}
