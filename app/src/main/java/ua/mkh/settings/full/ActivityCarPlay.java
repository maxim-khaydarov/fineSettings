package ua.mkh.settings.full;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by haydarov-m on 06/12/2016.
 */
public class ActivityCarPlay  extends Activity{

    Button btn_back;
    TextView textStatus;
    Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
    public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
    public static final String APP_PREFERENCES_text_size = "txt_size";
    public static final String APP_PREFERENCES_bold_text = "bold_txt";

    int center_to_right, center_to_right2;
    int center_to_left, center_to_left2;

    SharedPreferences mSettings;

    TextView textView11, textView12;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_carplay);
        String roman = "fonts/Regular.otf";
        String medium = "fonts/Medium.otf";
        String bold = "fonts/Bold.otf";
        String thin = "fonts/Thin.otf";
        typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
        typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
        typefaceBold = Typeface.createFromAsset(getAssets(), bold);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        btn_back = (Button) findViewById(R.id.buttonBack);
        btn_back.setText(R.string.app_name);
        textStatus = (TextView)findViewById(R.id.textOk);
        textStatus.setTypeface(typefaceBold);
        textStatus.setText(R.string.button_general);
        btn_back.setTypeface(typefaceMedium);


        textView11 = (TextView) findViewById(R.id.textView11);
        textView12 = (TextView) findViewById(R.id.textView12);

        textView11.setTypeface(typefaceRoman);
        textView12.setTypeface(typefaceRoman);

    }


    protected void onResume() {
        super.onResume();


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
                textView11.setTypeface(typefaceBold);
                textView12.setTypeface(typefaceBold);
            }
        }

        if (mSettings.contains(APP_PREFERENCES_text_size)) {
            String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
            if (size.contains("Small")) {
                textView11.setTextSize(11);
                textView12.setTextSize(11);
            }
            if (size.contains("Normal")) {
                textView11.setTextSize(13);
                textView12.setTextSize(13);
            }
            if (size.contains("Large")) {
                textView11.setTextSize(16);
                textView12.setTextSize(16);
            }
            if (size.contains("xLarge")) {
                textView11.setTextSize(18);
                textView12.setTextSize(18);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {

            case KeyEvent.KEYCODE_BACK:
                Intent intent18 = new Intent(this, ActivityOsnova.class);
                startActivity(intent18);

                overridePendingTransition(center_to_right, center_to_right2);
                return true;

        }
        return super.onKeyDown(keycode, e);
    }




    public void BackClick(View v)
    {
        Intent intent18 = new Intent(this, ActivityOsnova.class);
        startActivity(intent18);

        overridePendingTransition(center_to_right, center_to_right2);
    }

}
