package ua.mkh.settings.full;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.google.android.gms.auth.GoogleAuthUtil;




import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ActivityiCloud extends Activity implements View.OnClickListener {

	Typeface typefaceRoman, typefaceMedium, typefaceBold;
	SharedPreferences mSettings;


	int center_to_right, center_to_right2;
	int center_to_left, center_to_left2;


	PackageManager pm;


	public static final String APP_PREFERENCES = "mysettings";
	public static final String APP_PREFERENCES_text_size = "txt_size";
	public static final String APP_PREFERENCES_bold_text = "bold_txt";
	public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";

	public static final String APP_PREFERENCES_SEARCH = "search";
	public static final String APP_PREFERENCES_GEO = "geo";

	public static final String APP_PREFERENCES_NAME = "name";
	public static final String APP_PREFERENCES_MAIL = "mail_app";
	public static final String APP_PREFERENCES_NOTES = "notes_app";
	public static final String APP_PREFERENCES_PHONE = "phone_app";
	public static final String APP_PREFERENCES_MESSAGES = "messages_app";
	public static final String APP_PREFERENCES_SAFARI = "safari_app";
	public static final String APP_PREFERENCES_MUSIC = "music_app";
	public static final String APP_PREFERENCES_GAMECENTER = "gamecenter_app";
	public static final String APP_PREFERENCES_WEATHER = "weather_app";
	public static final String APP_PREFERENCES_COMPASS = "compass_app";
	public static final String APP_PREFERENCES_MAPS = "maps_app";
	public static final String APP_PREFERENCES_VK = "vk_app";
	public static final String APP_PREFERENCES_VIBER = "viber_app";
	public static final String APP_PREFERENCES_OK = "ok_app";
	public static final String APP_PREFERENCES_SKYPE = "skype_app";
	public static final String APP_PREFERENCES_WHATSAPP = "whatsapp_app";
	public static final String APP_PREFERENCES_TWITTER = "twitter_app";
	public static final String APP_PREFERENCES_FACEBOOK = "facebook_app";
	public static final String APP_PREFERENCES_INSTAGRAM = "instagram_app";

	public static final boolean APP_PREFERENCES_MAIL_TGB = false;
	public static final boolean APP_PREFERENCES_NOTEPAD_TGB = false;
	public static final boolean APP_PREFERENCES_PHONE_TGB = false;
	public static final boolean APP_PREFERENCES_MESSAGE_TGB = false;
	public static final boolean APP_PREFERENCES_COMPASS_TGB = false;
	public static final boolean APP_PREFERENCES_SAFARI_TGB = false;
	public static final boolean APP_PREFERENCES_MUSIC_TGB = false;
	public static final boolean APP_PREFERENCES_GAME_TGB = false;
	public static final boolean APP_PREFERENCES_WEATHER_TGB = false;
	public static final boolean APP_PREFERENCES_MAPS_TGB = false;
	public static final boolean APP_PREFERENCES_VK_TGB = false;
	public static final boolean APP_PREFERENCES_VIBER_TGB = false;
	public static final boolean APP_PREFERENCES_OK_TGB = false;
	public static final boolean APP_PREFERENCES_SKYPE_TGB = false;
	public static final boolean APP_PREFERENCES_WHATSAPP_TGB = false;
	public static final boolean APP_PREFERENCES_TWITTER_TGB = false;
	public static final boolean APP_PREFERENCES_FACEBOOK_TGB = false;
	public static final boolean APP_PREFERENCES_INSTAGRAM_TGB = false;
	public static final boolean APP_PREFERENCES_APPSTORE_TGB = false;


	Button btn_mail, btn_notepad, btn_phone, btn_message, btn_safari, btn_music, btn_game,
			btn_weather, btn_compass, btn_maps, btn_vk, btn_viber, btn_ok, btn_skype,
			btn_whatsapp, btn_twitter, btn_facebook, btn_instagram, btn_newacc, btn_delacc,
			btn_account, buttonBack, btn_menu_settings, btn_menu_cancel, button_update, button_sync,
			btn_search;

	Intent notif_inoty, notif_espier, control_hi, control_espier, mail_ino, mail_stok,
			notes_any, notes_espier, phone_kuandroid, phone_espier, phone_phone_stok, phone_stok,
			messages_easyandroid, messages_iphonestyle, messages_espier, messages_stok,
			safari_ios, safari_stok, music_easyandroid1, music_easyandroid2, music_hi, music_stok, compass_hanimobile, weather_cybob,
			weather_yahoo, weather_kuandroid, games_mkh, new1, new2, new3, new4, vk_stok, maps_stok, viber_stok,
			ok_stok, skype_stok, whatsapp_stok, twitter_stok, facebook_stok, instagram_stok;

	TextView textStatus, size_memory;
	ToggleButton tb_sync;

	ToggleButton mail, notes, phone, messages, compass, safari, music, game, weather, maps, vk, viber, ok, skype, whatsapp, twitter, facebook, instagram,
			search;

	SwipeBackActivityHelper helper = new SwipeBackActivityHelper();
	long total_size = 0;
	ProgressBar pg2, pg3, pg4, pg5;
	LinearLayout LinearLayoutICloud;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_icloud);
		String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold = "fonts/Bold.otf";

		helper.setEdgeMode(true)
				.setParallaxMode(false)
				.setParallaxRatio(0)
				.setNeedBackgroundShadow(false)
				.init(this);

		ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);
		OverScrollDecoratorHelper.setUpOverScroll(scrollView);


		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);

		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

		pg2 = (ProgressBar) findViewById(R.id.progressBar2);
		pg3 = (ProgressBar) findViewById(R.id.progressBar3);
		pg4 = (ProgressBar) findViewById(R.id.progressBar4);
		pg5 = (ProgressBar) findViewById(R.id.progressBar5);
		LinearLayoutICloud = (LinearLayout) findViewById(R.id.LinearLayoutICloud);
		LinearLayoutICloud.setOnClickListener(this);

		tb_sync = (ToggleButton) findViewById(R.id.synctoggle);
		tb_sync.setOnClickListener(this);


		button_sync = (Button) findViewById(R.id.Button19);

		btn_mail = (Button) findViewById(R.id.Button01);
		btn_mail.setOnClickListener(this);
		btn_mail.setEnabled(false);
		btn_notepad = (Button) findViewById(R.id.Button15);
		btn_notepad.setOnClickListener(this);
		btn_notepad.setEnabled(false);
		btn_phone = (Button) findViewById(R.id.Button14);
		btn_phone.setOnClickListener(this);
		btn_phone.setEnabled(false);
		btn_message = (Button) findViewById(R.id.Button13);
		btn_message.setOnClickListener(this);
		btn_message.setEnabled(false);
		btn_safari = (Button) findViewById(R.id.Button12);
		btn_safari.setOnClickListener(this);
		btn_safari.setEnabled(false);
		btn_music = (Button) findViewById(R.id.Button11);
		btn_music.setOnClickListener(this);
		btn_music.setEnabled(false);
		btn_game = (Button) findViewById(R.id.Button10);
		btn_game.setOnClickListener(this);
		btn_game.setEnabled(false);
		btn_weather = (Button) findViewById(R.id.Button09);
		btn_weather.setOnClickListener(this);
		btn_weather.setEnabled(false);
		btn_compass = (Button) findViewById(R.id.Button08);
		btn_compass.setOnClickListener(this);
		btn_compass.setEnabled(false);
		btn_maps = (Button) findViewById(R.id.Button07);
		btn_maps.setOnClickListener(this);
		btn_maps.setEnabled(false);
		btn_vk = (Button) findViewById(R.id.Button06);
		btn_vk.setOnClickListener(this);
		btn_vk.setEnabled(false);
		btn_viber = (Button) findViewById(R.id.Button05);
		btn_viber.setOnClickListener(this);
		btn_viber.setEnabled(false);
		btn_ok = (Button) findViewById(R.id.Button04);
		btn_ok.setOnClickListener(this);
		btn_ok.setEnabled(false);
		btn_skype = (Button) findViewById(R.id.Button03);
		btn_skype.setOnClickListener(this);
		btn_skype.setEnabled(false);
		btn_whatsapp = (Button) findViewById(R.id.Button02);
		btn_whatsapp.setOnClickListener(this);
		btn_whatsapp.setEnabled(false);
		btn_twitter = (Button) findViewById(R.id.Button18);
		btn_twitter.setOnClickListener(this);
		btn_twitter.setEnabled(false);
		btn_facebook = (Button) findViewById(R.id.Button17);
		btn_facebook.setOnClickListener(this);
		btn_facebook.setEnabled(false);
		btn_instagram = (Button) findViewById(R.id.Button16);
		btn_instagram.setOnClickListener(this);
		btn_instagram.setEnabled(false);
		btn_newacc = (Button) findViewById(R.id.Button20);
		btn_newacc.setOnClickListener(this);

		//btn_delacc = (Button) findViewById(R.id.Button19);
		btn_account = (Button) findViewById(R.id.ButtonWifi);

		btn_search = (Button) findViewById(R.id.Button22);
		btn_search.setOnClickListener(this);

		search = (ToggleButton) findViewById(R.id.ToggleButton01);

		mail = (ToggleButton) findViewById(R.id.ToggleButtonMail);
		mail.setOnClickListener(this);
		notes = (ToggleButton) findViewById(R.id.ToggleButtonNotes);
		notes.setOnClickListener(this);
		phone = (ToggleButton) findViewById(R.id.ToggleButtonPhone);
		phone.setOnClickListener(this);
		messages = (ToggleButton) findViewById(R.id.ToggleButtonMessage);
		messages.setOnClickListener(this);
		compass = (ToggleButton) findViewById(R.id.ToggleButtonCompass);
		compass.setOnClickListener(this);
		safari = (ToggleButton) findViewById(R.id.ToggleButtonSafari);
		safari.setOnClickListener(this);
		music = (ToggleButton) findViewById(R.id.ToggleButtonMusic);
		music.setOnClickListener(this);
		game = (ToggleButton) findViewById(R.id.ToggleButtonGame);
		game.setOnClickListener(this);
		weather = (ToggleButton) findViewById(R.id.ToggleButtonWeather);
		weather.setOnClickListener(this);
		maps = (ToggleButton) findViewById(R.id.ToggleButtonMaps);
		maps.setOnClickListener(this);
		vk = (ToggleButton) findViewById(R.id.ToggleButtonVk);
		vk.setOnClickListener(this);
		viber = (ToggleButton) findViewById(R.id.ToggleButtonViber);
		viber.setOnClickListener(this);
		ok = (ToggleButton) findViewById(R.id.ToggleButtonOk);
		ok.setOnClickListener(this);
		skype = (ToggleButton) findViewById(R.id.ToggleButtonSkype);
		skype.setOnClickListener(this);
		whatsapp = (ToggleButton) findViewById(R.id.ToggleButtonWhatsApp);
		whatsapp.setOnClickListener(this);
		twitter = (ToggleButton) findViewById(R.id.ToggleButtonTwitter);
		twitter.setOnClickListener(this);
		facebook = (ToggleButton) findViewById(R.id.ToggleButtonFacebook);
		facebook.setOnClickListener(this);
		instagram = (ToggleButton) findViewById(R.id.ToggleButtonInstagram);
		instagram.setOnClickListener(this);
		buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonBack.setText(R.string.app_name);
		btn_menu_settings = (Button) findViewById(R.id.ButtonMenuSettings);
		btn_menu_cancel = (Button) findViewById(R.id.ButtonMenuCancel);
		textStatus = (TextView) findViewById(R.id.textOk);

		size_memory = (TextView) findViewById(R.id.textView36);


		buttonBack.setTypeface(typefaceBold);
		btn_mail.setTypeface(typefaceRoman);
		btn_notepad.setTypeface(typefaceRoman);
		btn_phone.setTypeface(typefaceRoman);
		btn_message.setTypeface(typefaceRoman);
		btn_safari.setTypeface(typefaceRoman);
		btn_music.setTypeface(typefaceRoman);
		btn_game.setTypeface(typefaceRoman);
		btn_weather.setTypeface(typefaceRoman);
		btn_compass.setTypeface(typefaceRoman);
		btn_maps.setTypeface(typefaceRoman);
		btn_vk.setTypeface(typefaceRoman);
		btn_viber.setTypeface(typefaceRoman);
		btn_ok.setTypeface(typefaceRoman);
		btn_skype.setTypeface(typefaceRoman);
		btn_whatsapp.setTypeface(typefaceRoman);
		btn_twitter.setTypeface(typefaceRoman);
		btn_facebook.setTypeface(typefaceRoman);
		btn_instagram.setTypeface(typefaceRoman);
		btn_newacc.setTypeface(typefaceRoman);
		button_sync.setTypeface(typefaceRoman);
		btn_search.setTypeface(typefaceRoman);
		size_memory.setTypeface(typefaceRoman);


		textStatus.setTypeface(typefaceMedium);
		textStatus.setText(R.string.icloud);

