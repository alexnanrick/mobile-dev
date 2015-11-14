package com.zontzor.cadence;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    GridView gridView;
    DBManager db = new DBManager(this);
    static final String[] options = new String[] {
            "Profile", "Rides","Goals", "Cadence" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            db.open();
            //testDB();
            db.close();
        } catch (Exception ex) {
            Context context = getApplicationContext();
            CharSequence text = "Error opening database";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        setupGrid();
    }

    public void setupGrid() {
        gridView = (GridView) findViewById(R.id.menu_grid);
        gridView.setAdapter(new MainMenuAdapter(this, options));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent myNewActivity = null;

                TextView textView = (TextView) v.findViewById(R.id.grid_item_label);
                String selection = (String) textView.getText();

                switch (selection) {
                    case "Profile":
                        myNewActivity = new Intent(MainActivity.this, UserProfileActivity.class);
                        break;
                    case "Rides":
                        myNewActivity = new Intent(MainActivity.this, UserRidesActivity.class);
                        break;
                    case "Goals":
                        myNewActivity = new Intent(MainActivity.this, UserGoalsActivity.class);
                        break;
                    case "Cadence":
                        myNewActivity = new Intent(MainActivity.this, CadenceCalcActivity.class);
                        break;
                }

                startActivity(myNewActivity);
            }
        });
    }

    public void testDB() {
        try {
            db.insertUser("Zont", "1234", "Alex", "Kiernan");
            db.insertBicycle("My Racer", "Road Bike", "Bianchi", "Bike has 11 speed groupset", 1);
            db.insertRide("Lunch Ride", 3, 1, 1);
        } catch (Exception e) {
            Context context = getApplicationContext();
            CharSequence text = "Error inserting data";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
