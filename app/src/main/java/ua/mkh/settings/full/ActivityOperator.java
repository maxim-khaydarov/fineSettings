package ua.mkh.settings.full;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityOperator extends Activity implements View.OnClickListener{

	TextView TextView01, operator;
	

	Typeface typefaceRoman,  typefaceMedium, typefaceBold;
	
	String networkText;
	
	
	SharedPreferences mSettings;
	public static final String APP_PREFERENCES = "mysettings";
	public static final String APP_PREFERENCES_text_size = "txt_size";
	 public static final String APP_PREFERENCES_bold_text = "bold_txt";
	 public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	 public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	 public static final String APP_PREFERENCES_NETWORK = "networkText";
	 
	 Button Button01, Button02, Button1, btn_back;
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   String name;
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_operator);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String thin = "fonts/Thin.otf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			btn_back = (Button) findViewById(R.id.buttonBack);
			btn_back.setText(R.string.app_name);
			TextView textStatus = (TextView)findViewById(R.id.textOk);
	        operator = (TextView)findViewById(R.id.textView1);
	        TextView01 = (TextView) findViewById(R.id.TextView01);
	        //TextView number = (TextView)findViewById(R.id.number);
	        Button1 = (Button)findViewById(R.id.button1);
	        Button1.setOnClickListener(this);
	        Button01 = (Button)findViewById(R.id.Button01);
	        Button02 = (Button)findViewById(R.id.Button02);
	        Button02.setOnClickListener(this);
	        //Button Button03 = (Button)findViewById(R.id.Button03);
	        //textView2 = (TextView)findViewById(R.id.textView2);
	        
	        
	        
	        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			
			
			
	        textStatus.setTypeface(typefaceBold);
			btn_back.setTypeface(typefaceMedium);
			textStatus.setText(R.string.button_operator);
			operator.setTypeface(typefaceRoman);
			//number.setTypeface(typefaceThin);
			//textView2.setTypeface(typefaceRoman);
			TextView01.setTypeface(typefaceRoman);
			Button02.setTypeface(typefaceRoman);
			Button01.setTypeface(typefaceRoman);
			Button1.setTypeface(typefaceRoman);
			//Button03.setTypeface(typefaceRoman);
			
	        
	        
	 }
	 
	
	 
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId())  { 
		
		case R.id.Button02:			
			 Intent settingsIntent = new Intent(android.provider.Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
	       	startActivity(settingsIntent);
		 	        	overridePendingTransition(center_to_left, center_to_left2);
			break;
			
		case R.id.button1:
			enter_name_oper();
			break;
		}
		 
	}
	
	private void enter_name_oper() {
    	final Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent);
	     dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	     dialog.setContentView(R.layout.dialog_3_button);
	     
	     
	     Button ButtonMenuCancel = (Button)dialog.getWindow().findViewById(R.id.dialogButtonCancel);
	     Button ButtonMenuContinue = (Button)dialog.getWindow().findViewById(R.id.dialogButtonOK);
	     Button ButtonMenuClean = (Button)dialog.getWindow().findViewById(R.id.dialogButtonClean);
	     final EditText Ed = (EditText) dialog.getWindow().findViewById(R.id.editText1);
	     Ed.setText( networkText);
     
	     ButtonMenuCancel.setTypeface(typefaceRoman);
	     ButtonMenuClean.setTypeface(typefaceRoman);
	     ButtonMenuContinue.setTypeface(typefaceRoman);
	     Ed.setTypeface(typefaceRoman);
	     
	     ButtonMenuCancel.setOnClickListener(new OnClickListener(){

	   @Override
	   public void onClick(View v) {
	    dialog.dismiss();
	   }});
	     
	     ButtonMenuContinue.setOnClickListener(new OnClickListener(){

			   @Override
			   public void onClick(View v) {
				   String value = Ed.getText().toString();
					
					Editor editor = mSettings.edit();
					networkText = value;
				   	editor.putString(APP_PREFERENCES_NETWORK, value);
				   	editor.apply();
				   	
				   	Intent intent = getIntent();
                   overridePendingTransition(0, 0);
                   intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                   startActivity(intent);
			   
			   }

			
				
			});
	     
	     ButtonMenuClean.setOnClickListener(new OnClickListener(){

			   @Override
			   public void onClick(View v) {
				   Editor editor = mSettings.edit();
                   editor.remove("networkText").commit();
                   dialog.dismiss();
                   
                   Intent intent = getIntent();
                   overridePendingTransition(0, 0);
                   intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                   startActivity(intent);
			   
			   }

			
				
			});
	     
	    
	     
	     dialog.show();
	    }
	
	public void BackClick(View v)  
    {  
    	Intent intent18 = new Intent(this, MainActivity.class);
     	 startActivity(intent18);
		overridePendingTransition(center_to_right, center_to_right2);
   	 }

	 protected void onResume() {
	        super.onResume();
	        
	        network();
	        if (mSettings.contains(APP_PREFERENCES_NETWORK)) {
	        	 String network = mSettings.getString(APP_PREFERENCES_NETWORK, null);
				networkText = network;
				operator.setText(network);
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
				TextView01.setTypeface(typefaceBold);
				operator.setTypeface(typefaceBold);
				Button1.setTypeface(typefaceBold);
				Button01.setTypeface(typefaceBold);
				Button02.setTypeface(typefaceBold);
				}
	       }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, null);
				if (size .contains( "Small")){
				TextView01.setTextSize(13);
				operator.setTextSize(13);
				Button02.setTextSize(13);
				Button01.setTextSize(13);
				Button1.setTextSize(13);

				}
				if (size .contains( "Normal")){
					TextView01.setTextSize(15);
					operator.setTextSize(15);
					Button02.setTextSize(15);
					Button01.setTextSize(15);
					Button1.setTextSize(15);
				}
				if (size .contains( "Large")){
					TextView01.setTextSize(18);
					operator.setTextSize(18);
					Button02.setTextSize(18);
					Button01.setTextSize(18);
					Button1.setTextSize(18);
				}
				if (size .contains( "xLarge")){
					TextView01.setTextSize(20);
					operator.setTextSize(20);
					Button02.setTextSize(20);
					Button01.setTextSize(20);
					Button1.setTextSize(20);
				}
	       }
	 }


    private void network() {
    	TelephonyManager manager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        operator.setText(manager.getSimOperatorName());
        TextView01.setText(manager.getNetworkOperatorName());
		
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
