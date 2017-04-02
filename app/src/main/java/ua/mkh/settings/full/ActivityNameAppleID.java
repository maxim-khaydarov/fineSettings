package ua.mkh.settings.full;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 1 on 01.04.2017.
 */

public class ActivityNameAppleID extends Activity implements View.OnClickListener{

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
    Button name, email, phone, adress;
    TextView birthday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_nameappleid);
        String roman = "fonts/Regular.otf";
        String medium = "fonts/Medium.otf";
        String bold = "fonts/Bold.otf";

        typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
        typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
        typefaceBold = Typeface.createFromAsset(getAssets(), bold);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        TextView textStatus = (TextView) findViewById(R.id.textOk);
        btn_back = (Button) findViewById(R.id.buttonBack);

        textStatus.setText(R.string.name_appleid);
        btn_back.setText(R.string.apple_id);


        name = (Button) findViewById(R.id.name);
        name.setOnClickListener(this);
        email = (Button) findViewById(R.id.email);
        phone = (Button) findViewById(R.id.phone);
        adress = (Button) findViewById(R.id.adress);
        adress.setOnClickListener(this);

        birthday = (TextView) findViewById(R.id.birthday);


        textStatus.setTypeface(typefaceBold);
        btn_back.setTypeface(typefaceMedium);
        name.setTypeface(typefaceRoman);
        email.setTypeface(typefaceRoman);
        phone.setTypeface(typefaceRoman);

    }


    protected void onResume() {

        get_name_user();
        get_email_user();
        get_phone_user();
        get_birthday_user();


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



    private void get_name_user(){
        if(!mSettings.contains("name") || !mSettings.contains("surname")){
            //go to registration
            name.setText("Введите имя");
        }
        else{
            if(!mSettings.contains("patr")){
                name.setText(mSettings.getString("name", "Maxim") + " " + mSettings.getString("surname", "Khaydarov"));
            }
            else{
                name.setText(mSettings.getString("name", "Maxim") + " " + mSettings.getString("surname", "Khaydarov") + " " + mSettings.getString("patr", "Alievich"));
            }

        }

    }

    private void get_email_user(){
        if(!mSettings.contains("email")){
            //go to registration
            email.setText("Введите E-MAIL");
        }
        else{
            //email.setText(mSettings.getString("email", "maxim.khaydarpv@yandex.ru"));
            String t2 = mSettings.getString("email", "maxim.khaydarov@yandex.ru") + "<br />" + "<font color=\"#808080\" >" + "<small><small>" + getString(R.string.apple_id) +  "</small></small>" + "</font>";
            email.setText(Html.fromHtml(t2), TextView.BufferType.SPANNABLE);
        }

    }

    private void get_phone_user(){
        if(!mSettings.contains("phone")){
            //go to registration
            phone.setText("Введите телефон");
        }
        else{
            //phone.setText(mSettings.getString("phone", "+380 975192551"));
            String t2 = mSettings.getString("phone", "+380 975192551") + "<br />" + "<font color=\"#808080\" >" + "<small><small>" + getString(R.string.phone_text_appleid_name) +  "</small></small>" + "</font>";
            email.setText(Html.fromHtml(t2), TextView.BufferType.SPANNABLE);
        }

    }

    private void get_birthday_user (){
        birthday.setText(mSettings.getString("birthday", "28.12.94"));
    }

    public void BackClick(View v)
    {
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
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.name:
                Intent na = new Intent(this, ActivityName.class);
                startActivity(na);
                overridePendingTransition(center_to_left, center_to_left2);
                break;


            default:
                break;
        }

    }

    }
