package com.example.sanjay.monitoring.DeviceList.threeframgent.All;

/**
 * Created by SANJAY on 2/14/2018.
 */

public class AllGroup {
    String groupname;
    Integer size;

    public AllGroup(String groupname, Integer size) {
        this.groupname = groupname;
        this.size = size;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}

