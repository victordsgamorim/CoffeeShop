package com.dorset.coffeeshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dorset.coffeeshop.R;
import com.dorset.coffeeshop.model.Coffee;
import com.dorset.coffeeshop.model.object.CoffeeAddress;
import com.dorset.coffeeshop.ui.asynctask.CameraPositionAsyncTask;
import com.dorset.coffeeshop.ui.asynctask.PlaceMapMarkerAsyncTask;
import com.dorset.coffeeshop.ui.constants.CoffeeDataConstant;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private MapView mapView;
    private View view;
    private Coffee coffee;
    private List<CoffeeAddress> addresses;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, container, false);
        Intent data = getActivity().getIntent();

        if (data.hasExtra(CoffeeDataConstant.INTENT_DATA)) {
            coffee = (Coffee) data.getSerializableExtra(CoffeeDataConstant.INTENT_DATA);
            addresses = coffee.getAddress();
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = view.findViewById(R.id.frame_layout_map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        /**generate markers inside the map*/
        generateMarkerAsyncTask(googleMap);
        new CameraPositionAsyncTask(getActivity(), googleMap).execute(getFirstAddressFromList());

    }

    public String getFirstAddressFromList() {
        return addresses.get(0).getAddress();
    }

    private void generateMarkerAsyncTask(GoogleMap googleMap) {
        for (CoffeeAddress c : addresses) {
            new PlaceMapMarkerAsyncTask(getActivity(), googleMap).execute(c.getAddress());
        }
    }

}
