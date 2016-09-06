package com.soho.evgeny.fragmentdynamic;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    Fragment fragment1;
    Fragment fragment2;
    FragmentTransaction fragmentTransaction;
    CheckBox chbStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        chbStack = (CheckBox)findViewById(R.id.chbStack);
    }

    public void onClick(View view) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.btnAdd:
                fragmentTransaction.add(R.id.frgmCont,fragment1);
                fragmentTransaction.add(R.id.frgmCont,fragment2);
                break;
            case R.id.btnRemove:
                fragmentTransaction.remove(fragment1);
                fragmentTransaction.remove(fragment2);
                break;
            case R.id.btnReplace:

                break;
            default:
                break;
        }
        if(chbStack.isChecked()) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
