package ua.mkh.settings.full;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;







import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.DetailedState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ActivitySota extends Activity implements OnClickListener{

	ToggleButton tb_data, tb_roum, tb_3g;
	ConnectivityManager dataManager;
	Method dataMtd = null;
	TelephonyManager manager;
	Button Button01, Button02, btn_back, Button03, Button04;
	TextView textView1, textView2, textStatus, type;
	Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	
	
	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   SharedPreferences mSettings;
	   
	   
	
	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
		  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_sota);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String bold =  "fonts/Bold.otf";
			String thin = "fonts/Thin.otf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			typefaceThin = Typeface.createFromAsset(getAssets(), thin);
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);



			
			textView2 = (TextView)findViewById(R.id.textView02);
			//textView1 = (TextView)findViewById(R.id.textView1);
	        tb_data = (ToggleButton) findViewById(R.id.soundtoggle);
	        //tb_data.setOnClickListener(this);
	        tb_roum = (ToggleButton) findViewById(R.id.ToggleButton01);

		  Log.e("MY APP", String.valueOf(android.os.Build.VERSION.SDK_INT));
		  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M ) {
			 tb_data.setEnabled(false);
		  }
		  else{
			  tb_data.setOnClickListener(this);
		  }

	        Button01 = (Button)findViewById(R.id.Button01);
	        Button02 = (Button)findViewById(R.id.Button02);
		  	Button02.setOnClickListener(this);
	        Button03 = (Button)findViewById(R.id.Button03);
	        Button03.setOnClickListener(this);
	        Button04 = (Button)findViewById(R.id.Button04);
	        
	        textStatus = (TextView)findViewById(R.id.textOk);
	        type = (TextView)findViewById(R.id.textView007);
	        btn_back = (Button) findViewById(R.id.buttonBack);
	        btn_back.setText(R.string.app_name);
	        textStatus.setText(R.string.button_sota);
	        textStatus.setTypeface(typefaceBold);
	        btn_back.setTypeface(typefaceMedium);
	        
//	        textView2.setTypeface(typefaceRoman);
	        //textView1.setTypeface(typefaceRoman);
	        Button01.setTypeface(typefaceRoman);
	        Button02.setTypeface(typefaceRoman);
	        Button03.setTypeface(typefaceRoman);
	        //Button04.setTypeface(typefaceRoman);
	        type.setTypeface(typefaceRoman);
}
	  
	     
	     
	  protected void onResume() {
	        super.onResume();
	       ButtonData();
	       check_roum();
		  check_sharing();
	       
	      
	      
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
					//textView2.setTypeface(typefaceBold);
			        textView1.setTypeface(typefaceBold);
			        Button01.setTypeface(typefaceBold);
			        Button02.setTypeface(typefaceBold);
			        Button03.setTypeface(typefaceBold);
			        type.setTypeface(typefaceBold);
					
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					//textView2.setTextSize(11);
			        textView1.setTextSize(11);
			        Button01.setTextSize(13);
			        Button02.setTextSize(13);
			        Button03.setTextSize(13);
			        type.setTextSize(13);
				}
				if (size .contains( "Normal")){
					//textView2.setTextSize(13);
			        textView1.setTextSize(13);
			        Button01.setTextSize(15);
			        Button02.setTextSize(15);
			        Button03.setTextSize(15);
			        type.setTextSize(15);
				}
				if (size .contains( "Large")){
					//textView2.setTextSize(15);
			        textView1.setTextSize(15);
			        Button01.setTextSize(18);
			        Button02.setTextSize(18);
			        Button03.setTextSize(18);
			        type.setTextSize(18);
				}
				if (size .contains( "xLarge")){
					//textView2.setTextSize(18);
			        textView1.setTextSize(18);
			        Button01.setTextSize(20);
			        Button02.setTextSize(20);
			        Button03.setTextSize(20);
			        type.setTextSize(20);
				}
	       }
	       
	    }

	private void check_sharing() {
		WifiManager wifi = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		TextView tx32 = (TextView) findViewById(R.id.textView32);
		if(isSharingWiFi(wifi) == true){
			tx32.setText(R.string.on);
		}
		else{
			tx32.setText(R.string.off);
		}
	}

	private void check_roum() {
		  final TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
	        PhoneStateListener phoneStateListener = new PhoneStateListener() {
	            @Override
	            public void onServiceStateChanged(ServiceState serviceState) {
	                super.onServiceStateChanged(serviceState);
	                if (telephonyManager.isNetworkRoaming()) {
	                   type.setText(R.string.roaming_text +" "+ R.string.on);
	                } else {
						type.setText(R.string.roaming_text +" "+ R.string.off);
	                }
	                // You can also check roaming state using this
	                if (serviceState.getRoaming()) {
						type.setText(R.string.roaming_text +" "+ R.string.on);
	                } else {
						type.setText(R.string.roaming_text +" "+ R.string.off);
	                }
	            }
	        };
		
	}

	private void ButtonData () {
	    	dataManager  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
	    	android.net.NetworkInfo mobile = dataManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	    	if (mobile.isAvailable() && mobile.getDetailedState() == DetailedState.CONNECTED) {
	    		tb_data.setChecked(true);
	    	}
	    }


	private static boolean isSharingWiFi(final WifiManager manager)
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
	  
	  public void onClick(View v) {

		  switch (v.getId()){

			  case R.id.soundtoggle:
				  try {
					  dataManager  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
					  if((tb_data).isChecked()) {
						  try {
							  dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
						  } catch (NoSuchMethodException e) {
							  // TODO Auto-generated catch block
							  e.printStackTrace();
						  }
						  dataMtd.setAccessible(true);
						  try {
							  dataMtd.invoke(dataManager, true);
						  } catch (IllegalArgumentException e) {
							  // TODO Auto-generated catch block
							  e.printStackTrace();
						  } catch (IllegalAccessException e) {
							  // TODO Auto-generated catch block
							  e.printStackTrace();
						  } catch (InvocationTargetException e) {
							  // TODO Auto-generated catch block
							  e.printStackTrace();
						  }
					  }
					  else {
						  try {
							  dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
						  } catch (NoSuchMethodException e) {
							  // TODO Auto-generated catch block
							  e.printStackTrace();
						  }
						  dataMtd.setAccessible(true);
						  try {
							  dataMtd.invoke(dataManager, false);
						  } catch (IllegalArgumentException e) {
							  // TODO Auto-generated catch block
							  e.printStackTrace();
						  } catch (IllegalAccessException e) {
							  // TODO Auto-generated catch block
							  e.printStackTrace();
						  } catch (InvocationTargetException e) {
							  // TODO Auto-generated catch block
							  e.printStackTrace();
						  }
					  }
				  }catch(Exception e){

				  }
				  break;

			  case R.id.Button02:
				  Intent go = new Intent(this, ActivitySotaParam.class);
				  startActivity(go);
				  overridePendingTransition(center_to_left, center_to_left2);
				  break;

			  case R.id.Button03:
				  Intent go1 = new Intent(this, ActivityVPN.class);
				  startActivity(go1);
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
	        
	 
	  
	        public void BackClick(View v)  
	        {  
	        	Intent intent18 = new Intent(this, MainActivity.class);
	         	 startActivity(intent18);

	   		overridePendingTransition(center_to_right, center_to_right2);
	       	 }
	        }

