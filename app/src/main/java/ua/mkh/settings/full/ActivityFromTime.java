package ua.mkh.settings.full;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
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
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aigestudio.wheelpicker.WheelPicker;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;

public class ActivityFromTime extends Activity implements View.OnClickListener, WheelPicker.OnItemSelectedListener {


	DatePicker pickerDate;
	Button  btn_back, Button01, Button02, btn_save;
	final static int RQS_1 = 1;
	final String LOG_TAG = "myLogs";
	TextView textView1, textStatus;
	String strdate_from = null;
	String strdate_to = null;
	RelativeLayout LinearLayoutFrom;
	RelativeLayout LinearLayoutTo;
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

		String hour_1, min_1, hour_2, min_2;
		WheelPicker wheelPicker_hour_1, wheelPicker_hour_2, wheelPicker_min_1, wheelPicker_min_2;
	SwipeBackActivityHelper helper = new SwipeBackActivityHelper();



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_from_time);

		helper.setEdgeMode(true)
				.setParallaxMode(true)
				.setParallaxRatio(5)
				.setNeedBackgroundShadow(false)
				.init(this);

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


		wheelPicker_hour_1 = (WheelPicker) findViewById(R.id.wh_hour_1);
		wheelPicker_hour_1.setOnItemSelectedListener(this);
		wheelPicker_min_1 = (WheelPicker) findViewById(R.id.wh_min_1);
		wheelPicker_min_1.setOnItemSelectedListener(this);
		wheelPicker_hour_2 = (WheelPicker) findViewById(R.id.wh_hour_2);
		wheelPicker_hour_2.setOnItemSelectedListener(this);
		wheelPicker_min_2 = (WheelPicker) findViewById(R.id.wh_min_2);
		wheelPicker_min_2.setOnItemSelectedListener(this);


		List<String> hour = new ArrayList<>();
		for (int i = 0; i <= 23; i++) {
			String m = "";
			if(i < 10){
				m = " "+i;
				hour.add("      " + m + "   ");
			}
			else {
				hour.add("      " + i + "   ");
			}
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
		LinearLayoutTo = (RelativeLayout)findViewById(R.id.LinearLayoutTo);
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
	//	Calendar c1 = Calendar.getInstance();
	//	int curHours1 = c1.get(Calendar.HOUR_OF_DAY);
	//	int curMinutes1 = c1.get(Calendar.MINUTE);



		
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
	/*
	public void  start_from() {
		timeSet t = new timeSet (getBaseContext()); 
		t.setAlarm_from ();
	}
	public void  start_to() {
		timeSet t = new timeSet (getBaseContext()); 
		t.setAlarm_to ();
	}
	
*/

	
	protected void onResume() {
        super.onResume();

		get_save_time();
        

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

	private void get_save_time() {

		hour_1 = mSettings.getString("disturb_from_hour", "00");
		min_1 = mSettings.getString("disturb_from_min", "00");
		hour_2 = mSettings.getString("disturb_to_hour", "06");
		min_2 = mSettings.getString("disturb_to_min", "00");

		Button02.setText(getString(R.string.disturb_from) + " " + hour_1 + ":" + min_1);
		Button01.setText(getString(R.string.disturb_to) + " " + hour_2 + ":" + min_2);

		wheelPicker_hour_1.setSelectedItemPosition(Integer.parseInt(hour_1));
		wheelPicker_hour_2.setSelectedItemPosition(Integer.parseInt(hour_2));

		wheelPicker_min_1.setSelectedItemPosition(Integer.parseInt(min_1));
		wheelPicker_min_2.setSelectedItemPosition(Integer.parseInt(min_2));


	}

	public void BackClick(View v) {
		onBackPressed();

	}

	@Override
	public void onBackPressed() {

		View view = this.getCurrentFocus();
		if (view != null) {
			InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}

		helper.finish();
	}



	public void SaveClick(View v)  
    {  
		Calendar current1 = Calendar.getInstance();
		current1.set(Calendar.SECOND, 0);
		current1.set(Calendar.MILLISECOND, 0);
		
		
		
		Calendar calFrom = Calendar.getInstance();
		//Calendar calFromText = Calendar.getInstance();
		
		Calendar calTo = Calendar.getInstance();
		//Calendar calToText = Calendar.getInstance();
/*
		calTo.set( 
        		pickerDate.getYear(), 
				pickerDate.getMonth(),
				pickerDate.getDayOfMonth(),
				Integer.valueOf(hour_2),
				Integer.valueOf(min_2), 00
          );*/
		/*calToText.set(pickerDate.getYear(),
      	      	pickerDate.getMonth(),
				pickerDate.getDayOfMonth(),
				Integer.valueOf(hour_2),
				Integer.valueOf(min_2));
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		strdate_from = sdf1.format(calToText.getTime());
		*/

		
		/*
		calFrom.set( 
        		pickerDate.getYear(), 
				pickerDate.getMonth(),
				pickerDate.getDayOfMonth(),
				Integer.parseInt(hour_1),
				Integer.parseInt(min_1), 00
          );
*/
		calFrom.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour_1));
		calFrom.set(Calendar.MINUTE, Integer.parseInt(min_1));
		calFrom.set(Calendar.SECOND, 0);

		calTo.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour_2));
		calTo.set(Calendar.MINUTE, Integer.parseInt(min_2));
		calTo.set(Calendar.SECOND, 0);

		/*calFromText.set(pickerDate.getYear(),
      	      	pickerDate.getMonth(),
      	      	pickerDate.getDayOfMonth(),
				Integer.parseInt(hour_1),
				Integer.parseInt(min_1));
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
		strdate_to = sdf2.format(calFromText.getTime());
*/
		if (calFrom.compareTo(current1) <= 0){
			if (calTo.compareTo(current1) >= 0){
				start_notif ();
			}
		}
	////	/////////
		//Calendar calFrom_s = Calendar.getInstance();
		//Calendar calTo_s = Calendar.getInstance();

		//calFrom_s.set(timePicker2.getCurrentHour(),
      	//      timePicker2.getCurrentMinute());
		
		//calTo_s.set(
		//		timePicker2.getCurrentHour(),
	    //  	      timePicker2.getCurrentMinute());

		if (calFrom.compareTo(current1) <= 0){
			if (calFrom.compareTo(calTo) >= 0 ){
				start_notif ();
			}
		}
	////	//////////
	/*	if (calFrom.compareTo(current1) < 0){
			if (calTo.compareTo(current1) < 0){
				calFrom.add(Calendar.DATE, 1);
				calTo.add(Calendar.DATE, 1);
			}
		}
	*/




		/*
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
 			
 		}*/

		SharedPreferences.Editor editorName = mSettings.edit();
		editorName.putString("disturb_from_hour", hour_1);
		editorName.putString("disturb_from_min", min_1);
		editorName.putString("disturb_to_hour", hour_2);
		editorName.putString("disturb_to_min", min_2);

		editorName.putBoolean("shedule_disturb", true);

		editorName.apply();

		//start_from();
		//start_to();

		//timeSet t = new timeSet (this);
		//t.start_receiver(this);

		helper.finish();
     	 }

	@Override

	public void onItemSelected(WheelPicker picker, Object data, int position) {



		switch (picker.getId()) {

			case R.id.wh_hour_1:
				hour_1 = data.toString().trim();;
				Button02.setText(getString(R.string.disturb_from) + " " + hour_1+":"+min_1);
				break;

			case R.id.wh_min_1:
				min_1 = data.toString().trim();;
				Button02.setText(getString(R.string.disturb_from) + " " + hour_1+":"+min_1);
				break;

			case R.id.wh_hour_2:
				hour_2 = data.toString().trim();
				Button01.setText(getString(R.string.disturb_to) + " " + hour_2+":"+min_2);
				break;

			case R.id.wh_min_2:
				min_2 = data.toString().trim();
				Button01.setText(getString(R.string.disturb_to) + " " + hour_2+":"+min_2);
				break;


		}



	}

}