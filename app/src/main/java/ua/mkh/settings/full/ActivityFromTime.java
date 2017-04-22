package ua.mkh.settings.full;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;

public class ActivityFromTime extends Activity implements View.OnClickListener, WheelPicker.OnItemSelectedListener {


	DatePicker pickerDate;
	Button  btn_back, Button01, Button02, btn_save;
	final static int RQS_1 = 1;
	final String LOG_TAG = "myLogs";
	TextView textView1, textStatus;
	String strdate_from = null;
	String strdate_to = null;
	RelativeLayout LinearLayoutFrom;
	LinearLayout LinearLayoutTo;
	public final static String from_time_text = "from_time.txt";
	public final static String to_time_text = "to_time.txt";
	
	//private boolean timeChanged = false;
	//private boolean timeScrolled = false;
	
	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   
	   SharedPreferences mSettings;
	   Typeface typefaceRoman, typefaceMedium, typefaceBold;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_from_time);
		
		String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		
		textStatus = (TextView)findViewById(R.id.textOk);
        btn_back = (Button) findViewById(R.id.buttonBack);
        btn_save = (Button) findViewById(R.id.buttonSave);
		pickerDate = (DatePicker)findViewById(R.id.pickerdate);


		WheelPicker wheelPicker_hour_1 = (WheelPicker) findViewById(R.id.wh_hour_1);
		wheelPicker_hour_1.setOnItemSelectedListener(this);
		WheelPicker wheelPicker_min_1 = (WheelPicker) findViewById(R.id.wh_min_1);
		wheelPicker_min_1.setOnItemSelectedListener(this);
		WheelPicker wheelPicker_hour_2 = (WheelPicker) findViewById(R.id.wh_hour_2);
		wheelPicker_hour_2.setOnItemSelectedListener(this);
		WheelPicker wheelPicker_min_2 = (WheelPicker) findViewById(R.id.wh_min_2);
		wheelPicker_min_1.setOnItemSelectedListener(this);


		List<String> hour = new ArrayList<>();
		for (int i = 0; i <= 23; i++) {
			hour.add("      "+ i + "   ");
		}
		wheelPicker_hour_1.setData(hour);
		wheelPicker_hour_2.setData(hour);


		List<String> min = new ArrayList<>();
		for (int i = 0; i <= 59; i++) {
			String m = "0";
			if (i < 10){
				m = "0"+i;
				min.add("   "+m+"      ");
			}
			else {
				min.add("   "+i+"      ");
			}
		}
		wheelPicker_min_1.setData(min);
		wheelPicker_min_2.setData(min);



		LinearLayoutFrom = (RelativeLayout)findViewById(R.id.LinearLayoutFrom);
		LinearLayoutTo = (LinearLayout)findViewById(R.id.LinearLayoutTo);
		Button01 = (Button)findViewById(R.id.Button01);
		Button01.setOnClickListener(this);
		Button02 = (Button)findViewById(R.id.Button02);
		Button02.setOnClickListener(this);
		
		Button02.setBackgroundColor(-0x1F1F20);
		LinearLayoutTo.setVisibility(View.GONE);
		
		textView1 = (TextView)findViewById(R.id.textView1);
		Calendar now = Calendar.getInstance();

		  
		//timePicker1.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
		//timePicker1.setCurrentMinute(now.get(Calendar.MINUTE));
		//timePicker2.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
		//timePicker2.setCurrentMinute(now.get(Calendar.MINUTE));
		
		textStatus.setText(R.string.disturb);
        textStatus.setTypeface(typefaceBold);
        btn_back.setTypeface(typefaceMedium);
        btn_save.setTypeface(typefaceRoman);
        Button01.setTypeface(typefaceRoman);
        Button02.setTypeface(typefaceRoman);
        

		
	
		// set current time
		Calendar c1 = Calendar.getInstance();
		int curHours1 = c1.get(Calendar.HOUR_OF_DAY);
		int curMinutes1 = c1.get(Calendar.MINUTE);
	

		
	}
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		
		
		if (id == R.id.Button02){
			Button01.setBackgroundColor(-0x1);
			LinearLayoutTo.setVisibility(View.GONE);
			LinearLayoutFrom.setVisibility(View.VISIBLE);
			Button02.setBackgroundColor(-0x1F1F20);
		}
		if (id ==R.id.Button01){
			Button02.setBackgroundColor(-0x1);
			LinearLayoutFrom.setVisibility(View.GONE);
			LinearLayoutTo.setVisibility(View.VISIBLE);
			Button01.setBackgroundColor(-0x1F1F20);
		}
			
         }
        
		public void start_notif (){
			timeSet t = new timeSet (getBaseContext()); 
			t.start_notif (getBaseContext());		}
		
	public void  start_from(Calendar targetCalFrom) {
		timeSet t = new timeSet (getBaseContext()); 
		t.setAlarm_from (targetCalFrom);
	}
	public void  start_to(Calendar targetCalTo) {
		timeSet t = new timeSet (getBaseContext()); 
		t.setAlarm_to (targetCalTo);
	}
	
	
	public void from_time_text(View view) {
		Intent intent = new Intent(this, ActivityDisturb.class);
		
		try {
			OutputStream outputstream = openFileOutput(from_time_text, 0);
			OutputStreamWriter osw = new OutputStreamWriter(outputstream);
			osw.write(strdate_from.toString());
			osw.close();
		} catch (Throwable t) {
			
		}
		startActivity(intent);
	}
	
	public void to_time_text(View view) {
		Intent intent = new Intent(this, ActivityDisturb.class);
		
		try {
			OutputStream outputstream = openFileOutput(to_time_text, 0);
			OutputStreamWriter osw = new OutputStreamWriter(outputstream);
			osw.write(strdate_to.toString());
			osw.close();
		} catch (Throwable t) {
			
		}
		startActivity(intent);
		overridePendingTransition(center_to_right, center_to_right2);
	}
	
	private void addChangingListener1(final WheelView wheel1, final String label) {
		wheel1.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				//wheel.setLabel(newValue != 1 ? label + "s" : label);
			}
		});
	}
	private void addChangingListener2(final WheelView wheel2, final String label) {
		wheel2.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				//wheel.setLabel(newValue != 1 ? label + "s" : label);
			}
		});
	}
	
	protected void onResume() {
        super.onResume();
        

        	 int speed = mSettings.getInt(APP_PREFERENCES_ANIM_SPEED, 1);
			if (speed == 1){
				center_to_right = R.anim.slide_center_to_right_short;
				center_to_right2 = R.anim.slide_center_to_right2_short;
				center_to_left = R.anim.slide_center_to_left_short;
				center_to_left2 = R.anim.slide_center_to_left2_short;
			}
			if (speed == 2){
				center_to_right = R.anim.slide_center_to_right_medium;
				center_to_right2 = R.anim.slide_center_to_right2_medium;
				center_to_left = R.anim.slide_center_to_left_medium;
				center_to_left2 = R.anim.slide_center_to_left2_medium;
			}
			if (speed == 3){
				center_to_right = R.anim.slide_center_to_right_long;
				center_to_right2 = R.anim.slide_center_to_right2_long;
				center_to_left = R.anim.slide_center_to_left_long;
				center_to_left2 = R.anim.slide_center_to_left2_long;
			}
			
    
        
        
	if (mSettings.contains(APP_PREFERENCES_bold_text)) {
    	 Boolean bold = mSettings.getBoolean(APP_PREFERENCES_bold_text, true);
		if (bold == true){
	        Button01.setTypeface(typefaceBold);
	        Button02.setTypeface(typefaceBold);
			
		}
    }
		
   if (mSettings.contains(APP_PREFERENCES_text_size)) {
    	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
		if (size .contains( "Small")){
	        Button01.setTextSize(13);
	        Button02.setTextSize(13);
		}
		if (size .contains( "Normal")){
	        Button01.setTextSize(15);
	        Button02.setTextSize(15);
		}
		if (size .contains( "Large")){
	        Button01.setTextSize(18);
	        Button02.setTextSize(18);
		}
		if (size .contains( "xLarge")){
	        Button01.setTextSize(20);
	        Button02.setTextSize(20);
		}
   }
	}
   
	public void BackClick(View v)  
    {  
		Intent intent18 = new Intent(this, ActivityDisturb.class);
     	 startActivity(intent18);

		overridePendingTransition(center_to_right, center_to_right2);
   	 }
	@Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {
            case KeyEvent.KEYCODE_MENU:
            	
                return true;
            case KeyEvent.KEYCODE_BACK:
            	Intent intent18 = new Intent(this, ActivityDisturb.class);
   	       	 startActivity(intent18);
        		overridePendingTransition(center_to_right, center_to_right2);
                return true;
            
        }
        return super.onKeyDown(keycode, e);
   }
    

   
	
	public void SaveClick(View v)  
    {  
		Calendar current1 = Calendar.getInstance();
		current1.set(Calendar.SECOND, 0);
		current1.set(Calendar.MILLISECOND, 0);
		
		
		
		Calendar calFrom = Calendar.getInstance();
		Calendar calFromText = Calendar.getInstance();
		
		Calendar calTo = Calendar.getInstance();
		Calendar calToText = Calendar.getInstance();
				/*
		calTo.set( 
        		pickerDate.getYear(), 
        	      pickerDate.getMonth(), 
        	      pickerDate.getDayOfMonth(), 
        	      timePicker1.getCurrentHour(), 
        	      timePicker1.getCurrentMinute(), 00
          );
		calToText.set(pickerDate.getYear(), 
      	      pickerDate.getMonth(), 
      	      pickerDate.getDayOfMonth(), 
      	    timePicker1.getCurrentHour(), 
      	  timePicker1.getCurrentMinute());
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		strdate_from = sdf1.format(calToText.getTime());
		
		
		
		
		calFrom.set( 
        		pickerDate.getYear(), 
        	      pickerDate.getMonth(), 
        	      pickerDate.getDayOfMonth(), 
        	      timePicker2.getCurrentHour(), 
        	      timePicker2.getCurrentMinute(), 00
          );
		calFromText.set(pickerDate.getYear(), 
      	      pickerDate.getMonth(), 
      	      pickerDate.getDayOfMonth(), 
      	    timePicker2.getCurrentHour(), 
      	  timePicker2.getCurrentMinute());
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
		strdate_to = sdf2.format(calFromText.getTime());
		*/
		if (calFrom.compareTo(current1) < 0){
			if (calTo.compareTo(current1) > 0){
				start_notif ();
			}
		}
	////	/////////
		Calendar calFrom_s = Calendar.getInstance();
		Calendar calTo_s = Calendar.getInstance();
		/*
		calFrom_s.set(timePicker2.getCurrentHour(), 
      	      timePicker2.getCurrentMinute());
		
		calTo_s.set(
				timePicker2.getCurrentHour(), 
	      	      timePicker2.getCurrentMinute());
		*/
		if (calFrom.compareTo(current1) < 0){
			if (calFrom.compareTo(calTo) > 0 ){
				start_notif ();
			}
		}
	////	//////////
		if (calFrom.compareTo(current1) < 0){
			if (calTo.compareTo(current1) < 0){
				calFrom.add(Calendar.DATE, 1);
				calTo.add(Calendar.DATE, 1);
			}
		}
		
		start_from(calFrom);
		start_to(calTo);
		
		try {
 			OutputStream outputstream = openFileOutput(from_time_text, 0);
 			OutputStreamWriter osw = new OutputStreamWriter(outputstream);
 			osw.write(strdate_from);
 			osw.close();
 		} catch (Throwable t) {
 			
 		}
		
		try {
 			OutputStream outputstream = openFileOutput(to_time_text, 0);
 			OutputStreamWriter osw = new OutputStreamWriter(outputstream);
 			osw.write(strdate_to);
 			osw.close();
 		} catch (Throwable t) {
 			
 		}
		Intent intent = new Intent(this, ActivityDisturb.class);
	 	 startActivity(intent);
	 	overridePendingTransition(center_to_right, center_to_right2);
     	 }

	@Override

	public void onItemSelected(WheelPicker picker, Object data, int position) {



		switch (picker.getId()) {

			case R.id.day:
				//day = position+1;

				break;

			case R.id.month:
				//month = position+1;
				break;

			case R.id.year:
				//year = data.toString();

		}

		//Toast.makeText(this, day + "." + month + "." + year, Toast.LENGTH_SHORT).show();

	}

}