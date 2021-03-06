package ua.mkh.settings.full;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ActivityGeo extends Activity implements View.OnClickListener{

	TextView textView1, textView2, textView3;
	ToggleButton tb_gps;
	Intent intent;
	Button bt_geo;
	int OS = android.os.Build.VERSION.SDK_INT;
	
	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	 
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   

	SharedPreferences mSettings;
	Typeface typefaceRoman, typefaceMedium, typefaceBold;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_geo);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String bold =  "fonts/Bold.otf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			
			
			Button btn_back = (Button) findViewById(R.id.buttonBack);
			btn_back.setText(R.string.app_name);
			bt_geo = (Button)findViewById(R.id.ButtonGeo); 
			tb_gps = (ToggleButton)findViewById(R.id.gpstoggle);
			 tb_gps.setOnClickListener(this);
			
			TextView textStatus = (TextView)findViewById(R.id.textOk);
			textView1 = (TextView)findViewById(R.id.textView1);
			textView2 = (TextView)findViewById(R.id.textView2);
			textView3 = (TextView)findViewById(R.id.textView3);
			
			textStatus.setText(R.string.button_geolocatoin);
	        textStatus.setTypeface(typefaceMedium);
	        btn_back.setTypeface(typefaceBold);
	        textView1.setTypeface(typefaceRoman);
	        textView2.setTypeface(typefaceMedium);
	        textView3.setTypeface(typefaceRoman);
	        bt_geo.setTypeface(typefaceRoman);
	        
	        
	        
	 }
	 
	 
	 protected void onResume() {
	        super.onResume();
	       ButtonGps();
	       
	       
	       

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
					textView1.setTypeface(typefaceBold);
			        textView2.setTypeface(typefaceBold);
			        textView3.setTypeface(typefaceBold);
			        bt_geo.setTypeface(typefaceBold);
					
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					textView1.setTextSize(11);
			        textView2.setTextSize(13);
			        textView3.setTextSize(11);
			        bt_geo.setTextSize(13);
				}
				if (size .contains( "Normal")){
					textView1.setTextSize(13);
			        textView2.setTextSize(15);
			        textView3.setTextSize(12);
			        bt_geo.setTextSize(15);
				}
				if (size .contains( "Large")){
					textView1.setTextSize(15);
			        textView2.setTextSize(18);
			        textView3.setTextSize(15);
			        bt_geo.setTextSize(18); 
				}
				if (size .contains( "xLarge")){
					textView1.setTextSize(18);
			        textView2.setTextSize(20);
			        textView3.setTextSize(18);
			        bt_geo.setTextSize(20);
				}
	       }
	    }
	 
	 public void onClick(View v) {
		 int id = v.getId();
		 
	        if (id == R.id.gpstoggle) {
	        	if (OS > 10) {
          			 Intent settingsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
          	        	startActivity(settingsIntent);
        	 	        	overridePendingTransition(center_to_left, center_to_left2);
        	 	        	 }
          		 else {
            	if ((tb_gps).isChecked()) {
            		
            		String provider = Settings.Secure.getString(getContentResolver(),                  
                            Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            		if(!provider.contains("gps")){ //if gps is disabled
            final Intent poke = new Intent();
      		poke.setClassName("com.android.settings",  
      		                   "com.android.settings.widget.SettingsAppWidgetProvider");
      		poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
      		poke.setData(Uri.parse("3"));
      		sendBroadcast(poke);
            		}   
            	} 
      		else {
      			String provider2 = Settings.Secure.getString(getContentResolver(),                  
                        Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
      	if(provider2.contains("gps")){ //if gps is disabled
   		final Intent poke = new Intent();
   		poke.setClassName("com.android.settings",  
   		                   "com.android.settings.widget.SettingsAppWidgetProvider");
   		poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
   		poke.setData(Uri.parse("3"));
   		sendBroadcast(poke);
      	}
      	}
	        } }
	 }
	 
	 
	        
	 
	 ///GPS MODE
	    private void ButtonGps() {
	    	LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
	        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
	            tb_gps.setChecked(true);        
	    }
	    
	    }
	 
	    public void BackClick(View v)  
	    {  
	    	Intent intent18 = new Intent(this, MainActivity.class);
	      	 startActivity(intent18);

			overridePendingTransition(center_to_right, center_to_right2);
	   	 }
	
	    @Override
	    public boolean onKeyDown(int keycode, KeyEvent e) {
	        switch(keycode) {
	            
	            case KeyEvent.KEYCODE_BACK:
	            	Intent intent18 = new Intent(this, MainActivity.class);
	             	 startActivity(intent18);

	       		overridePendingTransition(center_to_right, center_to_right2);
	                return true;
	            
	        }
	        return super.onKeyDown(keycode, e);
	   }
	        
	 
		
}
