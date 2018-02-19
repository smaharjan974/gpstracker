package com.example.sanjay.monitoring.Track;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sanjay.monitoring.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class TrackingActivity extends AppCompatActivity implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {

    GoogleMap map;
    ImageView option,route,straightline;
    boolean ch = false;
    double longitude, latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        if (googleServiceAvailable()) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tracking);

            getSupportActionBar().setTitle("Tracking");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
            mapFragment.getMapAsync(this);

            option = findViewById(R.id.option);
            option.setImageResource(R.drawable.ic_user_silhouette);
            route = findViewById(R.id.route);
            route.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "https://www.google.com/maps/dir/?api=1&destination=" + 27.680089 + "," + 85.288153 + "&travelmode=driving";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
            });

            option.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ch) {
                        ch = false;
                        option.setImageResource(R.drawable.ic_user_silhouette);
                        gotolocation(27.680089, 85.288153, 15);

                    } else {
                        ch = true;
                        option.setImageResource(R.drawable.ic_car_icon);
                        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        if (ActivityCompat.checkSelfPermission(TrackingActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(TrackingActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

                        LatLng myPosition = new LatLng(latitude, longitude);

                        MarkerOptions currentLocationMarker = new MarkerOptions()
                                .position(myPosition);
//                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_position));

                        map.addMarker(currentLocationMarker);
                        gotolocation(latitude, longitude, 15);
                    }
                }
            });


        } else {
            //No google maps
            Toast.makeText(TrackingActivity.this, "no data", Toast.LENGTH_SHORT).show();
        }


    }

    public boolean googleServiceAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(TrackingActivity.this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(TrackingActivity.this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(TrackingActivity.this, "Cant connect", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
//        map.getUiSettings().setMyLocationButtonEnabled(false);
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
        map.setMyLocationEnabled(true);
        map.setOnInfoWindowClickListener(this);
        map.getUiSettings().setMapToolbarEnabled(false);

        map.addMarker(new MarkerOptions().position(new LatLng(27.680089, 85.288153)).title("FZS-6349" ).snippet("2017-08-05 2:00")).showInfoWindow();
        gotolocation(27.680089, 85.288153, 15);
    }

    private void gotolocation(double lattitude, double longitude, float zoom) {
        LatLng latLng = new LatLng(lattitude, longitude);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        map.moveCamera(update);

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(TrackingActivity.this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
