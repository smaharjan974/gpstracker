package com.example.sanjay.monitoring.DeviceList.Monitor;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sanjay.monitoring.DeviceList.threeframgent.ThreeFragment;
import com.example.sanjay.monitoring.GeoFence.GeoFenceActivity;
import com.example.sanjay.monitoring.History.HistoryActivity;
import com.example.sanjay.monitoring.R;
import com.example.sanjay.monitoring.Track.TrackingActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.widget.LinearLayout.*;

/**
 * Created by SANJAY on 2/15/2018.
 */

public class MonitoringFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, OnClickListener {

    GoogleMap map;
    View view;
    FrameLayout frameLayout;
    ImageView arrow,left,right;
    LinearLayout linear, linear1,tracking,history,geofence,alarm,command;
    boolean ch = false;
    MenuItem fullscreen,home,action_setting;
    FragmentManager fm;
    FragmentTransaction ft;
    View marker;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (googleServiceAvailable()) {
            view = inflater.inflate(R.layout.monitoring_fragment, null);
            setHasOptionsMenu(true);
            frameLayout = view.findViewById(R.id.framelayout);
            arrow = view.findViewById(R.id.arrow);
            linear = view.findViewById(R.id.linear);
            linear1 = view.findViewById(R.id.linear2);
            left = view.findViewById(R.id.left);
            right = view.findViewById(R.id.right);
            tracking = view.findViewById(R.id.track);
            history = view.findViewById(R.id.history);
            geofence = view.findViewById(R.id.geofence);
            alarm = view.findViewById(R.id.alarm);
            command = view.findViewById(R.id.command);

            tracking.setOnClickListener(this);
            history.setOnClickListener(this);
            geofence.setOnClickListener(this);
            
            left.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.addMarker(new MarkerOptions().position(new LatLng(27.680089, 85.288153)).title("FZS-6349").snippet("2017-08-05 2:00")).showInfoWindow();
                    LatLng cur_Latlng=new LatLng(27.680089,85.288153); // giving your marker to zoom to your location area.
                    map.moveCamera(CameraUpdateFactory.newLatLng(cur_Latlng));
                    map.animateCamera(CameraUpdateFactory.zoomTo(15));
                }
            });
            
            right.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.addMarker(new MarkerOptions().position(new LatLng(27.680089, 85.288153)).title("FZS-6349").snippet("2017-08-05 2:00")).showInfoWindow();
                    LatLng cur_Latlng=new LatLng(27.680089,85.288153); // giving your marker to zoom to your location area.
                    map.moveCamera(CameraUpdateFactory.newLatLng(cur_Latlng));
                    map.animateCamera(CameraUpdateFactory.zoomTo(15));
                }
            });

            arrow.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);

            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ch) {
                        ch = false;
                        arrow.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,3f);
                        frameLayout.setLayoutParams(param);
                    } else {
                        ch=true;
                        arrow.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,3.5f);
                        frameLayout.setLayoutParams(param);
                    }
                }
            });

            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment2);
            mapFragment.getMapAsync(this);


        } else {
            //No google maps
            Toast.makeText(getContext(), "no data", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    public boolean googleServiceAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(getActivity());
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(getActivity(), isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(getActivity(), "Cant connect", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnInfoWindowClickListener(this);
        map.getUiSettings().setMapToolbarEnabled(false);

        marker = LayoutInflater.from(getContext()).inflate(R.layout.custom_marker,null);

        map.addMarker(new MarkerOptions().position(new LatLng(27.680089, 85.288153)).title("FZS-6349" ).snippet("2017-08-05 2:00")).showInfoWindow();
        gotolocation(27.680089, 85.288153, 15);
        map.addMarker(new MarkerOptions().position(new LatLng(27.68, 86)).title("See").snippet("2017-08-05 2:00")).showInfoWindow();
        gotolocation(27.68, 86, 15);
    }

    private void gotolocation(double lattitude, double longitude, float zoom) {
        LatLng latLng = new LatLng(lattitude, longitude);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        map.moveCamera(update);

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(getContext(), "Info window clicked",
                Toast.LENGTH_SHORT).show();
    }

   

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        fullscreen = menu.findItem(R.id.fullscreen);
        home = menu.findItem(R.id.home);
        action_setting = menu.findItem(R.id.action_settings);

        fullscreen.setVisible(true);
        home.setVisible(true);
        action_setting.setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            action_setting.setVisible(false);
            home.setVisible(true);
            fullscreen.setVisible(true);

            fm=getChildFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.framelayout, new MonitoringFragment());
            ft.commit();
            return true;
        }else if(id==R.id.home){
            action_setting.setVisible(true);
            home.setVisible(false);
            fullscreen.setVisible(false);

            fm=getChildFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.framelayout, new ThreeFragment());
            ft.commit();
            return true;
        }else if(id==R.id.fullscreen){
            map.addMarker(new MarkerOptions().position(new LatLng(27.68, 86)).title("See").snippet("2017-08-05 2:00")).showInfoWindow();
            LatLng cur_Latlng=new LatLng(27.68,86); // giving your marker to zoom to your location area.
            map.moveCamera(CameraUpdateFactory.newLatLng(cur_Latlng));
            map.animateCamera(CameraUpdateFactory.zoomTo(15));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.track:
                startActivity(new Intent(getActivity(),TrackingActivity.class));
                break;

            case R.id.history:
                startActivity(new Intent(getActivity(),HistoryActivity.class));
                break;

            case R.id.geofence:
                startActivity(new Intent(getActivity(), GeoFenceActivity.class));
                break;
        }
    }
}
