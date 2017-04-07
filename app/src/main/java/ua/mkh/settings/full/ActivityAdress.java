package ua.mkh.settings.full;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by 1 on 07.04.2017.
 */

public class ActivityAdress extends Activity implements View.OnClickListener {

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
    ListView lv;
    Button b1, b2, b3, b4;
    EditText ed_adres, ed_city, ed_index, ed_country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_adres);
        String roman = "fonts/Regular.otf";
        String medium = "fonts/Medium.otf";
        String bold = "fonts/Bold.otf";

        typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
        typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
        typefaceBold = Typeface.createFromAsset(getAssets(), bold);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        TextView textStatus = (TextView) findViewById(R.id.textOk);
        btn_back = (Button) findViewById(R.id.buttonBack);
        btn_save = (Button) findViewById(R.id.buttonSave);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);

        ed_adres = (EditText) findViewById(R.id.ed_adres);
        ed_city = (EditText) findViewById(R.id.ed_city);
        ed_index = (EditText) findViewById(R.id.ed_index);
        ed_country = (EditText) findViewById(R.id.ed_country);

        ed_adres.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                if (!ed_adres.getText().toString().equals("") && !ed_city.getText().toString().equals("") && !ed_index.getText().toString().equals("") && ed_country.getText().toString().equals("")){
                    btn_save.setTextColor(Color.parseColor("#808080"));
                }
                else{
                    btn_save.setTextColor(Color.parseColor("#FF0071ED"));
                    //btn_save.setClickable(false);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {


            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });

        ed_city.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                if (!ed_adres.getText().toString().equals("") && !ed_city.getText().toString().equals("") && !ed_index.getText().toString().equals("") && ed_country.getText().toString().equals("")){
                    btn_save.setTextColor(Color.parseColor("#808080"));
                }
                else{
                    btn_save.setTextColor(Color.parseColor("#FF0071ED"));
                    //btn_save.setClickable(false);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {


            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });

        ed_index.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                if (!ed_adres.getText().toString().equals("") && !ed_city.getText().toString().equals("") && !ed_index.getText().toString().equals("") && ed_country.getText().toString().equals("")){
                    btn_save.setTextColor(Color.parseColor("#808080"));
                }
                else{
                    btn_save.setTextColor(Color.parseColor("#FF0071ED"));
                    //btn_save.setClickable(false);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {


            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });

        ed_country.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                if (!ed_adres.getText().toString().equals("") && !ed_city.getText().toString().equals("") && !ed_index.getText().toString().equals("") && ed_country.getText().toString().equals("")){
                    btn_save.setTextColor(Color.parseColor("#808080"));
                }
                else{
                    btn_save.setTextColor(Color.parseColor("#FF0071ED"));
                    //btn_save.setClickable(false);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {


            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });


        textStatus.setText(R.string.adress_osnov);
        btn_back.setText(R.string.back);


        textStatus.setTypeface(typefaceBold);
        btn_back.setTypeface(typefaceMedium);
        btn_save.setTypeface(typefaceMedium);
        b1.setTypeface(typefaceRoman);
        b2.setTypeface(typefaceRoman);
        b3.setTypeface(typefaceRoman);
        b4.setTypeface(typefaceRoman);
        ed_adres.setTypeface(typefaceRoman);
        ed_city.setTypeface(typefaceRoman);
        ed_index.setTypeface(typefaceRoman);
        ed_country.setTypeface(typefaceRoman);
    }

    protected void onResume() {

        get_user_adres();
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
        switch (keycode) {

            case KeyEvent.KEYCODE_BACK:

                Intent intent18 = new Intent(this, ActivityPhoneAppleID.class);
                startActivity(intent18);
                overridePendingTransition(center_to_right, center_to_right2);

                return true;

        }
        return super.onKeyDown(keycode, e);
    }

    public void BackClick(View v) {
        Intent intent18 = new Intent(this, ActivityPhoneAppleID.class);
        startActivity(intent18);
        overridePendingTransition(center_to_right, center_to_right2);

    }

    public void SaveClick(View v)
    {

        if (!ed_adres.getText().toString().equals("") && !ed_city.getText().toString().equals("") && !ed_index.getText().toString().equals("") && !ed_country.getText().toString().equals("") ) {
            String all_adres = ed_adres.getText().toString() + "\n" + ed_city.getText().toString() + " " + ed_index.getText().toString() + " " + ed_country.getText().toString();
            if(all_adres.equals(mSettings.getString("adres", ""))){
                // btn_save.setTextColor(Color.parseColor("#808080"));
            }
            else {
                SharedPreferences.Editor editorName = mSettings.edit();
                editorName.putString("adres", ed_adres.getText().toString());
                editorName.apply();

                SharedPreferences.Editor editorSurname = mSettings.edit();
                editorSurname.putString("city", ed_city.getText().toString());
                editorSurname.apply();

                SharedPreferences.Editor editorPatr = mSettings.edit();
                editorPatr.putString("index", ed_index.getText().toString());
                editorPatr.apply();

                SharedPreferences.Editor editorPatrs = mSettings.edit();
                editorPatrs.putString("country", ed_country.getText().toString());
                editorPatrs.apply();

                SharedPreferences.Editor editorPatroo = mSettings.edit();
                editorPatroo.putString("adress_full", all_adres);
                editorPatroo.apply();

                Intent intent18 = new Intent(this, ActivityNameAppleID.class);
                startActivity(intent18);
                overridePendingTransition(center_to_right, center_to_right2);
            }

        }
        else{


        }


    }

    private void get_user_adres(){
        if(mSettings.contains("adress_full")){
            ed_adres.setText(mSettings.getString("adres", ""));
            ed_city.setText(mSettings.getString("city", ""));
            ed_index.setText(mSettings.getString("index", ""));
            ed_country.setText(mSettings.getString("country", ""));
        }
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
