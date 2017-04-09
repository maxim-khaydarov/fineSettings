package ua.mkh.settings.full;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.bluzwong.swipeback.SwipeBackActivityHelper;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by 1 on 09.04.2017.
 */

public class ActivityPlatezh extends Activity implements View.OnClickListener{



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
    Button b1, b2;
    TextView txt1, txt2, txt3, txt4;

    SwipeBackActivityHelper helper = new SwipeBackActivityHelper();
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_platezhappleid);
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

    textStatus.setText(R.string.platezh_dostavka);
    btn_back.setText(R.string.apple_id);

    b1 = (Button) findViewById(R.id.add_card);
    b1.setOnClickListener(this);
    b2 = (Button) findViewById(R.id.add_adres);
    b2.setOnClickListener(this);

    txt1 = (TextView) findViewById(R.id.textView18);
    txt2 = (TextView) findViewById(R.id.textView31);
    txt3 = (TextView) findViewById(R.id.textView30);
    txt4 = (TextView) findViewById(R.id.textView28);

    textStatus.setTypeface(typefaceBold);
    btn_back.setTypeface(typefaceMedium);
    b1.setTypeface(typefaceRoman);
    b2.setTypeface(typefaceRoman);
    txt1.setTypeface(typefaceRoman);
    txt2.setTypeface(typefaceRoman);
    txt3.setTypeface(typefaceRoman);
    txt4.setTypeface(typefaceRoman);
}


    @Override
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

            case R.id.add_card:
                Intent add = new Intent(this, ActivityAddCard.class);
                SwipeBackActivityHelper.activityBuilder(this)
                        .intent(add)
                        .needParallax(false)
                        .needBackgroundShadow(false)
                        .startActivity();
                break;

            default:
                break;
        }

    }
}