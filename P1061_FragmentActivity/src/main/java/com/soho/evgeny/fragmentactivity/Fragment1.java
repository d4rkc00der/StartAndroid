package com.soho.evgeny.fragmentactivity;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by evgeny on 10.09.16.
 */
public class Fragment1 extends Fragment {
    final String LOG_TAG = "MyLogs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragments1,null);
        Button button = (Button)v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG,"Button click in Fragment1");
                ((Button)getActivity().findViewById(R.id.btnFind)).setText("Access from Fragment1");
            }
        });

        

        return v;
    }
}
