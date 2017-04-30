package ua.mkh.settings.full;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.suke.widget.SwitchButton;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by 1 on 30.04.2017.
 */

public class ActivityContact extends Activity implements View.OnClickListener {

    SharedPreferences mSettings;
    com.suke.widget.SwitchButton tg1;

    public static final String APP_PREFERENCES = "mysettings";

    TextView textStatus;
    Button buttonBack;
    Button account;


    SharedPreferences.Editor editor ;
    SwipeBackActivityHelper helper = new SwipeBackActivityHelper();

    private void theme() {
        try {

            SharedPreferences settings =
                    getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

            String fontSizePref = settings.getString("txt_size", "Medium");
            Log.e("!!", fontSizePref);

            int themeID = R.style.Medium;
            if (fontSizePref.contains("Small")) {
                themeID = R.style.Small;
            }
            else if (fontSizePref.contains("Large")) {
                themeID = R.style.Large;
            }
            else if (fontSizePref.contains("xLarge")){
                themeID = R.style.XLarge;
            }

            setTheme(themeID);



        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        theme();

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //int themeID = R.style.XLarge;
        //setTheme(themeID);


        setContentView(R.layout.activity_contact);

        helper.setEdgeMode(true)
                .setParallaxMode(true)
                .setParallaxRatio(5)
                .setNeedBackgroundShadow(false)
                .init(this);

        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        editor = mSettings.edit();

        tg1 = (com.suke.widget.SwitchButton) findViewById(R.id.tg_1);
        account = (Button) findViewById(R.id.ButtonAccount);
        account.setOnClickListener(this);

        textStatus = (TextView) findViewById(R.id.textOk);

        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setText(R.string.app_name);

        textStatus.setText(R.string.apps_contact);

        toggle_button();
    }

    private void get_tg() {
        tg1.setChecked(mSettings.getBoolean("tg1_contact", true));
    }

    private void toggle_button() {
        tg1.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (tg1.isChecked()) {
                    editor.putBoolean("tg1_contact", true).apply();
                } else {
                    editor.putBoolean("tg1_contact", false).apply();
                }
            }
        });
    }


    protected void onResume() {
        super.onResume();

        get_tg();

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
                SwipeBackActivityHelper.activityBuilder(this)
                        .intent(intent)
                        .needParallax(false)
                        .needBackgroundShadow(false)
                        .startActivity();

                break;

        }
    }

        }
