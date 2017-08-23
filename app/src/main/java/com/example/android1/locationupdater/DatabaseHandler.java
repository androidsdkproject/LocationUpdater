package com.example.android1.locationupdater;

/**
 * Created by Android1 on 6/15/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    String TAG="DatabaseHandler";
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "loc";
    // Contacts table name
    private static final String TABLE_LOCATIONS = "Location";

    // Contacts Table Columns names
    private static final String KEY_Date_Time = "datetime";
    private static final String KEY_Location = "location";
    private static final String KEY_Address = "address";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOCATION_TABLE = "CREATE TABLE " + TABLE_LOCATIONS + "("
                + KEY_Date_Time + " TEXT," + KEY_Location + " TEXT,"
                + KEY_Address + " TEXT" + ")";
        db.execSQL(CREATE_LOCATION_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addLocation(LocationList loc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Date_Time, loc.getDate_time());
        values.put(KEY_Location,loc.getLocation());
        values.put(KEY_Address,loc.getAddress());


        // Inserting Row
        db.insert(TABLE_LOCATIONS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    LocationList getLocation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LOCATIONS, new String[] { KEY_Date_Time,
                        KEY_Location, KEY_Address }, KEY_Date_Time + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        LocationList list = new LocationList(cursor.getString(0),
                cursor.getString(1), cursor.getString(2));

        return list;
    }


    public List<LocationList> getAllLocations() {
        List<LocationList> globallist = new ArrayList<LocationList>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_LOCATIONS+" order by "+KEY_Date_Time+"  DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LocationList locallist = new LocationList();
                locallist.setDate_time(cursor.getString(0));
                locallist.setLocation(cursor.getString(1));
                locallist.setAddress(cursor.getString(2));
                // Adding contact to list



                globallist.add(locallist);
            } while (cursor.moveToNext());
        }

        // return contact list
        return globallist;
    }

    // Updating single contact
    public int updateContact(LocationList list) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Date_Time, list.getDate_time());
        values.put(KEY_Location, list.getLocation());
        values.put(KEY_Address, list.getAddress());

        // updating row
        return db.update(TABLE_LOCATIONS, values, KEY_Date_Time + " = ?",
                new String[] { String.valueOf(list.getDate_time()) });
    }

    // Deleting single contact
    public void deleteLocation(LocationList list) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOCATIONS, KEY_Date_Time + " = ?",
                new String[] { String.valueOf(list.getDate_time()) });
        db.close();
    }



    public void delete_10_Row(int n)
    {

        Log.d(TAG,"Delete "+n);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_LOCATIONS, null, null, null, null, null, null);

        int count=0;
        while(cursor.moveToNext()&&count<n) {
            String rowId = cursor.getString(cursor.getColumnIndex(KEY_Date_Time));

            db.delete(TABLE_LOCATIONS, KEY_Date_Time + "=?",  new String[]{rowId});
            count++;
        }
        db.close();
    }
    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LOCATIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //////cursor.close();

        // return count
        return cursor.getCount();
    }

}