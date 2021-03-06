package com.soho.evgeny.dialogfragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DialogFragment dlg1;
    DialogFragment dlg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dlg1 = new Dialog1();
        dlg2 = new Dialog2();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDlg1:
                dlg1.show(getFragmentManager(),"dlg1");
                break;
            case R.id.btnDlg2:
                dlg2.show(getFragmentManager(),"dlg2");
                break;
            default:
                break;
        }
    }
}