///////////////////APP//////////////
		pm = this.getPackageManager();

		mail_stok = pm.getLaunchIntentForPackage("com.android.email");
		//Phone
		phone_stok = pm.getLaunchIntentForPackage("com.android.dialer");
		//Messages
		messages_stok = pm.getLaunchIntentForPackage("com.android.mms");
		//Safari
		safari_stok = pm.getLaunchIntentForPackage("com.android.browser");
		//Music;
		music_stok = pm.getLaunchIntentForPackage("com.android.music");
//VK
		vk_stok = pm.getLaunchIntentForPackage("com.vkontakte.android");
//Viber
		viber_stok = pm.getLaunchIntentForPackage("com.viber.voip");
//Ok
		ok_stok = pm.getLaunchIntentForPackage("ru.ok.android");
//Skype
		skype_stok = pm.getLaunchIntentForPackage("com.skype.raider");
//WhatsApp!!!!
		whatsapp_stok = pm.getLaunchIntentForPackage("com.whatsapp");
//Twitter
		twitter_stok = pm.getLaunchIntentForPackage("com.twitter.android");
//Facebook
		facebook_stok = pm.getLaunchIntentForPackage("com.facebook.katana");
//Instagram
		instagram_stok = pm.getLaunchIntentForPackage("com.instagram.android");
