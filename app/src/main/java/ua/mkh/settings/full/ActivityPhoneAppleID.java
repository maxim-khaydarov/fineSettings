package ua.mkh.settings.full;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by 1 on 03.04.2017.
 */

public class ActivityPhoneAppleID extends Activity implements View.OnClickListener{

    Typeface typefaceRoman, typefaceMedium, typefaceBold;
    SharedPreferences mSettings;

    int center_to_right, center_to_right2;
    int center_to_left, center_to_left2;

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_text_size = "txt_size";
    public static final String APP_PREFERENCES_bold_text = "bold_txt";
    public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
    public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";

    Button btn_back, btn_save;
    Button btn_country, btn_phone;
    TextView txt_country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_phone_appleid);
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
        btn_save = (Button) findViewById(R.id.buttonSave);

        btn_country = (Button) findViewById(R.id.btn_contry);
        btn_country.setOnClickListener(this);
        btn_phone = (Button) findViewById(R.id.btn_phone);
        txt_country = (TextView) findViewById(R.id.textView25);

        textStatus.setText(R.string.edit_phone);
        btn_back.setText(R.string.back);



        //String result = input.substring(0, input.indexOf(" "));



        btn_save.setTextColor(Color.parseColor("#808080"));
        btn_save.setText(getText(R.string.next));

        textStatus.setTypeface(typefaceBold);
        btn_back.setTypeface(typefaceMedium);
        btn_save.setTypeface(typefaceMedium);
        btn_country.setTypeface(typefaceRoman);
        btn_phone.setTypeface(typefaceRoman);
        txt_country.setTypeface(typefaceRoman);

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

    @Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {

            case KeyEvent.KEYCODE_BACK:

                Intent intent18 = new Intent(this, ActivityNameAppleID.class);
                startActivity(intent18);
                overridePendingTransition(center_to_right, center_to_right2);

                return true;

        }
        return super.onKeyDown(keycode, e);
    }

    public void BackClick(View v)
    {
        Intent intent18 = new Intent(this, ActivityNameAppleID.class);
        startActivity(intent18);
        overridePendingTransition(center_to_right, center_to_right2);

    }

    public void SaveClick(View v)
    {
/*
        if (ed1.getText().toString().contains("@") && !ed1.getText().toString().equals(mSettings.getString("email", ""))) {

            SharedPreferences.Editor editorName = mSettings.edit();
            editorName.putString("email", ed1.getText().toString());
            editorName.apply();

            Intent intent18 = new Intent(this, ActivityNameAppleID.class);
            startActivity(intent18);
            overridePendingTransition(center_to_right, center_to_right2);
        }
*/
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {



            default:
                break;
        }

    }

}
