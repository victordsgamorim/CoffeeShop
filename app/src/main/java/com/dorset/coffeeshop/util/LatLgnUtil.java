package com.dorset.coffeeshop.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class LatLgnUtil {

    public static LatLng addressConverterLatLgn(Context context, String address) {
        Geocoder coder = new Geocoder(context);
        List<Address> addresses;
        LatLng p1 = null;

        try {
            addresses = coder.getFromLocationName(address, 5);
            if (address == null) {
                return null;
            }

            Address location = addresses.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude());
            return p1;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
