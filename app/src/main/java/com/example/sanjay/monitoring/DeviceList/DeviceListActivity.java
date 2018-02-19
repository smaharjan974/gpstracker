package com.example.sanjay.monitoring.DeviceList;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanjay.monitoring.DeviceList.threeframgent.All.AllFragment;
import com.example.sanjay.monitoring.DeviceList.Monitor.MonitoringFragment;
import com.example.sanjay.monitoring.DeviceList.threeframgent.Moving.MovingFragment;
import com.example.sanjay.monitoring.DeviceList.threeframgent.StandBy.StandByFragment;
import com.example.sanjay.monitoring.DeviceList.threeframgent.ThreeFragment;
import com.example.sanjay.monitoring.R;

public class DeviceListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    FragmentManager fm;
    FragmentTransaction ft;
    NavigationView navigationView;
    MenuItem home,action_settings,fullscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Device List");

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getNavitem();

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.framelayout, new ThreeFragment());
        ft.commit();


    }

    private void getNavitem() {
      View header = navigationView.getRootView();
      TextView username = header.findViewById(R.id.nameofuser);
        LinearLayout user = header.findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DeviceListActivity.this, "You Clicked User!", Toast.LENGTH_SHORT).show();
            }
        });
      username.setText("SANJAY");


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.device_list, menu);
        fullscreen = menu.findItem(R.id.fullscreen);
        home = menu.findItem(R.id.home);
        action_settings = menu.findItem(R.id.action_settings);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            action_settings.setVisible(false);
            home.setVisible(true);
            fullscreen.setVisible(true);

            fm=getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.framelayout, new MonitoringFragment());
            ft.commit();
            return true;
        }else if(id==R.id.home){
            action_settings.setVisible(true);
            home.setVisible(false);
            fullscreen.setVisible(false);

            fm=getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.framelayout, new ThreeFragment());
            ft.commit();
            return true;
        }
//        else if(id==R.id.fullscreen){
//            Toast.makeText(this, "aksdfadfas", Toast.LENGTH_SHORT).show();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

    }
}
