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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
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
    Button b1, b2, b3;
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

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        TextView textStatus = (TextView) findViewById(R.id.textOk);
        btn_back = (Button) findViewById(R.id.buttonBack);
        btn_save = (Button) findViewById(R.id.buttonSave);

        textStatus.setText(R.string.info_platezh);
        btn_back.setText(R.string.back);
        btn_save.setText(R.string.next);

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
            String str=""; int strOldlen=0;
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

                str = ed_card.getText().toString();
                int strLen = str.length();


                if(strOldlen<strLen) {

                    if (strLen > 0) {
                        if (strLen == 4 || strLen == 9 || strLen == 14) {

                            str=str+" ";

                            ed_card.setText(str);
                            ed_card.setSelection(ed_card.getText().length());

                        }else{

                            if(strLen==5){
                                if(!str.contains(" ")){
                                    String tempStr=str.substring(0,strLen-1);
                                    tempStr +=" "+str.substring(strLen-1,strLen);
                                    ed_card.setText(tempStr);
                                    ed_card.setSelection(ed_card.getText().length());
                                }
                            }
                            if(strLen==10){
                                if(str.lastIndexOf(" ")!=9){
                                    String tempStr=str.substring(0,strLen-1);
                                    tempStr +=" "+str.substring(strLen-1,strLen);
                                    ed_card.setText(tempStr);
                                    ed_card.setSelection(ed_card.getText().length());
                                }
                            }
                            if(strLen == 15){
                                if(str.lastIndexOf(" ")!=14){
                                    String tempStr=str.substring(0,strLen-1);
                                    tempStr +=" "+str.substring(strLen-1,strLen);
                                    ed_card.setText(tempStr);
                                    ed_card.setSelection(ed_card.getText().length());
                                }
                            }
                            strOldlen = strLen;
                        }
                    }else{
                        return;
                    }

                }else{
                    strOldlen = strLen;
                }




        }
        });


        textStatus.setTypeface(typefaceBold);
        btn_back.setTypeface(typefaceMedium);
        btn_save.setTypeface(typefaceMedium);
        ed_name.setTypeface(typefaceRoman);
        ed_surname.setTypeface(typefaceRoman);
        ed_card.setTypeface(typefaceRoman);
        b1.setTypeface(typefaceRoman);
        b2.setTypeface(typefaceRoman);
        b3.setTypeface(typefaceRoman);
    }



    @Override
    protected void onResume() {
        super.onResume();

        setupUI(findViewById(R.id.parent));

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


            default:
                break;
        }

    }

    public void SaveClick(View v)
    {

        if (!ed_name.getText().toString().equals("") && !ed_surname.getText().toString().equals("") || !ed_card.getText().toString().equals("") ) {
            SharedPreferences.Editor editorName = mSettings.edit();
            editorName.putString("name_card", ed_name.getText().toString());
            editorName.apply();

            SharedPreferences.Editor editorSurname = mSettings.edit();
            editorSurname.putString("surname_card", ed_surname.getText().toString());
            editorSurname.apply();

            SharedPreferences.Editor editorCard = mSettings.edit();
            editorCard.putString("number_card", ed_card.getText().toString());
            editorCard.apply();


            onBackPressed();
            helper.finish();
            }


        }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(ActivityAddCard.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    }

