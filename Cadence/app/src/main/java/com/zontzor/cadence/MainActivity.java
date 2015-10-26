package com.zontzor.cadence;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

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
        gridView.setAdapter(new MyAdapter(this, options));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

            }
        });
    }
}
