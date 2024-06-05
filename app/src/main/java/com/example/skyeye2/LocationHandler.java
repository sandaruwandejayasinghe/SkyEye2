package com.example.skyeye2;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.util.List;
import java.util.Locale;

public class LocationHandler {

    private static final String TAG = "LocationHandler";
    private Activity activity;

    public LocationHandler(Activity activity) {
        this.activity = activity;
    }

    public void reverseGeocodeAndSetAddress(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                String address = addresses.get(0).getAddressLine(0);
                ((MainActivity) activity).updateAddressUI(address);
            } else {
                ((MainActivity) activity).updateAddressUI("Address not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ((MainActivity) activity).updateAddressUI("Unable to get address");
        }
    }
}
