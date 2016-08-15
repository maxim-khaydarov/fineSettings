package ua.mkh.settings.full;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by Haydarov-M on 09/08/2016.
 */
public class ActivitySotaParam extends Activity implements View.OnClickListener {

    Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;

    TextView  textStatus;
    Button btn_back;
    Button button01, button02, button03;
    ToggleButton tgb1;
    TextView textView, type;

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
        setContentView(R.layout.activity_sota_param);
        String roman = "fonts/Regular.otf";
        String medium = "fonts/Medium.otf";
        String bold = "fonts/Bold.otf";
        String thin = "fonts/Thin.otf";
        typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
        typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
        typefaceBold = Typeface.createFromAsset(getAssets(), bold);
        typefaceThin = Typeface.createFromAsset(getAssets(), thin);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        textStatus = (TextView) findViewById(R.id.textOk);

        button01 = (Button) findViewById(R.id.Button01);
        button02 = (Button) findViewById(R.id.Button02);
        button03 = (Button) findViewById(R.id.Button03);
        button03.setOnClickListener(this);
        tgb1 = (ToggleButton) findViewById(R.id.soundtoggle);
        tgb1.setEnabled(false);
        textView = (TextView) findViewById(R.id.textView);
        type = (TextView)findViewById(R.id.textView007);

        btn_back = (Button) findViewById(R.id.buttonBack);
        btn_back.setText(R.string.button_sota);
        textStatus.setText("");
        textStatus.setTypeface(typefaceBold);
        btn_back.setTypeface(typefaceMedium);
        button01.setTypeface(typefaceRoman);
        button02.setTypeface(typefaceRoman);
        button03.setTypeface(typefaceRoman);
        textView.setTypeface(typefaceRoman);
        type.setTypeface(typefaceRoman);




    }


    protected void onResume() {
        super.onResume();

        state();
        check_roum();



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


    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.Button03:
                Intent settingsIntent = new Intent(android.provider.Settings.ACTION_APN_SETTINGS);
                startActivity(settingsIntent);
                overridePendingTransition(center_to_left, center_to_left2);
                break;

        }
    }

    private void state() {

        TelephonyManager teleMan =
                (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = teleMan.getNetworkType();
        switch (networkType)
        {

            case 4:
                type.setText(R.string.cdma);
                break;
            case 2:
                type.setText(R.string.edge);
                break;

            case 1:
                type.setText(R.string.gprs);
                break;
            case 8:
                type.setText(R.string.hsdpa);
                break;
            case 10:
                type.setText(R.string.hspa);
                break;
            case 15:
                type.setText(R.string.hspa_plus);
                break;
            case 9:
                type.setText(R.string.hsupa);
                break;
            case 11:
                type.setText(R.string.iden);
                break;
            case 13:
                type.setText(R.string.lte);
                break;
            case 3:
                type.setText(R.string.umts);
                break;
            case 0:
                type.setText(R.string.unknown);
                break;
        }

    }

    private void check_roum() {
        final TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        PhoneStateListener phoneStateListener = new PhoneStateListener() {
            @Override
            public void onServiceStateChanged(ServiceState serviceState) {
                super.onServiceStateChanged(serviceState);
                if (telephonyManager.isNetworkRoaming()) {
                    tgb1.setChecked(true);
                } else {
                    // Not in Roaming
                }
                // You can also check roaming state using this
                if (serviceState.getRoaming()) {
                    tgb1.setChecked(true);
                } else {
                    // Not in Roaming
                }
            }
        };

    }


    @Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {
            
            case KeyEvent.KEYCODE_BACK:
                Intent intent18 = new Intent(this, ActivitySota.class);
                startActivity(intent18);

                overridePendingTransition(center_to_right, center_to_right2);
                return true;

        }
        return super.onKeyDown(keycode, e);
    }

   


    public void BackClick(View v)
    {
        Intent intent18 = new Intent(this, ActivitySota.class);
        startActivity(intent18);

        overridePendingTransition(center_to_right, center_to_right2);
    }
}