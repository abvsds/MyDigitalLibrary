package com.example.bookmanager;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;



public class GoogleMaps extends AppCompatActivity implements OnMapReadyCallback{

    public GoogleMap mMap;
    Button view_bookstores, view_libraries, refreshLocation;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;

    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);


        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FF6200EE"));
        actionBar.setBackgroundDrawable(colorDrawable);

        view_bookstores = findViewById(R.id.bookstores);
        view_libraries = findViewById(R.id.libraries);
        refreshLocation=findViewById(R.id.refreshLocation);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLocation();

        refreshLocation.setOnClickListener(new View.OnClickListener() {
           @Override
        public void onClick(View v) {
        finish();
        startActivity(getIntent());
         }
        });
        view_bookstores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url1 = "https://www.google.ro/maps/search/bookstore/" + currentLocation.getLatitude() + "," + currentLocation.getLongitude() + "z/data=!3m1!4b1";
                Uri uri = Uri.parse(url1);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);


            }
        });

        view_libraries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url2 = "https://www.google.ro/maps/search/library/" + currentLocation.getLatitude() + "," + currentLocation.getLongitude() + "z/data=!3m1!4b1";
                Uri uri = Uri.parse(url2);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);

            }
        });
    }



    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE);
            return;
        }
        if (GPSisEnabled()) {
            Task<Location> task = fusedLocationProviderClient.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        currentLocation = location;
                        Toast.makeText(getApplicationContext(), "Latitude: " + currentLocation.getLatitude() + "; " + "Longitude: " + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                        assert supportMapFragment != null;
                        supportMapFragment.getMapAsync(GoogleMaps.this);
                    }
                    else{
                        requestNewLocationData();
                    }
                }
            });
        } else{
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Activate your GPS");
            alert.setMessage("Your location seems to be not activated. Would you like to activate it?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(GoogleMaps.this, HomePage.class));
                }
            });
            alert.create().show();
        }
    }


    private final LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("This is your location!");
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
            mMap.addMarker(markerOptions);
        }
    };

        public boolean GPSisEnabled(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1*1000);
        mLocationRequest.setFastestInterval(1*1000);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("This is your location!");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
        googleMap.addMarker(markerOptions);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation();
            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            fetchLocation();
        }
    }

}