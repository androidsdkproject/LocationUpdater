package com.example.android1.locationupdater;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.android1.locationupdater.LocationService.db;

public class ReportActivity extends AppCompatActivity  {




    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);





        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.pink, R.color.indigo, R.color.lime);

        swipeRefreshLayout.setOnRefreshListener(new  SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });



        ListView listView = (ListView) findViewById(R.id.listView);


        Log.d("Reading: ", "Reading all Locations");
        Log.d("Report "," "+db.getContactsCount());
        ArrayList<LocationList> loclist = (ArrayList<LocationList>) db.getAllLocations();

        ActionBar actionBar = getSupportActionBar();


        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar);





        TextView text=(TextView)findViewById(R.id.liststatus);
        this.findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"click",Toast.LENGTH_LONG).show();
                db.delete_10_Row(db.getContactsCount());
                refreshData();

            }
        });



        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        LocationAdapter adapter = new LocationAdapter(getApplicationContext(), loclist);

        text.setVisibility(View.GONE);

        if(loclist.isEmpty())
        {


            text.setText("Locations List is Empty");
            text.setVisibility(View.VISIBLE);
        }




        listView.setAdapter(adapter);

    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void refreshData() {


        TextView text=(TextView)findViewById(R.id.liststatus);
        text.setVisibility(View.GONE);
        Log.d("Report refresh","Report refresh "+db.getContactsCount());
        if(db.getContactsCount()>100) {
            db.delete_10_Row(100 - db.getContactsCount());
            Log.d(" Report refresh","after delete Report refresh "+db.getContactsCount());

        }
        Log.d("Reading: ", "Reading all Locations");
        ListView listView = (ListView) findViewById(R.id.listView);

        Log.d("Reading: ", "Reading all Locations");
        ArrayList<LocationList> loclist = (ArrayList<LocationList>) db.getAllLocations();




        LocationAdapter adapter = new LocationAdapter(getApplicationContext(), loclist);


        if(loclist.isEmpty())
        {


            text.setText("Locations List is Empty");
            text.setVisibility(View.VISIBLE);
        }



        listView.setAdapter(adapter);


        swipeRefreshLayout.setRefreshing(false);
    }


}
