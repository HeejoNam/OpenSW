package io.google.checkout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.codelab.friendlychat.DistanceActivity;
import com.google.firebase.codelab.friendlychat.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLngBounds.Builder mBounds = new LatLngBounds.Builder();
    private double latitude;
    private double longitude;
    Location ward;
    Location locationB;
    Float between_distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Set up Google Maps
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Map setup. This is called when the GoogleMap is available to manipulate.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationB = new Location("point B");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                locationB.setLatitude(latitude);
                locationB.setLongitude(longitude);
                addPointToViewPort(ll);
                // we only want to grab the location once, to allow the user to pan and zoom freely.
                mMap.setOnMyLocationChangeListener(null);

            }
        });
        // Pad the map controls to make room for the button - note that the button may not have
// been laid out yet.
        // Get the Intent that started this activity and extract the string   
        Intent intent = getIntent();
        String message = intent.getStringExtra(DistanceActivity.DISTANCE);
        final int distance = Integer.parseInt(message);
        ward = new Location("point W");
        ward.setLatitude(37.566433);
        ward.setLongitude(126.948413);
        between_distance = ward.distanceTo(locationB);

        final Button button = (Button) findViewById(R.id.checkout_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (between_distance > distance) {
                    Toast.makeText(getApplicationContext(), String.valueOf(latitude) + "\t" + String.valueOf(longitude), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void addPointToViewPort(LatLng newPoint) {
        mBounds.include(newPoint);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(mBounds.build(),
                findViewById(R.id.checkout_button).getHeight()));
    }
}