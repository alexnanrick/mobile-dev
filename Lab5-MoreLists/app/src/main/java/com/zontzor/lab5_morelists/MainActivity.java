package com.zontzor.lab5_morelists;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] countries = new String[10];
        countries[0] = "Ireland";
        countries[1] = "Sri Lanka";
        countries[2] = "Saudi Arabia";
        countries[3] = "China";
        countries[4] = "Colombia";
        countries[5] = "Sealand";
        countries[6] = "Poland";
        countries[7] = "Whiskey Bar";
        countries[8] = "Germany";
        countries[9] = "USA";

        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries));
    }

    public void onListItemClick(ListView list, View v, int position, long id) {

    }
}
