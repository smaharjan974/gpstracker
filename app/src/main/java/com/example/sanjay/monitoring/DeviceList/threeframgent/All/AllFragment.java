package com.example.sanjay.monitoring.DeviceList.threeframgent.All;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.sanjay.monitoring.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SANJAY on 2/14/2018.
 */

public class AllFragment extends Fragment {

    ExpandableListView allexpand;

    List<AllGroup> grouplist;
    List<DeviceModel> deviceModelList,deviceModelList1;
    List<List<DeviceModel>> childlist;
    HashMap<AllGroup,List<DeviceModel>> hashMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_fragment,null);

        allexpand = view.findViewById(R.id.allexpand);

        grouplist = new ArrayList<>();
        grouplist.add(new AllGroup("Default Group",3));
        grouplist.add(new AllGroup("Two Wheelers Group",2));
//        grouplist.add(new AllGroup("Four Wheelers Group",2));

        deviceModelList = new ArrayList<>();
        deviceModelList.add(new DeviceModel("Sammy_600","stop"));
        deviceModelList.add(new DeviceModel("Gideon_600","stop"));
        deviceModelList.add(new DeviceModel("FZS_6349","stop"));

        deviceModelList1=new ArrayList<>();
        deviceModelList1.add(new DeviceModel("Sammy_600","stop"));
        deviceModelList1.add(new DeviceModel("Gideon_600","stop"));


        childlist = new ArrayList<>();
        childlist.add(deviceModelList);
        childlist.add(deviceModelList1);

        hashMap = new HashMap<>();
        for (int i=0;i<grouplist.size();i++){
            hashMap.put(grouplist.get(i),childlist.get(i));
        }

        allexpand.setAdapter(new AllExpandListViewAdapter(getContext(),grouplist,hashMap));
        allexpand.expandGroup(0);
        return view;
    }
}
