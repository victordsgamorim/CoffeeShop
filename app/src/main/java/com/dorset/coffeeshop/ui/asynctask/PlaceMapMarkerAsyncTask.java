package com.dorset.coffeeshop.ui.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.dorset.coffeeshop.util.LatLgnUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PlaceMapMarkerAsyncTask extends AsyncTask<String, Void, LatLng> {

    private Context context;
    private GoogleMap googleMap;
    private String address;

    public PlaceMapMarkerAsyncTask(Context context, GoogleMap googleMap) {

        this.context = context;
        this.googleMap = googleMap;
    }


    @Override
    protected LatLng doInBackground(String... strings) {
        address = strings[0];
        return LatLgnUtil.addressConverterLatLgn(context, address);
    }

    @Override
    protected void onPostExecute(LatLng latLng) {
        super.onPostExecute(latLng);
        googleMap.addMarker(
                new MarkerOptions()
                        .position(latLng)
                        .title(address));
    }
}
