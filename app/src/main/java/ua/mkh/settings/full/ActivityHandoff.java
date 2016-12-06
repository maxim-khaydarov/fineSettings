package ua.mkh.settings.full;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ActivityHandoff extends Activity implements View.OnClickListener{

	Button  btn_back;
	Button Button01;
	TextView TextView01;
	ToggleButton tg1;
	
	 
	 Typeface typefaceRoman, typefaceMedium, typefaceBold;
	 TextView textStatus;
	 
	 
	 public static final String APP_PREFERENCES = "mysettings"; 
	 public static final String APP_PREFERENCES_text_size = "txt_size";
	 public static final String APP_PREFERENCES_bold_text = "bold_txt";
	 public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	 public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	 public static final String APP_PREFERENCES_HANDOFF = "handoff";
	 
	
	 
	 int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	 
	 SharedPreferences mSettings;
	 
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_handoff);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String thin = "fonts/Thin.otf";
			String bold =  "fonts/Bold.otf";
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			 
			 btn_back = (Button) findViewById(R.id.buttonBack);
				btn_back.setText(R.string.app_name);
				textStatus = (TextView)findViewById(R.id.textOk);
				
				Button01 = (Button) findViewById(R.id.Button01);

				

				
				tg1 = (ToggleButton) findViewById(R.id.ToggleButton01);
				tg1.setOnClickListener(this);

				textStatus.setTypeface(typefaceBold);
				btn_back.setTypeface(typefaceMedium);
				btn_back.setText(R.string.button_general);
				textStatus.setText(R.string.handoff);
				Button01.setTypeface(typefaceRoman);

				//TextView01.setTypeface(typefaceRoman);

				
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
					TextView01.setTypeface(typefaceBold);

			        
					
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					Button01.setTextSize(13);
					TextView01.setTextSize(11);

				}
				if (size .contains( "Normal")){
					Button01.setTextSize(15);
					TextView01.setTextSize(13);

				}
				if (size .contains( "Large")){
					Button01.setTextSize(18);
					TextView01.setTextSize(15);

				}
				if (size .contains( "xLarge")){
					Button01.setTextSize(18);
					TextView01.setTextSize(20);

				}
	       }
	       
	       Boolean handoff = mSettings.getBoolean(APP_PREFERENCES_HANDOFF, true);
	       if(handoff == true){
	    	   tg1.setChecked(true);
	       }
	       else{
	    	   tg1.setChecked(false);
	       }
	       

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
	  
	        
	        public void onClick(View v) {
	        	switch(v.getId()){
	        	
	        	case R.id.ToggleButton01:
	        		if((tg1).isChecked()){
	        			Editor editor = mSettings.edit();
	    			   	editor.putBoolean(APP_PREFERENCES_HANDOFF, true).commit();
	        		}
	        		else{
	        			Editor editor = mSettings.edit();
	    			   	editor.putBoolean(APP_PREFERENCES_HANDOFF, false).commit();
	        		}
	        		break;
	        		

	        		
	        	}
	        }
}
