package com.example.bookmanager;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
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
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;



public class GoogleMaps extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    public GoogleMap mMap;
    // Context context;
    Button view_bookstores, view_libraries;
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
       // refresh_location = findViewById(R.id.refreshLocation);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        /*     SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                 .findFragmentById(R.id.map);
           mapFragment.getMapAsync(this);*/

      LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Activate your GPS");
            alert.setMessage("Your location seems to be not activated. Would you like to activate it?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                        /*MarkerOptions markerOption = new MarkerOptions().position(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())).title("Your location");

                        Marker marker = mMap.addMarker(markerOption);
                        marker.showInfoWindow();
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), 12));
                        mMap.addMarker(markerOption);*/



                        //  CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()).zoom(12).build();

                        //   mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(GoogleMaps.this, HomePage.class));
                }
            });
            alert.create().show();


        } else {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            fetchLocation();

        }


        /*if(!checkGPS()){
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Activate your GPS");
            alert.setMessage("Your location seems to be not activated. Would you like to activate it?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    context.startActivity(new Intent(context, HomePage.class));
                }
            });
            alert.show();
        } else
            fetchLocation();*/
     /*   if(!checkInternet()){
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Activate your network");
            alert.setMessage("Your Internet Network seems to be not activated. Would you like to activate it?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    context.startActivity(new Intent(context, HomePage.class));
                }
            });
            alert.create().show();
        } else*/


 /*       refresh_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           *//*    Intent i = new Intent(GoogleMaps.this, GoogleMaps.class);
                finish();
                overridePendingTransition(0, 0);
                startActivity(i);
                overridePendingTransition(0, 0);*//*

               *//* LocationRequest locationRequest = LocationRequest.create();
                locationRequest.setInterval(10000);
                locationRequest.setFastestInterval(5000);
                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);*//*

               // fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(currentLocation);
                mMap.clear();
                fetchLocation();
                LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("This is your location!");
                mMap.addMarker(markerOptions);
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));



            }
        });*/


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


  /*  private boolean checkGPS() {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

    }
 private boolean checkInternet() {
     LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
     return lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
 }*/


    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }else {
         /*   lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0, (android.location.LocationListener) locationListener);
            Location lastKnownLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            LatLng userLocation = new LatLng(lastKnownLocation.getLatitude(),lastKnownLocation.getLongitude());
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(userLocation).title(""));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,12));*/
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    Toast.makeText(getApplicationContext(), "Latitude: " + currentLocation.getLatitude() + " " + "Longitude: " + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    assert supportMapFragment != null;
                    supportMapFragment.getMapAsync(GoogleMaps.this);
                }
            }
        });
    }

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
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLocation();
                }
                break;
        }
    }


    @Override
    public void onLocationChanged(Location location) {
      /*  fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLocation();
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("This is your location!");
        mMap.addMarker(markerOptions);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));*/


    }

    /*   @Override
       protected void onResume() {
           super.onResume();
           if(mMap!=null){
               mMap.clear();
               LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
               MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("This is your location!");
               mMap.addMarker(markerOptions);
               mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
               mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
           }
       }*/
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(userLocation).title(""));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,12));
        }
    };

}