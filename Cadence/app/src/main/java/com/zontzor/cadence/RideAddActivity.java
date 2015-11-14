package com.zontzor.cadence;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RideAddActivity extends Activity {
    DBManager db = new DBManager(this);
    EditText rideName;
    EditText rideDuration;
    Button btnAddRide;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_add);

        rideName = (EditText) findViewById(R.id.editText_ride_name);
        rideDuration = (EditText) findViewById(R.id.editText_ride_duration);
        btnAddRide = (Button) findViewById(R.id.button_add_ride);

        btnAddRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.open();
                    db.insertRide(rideName.getText().toString(), Integer.parseInt(rideDuration.getText().toString()), 1, 1);
                    db.close();
                } catch (Exception e) {
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
