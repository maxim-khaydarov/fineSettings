package ua.mkh.settings.full;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class AlarmReceiverOn extends BroadcastReceiver {
 @Override
 public void onReceive(Context mContext, Intent intent) {



     timeSet t = new timeSet (mContext);
     t.start_notif (mContext);



 }

 
 
 }
 
