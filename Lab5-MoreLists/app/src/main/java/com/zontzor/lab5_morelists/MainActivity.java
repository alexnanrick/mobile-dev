package com.zontzor.lab5_morelists;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
        countries.add("Christmas Island");
        countries.add("DRPK");

        ArrayAdapter basicAdapter = new ArrayAdapter<>(this, R.layout.mylist, R.id.Itemname,countries);
        setListAdapter(basicAdapter);
    }

    public void onListItemClick(ListView list, View v, int position, long id) {
        String selection = list.getItemAtPosition(position).toString();
        Intent myNewActivity = new Intent(MainActivity.this, Country.class);
        myNewActivity.putExtra("Country", selection);
        startActivity(myNewActivity);
    }
/*
    class myAdapter extends ArrayAdapter {
        private ArrayList<String> objects;

        public myAdapter(Context context, int textViewResourceID, )
    }*/
}
