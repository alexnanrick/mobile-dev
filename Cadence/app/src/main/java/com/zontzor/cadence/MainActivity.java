package com.zontzor.cadence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity {
    GridView gridView;
    static final String[] options = new String[] {
            "Profile", "Rides","Goals", "Cadence" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                switch(selection){
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
}
