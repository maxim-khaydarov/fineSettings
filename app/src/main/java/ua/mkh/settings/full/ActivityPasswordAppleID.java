package ua.mkh.settings.full;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by 1 on 09.04.2017.
 */

public class ActivityPasswordAppleID extends Activity implements View.OnClickListener {

    Typeface typefaceRoman, typefaceMedium, typefaceBold;
    SharedPreferences mSettings;

    int center_to_right, center_to_right2;
    int center_to_left, center_to_left2;

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_text_size = "txt_size";
    public static final String APP_PREFERENCES_bold_text = "bold_txt";
    public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
    public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";

    Button btn_back;
    Button b1, b2, b3, b4;
    TextView txt1, txt2, txt3, txt4, txt5, txt6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_passwordappleid);
        String roman = "fonts/Regular.otf";
        String medium = "fonts/Medium.otf";
        String bold = "fonts/Bold.otf";

        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);


        typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
        typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
        typefaceBold = Typeface.createFromAsset(getAssets(), bold);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        TextView textStatus = (TextView) findViewById(R.id.textOk);
        btn_back = (Button) findViewById(R.id.buttonBack);


        b1 = (Button) findViewById(R.id.edit_password);
        b1.setOnClickListener(this);
        b2 = (Button) findViewById(R.id.autentification);
        b3 = (Button) findViewById(R.id.number);
        b4 = (Button) findViewById(R.id.get_password);
        b4.setOnClickListener(this);

        txt1 = (TextView) findViewById(R.id.textView29);
        txt2 = (TextView) findViewById(R.id.textView18);
        txt3 = (TextView) findViewById(R.id.textView16);
        txt4 = (TextView) findViewById(R.id.textView19);
        txt4.setOnClickListener(this);
        txt5 = (TextView) findViewById(R.id.textView27);
        txt6 = (TextView) findViewById(R.id.textView28);



        textStatus.setText(R.string.name_appleid);
        btn_back.setText(R.string.apple_id);

        textStatus.setTypeface(typefaceBold);
        btn_back.setTypeface(typefaceMedium);
        b1.setTypeface(typefaceRoman);
        b2.setTypeface(typefaceRoman);
        b3.setTypeface(typefaceRoman);
        b4.setTypeface(typefaceRoman);
        txt1.setTypeface(typefaceRoman);
        txt2.setTypeface(typefaceRoman);
        txt3.setTypeface(typefaceRoman);
        txt4.setTypeface(typefaceRoman);
        txt5.setTypeface(typefaceRoman);
        txt6.setTypeface(typefaceRoman);
    }

    @Override
    protected void onResume() {
        super.onResume();

        get_phone_user();

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


            }
        }

        if (mSettings.contains(APP_PREFERENCES_text_size)) {
            String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
            if (size.contains("Small")) {

            }
            if (size.contains("Normal")) {

            }
            if (size.contains("Large")) {

            }
            if (size.contains("xLarge")) {

            }
        }
    }


    private void get_phone_user(){
            b3.setText(mSettings.getString("phone", ""));
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {

            case KeyEvent.KEYCODE_BACK:
                if(!mSettings.contains("name")){
                    Intent intent18 = new Intent(this, MainActivity.class);
                    startActivity(intent18);
                    overridePendingTransition(center_to_right, center_to_right2);
                }
                else {
                    Intent intent18 = new Intent(this, ActivityAppleID.class);
                    startActivity(intent18);
                    overridePendingTransition(center_to_right, center_to_right2);
                }
                return true;

        }
        return super.onKeyDown(keycode, e);
    }

    public void BackClick(View v)
    {
        Intent intent18 = new Intent(this, ActivityAppleID.class);
        startActivity(intent18);
        overridePendingTransition(center_to_right, center_to_right2);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.edit_password:
                Intent callSettingIntent= new Intent(Settings.ACTION_SECURITY_SETTINGS);
                startActivity(callSettingIntent);
                overridePendingTransition(center_to_right, center_to_right2);
                break;

            case R.id.textView19:
                Intent callSettingIntentd= new Intent(ActivityPasswordAppleID.this, ActivityPhoneAppleID.class);
                startActivity(callSettingIntentd);
                overridePendingTransition(center_to_right, center_to_right2);
                break;

            case R.id.get_password:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.iCloud.com"));
                startActivity(browserIntent);
                overridePendingTransition(center_to_right, center_to_right2);
                break;
            default:
                break;
        }

    }
}