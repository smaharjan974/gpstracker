package com.example.sanjay.monitoring.GeoFence;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.example.sanjay.monitoring.R;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

public class GeoFenceMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_fence_map);

//        Circle circle = map.addCircle(new CircleOptions()
//                .center(new LatLng(lattitude, longitude))
//                .radius(1000)
//                .strokeColor(Color.RED)
//                .fillColor(Color.BLUE));
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        getMenuInflater().inflate(R.menu.menu_geo_fence_map,menu);
        return super.onCreatePanelMenu(featureId, menu);
    }
}
