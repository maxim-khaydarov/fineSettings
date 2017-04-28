package ua.mkh.settings.full;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.bluzwong.swipeback.SwipeBackActivityHelper;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by 1 on 28.04.2017.
 */

public class ActivityMail extends Activity implements View.OnClickListener {

    Typeface typefaceRoman, typefaceMedium, typefaceBold;
    SharedPreferences mSettings;


    int center_to_right, center_to_right2;
    int center_to_left, center_to_left2;

    public static final String APP_PREFERENCES = "mysettings";

    TextView textStatus;
    Button buttonBack;
    Button account;
    SharedPreferences.Editor editor ;
    SwipeBackActivityHelper helper = new SwipeBackActivityHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mail);
        String roman = "fonts/Regular.otf";
        String medium = "fonts/Medium.otf";
        String bold = "fonts/Bold.otf";

        helper.setEdgeMode(true)
                .setParallaxMode(true)
                .setParallaxRatio(5)
                .setNeedBackgroundShadow(false)
                .init(this);

        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);


        typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
        typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
        typefaceBold = Typeface.createFromAsset(getAssets(), bold);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        editor = mSettings.edit();

        account = (Button) findViewById(R.id.ButtonAccount);
        account.setOnClickListener(this);

        textStatus = (TextView) findViewById(R.id.textOk);

        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setText(R.string.app_name);


        buttonBack.setTypeface(typefaceBold);


        textStatus.setTypeface(typefaceMedium);
        textStatus.setText(R.string.mail);
    }


    protected void onResume() {
        super.onResume();


    }

    public void BackClick(View v) {
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

            case R.id.ButtonAccount:

                Intent intent = new Intent(Settings.ACTION_SYNC_SETTINGS);

                //startActivity(new Intent(Settings.ACTION_SYNC_SETTINGS));

                SwipeBackActivityHelper.activityBuilder(this)
						 .intent(intent)
						 .needParallax(false)
						 .needBackgroundShadow(false)
						 .startActivity();

                break;

        default:
        break;
    }


}



    }
