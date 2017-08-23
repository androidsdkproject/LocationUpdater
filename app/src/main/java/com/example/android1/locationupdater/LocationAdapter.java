package com.example.android1.locationupdater;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

/**
 * Created by Android1 on 6/15/2017.
 */

public class LocationAdapter extends ArrayAdapter<LocationList> {

    private final Context context;
    private final ArrayList<LocationList> itemsArrayList;

    public LocationAdapter(Context context, ArrayList<LocationList> itemsArrayList) {
        super(context, R.layout.list_row, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_row, parent, false);

        TextView date_and_time = (TextView) rowView.findViewById(R.id.datetime);
        TextView location = (TextView) rowView.findViewById(R.id.location);
        TextView address = (TextView) rowView.findViewById(R.id.address);



        date_and_time.setText(itemsArrayList.get(position).getDate_time());
        location.setText(itemsArrayList.get(position).getLocation());
        address.setText(itemsArrayList.get(position).getAddress());


        return rowView;
    }



}