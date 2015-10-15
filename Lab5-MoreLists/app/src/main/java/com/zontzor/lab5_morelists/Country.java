package com.zontzor.lab5_morelists;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nanrick on 15/10/15.
 */
public class Country extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_layout);

        // Get string from info passed from parent
        String country = getIntent().getExtras().getString("Country");

        // Set text for selected country
        TextView country_text = (TextView) findViewById(R.id.text_set);
        country_text.setText(country);

        // Set the flag corresponding to country
        ImageView country_flag = (ImageView) findViewById(R.id.flag_set);

        switch (country) {
            case "Ireland":
                country_flag.setImageResource(R.mipmap.ireland_flag);
                break;
            case "Sri Lanka":
                country_flag.setImageResource(R.mipmap.sri_lanka_flag);
                break;
            case "Saudi Arabia":
                country_flag.setImageResource(R.mipmap.saudi_flag);
                break;
            case "China":
                country_flag.setImageResource(R.mipmap.china_flag);
                break;
            case "Sealand":
                country_flag.setImageResource(R.mipmap.sealand_flag);
                break;
            case "Christmas Island":
                country_flag.setImageResource(R.mipmap.christmas_flag);
                break;
            case "DRPK":
                country_flag.setImageResource(R.mipmap.drpk_flag);
                break;
        }


    }
}
