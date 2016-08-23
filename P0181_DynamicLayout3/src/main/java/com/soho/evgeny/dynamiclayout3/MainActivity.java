package com.soho.evgeny.dynamiclayout3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnSeekBarChangeListener {

    SeekBar sbWight;
    Button btn1;
    Button btn2;

    LinearLayout.LayoutParams lParams1;
    LinearLayout.LayoutParams lParams2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        sbWight = (SeekBar) findViewById(R.id.sbWeight);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);

        lParams1 = (LinearLayout.LayoutParams)btn1.getLayoutParams();
        lParams2 = (LinearLayout.LayoutParams)btn2.getLayoutParams();

        sbWight.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Toast.makeText(this,"Changed",Toast.LENGTH_LONG).show();
        int leftValue = seekBar.getProgress();
        int rightValue = seekBar.getMax() - i;
        btn1.setText(String.valueOf(leftValue));
        btn2.setText(String.valueOf(rightValue));
        lParams1.weight = leftValue;
        lParams2.weight = rightValue;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
