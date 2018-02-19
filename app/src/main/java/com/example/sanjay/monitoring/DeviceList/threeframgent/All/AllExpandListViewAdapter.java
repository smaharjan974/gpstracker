package com.example.sanjay.monitoring.DeviceList.threeframgent.All;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.sanjay.monitoring.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by SANJAY on 2/14/2018.
 */

public class AllExpandListViewAdapter extends BaseExpandableListAdapter {

    Context context;
    List<AllGroup> allGroups;
    HashMap<AllGroup,List<DeviceModel>> hashMap;

    public AllExpandListViewAdapter(Context context, List<AllGroup> allGroups, HashMap<AllGroup, List<DeviceModel>> hashMap) {
        this.context = context;
        this.allGroups = allGroups;
        this.hashMap = hashMap;
    }

    @Override
    public int getGroupCount() {
        return allGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return hashMap.get(allGroups.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return allGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return hashMap.get(allGroups.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.parent_list_item, null);
        }
        TextView groupname = view.findViewById(R.id.groupname);
        TextView count = view.findViewById(R.id.count);

        groupname.setText(allGroups.get(groupPosition).getGroupname());
        count.setText(String.valueOf(allGroups.get(groupPosition).getSize()));
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_list_item, null);
        }
        TextView devicename = view.findViewById(R.id.devicename);
        TextView status = view.findViewById(R.id.status);

        devicename.setText(hashMap.get(allGroups.get(groupPosition)).get(childPosition).getDeviceName());
        status.setText(hashMap.get(allGroups.get(groupPosition)).get(childPosition).getStatus());
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
