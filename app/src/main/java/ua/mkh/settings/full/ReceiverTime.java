package ua.mkh.settings.full;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Calendar;

public class ReceiverTime extends BroadcastReceiver {

    public static final String APP_PREFERENCES = "mysettings";

    @Override
    public void onReceive(Context context, Intent intent) {

        //Log.e("RECEIVER", "START REVEIVER");

        SharedPreferences mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (intent.getAction().equalsIgnoreCase("android.intent.action.TIME_TICK")) {

            //Log.e("RECEIVER", "START REVEIVER TIME TICK");
            Calendar c = Calendar.getInstance();
            int min = c.get(Calendar.MINUTE);
            int hour = c.get(Calendar.HOUR_OF_DAY);

            if(mSettings.getBoolean("shedule_disturb", false) == true) {

                if (Integer.valueOf(mSettings.getString("disturb_from_hour", "00")) == hour && Integer.valueOf(mSettings.getString("disturb_from_min", "00")) == min) {
                    timeSet t = new timeSet(context);
                    t.start_notif(context);
                }

                if (Integer.valueOf(mSettings.getString("disturb_to_hour", "06")) == hour && Integer.valueOf(mSettings.getString("disturb_to_min", "00")) == min) {
                    timeSet t = new timeSet(context);
                    t.stop_notif(context);
                }
            }
            }
        }


}
