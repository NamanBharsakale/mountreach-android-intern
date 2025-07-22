package com.example.mysplashscr;

import androidx.fragment.app.FragmentActivity;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import com.example.mysplashscr.databinding.ActivityMyLocationBinding;

public class MyLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMyLocationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMyLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Example location 1 – Office/Origin
        LatLng originLocation = new LatLng(20.924376, 77.340127); // Replace with generalized or fake coordinates if needed
        mMap.addMarker(new MarkerOptions()
                .position(originLocation)
                .title("Origin Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_user))); // Replace with placeholder if needed

        mMap.moveCamera(CameraUpdateFactory.newLatLng(originLocation));
        mMap.addCircle(new CircleOptions()
                .strokeColor(Color.parseColor("#ff80ff"))
                .fillColor(0x330000FF)
                .radius(300)
                .center(originLocation));

        // Example location 2 – Institute/Destination
        LatLng destinationLocation = new LatLng(20.9463121823689, 77.76384974677566);
        mMap.addMarker(new MarkerOptions()
                .position(destinationLocation)
                .title("Institute Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_institute))); // Replace with placeholder if needed

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destinationLocation, 16), 5000, null);

        // Draw polyline between two points
        mMap.addPolyline(new PolylineOptions()
                .add(originLocation, destinationLocation)
                .color(Color.parseColor("#ff9933"))
                .width(3));

        mMap.addCircle(new CircleOptions()
                .strokeColor(Color.parseColor("#ff80ff"))
                .fillColor(0x330000FF)
                .radius(300)
                .center(destinationLocation));
    }
}
