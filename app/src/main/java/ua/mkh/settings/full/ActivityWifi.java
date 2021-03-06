package ua.mkh.settings.full;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager.LayoutParams;
import android.text.Html;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;



public class ActivityWifi extends Activity implements View.OnClickListener {

	TextView textConnected;
	final Context context = this;
	ToggleButton tb_in, tb_wifi, tb_ch;
	WifiManager wifi;
	TextView  textStatus, txtPercentage, textView3, textView4, textView5, textView6,
	textView1, textView2;
	Button btn_back, btn_1, Button04;
	TableLayout tableLayout;
	Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	LinearLayout LinearLayoutCon, MainLayout2;
	RelativeLayout LayoutMain;

	Wifi_Info_Adapter studentArrayAdapter;

	 ArrayList<Wifi_Info> studentArray = new ArrayList<Wifi_Info>();




	public static final String APP_PREFERENCES = "mysettings";
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	public static final String APP_PREFERENCES_attentional_wifi = "attentional_wifi";

	  

	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;

	   int pass = 0;

	   SharedPreferences mSettings;

	   ProgressBar pg1;

	   ListView lv;
	   ImageView img1, img2;
	   String Capabilities;

	  Button buttonScan, info;
	  int size = 0;
	  List<ScanResult> results;


	  String ITEM_KEY = "key", WIFI_KEY = "wifi_key", RSSI_KEY = "rssi_key", MAC_KEY = "mac_key",
			  CAP_KEY = "cap_key", RSSILEVEL_KEY;
	  ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
	  SimpleAdapter adapter;

	  WifiInfo myWifiInfo;
	  ConnectivityManager myConnManager;
	  NetworkInfo myNetworkInfo;
	  WifiManager myWifiManager;
	  final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 1001;
	  int wifi_state = 0;

