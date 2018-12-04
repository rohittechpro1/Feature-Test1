package com.example.hemaladani.photogallery;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by hemaladani on 6/11/17.
 */

public abstract class VisibleFragment extends android.support.v4.app.Fragment {

    private static final String TAG="VisibleFragment";
    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter=new IntentFilter(PollService.ACTION_SHOW_NOTIFICATION);
        getActivity().registerReceiver(mOnShowNotification,filter,PollService.PERM_PRIVATE,null);
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(mOnShowNotification);
    }

    private BroadcastReceiver mOnShowNotification=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Toast.makeText(getActivity(),"Got a BroadCast:"+intent.getAction(),Toast.LENGTH_LONG).show();
            Log.i(TAG,"Cancelling notification");
            setResultCode(Activity.RESULT_CANCELED);


        }
    };
}
