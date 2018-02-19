package com.example.sanjay.monitoring.History;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.sanjay.monitoring.R;
import com.example.sanjay.monitoring.Track.TrackingActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    ImageView playandpause;
    boolean ch = false;
    LinearLayout r1,r2;
    MenuItem itemlist;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       if(googleServiceAvailable()){
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_history);

           SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
           mapFragment.getMapAsync(this);

           getSupportActionBar().setTitle("History");
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
           r1 = findViewById(R.id.r1);
           r2 = findViewById(R.id.r2);

           playandpause = findViewById(R.id.playandpause);

           playandpause.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
           playandpause.setOnClickListener(this);
       }else {
           Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history_menu,menu);
        itemlist = menu.findItem(R.id.speed);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.calendar:

                Dialog dialog = new Dialog(HistoryActivity.this);
                dialog.setContentView(R.layout.pop_up_menu_history_calendar);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();
                break;

            case R.id.speed:
//                Dialog dialog1 = new Dialog(HistoryActivity.this);
//                dialog1.setContentView(R.layout.pop_up_menu_history_speed);
//                dialog1.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
//                dialog1.show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public boolean googleServiceAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(HistoryActivity.this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(HistoryActivity.this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(HistoryActivity.this, "Cant connect", Toast.LENGTH_SHORT).show();
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.playandpause:
                if(ch){
                    playandpause.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                    ch=false;
                }else {
                    playandpause.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                    ch = true;
                }
        }
    }

}
