package com.example.weatherapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SplashActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private String latLong;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        requestPermission();
        textView = findViewById(R.id.location);
        getLocation();
    }

    private void getLocation() {
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null) {
                latLong = location.getLatitude() + "," + location.getLongitude();
                textView.setText(latLong);
            }
        });
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }
}
