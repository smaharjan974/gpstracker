package com.example.sanjay.monitoring.DeviceList.threeframgent.All;

/**
 * Created by SANJAY on 2/14/2018.
 */

public class DeviceModel {

    String DeviceName;
    String status;

    public DeviceModel(String deviceName, String status) {
        DeviceName = deviceName;
        this.status = status;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
