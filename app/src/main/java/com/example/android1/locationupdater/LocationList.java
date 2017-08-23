package com.example.android1.locationupdater;

import android.location.Location;

import java.util.Date;

/**
 * Created by Android1 on 6/15/2017.
 */
public class LocationList {

    private String date_time;
    private String location;
    private String address;

    public LocationList(String date_time, String location, String address) {
        super();
        this.date_time = date_time;
        this.location = location;
        this.address=address;
    }

    public LocationList(String date_time, Location location, String address) {

    }

    public LocationList() {

    }

    public String getAddress() {
        return address;
    }

    public String getDate_time() {
        return date_time;
    }

    public String getLocation() {
        return location;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }


    public void setLocation(String location) {
        this.location = location;
    }
}
