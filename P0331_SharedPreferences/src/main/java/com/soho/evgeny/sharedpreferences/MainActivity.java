package com.soho.evgeny.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    EditText edText;
    Button btnLoad, btnSave;

    SharedPreferences sPref;
    final String SAVED_TEXT = "saved_text";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edText = (EditText)findViewById(R.id.etText);
        btnLoad = (Button)findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(this);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        loadText();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnLoad:
                loadText();
                break;
            case R.id.btnSave:
                saveText();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        saveText();
        super.onDestroy();
    }

    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(SAVED_TEXT,edText.getText().toString());
        editor.apply();
        Toast.makeText(this,"Saved.",Toast.LENGTH_SHORT).show();
    }
    private void loadText(){
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT,"");
        edText.setText(savedText);
        Toast.makeText(this,"Loaded",Toast.LENGTH_SHORT).show();
    }
}
