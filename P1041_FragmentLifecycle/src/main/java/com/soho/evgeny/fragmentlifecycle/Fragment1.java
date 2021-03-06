package com.soho.evgeny.fragmentlifecycle;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Fragment1 extends Fragment {

    final String LOG_TAG = "MyLog";
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(LOG_TAG,"Fragment1 onAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG,"Fragment1 onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOG_TAG,"Fragment1 onCreateView()");
        return inflater.inflate(R.layout.fragment1,null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(LOG_TAG,"Fragment1 onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG,"Fragment1 onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG,"Fragment1 onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG,"Fragment1 onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG,"Fragment1 onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(LOG_TAG,"Fragment1 onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,"Fragment1 onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(LOG_TAG,"Fragment1 onDetach()");
    }
}
