package com.zontzor.lab5_morelists;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by nanrick on 15/10/15.
 */
public class Country extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_layout);

        String country = getIntent().getExtras().getString("Country");
        TextView country_sent = (TextView) findViewById(R.id.country_sent);
        country_sent.setText(country);
    }
}
