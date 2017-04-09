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
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.bluzwong.swipeback.SwipeBackActivityHelper;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by 1 on 09.04.2017.
 */

public class ActivityAddCard extends Activity implements View.OnClickListener{



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
    //Button b1, b2;
    //TextView txt1, txt2, txt3, txt4;
    EditText ed_name, ed_surname, ed_card;

    SwipeBackActivityHelper helper = new SwipeBackActivityHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_card_appleid);
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
        TextView textStatus = (TextView) findViewById(R.id.textOk);
        btn_back = (Button) findViewById(R.id.buttonBack);
        btn_save = (Button) findViewById(R.id.buttonSave);

        textStatus.setText(R.string.info_platezh);
        btn_back.setText(R.string.back);
        btn_save.setText(R.string.ready);

        ed_name = (EditText) findViewById(R.id.ed_name);
        ed_surname = (EditText) findViewById(R.id.ed_surname);
        ed_card = (EditText) findViewById(R.id.ed_card);

        btn_save.setTextColor(Color.parseColor("#808080"));

        ed_name.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                if (!ed_name.getText().toString().equals("") && !ed_surname.getText().toString().equals("") && !ed_card.getText().toString().equals("")){
                    btn_save.setTextColor(Color.parseColor("#FF0071ED"));

                }
                else{
                    btn_save.setTextColor(Color.parseColor("#808080"));
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

        ed_surname.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                if (!ed_name.getText().toString().equals("") && !ed_surname.getText().toString().equals("") && !ed_card.getText().toString().equals("")){
                    btn_save.setTextColor(Color.parseColor("#FF0071ED"));

                }
                else{
                    btn_save.setTextColor(Color.parseColor("#808080"));
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

        ed_card.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                if (!ed_name.getText().toString().equals("") && !ed_surname.getText().toString().equals("") && !ed_card.getText().toString().equals("")){
                    btn_save.setTextColor(Color.parseColor("#FF0071ED"));

                }
                else{
                    btn_save.setTextColor(Color.parseColor("#808080"));
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


        textStatus.setTypeface(typefaceBold);
        btn_back.setTypeface(typefaceMedium);
        btn_save.setTypeface(typefaceMedium);
        ed_name.setTypeface(typefaceRoman);
        ed_surname.setTypeface(typefaceRoman);
        ed_card.setTypeface(typefaceRoman);

    }

    @Override
    protected void onResume() {
        super.onResume();

        ed_name.setText(mSettings.getString("name_card", mSettings.getString("name", "")));
        ed_surname.setText(mSettings.getString("surname_card", mSettings.getString("surname", "")));
        ed_card.setText(mSettings.getString("number_card", ""));

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

    public void BackClick(View v)
    {
        helper.finish();

    }

    @Override
    public void onBackPressed() {
        helper.finish();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {


            default:
                break;
        }

    }

    public void SaveClick(View v)
    {

        if (!ed_name.getText().toString().equals("") && !ed_surname.getText().toString().equals("") ) {
            SharedPreferences.Editor editorName = mSettings.edit();
            editorName.putString("name_card", ed_name.getText().toString());
            editorName.apply();

            SharedPreferences.Editor editorSurname = mSettings.edit();
            editorSurname.putString("surname_card", ed_surname.getText().toString());
            editorSurname.apply();

            SharedPreferences.Editor editorCard = mSettings.edit();
            editorCard.putString("number_card", ed_card.getText().toString());
            editorCard.apply();



            helper.finish();
            }


        }



    }

