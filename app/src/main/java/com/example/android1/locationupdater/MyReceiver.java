package com.example.android1.locationupdater;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

// make sure we use a WakefulBroadcastReceiver so that we acquire a partial wakelock
public class MyReceiver extends WakefulBroadcastReceiver {
    private static final String TAG = "MyReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"called Receiver");
        context.startService(new Intent(context, LocationService.class));
    }
}
