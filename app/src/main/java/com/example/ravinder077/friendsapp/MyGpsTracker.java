package com.example.ravinder077.friendsapp;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.List;

/**
 * Created by SandahSaab on 8/10/2017.
 */

public class MyGpsTracker extends Activity


{
    Geocoder geocoder;
    String bestProvider;
    List<Address> user = null;
    double lat;
    double lng;
    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.login);


        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        bestProvider = lm.getBestProvider(criteria, false);

        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {

                            android.Manifest.permission.ACCESS_COARSE_LOCATION
            },
                    MY_PERMISSION_ACCESS_COARSE_LOCATION );
        }
        Location location = lm.getLastKnownLocation(bestProvider);

        if (location == null) {
            Toast.makeText(this, "Location Not found", Toast.LENGTH_LONG).show();
        } else {
            geocoder = new Geocoder(this);
            try {
                user = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                lat = (double) user.get(0).getLatitude();
                lng = (double) user.get(0).getLongitude();
                System.out.println(" DDD lat: " + lat + ",  longitude: " + lng);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
