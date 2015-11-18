package com.zontzor.lab9_locationservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyLocation extends AppCompatActivity implements LocationListener {

    private LocationManager locationManager;
    private TextView locationText;
    private Button locationButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpstest);
        locationText = (TextView) findViewById(R.id.text_location);
        locationButton = (Button) findViewById(R.id.btn_get_loc);
        setupLocation();

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        String latestLocation = "";

        if (location != null) {
            latestLocation = String.format("Current Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude());
        }

        Toast.makeText(MyLocation.this, latestLocation, Toast.LENGTH_LONG).show();
        locationText.setText(latestLocation);
    }
    public void onProviderDisabled(String arg0) {
        Log.e("GPS", "provider disabled " + arg0);
    }
    public void onProviderEnabled(String arg0) {
        Log.e("GPS", "provider enabled " + arg0);
    }
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        Log.e("GPS", "status changed to " + arg0 + " [" + arg1 + "]");
    }

    private void setupLocation() {
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 5, this);
        } catch (SecurityException e) {
            Log.e("PERMISSION_EXCEPTION","PERMISSION_NOT_GRANTED");
        }
    }

    protected void onResume() {
        super.onResume();
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 5, this);
        } catch (SecurityException e) {
            Log.e("PERMISSION_EXCEPTION","PERMISSION_NOT_GRANTED");
        }
    }

    protected void onPause() {
        super.onPause();
        try {
            locationManager.removeUpdates(this);
        } catch (SecurityException e) {
            Log.e("PERMISSION_EXCEPTION","PERMISSION_NOT_GRANTED");
        }
    }
}