	    /* Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_wifi);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String bold =  "fonts/Bold.otf";
			String thin = "fonts/Thin.otf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			typefaceThin = Typeface.createFromAsset(getAssets(), thin);

			pg1 = (ProgressBar) findViewById(R.id.progressBar2);


			 myConnManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		     myNetworkInfo = myConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		     myWifiManager = (WifiManager)this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
			myWifiInfo = myWifiManager.getConnectionInfo();

			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

			Boolean m = mSettings.getBoolean(APP_PREFERENCES_attentional_wifi, false);
			if (m == false) {
				if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
					show_danger();
				}
			}

			//LinearLayoutCon = (LinearLayout) findViewById(R.id.LinearLayoutCon);
			LayoutMain = (RelativeLayout) findViewById(R.id.LayoutMain);
			MainLayout2 = (LinearLayout) findViewById(R.id.MainLayout2);
			LayoutMain.setVisibility(View.GONE);
			MainLayout2.setVisibility(View.GONE);


			btn_back = (Button) findViewById(R.id.buttonBack);
			btn_back.setText(R.string.app_name);
			textStatus = (TextView)findViewById(R.id.textOk);
			btn_1 = (Button) findViewById(R.id.ButtonWifi);






			tb_wifi = (ToggleButton) findViewById(R.id.ToggleButton01);
			tb_wifi.setOnClickListener(this);
			 tb_in = (ToggleButton) findViewById(R.id.toggleButtonInfo);
			 tb_in.setOnClickListener(this);
			 wifi = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
			 tb_ch = (ToggleButton) findViewById(R.id.toggleButtonChek);
			 tb_ch.setOnClickListener(this);

			 info = (Button) findViewById(R.id.button1);
			 info.setOnClickListener(this);

			 textConnected = (TextView)findViewById(R.id.Connected);
			 textConnected.setOnClickListener(this);

		       textView1 = (TextView)findViewById(R.id.textView1);
		       textView2 = (TextView)findViewById(R.id.textView2);




		       textConnected.setTypeface(typefaceRoman);

		       tb_in.setVisibility(View.INVISIBLE);
          		tb_ch.setVisibility(View.INVISIBLE);
          		img2 = (ImageView) findViewById(R.id.imageView1);
          		img2.setVisibility(View.INVISIBLE);

          		textStatus.setTypeface(typefaceBold);
    			btn_back.setTypeface(typefaceMedium);
    			textStatus.setText(R.string.button_wifi);
    			btn_1.setTypeface(typefaceRoman);


    			textView1.setTypeface(typefaceRoman);
    			textView2.setTypeface(typefaceRoman);








		       studentArrayAdapter = new Wifi_Info_Adapter(ActivityWifi.this, R.layout.row_wifi, studentArray);
		       lv = (ListView)findViewById(R.id.list);
		       lv.setItemsCanFocus(true);
		       lv.setAdapter(studentArrayAdapter);


		       studentArrayAdapter.setListener(new Wifi_Info_Adapter.OnPairButtonClickListener() {
					@Override
					public void onPairButtonClick(int position) {
						/*
						Toast.makeText(ActivityWifi.this,
					    	      "List Item Clicked:" + position, Toast.LENGTH_LONG)
					    	      .show();*/
					}
				});








	    }

	private void show_danger() {

			final Dialog Activation = new Dialog(ActivityWifi.this, android.R.style.Theme_Translucent);
			Activation.requestWindowFeature(Window.FEATURE_NO_TITLE);
			Activation.setContentView(R.layout.dialog_inform);

			// set the custom dialog components - text, image and button

			Button dialogButton = (Button) Activation.findViewById(R.id.dialogButtonOK);
			TextView text = (TextView) Activation.findViewById(R.id.text);
			TextView textBold = (TextView) Activation.findViewById(R.id.textBold);

			dialogButton.setTypeface(typefaceRoman);
			textBold.setTypeface(typefaceBold);
			dialogButton.setText(R.string.ok);
			text.setTypeface(typefaceRoman);


			text.setText(R.string.information_wifi_23);

			text.setPadding(30, 10, 10, 10);

			textBold.setText(R.string.attention);
			textBold.setTextSize(16);
			text.setTextSize(14);
			text.setGravity(Gravity.LEFT);

			// if button is clicked, close the custom dialog
			dialogButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Activation.dismiss();
					SharedPreferences.Editor editor = mSettings.edit();
					editor.putBoolean(APP_PREFERENCES_attentional_wifi, true);
					editor.commit();
				}
			});

			Activation.show();
		}



	public void info_wifi(int position, String name, String rssi, String mac, String cap, String rssilevel){

		String mem = mSettings.getString(name, "54324512");

		if (mem.contains("54324512")){
			final Dialog Activation = new Dialog(ActivityWifi.this,android.R.style.Theme_Translucent);
			Activation.requestWindowFeature(Window.FEATURE_NO_TITLE);
			Activation.setContentView(R.layout.dialog_inform);

			// set the custom dialog components - text, image and button

			Button dialogButton = (Button) Activation.findViewById(R.id.dialogButtonOK);
			TextView text = (TextView)Activation.findViewById(R.id.text);
			TextView textBold = (TextView)Activation.findViewById(R.id.textBold);

			dialogButton.setTypeface(typefaceRoman);
			textBold.setTypeface(typefaceBold);
			dialogButton.setText(R.string.ok);
			text.setTypeface(typefaceRoman);
			//text.setText("Уровень сигнала\n" + rssi + "\nБезопасность\n" + cap + "\nМАС адрес\n" + mac);
			String cape = null;

			if(cap.contains("WPA")){
				cape = "WPA/WPA2 PSK";
			}
			else if(cap.contains("WEP")){
				cape = "WEP PSK";
			}
			else{
				cape = "Нет";
			}

			String t2 = getString(R.string.level_signal) +"<br>"+ "<font color=\"#808080\"><small>"  + rssilevel + "</small></font>"
					+ "<br><br>" + getString(R.string.security) + "<br>" + "<font color=\"#808080\"><small>"  + cape + "</small></font>"
					+ "<br><br>" + getString(R.string.mac_wifi_) + "<br>" + "<font color=\"#808080\"><small>"  + mac + "</small></font>";

			text.setText(Html.fromHtml(t2), TextView.BufferType.SPANNABLE);

			text.setPadding(25,5,5,5);

			textBold.setText("\"" + name + "\"");
			textBold.setTextSize(16);
			text.setTextSize(15);
			text.setGravity(Gravity.LEFT);

			// if button is clicked, close the custom dialog
			dialogButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {



					Activation.dismiss();
				}
			});

			Activation.show();
		}
		else{
			Intent settingsIntent3 = new Intent(this, ActivityWifiInfo.class);
			settingsIntent3.putExtra("name", name);
			settingsIntent3.putExtra("cap", cap);
			startActivity(settingsIntent3);
		}




	    }


	    private void doIt (final String name){

	    	LayoutMain.setVisibility(View.VISIBLE);
        	textConnected.setText(name);



	    	new Handler().postDelayed(new Runnable() {

                @Override
                public void run()
                {
                	NetworkInfo myNetworkInfo = myConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                	if(!myNetworkInfo.isConnected()){
                		LayoutMain.setVisibility(View.GONE);
                	}
                    // TODO Auto-generated method stub

                	//textConnected.setText(name);

                }
            }, 7000);
	    }

	    public  void connectToWifi(final int position)
        {
                final int value = results.size()-1 - position;

                Capabilities =  results.get(value).capabilities;


					String ssid = mSettings.getString(results.get(value).SSID, "54324512");


				Log.e("MY APP", "ssid: " + ssid);


			if(ssid.contains("54324512")){
				//Then you could add some code to check for a specific security type.
				if(Capabilities.contains("WPA")){

					final Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.pass_wifi);
					dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

					Button ButtonOK = (Button)dialog.getWindow().findViewById(R.id.buttonSave1);
					Button ButtonMenuCancel = (Button)dialog.getWindow().findViewById(R.id.buttonBack1);
					TextView top = (TextView) dialog.getWindow().findViewById(R.id.textView1);
					TextView title = (TextView) dialog.getWindow().findViewById(R.id.textOk);
					TextView title_pass = (TextView) dialog.getWindow().findViewById(R.id.textView2);
					final EditText ed1 = (EditText) dialog.getWindow().findViewById(R.id.editText1);

					top.setText(getResources().getString(R.string.wifi_connect_to, results.get(value).SSID));

					ButtonMenuCancel.setTypeface(typefaceMedium);
					ButtonOK.setTypeface(typefaceRoman);
					top.setTypeface(typefaceRoman);
					title.setTypeface(typefaceBold);
					title_pass.setTypeface(typefaceRoman);
					ButtonOK.setText(R.string.wifi_connect_title);
					title.setText(R.string.wifi_title_connect);

					ButtonMenuCancel.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							dialog.dismiss();
						}});



					ButtonOK.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							// launchIntent();
							if(ed1.getText().toString().length() == 0){
								Log.e("!!!!", "ED1 is NULL" );
							}
							else {
								doIt(results.get(value).SSID);

								wifi.disconnect();
								WifiConfiguration wifiConfiguration = new WifiConfiguration();
								wifiConfiguration.SSID = "\"" + results.get(value).SSID + "\"";
								wifiConfiguration.preSharedKey = "\"" + ed1.getText().toString() + "\"";
								wifiConfiguration.hiddenSSID = true;
								wifiConfiguration.status = WifiConfiguration.Status.ENABLED;
								wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.WPA); // For WPA
								wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.RSN); // For WPA2
								wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
								wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_EAP);
								wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
								wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
								wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
								wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
								wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
								wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
								int res = wifi.addNetwork(wifiConfiguration);
								Log.d("WifiPreference", "add Network returned " + res);
								wifi.disconnect();
								//if (res == -1) {
									boolean b = wifi.enableNetwork(res, true);
									wifi.reconnect();
									Log.d("WifiPreference", "enableNetwork returned " + b);
								//}
								SharedPreferences.Editor editor = mSettings.edit();
								editor.putString(results.get(value).SSID, ed1.getText().toString());
								Log.e("MY APP", "ЗАПИСЬ В ПАМЯТЬ:_" + ed1.getText().toString()+ "_");
								editor.commit();
								dialog.dismiss();



							}

						}});

					dialog.show();

				}
				else if(Capabilities.contains("WEP"))
				{
					final Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.pass_wifi);
					dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

					Button ButtonOK = (Button)dialog.getWindow().findViewById(R.id.buttonSave1);
					Button ButtonMenuCancel = (Button)dialog.getWindow().findViewById(R.id.buttonBack1);
					TextView top = (TextView) dialog.getWindow().findViewById(R.id.textView1);
					TextView title = (TextView) dialog.getWindow().findViewById(R.id.textOk);
					TextView title_pass = (TextView) dialog.getWindow().findViewById(R.id.textView2);
					final EditText ed1 = (EditText) dialog.getWindow().findViewById(R.id.editText1);

					top.setText(getResources().getString(R.string.wifi_connect_to, results.get(value).SSID));

					ButtonMenuCancel.setTypeface(typefaceMedium);
					ButtonOK.setTypeface(typefaceRoman);
					top.setTypeface(typefaceRoman);
					title.setTypeface(typefaceBold);
					title_pass.setTypeface(typefaceRoman);
					ButtonOK.setText(R.string.wifi_connect_title);
					title.setText(R.string.wifi_title_connect);

					ButtonMenuCancel.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							dialog.dismiss();
						}});


					ButtonOK.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							// launchIntent();
							if(ed1.getText().toString().length() == 0){
								Log.e("!!!!", "ED1 is NULL" );
							}
							else {
								doIt(results.get(value).SSID);

								WifiConfiguration wifiConfiguration = new WifiConfiguration();
								wifiConfiguration.SSID = "\"" + results.get(value).SSID + "\"";
								wifiConfiguration.wepKeys[0] = "\"" + ed1.getText().toString() + "\"";
								wifiConfiguration.wepTxKeyIndex = 0;
								wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
								wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
								int res = wifi.addNetwork(wifiConfiguration);
								Log.d("WifiPreference", "add Network returned " + res);
								wifi.disconnect();
							//	if (res == -1) {
									boolean b = wifi.enableNetwork(res, true);
									wifi.reconnect();
									Log.d("WifiPreference", "enableNetwork returned " + b);
							//	}
								SharedPreferences.Editor editor = mSettings.edit();
								editor.putString(results.get(value).SSID, ed1.getText().toString());
								Log.e("MY APP", "ЗАПИСЬ В ПАМЯТЬ:_" + ed1.getText().toString()+ "_");
								editor.commit();
								dialog.dismiss();



							}

						}});

					dialog.show();

				}
				else
				{
					doIt(results.get(value).SSID);

					wifi.disconnect();
					WifiConfiguration wifiConfiguration = new WifiConfiguration();
					wifiConfiguration.SSID = "\"" + results.get(value).SSID + "\"";
					wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
					int res = wifi.addNetwork(wifiConfiguration);
					Log.d("WifiPreference", "add Network returned " + res );
					wifi.disconnect();
					//if (res == -1) {
						boolean b = wifi.enableNetwork(res, true);
						wifi.reconnect();
						Log.d("WifiPreference", "enableNetwork returned " + b);
					//}
				}
			}
			else{
				if(Capabilities.contains("WPA")){
					doIt(results.get(value).SSID);

					wifi.disconnect();
					WifiConfiguration wifiConfiguration = new WifiConfiguration();
					wifiConfiguration.SSID = "\"" + results.get(value).SSID + "\"";
					wifiConfiguration.preSharedKey = "\"" + ssid + "\"";
					wifiConfiguration.hiddenSSID = true;
					wifiConfiguration.status = WifiConfiguration.Status.ENABLED;
					wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.WPA); // For WPA
					wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.RSN); // For WPA2
					wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
					wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_EAP);
					wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
					wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
					wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
					wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
					wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
					wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
					int res = wifi.addNetwork(wifiConfiguration);
					Log.d("WifiPreference", "add Network returned " + res);
					wifi.disconnect();
					//if (res == -1) {
						boolean b = wifi.enableNetwork(res, true);
						wifi.reconnect();
						Log.d("WifiPreference", "enableNetwork returned " + b);
					//}
				}
				else if (Capabilities.contains("WEP")){
					doIt(results.get(value).SSID);

					wifi.disconnect();
					WifiConfiguration wifiConfiguration = new WifiConfiguration();
					wifiConfiguration.SSID = "\"" + results.get(value).SSID + "\"";
					wifiConfiguration.wepKeys[0] = "\"" + ssid + "\"";
					wifiConfiguration.wepTxKeyIndex = 0;
					wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
					wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
					int res = wifi.addNetwork(wifiConfiguration);
					Log.d("WifiPreference", "add Network returned " + res);
					wifi.disconnect();
					//if (res == -1) {
						boolean b = wifi.enableNetwork(res, true);
						wifi.reconnect();
						Log.d("WifiPreference", "enableNetwork returned " + b);
					//}
				}
				else{
					doIt(results.get(value).SSID);

					wifi.disconnect();
					WifiConfiguration wifiConfiguration = new WifiConfiguration();
					wifiConfiguration.SSID = "\"" + results.get(value).SSID + "\"";
					wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
					int res = wifi.addNetwork(wifiConfiguration);
					Log.d("WifiPreference", "add Network returned " + res );
					wifi.disconnect();
					//if (res == -1) {
						boolean b = wifi.enableNetwork(res, true);
						wifi.reconnect();
						Log.d("WifiPreference", "enableNetwork returned " + b);
					//}
				}

			}






        }

	    @Override
		public void onPause() {
			super.onPause();
			unregisterReceiver ( myWifiReceiver );
		}


	    protected void onResume() {
	        super.onResume();

	        if (wifi.isWifiEnabled()){
	        	wifi.startScan();
				pg1.setVisibility(View.VISIBLE);
	        }
	       ButtonWifi();
	       ButtonWifi1();
	       DisplayWifiState();
	      // this.registerReceiver(this.myWifiReceiver,
			//         new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

	       IntentFilter  mIntentFilter = new IntentFilter();
	        mIntentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
	        mIntentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
			mIntentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
	        registerReceiver(myWifiReceiver, mIntentFilter);

	        IntentFilter  mIntentFilter2 = new IntentFilter();
	        mIntentFilter2.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
	        registerReceiver(myWifiReceiver2, mIntentFilter2);


	       


				// Получаем число из настроек
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
				// Получаем число из настроек
	        	 Boolean bold = mSettings.getBoolean(APP_PREFERENCES_bold_text, true);
				if (bold == true){
					btn_1.setTypeface(typefaceBold);
	    			textView1.setTypeface(typefaceBold);
	    			textConnected.setTypeface(typefaceBold);

				}
	        }

	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					btn_1.setTextSize(13);
	    			textView1.setTextSize(11);
	    			textConnected.setTextSize(13);
				}
				if (size .contains( "Normal")){
					btn_1.setTextSize(15);
	    			textView1.setTextSize(13);
	    			textConnected.setTextSize(15);
				}
				if (size .contains( "Large")){
					btn_1.setTextSize(18);
	    			textView1.setTextSize(15);
	    			textConnected.setTextSize(18);
				}
				if (size .contains( "xLarge")){
					btn_1.setTextSize(20);
	    			textView1.setTextSize(18);
	    			textConnected.setTextSize(20);
				}
	       }
	    }

	    private void ButtonWifi1() {
	    	ConnectivityManager connManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
	    	NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    	if(mWifi.isConnected()){
	    		info.setVisibility(View.VISIBLE);
           		tb_ch.setVisibility(View.VISIBLE);
           		img2.setVisibility(View.VISIBLE);
           		//LayoutMain.setVisibility(View.VISIBLE);

	        }
	    	else {
	    		info.setVisibility(View.GONE);
           		//tb_ch.setVisibility(View.INVISIBLE);
           		//LayoutMain.setVisibility(View.GONE);
	    	}

	    }
 private void ButtonWifi() {

	    	if(wifi.isWifiEnabled()){
	            tb_wifi.setChecked(true);
	            LayoutMain.setVisibility(View.VISIBLE);
           		MainLayout2.setVisibility(View.VISIBLE);
           		img2.setVisibility(View.VISIBLE);
           		textView2.setVisibility(View.GONE);

	        }
	    	else {
	    		textView2.setVisibility(View.VISIBLE);
	    		//LinearLayoutCon.setVisibility(View.GONE);
	    		LayoutMain.setVisibility(View.GONE);
           		MainLayout2.setVisibility(View.GONE);
	    	}

	    }

 public void BackClick(View v)
 {
	 Intent intent18 = new Intent(this, MainActivity.class);
      	 startActivity(intent18);

		overridePendingTransition(center_to_right, center_to_right2);
	 }

 private BroadcastReceiver myWifiReceiver = new BroadcastReceiver(){

	   @Override
	   public void onReceive(Context arg0, Intent arg1) {/*
	    // TODO Auto-generated method stub
	    NetworkInfo networkInfo = (NetworkInfo) arg1.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
	    if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
	     DisplayWifiState();
	     
	    }*/
		   String action = arg1.getAction();

           if (action.equals(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION)) {
               if (arg1.getParcelableExtra(WifiManager.EXTRA_NEW_STATE) == SupplicantState.COMPLETED) {
                   Log.e("!!!MY!!!", "WiFi is associated");//
                   WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                   WifiInfo wi = wifiManager.getConnectionInfo();
                   if (wi != null) {
                	   Log.e("!!!MY!!!", "Wifi info available (should be, we are associated)");////
                       if (wi.getIpAddress() != 0) {
                    	   Log.e("!!!MY!!!", " Lucky us, we already have an ip address. This happens when a connection is complete, e.g. after rekeying");////

                           if (wi.getBSSID().equals("c0:ff:ee:c0:ff:ee")) {
                               // ... Do your stuff here
                               // ...
                               // ...
                           }
                       }
                       }
                   }
                                           };


		   if (arg1.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {


	            Log.d("G_TAG", "network state was changed");
	            NetworkInfo networkInfo = arg1.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
	            //DisplayWifiState();
	            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
	                Log.d("LOG_TAG", "network connection has been established");

	                DisplayWifiState();
	              LayoutMain.setVisibility(View.VISIBLE);
	                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService (Context.WIFI_SERVICE);
	                WifiInfo info = wifiManager.getConnectionInfo ();
	                if(info.getSSID ().contains("unknown ssid")){
	                	textConnected.setText(R.string.no_connections);
	                }
	                else{
	                	textConnected.setText(info.getSSID().replace('"',' ') );
	                }

//	                public void schedule (TimerTask task, long delay, long period) 
//	                Schedule a task for repeated fixed-delay execution after a specific delay.
	        //
//	                Parameters
//	                task  the task to schedule. 
//	                delay  amount of time in milliseconds before first execution. 
//	                period  amount of time in milliseconds between subsequent executions. 


	                // do something...

	            }
	        }
	   }};



	   private BroadcastReceiver myWifiReceiver2 = new BroadcastReceiver(){
		   @Override
           public void onReceive(Context c, Intent intent)
           {
        	   studentArray.clear();

			   if (isGPSEnabled(c) == false) {
				   Toast.makeText(c, "Включите GPS", Toast.LENGTH_LONG).show();
				   startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

			   }
              results = wifi.getScanResults();
              size = results.size();
              try
              {
                  size = size - 1;
                  while (size >= 0)
                  {
                	   RSSI_KEY = null;
                	   WIFI_KEY = null;
                	   ITEM_KEY = null;
                      //HashMap<String, String> item = new HashMap<String, String>();
                      int result = results.get(size).level;


                      if(results.get(size).SSID.length() != 0){
                    	  //item.put(ITEM_KEY, results.get(size).SSID);
                    	  ITEM_KEY = results.get(size).SSID;
                      }
                      else{
                    	  //item.put(ITEM_KEY, getString(R.string.bluetooth_hidden));
                      		ITEM_KEY = getString(R.string.bluetooth_hidden); }


                      int w = result;

                      if (w > -67){
                    	  //item.put(RSSI_KEY, Integer.toString(R.drawable.wifi_signal_4));
                    	  RSSI_KEY = Integer.toString(R.drawable.wifi_signal_4);
                    	  RSSILEVEL_KEY = "Очень сильный";
                      }
                      else if (w > -79){
                    	  //item.put(RSSI_KEY, Integer.toString(R.drawable.wifi_signal_3));
                    	  RSSI_KEY = Integer.toString(R.drawable.wifi_signal_3);
                    	  RSSILEVEL_KEY = "Cильный";
                      }
                      else if (w > -91){
                    	  //item.put(RSSI_KEY, Integer.toString(R.drawable.wifi_signal_2));
                    	  RSSI_KEY = Integer.toString(R.drawable.wifi_signal_2);
                    	  RSSILEVEL_KEY = "Средний";
                      }
                      else {
                    	  //item.put(RSSI_KEY, Integer.toString(R.drawable.wifi_signal_1));
                    	  RSSI_KEY = Integer.toString(R.drawable.wifi_signal_1);
                    	  RSSILEVEL_KEY = "Слабый";
                      }

                      Capabilities =  results.get(size).capabilities;
                      if(Capabilities.contains("WPA"))

		                {
                    	 // item.put(WIFI_KEY, Integer.toString(R.drawable.lock_wifi));
                    	  WIFI_KEY = Integer.toString(R.drawable.lock_wifi);
		                }

                      if(Capabilities.contains("WEP"))
	                    {
                    	//  item.put(WIFI_KEY, Integer.toString(R.drawable.lock_wifi));
                    	  WIFI_KEY = Integer.toString(R.drawable.lock_wifi);
	                    }

                      String wi = myWifiInfo.getSSID();
                      String wi_scan = "\"" + results.get(size).SSID + "\"";
                      String wi3 = myWifiInfo.getSSID().replace("\"","");

                     // Log.e("WI_SCAN", wi_scan);
                      //Log.e("WI", wi);

                     // if(wi == null){
                      if (wi.contains(wi_scan)){
                    	  if(!Capabilities.contains("WPA") && !Capabilities.contains("WEP")){
                    		  wifi_state = 1;
                    		  //DisplayWifiState();
                    		  String t2 = wi3 + "<br />" + "<font color=\"#808080\" >" + "<small><small>" +  getString(R.string.wifi_no_pass) + "</small></small>" + "</font>";
                		       textConnected.setText(Html.fromHtml(t2), TextView.BufferType.SPANNABLE);
                    	  }
                      }
                    //  }
                     CAP_KEY = results.get(size).capabilities;
                      MAC_KEY = results.get(size).BSSID;
                      //RSSILEVEL_KEY = String.valueOf(results.get(size).level);

					  if (wi.contains(wi_scan)){

					  }
					  else{
						  studentArray.add(new Wifi_Info(ITEM_KEY, RSSI_KEY, WIFI_KEY, MAC_KEY, CAP_KEY, RSSILEVEL_KEY));
					  }

                      //arraylist.add(item);
                      size--;
                      studentArrayAdapter.notifyDataSetChanged();
                  }
              }
              catch (Exception e)
              {  }
              setListViewHeightBasedOnChildren(lv);

	   }};

	public boolean isGPSEnabled(Context mContext)
	{
		LocationManager lm = (LocationManager)
				mContext.getSystemService(Context.LOCATION_SERVICE);
		return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	    private void DisplayWifiState(){

	     ConnectivityManager myConnManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
	     NetworkInfo myNetworkInfo = myConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	   //WifiInfo myWifiInfo = myWifiManager.getConnectionInfo();
	     Log.d("LOG_TAG", "wifi_display start");
			tb_ch.setVisibility(View.VISIBLE);
	     if (myNetworkInfo.isConnected()){
	    	 Log.d("LOG_TAG", "wifi_display isConnected");
	      info.setVisibility(View.VISIBLE);
			tb_ch.setVisibility(View.VISIBLE);
			img2.setVisibility(View.VISIBLE);
			LayoutMain.setVisibility(View.VISIBLE);


			if (wifi_state == 0){
				String wi = myWifiInfo.getSSID().replace('"',' ');
		    	   textConnected.setText(wi);
		    	   Log.d("LOG_TAG", "wifi_display isConnected - wifi_state = 0");


			}/*
			else{
				 String t2 = capa + "<br />" + "<font color=\"#808080\" >" + "<small><small>" +  getString(R.string.wifi_no_pass) + "</small></small>" + "</font>";
     		       textConnected.setText(Html.fromHtml(t2), TextView.BufferType.SPANNABLE);
     		      Log.d("LOG_TAG", "wifi_display isConnected - else wifi_state = 0");
			}*/

			if(textConnected.getText().toString().contains("<unknown ssid>")){
				reload();
			}
	     }
	     else{
	    	 Log.d("LOG_TAG", "wifi_display no isConnected");
	      textConnected.setText(R.string.no_connections);
	      info.setVisibility(View.INVISIBLE);
			tb_ch.setVisibility(View.INVISIBLE);
	      img2.setVisibility(View.INVISIBLE);
	    LayoutMain.setVisibility(View.GONE);

	     }

	    }



		 public void onClick(View v) {
			 int id = v.getId();

		        if (id ==  R.id.ToggleButton01){
		        	//TableLayout tableLayout2 = (TableLayout)findViewById(R.id.tableLayout1);
		        	tb_in = (ToggleButton) findViewById(R.id.toggleButtonInfo);
		        	if((tb_wifi).isChecked())
	               	 {
	               		 wifi.setWifiEnabled(true);
	               		 wifi.startScan();
	               		//LayoutMain.setVisibility(View.VISIBLE);
	               		//MainLayout2.setVisibility(View.VISIBLE);
	               		Animation animationOUT = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);
	               		Animation animationIN = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);

	               		textView2.startAnimation(animationOUT);
	               		//LayoutMain.startAnimation(animationIN);
	               		MainLayout2.startAnimation(animationIN);

	               		//textView2.setVisibility(View.GONE);

	               		animationIN.setAnimationListener(new Animation.AnimationListener(){
	               		    @Override
	               		    public void onAnimationStart(Animation arg0) {
	               		    	//LayoutMain.setVisibility(View.VISIBLE);
	    	               		MainLayout2.setVisibility(View.VISIBLE);
	               		    }
	               		    @Override
	               		    public void onAnimationRepeat(Animation arg0) {
	               		    }
	               		    @Override
	               		    public void onAnimationEnd(Animation arg0) {
	               		    }
	               		});

	               		animationOUT.setAnimationListener(new Animation.AnimationListener(){
	               		    @Override
	               		    public void onAnimationStart(Animation arg0) {

	               		    }
	               		    @Override
	               		    public void onAnimationRepeat(Animation arg0) {
	               		    }
	               		    @Override
	               		    public void onAnimationEnd(Animation arg0) {
	               		    	textView2.setVisibility(View.GONE);
	               		    }
	               		});



	               	 }
	               	 else {
	               		 wifi.setWifiEnabled(false);


	               		info.setVisibility(View.INVISIBLE);
	               		tb_ch.setVisibility(View.INVISIBLE);
	               		img2.setVisibility(View.INVISIBLE);

	               		Animation animationIN = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
	               		Animation animationOUT = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);
	               		Animation animationOUTalpha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alphaout);

	               		animationIN.setAnimationListener(new Animation.AnimationListener(){
	               		    @Override
	               		    public void onAnimationStart(Animation arg0) {
	               		    	textView2.setVisibility(View.VISIBLE);
	               		    }
	               		    @Override
	               		    public void onAnimationRepeat(Animation arg0) {
	               		    }
	               		    @Override
	               		    public void onAnimationEnd(Animation arg0) {
	               		    }
	               		});

	               		animationOUT.setAnimationListener(new Animation.AnimationListener(){
	               		    @Override
	               		    public void onAnimationStart(Animation arg0) {

	               		    }
	               		    @Override
	               		    public void onAnimationRepeat(Animation arg0) {
	               		    }
	               		    @Override
	               		    public void onAnimationEnd(Animation arg0) {
	               		    	MainLayout2.setVisibility(View.GONE);
	               		    	LayoutMain.setVisibility(View.GONE);

	               		    }
	               		});

	               		MainLayout2.startAnimation(animationOUT);
	               		LayoutMain.startAnimation(animationOUTalpha);
	               		//textView2.setVisibility(View.VISIBLE);
	               		textView2.startAnimation(animationIN);

	               		//MainLayout2.setVisibility(View.GONE);*/

	               	 }
		        }

		        if (id == R.id.Button04){
		        	Intent settingsIntent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
		        	startActivity(settingsIntent);
			        	overridePendingTransition(center_to_left, center_to_left2);
			        	 }

		        else if (id == R.id.Connected){
		        	WifiManager myWifiManager = (WifiManager)this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		        	WifiInfo myWifiInfo = myWifiManager.getConnectionInfo();
 					DhcpInfo info = wifi.getDhcpInfo();
 					WifiConfiguration wifiConfiguration = new WifiConfiguration();



 					String s_netmask = Formatter.formatIpAddress(info.netmask);
 					String s_ipAddress = Formatter.formatIpAddress(info.ipAddress);
 					String s_serverAddress = Formatter.formatIpAddress(info.serverAddress);
 					String s_dns1 =Formatter.formatIpAddress(info.dns1);
 					String s_dns2 =Formatter.formatIpAddress(info.dns2);
 					String wi = myWifiInfo.getSSID().replace('"',' ');


		        	Intent settingsIntent = new Intent(this, ActivityWifiInfo.class);
		        	settingsIntent.putExtra("ip", s_ipAddress);
		        	settingsIntent.putExtra("dns1", s_dns1);
		        	settingsIntent.putExtra("dns2", s_dns2);
		        	settingsIntent.putExtra("mask", s_netmask);
		        	settingsIntent.putExtra("marsh", s_serverAddress);
		        	settingsIntent.putExtra("name", wi);
		        	settingsIntent.putExtra("pass1", wifi_state);


		        	startActivity(settingsIntent);
			        	overridePendingTransition(center_to_left, center_to_left2);

		        }

		        else if (id == R.id.button1){
		        	WifiManager myWifiManager = (WifiManager)this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		        	WifiInfo myWifiInfo = myWifiManager.getConnectionInfo();
 					DhcpInfo info = wifi.getDhcpInfo();
 					WifiConfiguration wifiConfiguration = new WifiConfiguration();


 					String s_netmask = Formatter.formatIpAddress(info.netmask);
 					String s_ipAddress = Formatter.formatIpAddress(info.ipAddress);
 					String s_serverAddress = Formatter.formatIpAddress(info.serverAddress);
 					String s_dns1 =Formatter.formatIpAddress(info.dns1);
 					String s_dns2 =Formatter.formatIpAddress(info.dns2);
 					String wi = myWifiInfo.getSSID().replace('"',' ');

		        	Intent settingsIntent = new Intent(this, ActivityWifiInfo.class);
		        	settingsIntent.putExtra("ip", s_ipAddress);
		        	settingsIntent.putExtra("dns1", s_dns1);
		        	settingsIntent.putExtra("dns2", s_dns2);
		        	settingsIntent.putExtra("mask", s_netmask);
		        	settingsIntent.putExtra("marsh", s_serverAddress);
		        	settingsIntent.putExtra("name", wi);
		        	settingsIntent.putExtra("pass1", wifi_state);
		        	startActivity(settingsIntent);
			        	overridePendingTransition(center_to_left, center_to_left2);

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

		

		        public static void setListViewHeightBasedOnChildren(ListView lv) {
		            ListAdapter listAdapter = lv.getAdapter();
		            if (listAdapter == null)
		                return;

		            int desiredWidth = MeasureSpec.makeMeasureSpec(lv.getWidth(), MeasureSpec.UNSPECIFIED);
		            int totalHeight = 0;
		            View view = null;
		            for (int i = 0; i < listAdapter.getCount(); i++) {
		                view = listAdapter.getView(i, view, lv);
		                if (i == 0)
		                    view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

		                view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
		                totalHeight += view.getMeasuredHeight();
		            }
		            ViewGroup.LayoutParams params = lv.getLayoutParams();
		            params.height = totalHeight + (lv.getDividerHeight() * (listAdapter.getCount() - 1));
		            lv.setLayoutParams(params);
		            lv.requestLayout();
		        }




		        public void reload() {
		            Intent intent = getIntent();
		            overridePendingTransition(0, 0);
		            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		            finish();
		            overridePendingTransition(0, 0);
		            startActivity(intent);
		        }

////////////////////////////////////////////////////////////////////






}

        
       






