package com.zontzor.lab7_sqlitelists;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;

public class MainActivity extends Activity {
    DBManager db = new DBManager(this);
    EditText taskName;
    EditText taskDesc;
    TextView taskStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button setButton = (Button)findViewById(R.id.button_submit);
        Button getButton = (Button)findViewById(R.id.button_retrieve);

        final ListView listTasks = (ListView) findViewById(R.id.listView_tasks);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something when the button is clicked
                try {
                    db.open();
                    taskName = (EditText) findViewById(R.id.editText_taskname);
                    taskDesc = (EditText) findViewById(R.id.editText_taskdesc);
                    db.insertTask(taskName.getText().toString(), taskDesc.getText().toString());
                    db.close();
                } catch (Exception ex) {
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
                // do something when the button is clicked
                try {
                    db.open();
                    Cursor result = db.getAll();
                    MyCursorAdapter cursorAdapter = new MyCursorAdapter(MainActivity.this, result);
                    listTasks.setAdapter(cursorAdapter);
                    db.close();
                } catch (Exception ex) {
                    Context context = getApplicationContext();
                    CharSequence text = "Error opening database";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        listTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View view, int position, long arg) {
                //taskStatus = (TextView) findViewById(R.id.TextView_task_comp);
                Cursor mycursor = (Cursor) av.getItemAtPosition(position);
                String selection = mycursor.getString(3);

                Context context = getApplicationContext();
                CharSequence text = selection;
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}