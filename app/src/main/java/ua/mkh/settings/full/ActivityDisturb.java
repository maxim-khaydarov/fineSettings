package ua.mkh.settings.full;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.github.bluzwong.swipeback.SwipeBackActivityHelper;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ActivityDisturb extends Activity implements View.OnClickListener,  RadioGroup.OnCheckedChangeListener{

	ToggleButton tb_on, tb_shedule, tb_repeat;
	TextView text_from, text_to, textView1, textView4, textStatus, TextView01, TextView02, TextView03, TextView04;
	LinearLayout LinearLayoutShedule;
	Button btn_back, Button01, Button02, Button03, Button04, Button05;
	AudioManager audio_mngr;
	public final static String to_time_text = "to_time.txt";
	public final static String from_time_text = "from_time.txt";
	int shedule = 0;
	final static int RQS_1 = 1;
	Typeface typefaceRoman, typefaceMedium, typefaceBold;
	int OS = android.os.Build.VERSION.SDK_INT;
	Boolean enable_disturb;
	ReceiverTime  Receiver = new ReceiverTime();
	
	public static final String APP_PREFERENCES = "mysettings";
	public static final String APP_PREFERENCES_text_size = "txt_size";
	public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   public static final String APP_PREFERENCES_ALLOW_CALL = "allow_call";
	   public static final String APP_PREFERENCES_REPEAT_CALL = "repeat_call";
	   public static final String APP_PREFERENCES_SILENCE_CALL = "silence_call";
	   public static final String APP_PREFERENCES_DISTURB_ENABLE = "disturb_enable";
	   
	  
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	
	SharedPreferences mSettings;
	
	private RadioButton always, locked;

	SwipeBackActivityHelper helper = new SwipeBackActivityHelper();
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_disturb);
        String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String thin = "fonts/Thin.otf";
		String bold =  "fonts/Bold.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		Typeface typefaceThin = Typeface.createFromAsset(getAssets(), thin);
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

		helper.setEdgeMode(true)
				.setParallaxMode(true)
				.setParallaxRatio(5)
				.setNeedBackgroundShadow(false)
				.init(this);

		ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);
		OverScrollDecoratorHelper.setUpOverScroll(scrollView);



		RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radiogroup.setOnCheckedChangeListener(this);
     // radio button setting
        always=(RadioButton) findViewById(R.id.radio0);
        locked=(RadioButton) findViewById(R.id.radio1);
		
        tb_on = (ToggleButton)findViewById(R.id.ToggleButton01);
        tb_on.setOnClickListener(this);
        tb_shedule =  (ToggleButton)findViewById(R.id.ToggleButton02);
        tb_shedule.setOnClickListener(this);
        tb_repeat =  (ToggleButton)findViewById(R.id.ToggleButton03);
        tb_repeat.setOnClickListener(this);
        
        text_from = (TextView)findViewById(R.id.textView3);
        text_to = (TextView)findViewById(R.id.textView2);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView4 = (TextView) findViewById(R.id.textView4);
        TextView01 = (TextView) findViewById(R.id.TextView01);
        TextView02 = (TextView) findViewById(R.id.TextView02);
        TextView03 = (TextView) findViewById(R.id.TextView03);
        TextView04 = (TextView) findViewById(R.id.TextView04);

        textStatus = (TextView)findViewById(R.id.textOk);
        btn_back = (Button) findViewById(R.id.buttonBack);
        btn_back.setText(R.string.app_name);
        Button01 = (Button) findViewById(R.id.Button01);
        Button03 = (Button) findViewById(R.id.Button03);
        Button04 = (Button) findViewById(R.id.Button04);
        Button04.setOnClickListener(this);
        Button02 = (Button) findViewById(R.id.Button02);
        Button02.setOnClickListener(this);
        Button05 = (Button) findViewById(R.id.Button05);
        
        
        LinearLayoutShedule = (LinearLayout)findViewById(R.id.LinearLayoutShedule);

        textStatus.setText(R.string.disturb);
        textStatus.setTypeface(typefaceBold);
        btn_back.setTypeface(typefaceMedium);
        Button01.setTypeface(typefaceRoman);
        Button03.setTypeface(typefaceRoman);
        Button02.setTypeface(typefaceRoman);
        Button04.setTypeface(typefaceRoman);
        Button05.setTypeface(typefaceRoman);
        textView1.setTypeface(typefaceRoman);
        textView4.setTypeface(typefaceRoman);
        always.setTypeface(typefaceRoman);
        locked.setTypeface(typefaceRoman);
        TextView01.setTypeface(typefaceRoman);
        TextView02.setTypeface(typefaceRoman);
        TextView03.setTypeface(typefaceRoman);
        TextView04.setTypeface(typefaceRoman);
        
        
        text_from.setTypeface(typefaceRoman);
        text_to.setTypeface(typefaceRoman);
        
        audio_mngr = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        
       
        
	}
	
	protected void onResume() {
        super.onResume();
       ButtonSound();
       time_read();
       Shedule_list();
       
       
      
       

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
		        Button03.setTypeface(typefaceBold);
		        Button02.setTypeface(typefaceBold);
		        Button04.setTypeface(typefaceBold);
		        Button05.setTypeface(typefaceBold);
		        textView1.setTypeface(typefaceBold);
		        textView4.setTypeface(typefaceBold);
		        text_from.setTypeface(typefaceBold);
		        text_to.setTypeface(typefaceBold);
		        always.setTypeface(typefaceBold);
		        locked.setTypeface(typefaceBold);
		        TextView01.setTypeface(typefaceBold);
		        TextView02.setTypeface(typefaceBold);
		        TextView03.setTypeface(typefaceBold);
		        TextView04.setTypeface(typefaceBold);
				
			}
       }
			
      if (mSettings.contains(APP_PREFERENCES_text_size)) {
       	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
			if (size .contains( "Small")){
				Button01.setTextSize(13);
		        Button03.setTextSize(13);
		        Button04.setTextSize(13);
		        Button05.setTextSize(13);
		        Button02.setTextSize(13);
		        textView1.setTextSize(11);
		        TextView01.setTextSize(11);
		        TextView02.setTextSize(11);
		        TextView03.setTextSize(11);
		        TextView04.setTextSize(11);
		        textView4.setTextSize(13);
		        text_from.setTextSize(13);
		        text_to.setTextSize(13);
		        always.setTextSize(13);
		        locked.setTextSize(13);
			}
			if (size .contains( "Normal")){
				Button01.setTextSize(15);
		        Button03.setTextSize(15);
		        Button04.setTextSize(15);
		        Button05.setTextSize(15);
		        Button02.setTextSize(15);
		        textView1.setTextSize(13);
		        TextView01.setTextSize(13);
		        TextView02.setTextSize(13);
		        TextView03.setTextSize(13);
		        TextView04.setTextSize(13);
		        textView4.setTextSize(15);
		        text_from.setTextSize(15);
		        text_to.setTextSize(15);
		        always.setTextSize(15);
		        locked.setTextSize(15);
			}
			if (size .contains( "Large")){
				Button01.setTextSize(18);
		        Button03.setTextSize(18);
		        Button04.setTextSize(18);
		        Button05.setTextSize(18);
		        Button02.setTextSize(18);
		        TextView01.setTextSize(15);
		        TextView02.setTextSize(15);
		        TextView03.setTextSize(15);
		        TextView04.setTextSize(15);
		        textView1.setTextSize(15);
		        textView4.setTextSize(18);
		        text_from.setTextSize(18);
		        text_to.setTextSize(18);
		        always.setTextSize(18);
		        locked.setTextSize(18);
			}
			if (size .contains( "xLarge")){
				Button01.setTextSize(20);
		        Button03.setTextSize(20);
		        Button04.setTextSize(20);
		        Button05.setTextSize(20);
		        Button02.setTextSize(20);
		        textView1.setTextSize(18);
		        TextView01.setTextSize(18);
		        TextView02.setTextSize(18);
		        TextView03.setTextSize(18);
		        TextView04.setTextSize(18);
		        textView4.setTextSize(20);
		        text_from.setTextSize(20);
		        text_to.setTextSize(20);
		        always.setTextSize(20);
		        locked.setTextSize(20);
			}
			
      }
      
			String size = mSettings.getString(APP_PREFERENCES_SILENCE_CALL, "always");
			
			if(size.equals("always"))
    		{
    			always.setChecked(true);
    			TextView04.setText(R.string.silence_call_disturb_text_always);
    		}
			else if (size.contains("locked")){
				locked.setChecked(true);
				TextView04.setText(R.string.silence_call_disturb_text_lock);
			}
		
      
      if (mSettings.contains(APP_PREFERENCES_REPEAT_CALL)) {
     	 Boolean menu = mSettings.getBoolean(APP_PREFERENCES_REPEAT_CALL, false);
			if (menu == true){
				tb_repeat.setChecked(true);
			}
			else{
				tb_repeat.setChecked(false);
			}
     }
      
     // if (mSettings.contains(APP_PREFERENCES_ALLOW_CALL)) {
   	 String menu444 = mSettings.getString(APP_PREFERENCES_ALLOW_CALL, "no one");
			if (menu444.contains("everyone")){
				textView4.setText(R.string.allow_call_disturb_everyone);
			}
			else if (menu444.contains("no one")){
				textView4.setText(R.string.allow_call_disturb_noone);
			}
			else if (menu444.contains("contacts")){
				textView4.setText(R.string.allow_call_disturb_contacts);
			}
 //  }
      
	}
	
	private void ButtonSound() {
		enable_disturb = mSettings.getBoolean(APP_PREFERENCES_DISTURB_ENABLE, false);
    	if (enable_disturb ==  true) {
    		tb_on.setChecked(true);
    	}
    }
	
	
	private void Shedule_list () {

		tb_shedule.setChecked(mSettings.getBoolean("shedule_disturb", false));
		if(tb_shedule.isChecked()) {
			LinearLayoutShedule.setVisibility(View.VISIBLE);

		}
		else{
			LinearLayoutShedule.setVisibility(View.GONE);
		}
/*
		if (text_from.getText().equals("--:--")) {
			LinearLayoutShedule.setVisibility(View.GONE);
			shedule = 0; 
			tb_shedule.setChecked(false);}
		else {
			LinearLayoutShedule.setVisibility(View.VISIBLE);
			shedule = 1;
			tb_shedule.setChecked(true);
		}
		*/
	}
	
	
	public void onClick(View v) {
        int id = v.getId();
        
		if (id == R.id.ToggleButton01) {
			if((tb_on).isChecked()) {
        		
        		start_notif ();}
        	else {
        		
        		stop_notif(); }
		} 
		else if (id == R.id.ToggleButton02) {
			if(tb_shedule.isChecked()) {
				timeSet t = new timeSet (this);
				t.start_receiver();


				//registerReceiver(Receiver, new IntentFilter(
				//		"android.intent.action.TIME_TICK"));
				LinearLayoutShedule.setVisibility(View.VISIBLE);
			}
        	else {
				timeSet t = new timeSet (this);
				t.stop_receiver();

				LinearLayoutShedule.setVisibility(View.GONE);
        		 }
		}
		else if (id == R.id.Button04) {
			Intent n1111 = new Intent (this, ActivityFromTime.class);
			SwipeBackActivityHelper.activityBuilder(this)
					.intent(n1111)
					.needParallax(false)
					.needBackgroundShadow(false)
					.startActivity();

	 	        	 }
		else if (id == R.id.ToggleButton03) {
			if((tb_repeat).isChecked()) {
				Editor editor = mSettings.edit();
			   	editor.putBoolean(APP_PREFERENCES_REPEAT_CALL, true);
			   	editor.commit();}
        	else {
        		Editor editor = mSettings.edit();
			   	editor.putBoolean(APP_PREFERENCES_REPEAT_CALL, false);
			   	editor.commit(); }
		} 
		else if (id == R.id.Button02){
			Intent intent1 = new Intent(this, ActivityAllowDisturb.class);
			startActivity(intent1);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
		}
	}
	
	private void time_read () {
		text_from.setText(mSettings.getString("disturb_from_hour","00")+":"+mSettings.getString("disturb_from_min", "00"));
		text_to.setText(mSettings.getString("disturb_to_hour","06")+":"+mSettings.getString("disturb_to_min", "00"));
		/*
		try {
			
			InputStream inputstream = openFileInput(to_time_text);
			InputStreamReader isr = new InputStreamReader(inputstream);
			BufferedReader reader = new BufferedReader(isr);
			String str;
			StringBuffer buffer = new StringBuffer();

			while ((str = reader.readLine()) != null) {
				buffer.append(str + "");
			}

			inputstream.close();
			text_to.setText(buffer.toString());
			
			} catch (Throwable t) {
				text_to.setText("");
			}
		//////
try {
			
			InputStream inputstream = openFileInput(from_time_text);
			InputStreamReader isr = new InputStreamReader(inputstream);
			BufferedReader reader = new BufferedReader(isr);
			String str;
			StringBuffer buffer = new StringBuffer();

			while ((str = reader.readLine()) != null) {
				buffer.append(str + "");
			}

			inputstream.close();
			text_from.setText(buffer.toString());
			
			} catch (Throwable t) {
				text_from.setText("");
			}
*/
	}
	
	private void time_0 () {

		mSettings.edit().remove("disturb_from_hour").apply();
		mSettings.edit().remove("disturb_from_min").apply();
		mSettings.edit().remove("disturb_to_hour").apply();
		mSettings.edit().remove("disturb_to_min").apply();
		/*
		try {
 			OutputStream outputstream = openFileOutput(from_time_text, 0);
 			OutputStreamWriter osw = new OutputStreamWriter(outputstream);
 			osw.write("");
 			osw.close();
 		} catch (Throwable t) {
 			
 		}
		try {
 			OutputStream outputstream = openFileOutput(to_time_text, 0);
 			OutputStreamWriter osw = new OutputStreamWriter(outputstream);
 			osw.write("");
 			osw.close();
 		} catch (Throwable t) {
 			
 		}
		*/
	}
	
	

	   
	public void start_notif (){
		timeSet t = new timeSet (getBaseContext()); 
		t.start_notif (getBaseContext());
	}
	public void stop_notif (){
		timeSet t = new timeSet (getBaseContext()); 
		t.stop_notif (getBaseContext());
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



	public void onCheckedChanged(RadioGroup group, int checkedId) {
		        switch (checkedId) {
		        case R.id.radio0:
		        	TextView04.setText(R.string.silence_call_disturb_text_always);
		        	Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_SILENCE_CALL, "always");
				   	editor.commit();
		      
		          break;

		        case R.id.radio1:
		        	TextView04.setText(R.string.silence_call_disturb_text_lock);
		        	Editor editor2 = mSettings.edit();
				   	editor2.putString(APP_PREFERENCES_SILENCE_CALL, "locked");
				   	editor2.commit();
		         
		          break;

		       
		        }
		      }
	       
	        
}
