package com.soho.evgeny.fragmentactivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment frag2 = new Fragment2();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment2,frag2);
        fragmentTransaction.commit();
    }

    public void onClick(View view) {
        Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragment1);
        ((TextView)frag1.getView().findViewById(R.id.textView)).setText("Access to Fragment1 from MainActivity");

        Fragment frag2 = getFragmentManager().findFragmentById(R.id.fragment2);
        ((TextView)frag2.getView().findViewById(R.id.textView)).setText("Access to Fragment2 from MainActivity");
    }
}
