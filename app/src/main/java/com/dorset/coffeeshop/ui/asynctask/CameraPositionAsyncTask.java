package com.dorset.coffeeshop.ui.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.dorset.coffeeshop.util.LatLgnUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class CameraPositionAsyncTask extends AsyncTask<String, Void, LatLng> {


    private final Context context;
    private final GoogleMap googleMap;

    public CameraPositionAsyncTask(Context context, GoogleMap googleMap) {

        this.context = context;
        this.googleMap = googleMap;
    }

    @Override
    protected LatLng doInBackground(String... strings) {
        return LatLgnUtil.addressConverterLatLgn(context, strings[0]);
    }

    @Override
    protected void onPostExecute(LatLng latLng) {
        super.onPostExecute(latLng);
        CameraPosition cameraPosition = CameraPosition.builder()
                .target(latLng)
                .zoom(13).bearing(0)
                .tilt(45)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}
