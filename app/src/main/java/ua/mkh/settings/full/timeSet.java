package ua.mkh.settings.full;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class timeSet {
	final static int RQS_1 = 1;
	Context kContext;
	private static final int MY_NOTIFICATION_ID=1;
	NotificationManager notificationManager;
	Notification myNotification;
	public static final String APP_PREFERENCES_DISTURB_ENABLE = "disturb_enable";
	ReceiverTime  Receiver = new ReceiverTime();;


	public static final String APP_PREFERENCES = "mysettings";
    long f = 0;
	
	 public timeSet(Context kContext){
	       this.kContext = kContext;
	  }

	  public Calendar get_calendar_from(){

          SharedPreferences mSettings = kContext.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

          Calendar calendar = Calendar.getInstance();
          calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(mSettings.getString("disturb_from_hour", "")));
          calendar.set(Calendar.MINUTE, Integer.valueOf(mSettings.getString("disturb_from_min", "")));
          calendar.set(Calendar.SECOND, 0);

          return calendar;
      }

    public Calendar get_calendar_to(){

        SharedPreferences mSettings = kContext.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(mSettings.getString("disturb_to_hour", "")));
        calendar.set(Calendar.MINUTE, Integer.valueOf(mSettings.getString("disturb_to_min", "")));
        calendar.set(Calendar.SECOND, 0);

        return calendar;
    }
	/*
	 public void setAlarm_from(){

         Intent intent = new Intent(kContext, AlarmReceiverOn.class);
         PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		 AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
		 //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCalFrom.getTimeInMillis(), 24*60*60*1000, pendingIntent);

         if (Build.VERSION.SDK_INT >= 23) {
            // Wakes up the device in Doze Mode
             alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, get_calendar_from().getTimeInMillis(),
                     pendingIntent);
         } else if (Build.VERSION.SDK_INT >= 19) {
            // Wakes up the device in Idle Mode
             alarmManager.setExact(AlarmManager.RTC_WAKEUP, get_calendar_from().getTimeInMillis()+f, pendingIntent);
         } else {
            // Old APIs
             alarmManager.set(AlarmManager.RTC_WAKEUP, get_calendar_from().getTimeInMillis()+f, pendingIntent);
         }


		 }

    public void setAlarm_from_add(){

        Intent intent = new Intent(kContext, AlarmReceiverOn.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCalFrom.getTimeInMillis(), 24*60*60*1000, pendingIntent);


        SharedPreferences mSettings = kContext.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, Integer.valueOf(mSettings.getString("disturb_from_hour", "0")));
        c.set(Calendar.MINUTE, Integer.valueOf(mSettings.getString("disturb_from_min", "0"))-1);
        c.add(Calendar.DATE, 1);

        if (Build.VERSION.SDK_INT >= 23) {
            // Wakes up the device in Doze Mode
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
                    pendingIntent);
        } else if (Build.VERSION.SDK_INT >= 19) {
            // Wakes up the device in Idle Mode
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        } else {
            // Old APIs
            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
    }

    public void setAlarm_to_add(){

        Intent intent = new Intent(kContext, AlarmReceiverOff.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCalFrom.getTimeInMillis(), 24*60*60*1000, pendingIntent);


        SharedPreferences mSettings = kContext.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, Integer.valueOf(mSettings.getString("disturb_to_hour", "0")));
        c.set(Calendar.MINUTE, Integer.valueOf(mSettings.getString("disturb_to_min", "0"))-1);
        c.add(Calendar.DATE, 1);

        if (Build.VERSION.SDK_INT >= 23) {
            // Wakes up the device in Doze Mode
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
                    pendingIntent);
        } else if (Build.VERSION.SDK_INT >= 19) {
            // Wakes up the device in Idle Mode
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        } else {
            // Old APIs
            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
    }


	 public void setAlarm_to(){
		  
		  Intent intent = new Intent(kContext, AlarmReceiverOff.class);
		  PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, 0);
		  AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
		  //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCalTo.getTimeInMillis(), 24*60*60*1000, pendingIntent);

         if (Build.VERSION.SDK_INT >= 23) {
             // Wakes up the device in Doze Mode
             alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, get_calendar_to().getTimeInMillis()+f,
                     pendingIntent);
         } else if (Build.VERSION.SDK_INT >= 19) {
             // Wakes up the device in Idle Mode
             alarmManager.setExact(AlarmManager.RTC_WAKEUP, get_calendar_to().getTimeInMillis(), pendingIntent);
         } else {
             // Old APIs
             alarmManager.set(AlarmManager.RTC_WAKEUP, get_calendar_to().getTimeInMillis(), pendingIntent);
         }
		 }



/////////////////////////////////////////////////STOP
	 public void setStop_to () {
			
			Intent intent = new Intent(kContext, AlarmReceiverOff.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, 0);
			AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
			
			alarmManager.cancel(pendingIntent);
		}

	public void setStop_from () {

		Intent intent = new Intent(kContext, AlarmReceiverOn.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, 0);
		AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);

		alarmManager.cancel(pendingIntent);
	}
///////////////////////////////////////////////////////
	 
	 public void setAlarm_from_manual(){
		  
		  Intent intent = new Intent(kContext, AlarmReceiverOn.class);
		  PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, 0);
		  AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
		  alarmManager.set(AlarmManager.RTC_WAKEUP, 0, pendingIntent);
		  
		 }
	 */
	 public void setStop_from_manual() {
			
			Intent intent = new Intent(kContext, AlarmReceiverOff.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, 0);
			AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
			
			alarmManager.cancel(pendingIntent);
		}
	 /*
	 public void setAlarm_to_manual(){
		  
		  Intent intent = new Intent(kContext, AlarmReceiverOff.class);
		  PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, 0);
		  AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
		  alarmManager.set(AlarmManager.RTC_WAKEUP, 0, pendingIntent);
		  
		 }
	 */
	 public void setStop_to_manual() {
			
			Intent intent = new Intent(kContext, AlarmReceiverOff.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, 0);
			AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
			
			alarmManager.cancel(pendingIntent);
		}
	 
	 
	 /////////////////////////////////////////////
	 ///////////////////////////////////////
	 
	 public void start_notif (Context kContext){
		 AudioManager audio_mngr = (AudioManager) kContext.getSystemService(Context.AUDIO_SERVICE);
		  audio_mngr.setRingerMode(AudioManager.RINGER_MODE_SILENT); 
		  
		  SharedPreferences mSettings = kContext.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		  
		  Intent myIntent = new Intent(kContext, ActivityDisturb.class);
		  PendingIntent pendingIntent = PendingIntent.getActivity(kContext, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		  
		  CharSequence Title = kContext.getString(R.string.do_not_disturb_title);
		  CharSequence Ticker = kContext.getString(R.string.do_not_disturb_on);
		  
		  myNotification = new NotificationCompat.Builder(kContext)
		    .setContentTitle(Title)
		    .setContentText("")
		    .setTicker(Ticker)
		    .setWhen(System.currentTimeMillis())
		    .setContentIntent(pendingIntent)
		    .setSmallIcon(R.drawable.ic_stat_notify)
		    .build();
		  
		  myNotification.flags = Notification.FLAG_NO_CLEAR;
		  notificationManager = 
		    (NotificationManager)kContext.getSystemService(Context.NOTIFICATION_SERVICE);
		  
		  notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
		  
		  Editor editor = mSettings.edit();
		 	editor.putBoolean(APP_PREFERENCES_DISTURB_ENABLE, true);
		 	editor.apply();
	 }
	 
	 public void stop_notif (Context kContext){
		 AudioManager audio_mngr = (AudioManager) kContext.getSystemService(Context.AUDIO_SERVICE);
		  audio_mngr.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		  notificationManager = (NotificationManager)kContext.getSystemService(Context.NOTIFICATION_SERVICE);
		  notificationManager.cancel(MY_NOTIFICATION_ID);

		  
		  SharedPreferences mSettings = kContext.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		  
		  Editor editor = mSettings.edit();
		 	editor.putBoolean(APP_PREFERENCES_DISTURB_ENABLE, false);
		 	editor.apply();
	 }


	 public void start_receiver (Context context){

			 //context.registerReceiver(Receiver, new IntentFilter(
			//		 "android.intent.action.TIME_TICK"));
			 //Toast.makeText(kContext, "Приёмник включен",
			//		 Toast.LENGTH_SHORT).show();

		 SharedPreferences mSettings = kContext.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		 SharedPreferences.Editor editorName = mSettings.edit();
		 editorName.putBoolean("shedule_disturb", true);
		 editorName.apply();


	 }

	 public void stop_receiver(Context context){

		 SharedPreferences mSettings = kContext.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		 SharedPreferences.Editor editorName = mSettings.edit();
		 editorName.putBoolean("shedule_disturb", false);
		 editorName.apply();
	 }
	
}

