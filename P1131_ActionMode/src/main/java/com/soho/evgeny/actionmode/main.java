package com.soho.evgeny.actionmode;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class main extends Activity {

    final String LOG_TAG = "MyLog";

    android.view.ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public void onClick(View view){
        if(actionMode == null)
            actionMode = startActionMode(callback);
        else
            actionMode.finish();
    }

    ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }



        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.d(LOG_TAG, "item " + item.getTitle());
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Log.d(LOG_TAG, "destroy");
            actionMode = null;
        }
    };

}
