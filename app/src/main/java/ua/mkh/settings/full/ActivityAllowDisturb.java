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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ActivityAllowDisturb extends Activity implements View.OnClickListener,  RadioGroup.OnCheckedChangeListener{

	 public static final String APP_PREFERENCES_ALLOW_CALL = "allow_call";
	 
	 public static final String APP_PREFERENCES = "mysettings";
		public static final String APP_PREFERENCES_text_size = "txt_size";
		public static final String APP_PREFERENCES_bold_text = "bold_txt";
		   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
		   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	 
	 Typeface typefaceRoman, typefaceMedium, typefaceBold;
	 
	 
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   Button btn_back;
	   TextView textStatus;
	   
	   private RadioButton everyone, noone, contacts;
	
	SharedPreferences mSettings;
	String blockingMode;
	
	private RadioButton always, locked;
	
	
	
	@Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
      setContentView(R.layout.activity_allow_call);
      String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String thin = "fonts/Thin.otf";
		String bold =  "fonts/Bold.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		Typeface typefaceThin = Typeface.createFromAsset(getAssets(), thin);
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		
		
		everyone=(RadioButton) findViewById(R.id.radio0);
		noone=(RadioButton) findViewById(R.id.radio1);
		contacts=(RadioButton) findViewById(R.id.radio2);
		
		
		btn_back = (Button) findViewById(R.id.buttonBack);
        btn_back.setText(R.string.back);
        textStatus = (TextView)findViewById(R.id.textOk);
        
		RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroup1);
      radiogroup.setOnCheckedChangeListener(this);
   // radio button setting
      
      textStatus.setText(R.string.disturb);
      textStatus.setTypeface(typefaceMedium);
      btn_back.setTypeface(typefaceBold);
      everyone.setTypeface(typefaceRoman);
      noone.setTypeface(typefaceRoman);
      contacts.setTypeface(typefaceRoman);
      
}

	
	public void onClick(View v) {
        int id = v.getId();
        
        
	}
	
	
	
	public void loadData (){
		blockingMode = mSettings.getString(APP_PREFERENCES_ALLOW_CALL, "no one");
		
		if (blockingMode.contains("no one")){
			noone.setChecked(true);
		}
		else if (blockingMode.contains("everyone")){
			everyone.setChecked(true);
		}
		else {
			contacts.setChecked(true);
		}
	}
	
     
     public void onCheckedChanged(RadioGroup group, int checkedId) {
	        switch (checkedId) {
	        case R.id.radio0:
	        	Editor editor = mSettings.edit();
			   	editor.putString(APP_PREFERENCES_ALLOW_CALL, "everyone");//ot vseh
			   	editor.commit();
	      
	          break;

	        case R.id.radio1:
	        	Editor editor2 = mSettings.edit();
			   	editor2.putString(APP_PREFERENCES_ALLOW_CALL, "no one");//ni ot kogo
			   	editor2.commit();
	         
	          break;
	          
	        case R.id.radio2:
	        	Editor editor3 = mSettings.edit();
			   	editor3.putString(APP_PREFERENCES_ALLOW_CALL, "contacts");//ot kontaktov
			   	editor3.commit();
	         
	          break;
	       
	        }
	      }
     
     @Override
	    public boolean onKeyDown(int keycode, KeyEvent e) {
	        switch(keycode) {
	            
	            case KeyEvent.KEYCODE_BACK:
	            	Intent intent18 = new Intent(this, ActivityDisturb.class);
	   	       	 startActivity(intent18);
	   	        	overridePendingTransition(center_to_right, center_to_right2);
	                return true;
	            
	        }
	        return super.onKeyDown(keycode, e);
	   }
     
    
		
	        public void BackClick(View v)  
	        {  
	    		Intent intent18 = new Intent(this, ActivityDisturb.class);
	         	 startActivity(intent18);

	    		overridePendingTransition(center_to_right, center_to_right2);
	       	 }
	        
	        protected void onResume() {
	            super.onResume();
	           
	            loadData ();
	           

	           
	           

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
	    				everyone.setTypeface(typefaceBold);
	    			      noone.setTypeface(typefaceBold);
	    			      contacts.setTypeface(typefaceBold);
	    				
	    			}
	           }
	    			
	          if (mSettings.contains(APP_PREFERENCES_text_size)) {

	           	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
	    			if (size .contains( "Small")){
	    				everyone.setTextSize(13);
	    				noone.setTextSize(13);
	    				contacts.setTextSize(13);
	    			}
	    			if (size .contains( "Normal")){
	    				everyone.setTextSize(15);
	    				noone.setTextSize(15);
	    				contacts.setTextSize(15);
	    			}
	    			if (size .contains( "Large")){
	    				everyone.setTextSize(18);
	    				noone.setTextSize(18);
	    				contacts.setTextSize(18);
	    			}
	    			if (size .contains( "xLarge")){
	    				everyone.setTextSize(20);
	    				noone.setTextSize(20);
	    				contacts.setTextSize(20);
	    			}
	    			
	          }
	        }
	
}