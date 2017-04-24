package ua.mkh.settings.full;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;


public class AlarmReceiverOff extends BroadcastReceiver {


    @Override
    public void onReceive(Context mContext, Intent intent) {

	 final timeSet t = new timeSet (mContext);
     t.stop_notif (mContext);


 }
 
 
 
}

