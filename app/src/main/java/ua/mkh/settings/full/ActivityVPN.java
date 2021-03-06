package ua.mkh.settings.full;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ActivityVPN extends Activity implements View.OnClickListener {

	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   
	   SharedPreferences mSettings;
	   
	   Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	   
	   Button btn_back, Button07, Buttongeo;
	   TextView textStatus, textView1, textView2, textView3, textView4;
	   ToggleButton vpn;
	   WifiManager wifi_manager;
	   
	   
	   @Override
	    public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_vpn);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String bold =  "fonts/Bold.otf";
			String thin = "fonts/Thin.otf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			typefaceThin = Typeface.createFromAsset(getAssets(), thin);
			
			vpn = (ToggleButton) findViewById(R.id.gpstoggle);
			vpn.setOnClickListener(this);
			
			Button07 = (Button) findViewById(R.id.Button07);
			Button07.setOnClickListener(this);
			Buttongeo = (Button) findViewById(R.id.ButtonGeo);
			textView1 = (TextView) findViewById(R.id.textView1);
			textView2 = (TextView) findViewById(R.id.textView2);
			//textView2.setLineSpacing(25f, 0f);
			textView3 = (TextView) findViewById(R.id.textView3);
			//textView3.setLineSpacing(25f, 0f);
			textView4 = (TextView) findViewById(R.id.textView4);
			//textView4.setLineSpacing(25f, 0f);
			
			wifi_manager = (WifiManager) this.getSystemService(this.WIFI_SERVICE);
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			
			 
			 btn_back = (Button) findViewById(R.id.buttonBack);
				btn_back.setText(R.string.app_name);
				textStatus = (TextView)findViewById(R.id.textOk);
				
				
				textStatus.setTypeface(typefaceBold);
    			btn_back.setTypeface(typefaceMedium);
    			textStatus.setText(R.string.apn);
    			Button07.setTypeface(typefaceRoman);
				Buttongeo.setTypeface(typefaceRoman);
				textView1.setTypeface(typefaceRoman);
				textView2.setTypeface(typefaceRoman);
				textView3.setTypeface(typefaceRoman);
				textView4.setTypeface(typefaceRoman);
	    }
	   
	    protected void onResume() {
	        super.onResume();
	       
	        if (isSharingWiFi(wifi_manager) == true){
	        	vpn.setChecked(true);
	        }
	       
	       

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
					Button07.setTypeface(typefaceBold);
					Buttongeo.setTypeface(typefaceBold);
					textView1.setTypeface(typefaceBold);
					textView2.setTypeface(typefaceBold);
					textView3.setTypeface(typefaceBold);
					textView4.setTypeface(typefaceBold);
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					Button07.setTextSize(13);
					Buttongeo.setTextSize(13);
					textView1.setTextSize(11);
					textView2.setTextSize(11);
					textView3.setTextSize(11);
					textView4.setTextSize(11);
				}
				if (size .contains( "Normal")){
					Button07.setTextSize(15);
					Buttongeo.setTextSize(15);
					textView1.setTextSize(13);
					textView2.setTextSize(13);
					textView3.setTextSize(13);
					textView4.setTextSize(13);
				}
				if (size .contains( "Large")){
					Button07.setTextSize(18);
					Buttongeo.setTextSize(18);
					textView1.setTextSize(15);
					textView2.setTextSize(15);
					textView3.setTextSize(15);
					textView4.setTextSize(15);
				}
				if (size .contains( "xLarge")){
					Button07.setTextSize(20);
					Buttongeo.setTextSize(20);
					textView1.setTextSize(18);
					textView2.setTextSize(18);
					textView3.setTextSize(18);
					textView4.setTextSize(18);
				}
	       }
	    }
	    
	    public static boolean isSharingWiFi(final WifiManager manager)
	    {
	        try
	        {
	            final Method method = manager.getClass().getDeclaredMethod("isWifiApEnabled");
	            method.setAccessible(true); //in the case of visibility change in future APIs
	            return (Boolean) method.invoke(manager);
	        }
	        catch (final Throwable ignored)
	        {
	        }

	        return false;
	    }

		public void BackClick(View v)  
	    {  
	   	 Intent intent18 = new Intent(this, MainActivity.class);
	         	 startActivity(intent18);

	   		overridePendingTransition(center_to_right, center_to_right2);
	   	 }
	    
	    public void onClick(View v) {
	    	switch(v.getId())  { 
	    	
	        case  R.id.gpstoggle:
	        	if(vpn.isChecked())//means this is the request to turn ON AIRPLANE mode
	        	{
	        		WifiConfiguration wifi_configuration = null;
	        	      wifi_manager.setWifiEnabled(false);

	        	      try 
	        	      {
	        	         //USE REFLECTION TO GET METHOD "SetWifiAPEnabled"
	        	         Method method=wifi_manager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);
	        	         method.invoke(wifi_manager, wifi_configuration, true);
	        	      } 
	        	      catch (NoSuchMethodException e) 
	        	      {
	        	         // TODO Auto-generated catch block
	        	         e.printStackTrace();
	        	      } 
	        	      catch (IllegalArgumentException e) 
	        	      {
	        	         // TODO Auto-generated catch block
	        	         e.printStackTrace();
	        	      } 
	        	      catch (IllegalAccessException e) 
	        	      {
	        	         // TODO Auto-generated catch block
	        	         e.printStackTrace();
	        	      } 
	        	      catch (InvocationTargetException e) 
	        	      {
	        	         // TODO Auto-generated catch block
	        	         e.printStackTrace();
	        	      }
	        	   }
	        	else {
	        		WifiConfiguration wifi_configuration = null;
	        	      wifi_manager.setWifiEnabled(false);

	        	      try 
	        	      {
	        	         //USE REFLECTION TO GET METHOD "SetWifiAPEnabled"
	        	         Method method=wifi_manager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);
	        	         method.invoke(wifi_manager, wifi_configuration, false);
	        	      } 
	        	      catch (NoSuchMethodException e) 
	        	      {
	        	         // TODO Auto-generated catch block
	        	         e.printStackTrace();
	        	      } 
	        	      catch (IllegalArgumentException e) 
	        	      {
	        	         // TODO Auto-generated catch block
	        	         e.printStackTrace();
	        	      } 
	        	      catch (IllegalAccessException e) 
	        	      {
	        	         // TODO Auto-generated catch block
	        	         e.printStackTrace();
	        	      } 
	        	      catch (InvocationTargetException e) 
	        	      {
	        	         // TODO Auto-generated catch block
	        	         e.printStackTrace();
	        	      }
	        	   }
	        	break;
	    	case R.id.Button07:
	    		Intent settingsIntent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
	        	startActivity(settingsIntent);
		 	        	overridePendingTransition(center_to_left, center_to_left2);
		 	        	
	    		break;
	        	}
	    	
	    		
	    	
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