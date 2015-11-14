package com.zontzor.cadence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserRidesActivity extends Activity {
    private Button btnAddRide;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rides);

        btnAddRide = (Button) findViewById(R.id.btn_rides_add);

        btnAddRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addRideActivity = new Intent(UserRidesActivity.this, RideAddActivity.class);
                startActivity(addRideActivity);
            }
        });
    }
}
