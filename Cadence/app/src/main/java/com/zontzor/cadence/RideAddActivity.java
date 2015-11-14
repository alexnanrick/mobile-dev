package com.zontzor.cadence;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class RideAddActivity extends Activity {
    DBManager db = new DBManager(this);
    EditText rideName;
    EditText rideDuration;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_add);
    }
}
