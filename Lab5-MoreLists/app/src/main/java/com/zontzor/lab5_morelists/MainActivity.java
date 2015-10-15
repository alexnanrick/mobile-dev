package com.zontzor.lab5_morelists;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> countries = new ArrayList<String>();
        countries.add("Ireland");
        countries.add("Sri Lanka");
        countries.add("Saudi Arabia");
        countries.add("China");
        countries.add("Sealand");
        countries.add("Chrismas Island");
        countries.add("DRPK");

        setListAdapter(new ArrayAdapter<>(this, R.layout.mylist, R.id.Itemname,countries));
    }

    public void onListItemClick(ListView listView, View v, int position, long id) {

        Intent myNewActivity = new Intent(MainActivity.this, Country.class);
        startActivity(myNewActivity);
    }
}
