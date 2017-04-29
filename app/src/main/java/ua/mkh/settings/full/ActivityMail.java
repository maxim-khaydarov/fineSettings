package ua.mkh.settings.full;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.suke.widget.SwitchButton;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by 1 on 28.04.2017.
 */

public class ActivityMail extends Activity implements View.OnClickListener {

    Typeface typefaceRoman, typefaceMedium, typefaceBold;
    SharedPreferences mSettings;


    int center_to_right, center_to_right2;
    int center_to_left, center_to_left2;

    com.suke.widget.SwitchButton tg1, tg2, tg3, tg4, tg5, tg6;
    ToggleButton tg_1, tg_2;

    public static final String APP_PREFERENCES = "mysettings";

    TextView textStatus;
    Button buttonBack;
    Button account;
    SharedPreferences.Editor editor ;
    SwipeBackActivityHelper helper = new SwipeBackActivityHelper();


    private void theme() {
Log.e("!!", "Theme");
        // Enclose everything in a try block so we can just
        // use the default view if anything goes wrong.
        try {
            // Get the font size value from SharedPreferences.
            SharedPreferences settings =
                    getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

            // Get the font size option.  We use "FONT_SIZE" as the key.
            // Make sure to use this key when you set the value in SharedPreferences.
            // We specify "Medium" as the default value, if it does not exist.
            String fontSizePref = settings.getString("txt_size", "Medium");
            Log.e("!!", fontSizePref);

            // Select the proper theme ID.
            // These will correspond to your theme names as defined in themes.xml.
            int themeID = R.style.Medium;
            if (fontSizePref.contains("Small")) {
                themeID = R.style.Small;
            }
            else if (fontSizePref.contains("Large")) {
                themeID = R.style.Large;
            }
            else if (fontSizePref.contains("xLarge")){
                themeID = R.style.XLarge;
            }

            // Set the theme for the activity.
            setTheme(themeID);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        theme();
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //int themeID = R.style.XLarge;
        //setTheme(themeID);

        setContentView(R.layout.activity_mail);
        String roman = "fonts/Regular.otf";
        String medium = "fonts/Medium.otf";
        String bold = "fonts/Bold.otf";

        helper.setEdgeMode(true)
                .setParallaxMode(true)
                .setParallaxRatio(5)
                .setNeedBackgroundShadow(false)
                .init(this);

        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);


        typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
        typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
        typefaceBold = Typeface.createFromAsset(getAssets(), bold);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        editor = mSettings.edit();

        tg1 = (com.suke.widget.SwitchButton) findViewById(R.id.tg_1);
        tg2 = (com.suke.widget.SwitchButton) findViewById(R.id.tg_2);
        tg3 = (com.suke.widget.SwitchButton) findViewById(R.id.tg_3);
        tg4 = (com.suke.widget.SwitchButton) findViewById(R.id.tg_4);
        tg5 = (com.suke.widget.SwitchButton) findViewById(R.id.tg_5);
        tg6 = (com.suke.widget.SwitchButton) findViewById(R.id.tg_6);

        tg_1 = (ToggleButton) findViewById(R.id.tg1);
        tg_1.setOnClickListener(this);
        tg_2 = (ToggleButton) findViewById(R.id.tg2);
        tg_2.setOnClickListener(this);

        account = (Button) findViewById(R.id.ButtonAccount);
        account.setOnClickListener(this);

        textStatus = (TextView) findViewById(R.id.textOk);

        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setText(R.string.app_name);


        buttonBack.setTypeface(typefaceBold);


        textStatus.setTypeface(typefaceMedium);
        textStatus.setText(R.string.mail);

        toggle_button();
    }

    private void get_tg() {
        tg1.setChecked(mSettings.getBoolean("tg1_mail", true));
        tg2.setChecked(mSettings.getBoolean("tg2_mail", true));
        tg3.setChecked(mSettings.getBoolean("tg3_mail", true));
        tg4.setChecked(mSettings.getBoolean("tg4_mail", true));
        tg5.setChecked(mSettings.getBoolean("tg5_mail", true));
        tg6.setChecked(mSettings.getBoolean("tg6_mail", true));
        tg_1.setChecked(mSettings.getBoolean("tg_1_mail", true));
        tg_2.setChecked(mSettings.getBoolean("tg_2_mail", true));
    }

    private void toggle_button(){
        tg1.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(tg1.isChecked()) {
                    editor.putBoolean("tg1_mail", true).apply();
                }
                else {
                    editor.putBoolean("tg1_mail", false).apply();
                }
            }
        });
        ////////////////////////////
        tg2.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(tg2.isChecked()) {
                    editor.putBoolean("tg2_mail", true).apply();
                }
                else {
                    editor.putBoolean("tg2_mail", false).apply();
                }
            }
        });
        ////////////////////////////
        tg3.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(tg3.isChecked()) {
                    editor.putBoolean("tg3_mail", true).apply();
                }
                else {
                    editor.putBoolean("itunes_book", false).apply();
                }
            }
        });
        ////////////////////////////
        tg4.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(tg4.isChecked()) {
                    editor.putBoolean("tg4_mail", true).apply();
                }
                else {
                    editor.putBoolean("itunes_update", false).apply();
                }
            }
        });
        ////////////////////////////
        tg5.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(tg5.isChecked()) {
                    editor.putBoolean("tg5_mail", true).apply();
                }
                else {
                    editor.putBoolean("tg5_mail", false).apply();
                }
            }
        });
        ////////////////////////////
        tg6.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(tg6.isChecked()) {
                    editor.putBoolean("tg6_mail", true).apply();
                }
                else {
                    editor.putBoolean("tg6_mail", false).apply();
                }
            }
        });
        ////////////////////////////
    }


    protected void onResume() {
        super.onResume();

        get_tg();
        onStart();
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


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.ButtonAccount:

                Intent intent = new Intent(Settings.ACTION_SYNC_SETTINGS);
                SwipeBackActivityHelper.activityBuilder(this)
						 .intent(intent)
						 .needParallax(false)
						 .needBackgroundShadow(false)
						 .startActivity();

                break;

            case R.id.tg1:
                if(tg_1.isChecked()){
                    editor.putBoolean("tg_1_mail", true).apply();
                }
                else{
                    editor.putBoolean("tg_1_mail", false).apply();
                }
                break;

            case R.id.tg2:
                if(tg_2.isChecked()){
                    editor.putBoolean("tg_2_mail", true).apply();
                }
                else{
                    editor.putBoolean("tg_2_mail", false).apply();
                }
                break;

        default:
        break;
    }


}



    }
