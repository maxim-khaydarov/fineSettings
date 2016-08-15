package ua.mkh.settings.full;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityRegulatory extends Activity implements View.OnClickListener{

	
	
	TextView textStatus;
	
	 
	 Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	 
	 public static final String APP_PREFERENCES = "mysettings";
	 public static final String APP_PREFERENCES_tgb_apn = "tgb_apn";
	 public static final String APP_PREFERENCES_tgb_passcode = "tgb_passcode";
	 public static final String APP_PREFERENCES_tgb_privacy = "tgb_privacy";
	 public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   
	   Button btn_back;
	   
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   SharedPreferences mSettings;
	
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
		   this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_regulatory);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String bold =  "fonts/Bold.otf";
			String thin = "fonts/Thin.otf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			
			typefaceThin = Typeface.createFromAsset(getAssets(), thin);
			
			 btn_back = (Button) findViewById(R.id.buttonBack);
			 btn_back.setText(R.string.button_general);
			 

			 
			 textStatus = (TextView)findViewById(R.id.textOk);
			 
			 textStatus.setTypeface(typefaceBold);
			 textStatus.setText(R.string.regulatory);
		     btn_back.setTypeface(typefaceMedium);
		     
		     
		     
			
			
	   }
	   
	   
	            
	    @Override
	    public boolean onKeyDown(int keycode, KeyEvent e) {
	        switch(keycode) {
	           
	            case KeyEvent.KEYCODE_BACK:
	            	Intent intent18 = new Intent(this, ActivityOsnova.class);
	             	 startActivity(intent18);

	       		overridePendingTransition(center_to_right, center_to_right2);
	                return true;
	            
	        }
	        return super.onKeyDown(keycode, e);
	   }
	        
	    
	   	
	        public void BackClick(View v)  
	        {  
	        	Intent intent18 = new Intent(this, ActivityOsnova.class);
	         	 startActivity(intent18);

	   		overridePendingTransition(center_to_right, center_to_right2);
	       	 }

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
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
					
		    
			}
}
