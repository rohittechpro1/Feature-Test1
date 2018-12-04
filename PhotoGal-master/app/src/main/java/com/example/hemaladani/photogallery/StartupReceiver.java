package com.example.hemaladani.photogallery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by hemaladani on 6/8/17.
 */

public class StartupReceiver extends BroadcastReceiver {
    private static final String TAG="StartupReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"Received Broadcast intent:"+intent.getAction());

        boolean isOn=QueryPreferences.isAlarmOn(context);
        PollService.setServiceAlarm(context,isOn);

    }
}
