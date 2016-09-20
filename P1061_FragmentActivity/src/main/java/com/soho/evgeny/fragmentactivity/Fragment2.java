package com.soho.evgeny.fragmentactivity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by evgeny on 10.09.16.
 */
public class Fragment2 extends Fragment {
    final String LOG_TAG = "MyLog";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2,null);

        Button button = (Button)view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG,"Button click in fragment2");
                ((Button)getActivity().findViewById(R.id.btnFind)).setText("Access from Fragment2");
            }
        });
        return view;
    }
}
