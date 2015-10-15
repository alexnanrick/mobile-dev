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

        setListAdapter(new ArrayAdapter<>(this, R.layout.mylist,R.id.Itemname,countries));

        //setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries));
    }

    public void onListItemClick(ListView listView, View v, int position, long id) {

        switch( position )
        {
            case 0:  Intent newActivity1 = new Intent(this, Ireland.class);
                startActivity(newActivity1);
                break;
            case 1:  Intent newActivity2 = new Intent(this, SriLanka.class);
                startActivity(newActivity2);
                break;
            case 2:  Intent newActivity3 = new Intent(this, SaudiArabia.class);
                startActivity(newActivity3);
                break;
            case 3:  Intent newActivity4 = new Intent(this, China.class);
                startActivity(newActivity4);
                break;
            case 4:  Intent newActivity5 = new Intent(this, Colombia.class);
                startActivity(newActivity5);
                break;
        }
    }
}
