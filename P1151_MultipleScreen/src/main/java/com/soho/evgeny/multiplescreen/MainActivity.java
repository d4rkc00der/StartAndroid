package com.soho.evgeny.multiplescreen;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements TestFragment.onItemClickListener {

    int position = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            position = savedInstanceState.getInt("position");
            showDetails(position);
        }
    }

    void showDetails(int pos){
        DetailsFragment detailsFragment = (DetailsFragment)getSupportFragmentManager()
                .findFragmentById(R.id.cont);
        if(detailsFragment == null || detailsFragment.getPosition() != pos){
            detailsFragment = DetailsFragment.newInstance(pos);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.cont, detailsFragment).commit();
        }
    }

    public void itemClick(int position){
        this.position = position;
        showDetails(position);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
    }
}
