package com.example.sanjay.monitoring.DeviceList.threeframgent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.sanjay.monitoring.DeviceList.threeframgent.All.AllFragment;
import com.example.sanjay.monitoring.DeviceList.threeframgent.Moving.MovingFragment;
import com.example.sanjay.monitoring.DeviceList.threeframgent.StandBy.StandByFragment;
import com.example.sanjay.monitoring.R;

/**
 * Created by SANJAY on 2/15/2018.
 */

public class ThreeFragment extends Fragment implements View.OnClickListener {

    TextView all, standby, moving;
    FrameLayout fragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.threefragment,null);
        all = view.findViewById(R.id.all);
        standby = view.findViewById(R.id.standby);
        moving = view.findViewById(R.id.moving);
        fragment = view.findViewById(R.id.fragment);

        all.setOnClickListener(this);
        standby.setOnClickListener(this);
        moving.setOnClickListener(this);

        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, new AllFragment());
        ft.commit();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all:
                all.setTextColor(getResources().getColor(R.color.white));
                all.setBackgroundResource(R.color.skyblue);
                standby.setBackgroundResource(R.drawable.custom_rectangle_sky_blue);
                moving.setBackgroundResource(R.drawable.custom_rectangle_sky_blue);
                moving.setTextColor(getResources().getColor(R.color.skyblue));
                standby.setTextColor(getResources().getColor(R.color.skyblue));

                FragmentManager fm = getChildFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment, new AllFragment());
                ft.commit();

                break;

            case R.id.standby:
                standby.setTextColor(getResources().getColor(R.color.white));
                standby.setBackgroundResource(R.color.skyblue);
                moving.setBackgroundResource(R.drawable.custom_rectangle_sky_blue);
                moving.setTextColor(getResources().getColor(R.color.skyblue));
                all.setBackgroundResource(R.drawable.custom_rectangle_sky_blue);
                all.setTextColor(getResources().getColor(R.color.skyblue));

                FragmentManager fm1 = getChildFragmentManager();
                FragmentTransaction ft1 = fm1.beginTransaction();
                ft1.replace(R.id.fragment, new StandByFragment());
                ft1.commit();

                break;

            case R.id.moving:
                moving.setTextColor(getResources().getColor(R.color.white));
                moving.setBackgroundResource(R.color.skyblue);
                standby.setBackgroundResource(R.drawable.custom_rectangle_sky_blue);
                standby.setTextColor(getResources().getColor(R.color.skyblue));
                all.setBackgroundResource(R.drawable.custom_rectangle_sky_blue);
                all.setTextColor(getResources().getColor(R.color.skyblue));

                FragmentManager fm2 = getChildFragmentManager();
                FragmentTransaction ft2 = fm2.beginTransaction();
                ft2.replace(R.id.fragment, new MovingFragment());
                ft2.commit();

                break;

        }
    }
}
