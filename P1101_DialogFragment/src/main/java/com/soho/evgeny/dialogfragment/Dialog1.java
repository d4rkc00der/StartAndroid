package com.soho.evgeny.dialogfragment;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by evgeny on 20.09.16.
 */
public class Dialog1 extends DialogFragment implements View.OnClickListener {
    final String LOG_TAG = "MyLog";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Title");

        View v = inflater.inflate(R.layout.dialog1,null);
        v.findViewById(R.id.btnYes).setOnClickListener(this);
        v.findViewById(R.id.btnNo).setOnClickListener(this);
        v.findViewById(R.id.btnMaybe).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        Log.d(LOG_TAG,"Dialog1: " + ((Button)view).getText());
        dismiss();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LOG_TAG,"Dialog1 onDismiss");
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG,"Dialog1 on Cancel");
    }
}
