package ua.mkh.settings.full;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
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
    EditText ed1;
    SwipeBackActivityHelper helper = new SwipeBackActivityHelper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_phone_appleid);
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

        btn_country = (Button) findViewById(R.id.btn_contry);
        btn_country.setOnClickListener(this);
        btn_phone = (Button) findViewById(R.id.btn_phone);
        txt_country = (TextView) findViewById(R.id.textView25);
        ed1 = (EditText) findViewById(R.id.editText);

        btn_save.setTextColor(Color.parseColor("#808080"));


        ed1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text

                if (!ed1.getText().toString().equals("") && txt_country.getText().toString().contains("+") ){
                    String code = txt_country.getText().toString();
                    String number = ed1.getText().toString();
                    String result_code = code.substring(0, code.indexOf(" "));
                    String all = result_code + " " + number;
                    if(!mSettings.getString("phone", "").equals(all)){
                        btn_save.setTextColor(Color.parseColor("#FF0071ED"));
                    }
                    else {
                        btn_save.setTextColor(Color.parseColor("#808080"));
                        //btn_save.setClickable(true);
                    }

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

        textStatus.setText(R.string.edit_phone);
        btn_back.setText(R.string.back);







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

            get_user_phone();
            setupUI(findViewById(R.id.parent));

            if(mSettings.contains("phone_country_text")){
                txt_country.setText(mSettings.getString("phone_country_text", "+2222 (Тилимилитряндия)"));
            }

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


        private void get_user_phone(){
            if(mSettings.contains("phone_number")){
                ed1.setText(mSettings.getString("phone_number", "00"));
                ed1.setSelection(ed1.getText().length());
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

    public void SaveClick(View v)
    {
        if (!ed1.getText().toString().equals("") && txt_country.getText().toString().contains("+") ){
        String code = txt_country.getText().toString();
        String number = ed1.getText().toString();
        String result_code = code.substring(0, code.indexOf(" "));
        String all = result_code + " " + number;
        if(!mSettings.getString("phone", "").equals(all)){
            SharedPreferences.Editor editorName = mSettings.edit();
            editorName.putString("phone", all);
            editorName.apply();

            editorName.putString("phone_number", number);
            editorName.apply();
            BackClick(v);
        }


      }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.btn_contry:
                Intent n11 = new Intent (this, ActivityPhone.class);
                SwipeBackActivityHelper.activityBuilder(this)
                        .intent(n11)
                        .needParallax(false)
                        .needBackgroundShadow(false)
                        .startActivity();
                break;


            default:
                break;
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
                    hideSoftKeyboard(ActivityPhoneAppleID.this);
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
