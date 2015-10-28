package com.zontzor.lab6_sqlite;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    DBManager db = new DBManager(this);
    EditText Task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button setButton = (Button)findViewById(R.id.button_press);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long id = 0;
                // do something when the button is clicked
                try
                {
                    db.open();
                    Task = (EditText)findViewById(R.id.edittext_task);
                    db.insertTask(Task.getText().toString());
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