//Maps
		maps_stok = pm.getLaunchIntentForPackage("com.google.android.apps.maps");

	}


	protected void onResume() {
		super.onResume();

		ButtonSync();

		memory();
		persent();


		int speed = mSettings.getInt(APP_PREFERENCES_ANIM_SPEED, 1);
		if (speed == 1) {
			center_to_right = R.anim.slide_center_to_right_short;
			center_to_right2 = R.anim.slide_center_to_right2_short;
			center_to_left = R.anim.slide_center_to_left_short;
			center_to_left2 = R.anim.slide_center_to_left2_short;
		}
		if (speed == 2) {
			center_to_right = R.anim.slide_center_to_right_medium;
			center_to_right2 = R.anim.slide_center_to_right2_medium;
			center_to_left = R.anim.slide_center_to_left_medium;
			center_to_left2 = R.anim.slide_center_to_left2_medium;
		}
		if (speed == 3) {
			center_to_right = R.anim.slide_center_to_right_long;
			center_to_right2 = R.anim.slide_center_to_right2_long;
			center_to_left = R.anim.slide_center_to_left_long;
			center_to_left2 = R.anim.slide_center_to_left2_long;
		}


		if (mSettings.contains(APP_PREFERENCES_bold_text)) {
			Boolean bold = mSettings.getBoolean(APP_PREFERENCES_bold_text, true);
			if (bold == true) {
				btn_mail.setTypeface(typefaceBold);
				btn_notepad.setTypeface(typefaceBold);
				btn_phone.setTypeface(typefaceBold);
				btn_message.setTypeface(typefaceBold);
				btn_safari.setTypeface(typefaceBold);
				btn_music.setTypeface(typefaceBold);
				btn_game.setTypeface(typefaceBold);
				btn_weather.setTypeface(typefaceBold);
				btn_compass.setTypeface(typefaceBold);
				btn_maps.setTypeface(typefaceBold);
				btn_vk.setTypeface(typefaceBold);
				btn_viber.setTypeface(typefaceBold);
				btn_ok.setTypeface(typefaceBold);
				btn_skype.setTypeface(typefaceBold);
				btn_whatsapp.setTypeface(typefaceBold);
				btn_twitter.setTypeface(typefaceBold);
				btn_facebook.setTypeface(typefaceBold);
				btn_instagram.setTypeface(typefaceBold);
				btn_newacc.setTypeface(typefaceBold);
				btn_search.setTypeface(typefaceBold);
				button_sync.setTypeface(typefaceBold);


			}
		}

		if (mSettings.contains(APP_PREFERENCES_text_size)) {
			String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
			if (size.contains("Small")) {
				btn_mail.setTextSize(13);
				btn_notepad.setTextSize(13);
				btn_phone.setTextSize(13);
				btn_message.setTextSize(13);
				btn_safari.setTextSize(13);
				btn_music.setTextSize(13);
				btn_game.setTextSize(13);
				btn_weather.setTextSize(13);
				btn_compass.setTextSize(13);
				btn_maps.setTextSize(13);
				btn_vk.setTextSize(13);
				btn_viber.setTextSize(13);
				btn_ok.setTextSize(13);
				btn_skype.setTextSize(13);
				btn_whatsapp.setTextSize(13);
				btn_twitter.setTextSize(13);
				btn_facebook.setTextSize(13);
				btn_instagram.setTextSize(13);
				btn_newacc.setTextSize(13);
				button_sync.setTextSize(13);
				btn_search.setTextSize(13);
			}
			if (size.contains("Normal")) {
				btn_mail.setTextSize(15);
				btn_notepad.setTextSize(15);
				btn_phone.setTextSize(15);
				btn_message.setTextSize(15);
				btn_safari.setTextSize(15);
				btn_music.setTextSize(15);
				btn_game.setTextSize(15);
				btn_weather.setTextSize(15);
				btn_compass.setTextSize(15);
				btn_maps.setTextSize(15);
				btn_vk.setTextSize(15);
				btn_viber.setTextSize(15);
				btn_ok.setTextSize(15);
				btn_skype.setTextSize(15);
				btn_whatsapp.setTextSize(15);
				btn_twitter.setTextSize(15);
				btn_facebook.setTextSize(15);
				btn_instagram.setTextSize(15);
				btn_newacc.setTextSize(15);
				button_sync.setTextSize(15);
				btn_search.setTextSize(15);
			}
			if (size.contains("Large")) {
				btn_mail.setTextSize(18);
				btn_notepad.setTextSize(18);
				btn_phone.setTextSize(18);
				btn_message.setTextSize(18);
				btn_safari.setTextSize(18);
				btn_music.setTextSize(18);
				btn_game.setTextSize(18);
				btn_weather.setTextSize(18);
				btn_compass.setTextSize(18);
				btn_maps.setTextSize(18);
				btn_vk.setTextSize(18);
				btn_viber.setTextSize(18);
				btn_ok.setTextSize(18);
				btn_skype.setTextSize(18);
				btn_whatsapp.setTextSize(18);
				btn_twitter.setTextSize(18);
				btn_facebook.setTextSize(18);
				btn_instagram.setTextSize(18);
				btn_newacc.setTextSize(18);
				button_sync.setTextSize(18);
				btn_search.setTextSize(18);
			}
			if (size.contains("xLarge")) {
				btn_mail.setTextSize(20);
				btn_notepad.setTextSize(20);
				btn_phone.setTextSize(20);
				btn_message.setTextSize(20);
				btn_safari.setTextSize(20);
				btn_music.setTextSize(20);
				btn_game.setTextSize(20);
				btn_weather.setTextSize(20);
				btn_compass.setTextSize(20);
				btn_maps.setTextSize(20);
				btn_vk.setTextSize(20);
				btn_viber.setTextSize(20);
				btn_ok.setTextSize(20);
				btn_skype.setTextSize(20);
				btn_whatsapp.setTextSize(20);
				btn_twitter.setTextSize(20);
				btn_facebook.setTextSize(20);
				btn_instagram.setTextSize(20);
				btn_newacc.setTextSize(20);
				button_sync.setTextSize(20);
				btn_search.setTextSize(20);
			}
		}

		Boolean searchb = mSettings.getBoolean(APP_PREFERENCES_SEARCH, true);
		if (searchb == true) {
			search.setChecked(true);
		} else {
			search.setChecked(false);
		}

		if (mSettings.contains(APP_PREFERENCES_MAIL)) {
			mail.setChecked(true);
			btn_mail.setEnabled(true);
		}


		if (mSettings.contains(APP_PREFERENCES_NOTES)) {
			notes.setChecked(true);
			btn_notepad.setEnabled(true);
		}


		if (mSettings.contains(APP_PREFERENCES_PHONE)) {
			phone.setChecked(true);
			btn_phone.setEnabled(true);
		}


		if (mSettings.contains(APP_PREFERENCES_MESSAGES)) {
			messages.setChecked(true);
			btn_message.setEnabled(true);
		}


		if (mSettings.contains(APP_PREFERENCES_SAFARI)) {
			safari.setChecked(true);
			btn_safari.setEnabled(true);
		}


		if (mSettings.contains(APP_PREFERENCES_MUSIC)) {
			music.setChecked(true);
			btn_music.setEnabled(true);
		}


		if (mSettings.contains(APP_PREFERENCES_GAMECENTER)) {
			game.setChecked(true);
			btn_game.setEnabled(true);
		}


		if (mSettings.contains(APP_PREFERENCES_WEATHER)) {
			weather.setChecked(true);
			btn_weather.setEnabled(true);
		}


		if (mSettings.contains(APP_PREFERENCES_COMPASS)) {
			compass.setChecked(true);
			btn_compass.setEnabled(true);
		}

		if (mSettings.contains(APP_PREFERENCES_MAPS)) {
			maps.setChecked(true);
			btn_maps.setEnabled(true);
		}

		if (mSettings.contains(APP_PREFERENCES_VK)) {
			vk.setChecked(true);
			btn_vk.setEnabled(true);
		}

		if (mSettings.contains(APP_PREFERENCES_VIBER)) {
			viber.setChecked(true);
			btn_viber.setEnabled(true);
		}

		if (mSettings.contains(APP_PREFERENCES_OK)) {
			ok.setChecked(true);
			btn_ok.setEnabled(true);
		}

		if (mSettings.contains(APP_PREFERENCES_SKYPE)) {
			skype.setChecked(true);
			btn_skype.setEnabled(true);
		}

		if (mSettings.contains(APP_PREFERENCES_WHATSAPP)) {
			whatsapp.setChecked(true);
			btn_whatsapp.setEnabled(true);
		}

		if (mSettings.contains(APP_PREFERENCES_TWITTER)) {
			twitter.setChecked(true);
			btn_twitter.setEnabled(true);
		}

		if (mSettings.contains(APP_PREFERENCES_FACEBOOK)) {
			facebook.setChecked(true);
			btn_facebook.setEnabled(true);
		}

		if (mSettings.contains(APP_PREFERENCES_INSTAGRAM)) {
			instagram.setChecked(true);
			btn_instagram.setEnabled(true);
		}

	}


	//SYNC MODE
	boolean isMasterSyncEnabled = ContentResolver.getMasterSyncAutomatically();

	private void ButtonSync() {
		if (isMasterSyncEnabled == true) {
			tb_sync.setChecked(true);
		}
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


	public void memory() {

		//size_memory.setText(getTotalInternalMemorySize());

		///////Доступно
		long availableSpace = -1L;
		StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
		availableSpace = (long) stat.getAvailableBlocks() * (long) stat.getBlockSize();
		////////

		StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
		//StatFs statFs = new StatFs("/data");
		long total = ((long) statFs.getBlockCount() * (long) statFs.getBlockSize());


		long size = 1073741824L; ////1Gb

		Log.e("!!", "total:" + getFileSize(total) + " size:" + getFileSize(size));

		if (total > size) {
			size = 2147483648L; /////2Gb
			if (total > size) {
				size = 4294967296L;////4Gb
				if (total > size) {
					size = 8589934592L;////8Gb
					if (total > size) {
						size = 17179869184L;////16Gb
						if (total > size) {
							size = 34359738368L;////32Gb
							if (total > size) {
								size = total;
							}
						}
					}
				}
			}
		}

		Long busy = size - availableSpace;

		size_memory.setText(getFileSize(busy) + " из " + getFileSize(size));
		total_size = size;

	}

	private long getAudioList() {
		long sssize = 0;
		try {
			final Cursor mCursor = getContentResolver().query(
					MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
					new String[]{MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.DATA}, null, null,
					"LOWER(" + MediaStore.Audio.Media.TITLE + ") ASC");
			String mAudioPath = "";

			if (mCursor.moveToFirst()) {
				do {

					mAudioPath = mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
					sssize = sssize + get_music_size(mAudioPath);


				} while (mCursor.moveToNext());
			}
			mCursor.close();
		} catch (NullPointerException e) {
		}

		return sssize;
	}

	public long get_music_size(String path){
		//String path = file:///storage/emulated/0/Music/Tamil/I%20(2014)/Ennodu%20Nee%20Irundhaal.mp3
		String where = String.format("%s='%s'", MediaStore.Audio.Media.DATA, path);
		Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		Cursor cursor = this.getContentResolver().query(uri, null, where, null, null);
		long s = 0;
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {

			s = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));

		}

		return s;


	}

	private long getPhotoList() {
		long sssize = 0;
		try{


			final Cursor mCursor = getContentResolver().query(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
					new String[] { MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.DATA }, null, null,
					null);

			String mAudioPath = "";
			if (mCursor.moveToFirst()) {
				do {
					mAudioPath = mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
					sssize = sssize + get_music_size(mAudioPath);
				} while (mCursor.moveToNext());
			}

			mCursor.close();


		}catch(NullPointerException e){
		}
		return sssize;
	}

	private long getVideoList() {
		long sssize = 0;
		try{
			final Cursor mCursor = getContentResolver().query(
					MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
					null, null, null,
					null);
			String mAudioPath = "";
			if (mCursor.moveToFirst()) {
				do {
					mAudioPath = mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
					sssize = sssize + get_music_size(mAudioPath);
				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}catch(NullPointerException e){
		}
		return sssize;
	}

private void persent (){

	long availableSpace = -1L;
	StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
	availableSpace = (long) stat.getAvailableBlocks() * (long) stat.getBlockSize();

	long busy = total_size - availableSpace;

	long music = getAudioList();
	long total = total_size;
	long photo = getPhotoList();
	long video = getVideoList();
	long other = busy;

	pg2.setMax(100);
	pg3.setMax(100);
	pg4.setMax(100);
	pg5.setMax(100);

	pg2.setProgress((int)(photo*100/total));
	pg3.setProgress((int)((photo*100/total)+(video*100/total)));
	pg4.setProgress((int) ((photo*100/total)+(video*100/total) + (music*100/total)));
	pg5.setProgress((int) ((photo*100/total)+(video*100/total) + (music*100/total) + (other*100/total)));


	Log.e("!!!", "Total: 100%; Music: " + music*100/total + "%; Photo: " + photo*100/total + "%; Other: " + other*100/total + "%");

}






	public  String getFileSize(long size) {
		if (size <= 0)
			return "0";
		final String[] unit = getResources().getStringArray(R.array.units);

		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + unit[digitGroups];
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

			case R.id.LinearLayoutICloud:
				Intent n11 = new Intent (this, ActivityUsage.class);
				SwipeBackActivityHelper.activityBuilder(this)
						.intent(n11)
						.needParallax(false)
						.needBackgroundShadow(false)
						.startActivity();
				break;
		
		case R.id.synctoggle:
	        	if((tb_sync).isChecked()) {
              		 ContentResolver.setMasterSyncAutomatically(true);
              	 }
              	 else {
              		 ContentResolver.setMasterSyncAutomatically(false); 
              	 }
	    	break;
		
		case R.id.ToggleButtonMail:
			if(mail.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != mail_stok){
					String mail_app = "com.android.email";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_MAIL, mail_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsMail.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_mail.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("mail_app").apply();
                mail.setChecked(false);
                btn_mail.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonNotes:
			if(notes.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				Intent intent = new Intent(this, ActivityAppsNotes.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	btn_notepad.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("notes_app").commit();
                notes.setChecked(false);
                btn_notepad.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonPhone:
			if(phone.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != phone_stok){
					String phone_app = "com.android.dialer";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_PHONE, phone_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsPhone.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_phone.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("phone_app").commit();
                phone.setChecked(false);
                btn_phone.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonMessage:
			if(messages.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != messages_stok){
				String messages_app = "com.android.mms";
				Editor editor = mSettings.edit();
			   	editor.putString(APP_PREFERENCES_MESSAGES, messages_app);
			   	editor.apply();
			}
			else {
			Intent intent = new Intent(this, ActivityAppsMessages.class);
	       	 startActivity(intent);
	        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_message.setEnabled(true);
    	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("messages_app").commit();
                messages.setChecked(false);
                btn_message.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonSafari:
			if(safari.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != safari_stok){
					String safari_app = "com.android.browser";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_SAFARI, safari_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsSafari.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_safari.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("safari_app").commit();
                safari.setChecked(false);
                btn_safari.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonMusic:
			if(music.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != music_stok){
					String music_app = "com.android.music";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_MUSIC, music_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsMusic.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_music.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("music_app").commit();
                music.setChecked(false);
                btn_music.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonGame:
			if(game.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				Intent intent = new Intent(this, ActivityAppsGameCenter.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	btn_game.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("gamecenter_app").commit();
                game.setChecked(false);
                btn_game.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonWeather:
			if(weather.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				Intent intent = new Intent(this, ActivityAppsWeather.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	btn_weather.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("weather_app").commit();
                weather.setChecked(false);
                btn_weather.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonCompass:
			if(compass.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				Intent intent = new Intent(this, ActivityAppsCompass.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	btn_compass.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("compass_app").commit();
                compass.setChecked(false);
                btn_compass.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonMaps:
			if(maps.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != maps_stok){
					String maps_app = "com.google.android.apps.maps";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_MAPS, maps_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsMaps.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_maps.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("maps_app").commit();
                maps.setChecked(false);
                btn_maps.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonVk:
			if(vk.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != vk_stok){
					String vk_app = "com.vkontakte.android";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_VK, vk_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsVk.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_vk.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("vk_app").commit();
                vk.setChecked(false);
                btn_vk.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonViber:
			if(viber.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != viber_stok){
					String viber_app = "com.viber.voip";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_VIBER, viber_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsViber.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_viber.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("viber_app").commit();
                viber.setChecked(false);
                btn_viber.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonOk:
			if(ok.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != ok_stok){
					String ok_app = "ru.ok.android";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_OK, ok_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsOk.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_ok.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("ok_app").commit();
                ok.setChecked(false);
                btn_ok.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonSkype:
			if(skype.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != skype_stok){
					String skype_app = "com.skype.raider";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_SKYPE, skype_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsSkype.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_skype.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("skype_app").commit();
                skype.setChecked(false);
                btn_skype.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonWhatsApp:
			if(whatsapp.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != whatsapp_stok){
					String whatsapp_app = "com.whatsapp";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_WHATSAPP, whatsapp_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsWhatsapp.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_whatsapp.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("whatsapp_app").commit();
                whatsapp.setChecked(false);
                btn_whatsapp.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonTwitter:
			if(twitter.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != twitter_stok){
					String twitter_app = "com.twitter.android";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_TWITTER, twitter_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsTwitter.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_twitter.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("twitter_app").commit();
                twitter.setChecked(false);
                btn_twitter.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonFacebook:
			if(facebook.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != facebook_stok){
					String facebook_app = "com.facebook.katana";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_FACEBOOK, facebook_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsFacebook.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_facebook.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("facebook_app").commit();
                facebook.setChecked(false);
                btn_facebook.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonInstagram:
			if(instagram.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != instagram_stok){
					String instagram_app = "com.instagram.android";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_INSTAGRAM, instagram_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsInstagram.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_instagram.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("instagram_app").commit();
                instagram.setChecked(false);
                btn_instagram.setEnabled(false);
			}
			break;
			

			
		
		case R.id.Button01:
			Intent intent = new Intent(this, ActivityAppsMail.class);
	       	 startActivity(intent);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button15:
			Intent intent2 = new Intent(this, ActivityAppsNotes.class);
	       	 startActivity(intent2);
	        	overridePendingTransition(center_to_left, center_to_left2);	
	        	break;
		case R.id.Button14:
			Intent intent3 = new Intent(this, ActivityAppsPhone.class);
	       	 startActivity(intent3);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button13:
			Intent intent4 = new Intent(this, ActivityAppsMessages.class);
	       	 startActivity(intent4);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button12:
			Intent intent5 = new Intent(this, ActivityAppsSafari.class);
	       	 startActivity(intent5);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button11:
			Intent intent6 = new Intent(this, ActivityAppsMusic.class);
	       	 startActivity(intent6);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button10:
			Intent intent7 = new Intent(this, ActivityAppsGameCenter.class);
	       	 startActivity(intent7);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button09:
			Intent intent8 = new Intent(this, ActivityAppsWeather.class);
	       	 startActivity(intent8);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button08:
			Intent intent9 = new Intent(this, ActivityAppsCompass.class);
	       	 startActivity(intent9);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button07:
			Intent intent10 = new Intent(this, ActivityAppsMaps.class);
	       	 startActivity(intent10);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button06:
			Intent intent11 = new Intent(this, ActivityAppsVk.class);
	       	 startActivity(intent11);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button05:
			Intent intent12 = new Intent(this, ActivityAppsViber.class);
	       	 startActivity(intent12);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button04:
			Intent intent13 = new Intent(this, ActivityAppsOk.class);
	       	 startActivity(intent13);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button03:
			Intent intent14 = new Intent(this, ActivityAppsSkype.class);
	       	 startActivity(intent14);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button02:
			Intent intent15 = new Intent(this, ActivityAppsWhatsapp.class);
	       	 startActivity(intent15);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button18:
			Intent intent16 = new Intent(this, ActivityAppsTwitter.class);
	       	 startActivity(intent16);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button17:
			Intent intent17 = new Intent(this, ActivityAppsFacebook.class);
	       	 startActivity(intent17);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button16:
			Intent intent18 = new Intent(this, ActivityAppsInstagram.class);
	       	 startActivity(intent18);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button20:
			 Intent settingsIntent = new Intent(android.provider.Settings.ACTION_ADD_ACCOUNT);
	        	startActivity(settingsIntent);
		        	overridePendingTransition(center_to_left, center_to_left2);
			break;
        	
		case R.id.Button22:
			Intent intent22 = new Intent(this, ActivitySearchiPhone.class);
	       	 startActivity(intent22);
	        	overridePendingTransition(center_to_left, center_to_left2);
			break;
		}
	}

}
