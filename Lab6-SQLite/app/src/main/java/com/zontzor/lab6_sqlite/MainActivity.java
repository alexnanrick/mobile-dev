package com.zontzor.lab6_sqlite;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

public class MainActivity extends Activity {
    DBManager db = new DBManager(this);
    EditText taskName;
    EditText taskDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button setButton = (Button)findViewById(R.id.button_submit);
        Button getButton = (Button)findViewById(R.id.button_retrieve);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long id = 0;
                // do something when the button is clicked
                try
                {
                    db.open();
                    taskName = (EditText)findViewById(R.id.editText_taskname);
                    taskDesc = (EditText)findViewById(R.id.editText_taskdesc);
                    db.insertTask(taskName.getText().toString(), taskDesc.getText().toString());
                    db.close();
                }
                catch (Exception ex)
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Error opening database";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long id = 0;
                Cursor result;
                String str;
                // do something when the button is clicked
                try
                {
                    db.open();
                    taskName = (EditText)findViewById(R.id.editText_taskname);
                    result = db.getTask(taskName.getText().toString());

                    str = result.getString(result.getColumnIndex("description"));

                    Context context = getApplicationContext();
                    CharSequence text = str;
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    db.close();
                }
                catch (Exception ex)
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Error opening database";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });
    }
}
