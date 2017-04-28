package ua.mkh.settings.full;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.suke.widget.SwitchButton;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by 1 on 25.04.2017.
 */

public class ActivityiTunes extends Activity implements View.OnClickListener {

    Typeface typefaceRoman, typefaceMedium, typefaceBold;
    SharedPreferences mSettings;


    int center_to_right, center_to_right2;
    int center_to_left, center_to_left2;

    public static final String APP_PREFERENCES = "mysettings";

    TextView textStatus;
    Button buttonBack;
    Button b1;
    com.suke.widget.SwitchButton tg_sota_data, tg_music, tg_apps, tg_book, tg_update;
    SharedPreferences.Editor editor ;
    SwipeBackActivityHelper helper = new SwipeBackActivityHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_itunes);
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

        textStatus = (TextView) findViewById(R.id.textOk);

        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setText(R.string.apple_id);

        tg_music = (com.suke.widget.SwitchButton) findViewById(R.id.tg_music);
        tg_music.setOnClickListener(this);
        tg_apps = (com.suke.widget.SwitchButton) findViewById(R.id.tg_apps);
        tg_apps.setOnClickListener(this);
        tg_book = (com.suke.widget.SwitchButton) findViewById(R.id.tg_book);
        tg_book.setOnClickListener(this);
        tg_update = (com.suke.widget.SwitchButton) findViewById(R.id.tg_update);
        tg_update.setOnClickListener(this);

        tg_sota_data = (com.suke.widget.SwitchButton)
                findViewById(R.id.tg_sota_data);
        tg_sota_data.setOnClickListener(this);

        b1 = (Button) findViewById(R.id.Button01);
        b1.setOnClickListener(this);

        buttonBack.setTypeface(typefaceBold);


        textStatus.setTypeface(typefaceMedium);
        textStatus.setText(R.string.itunes_appstore);

        toggleButton();

    }

    protected void onResume() {
        super.onResume();

        b1.setText(getString(R.string.apple_id) + ": " + mSettings.getString("email", ""));

        get_tg();

    }

    private void toggleButton(){

        tg_music.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(tg_music.isChecked()) {
                    editor.putBoolean("itunes_music", true).apply();
                }
                else {
                    editor.putBoolean("itunes_music", false).apply();
                }
            }
        });
        ////////////////////////////
        tg_apps.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(tg_apps.isChecked()) {
                    editor.putBoolean("itunes_apps", true).apply();
                }
                else {
                    editor.putBoolean("itunes_apps", false).apply();
                }
            }
        });
        ////////////////////////////
        tg_book.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(tg_book.isChecked()) {
                    editor.putBoolean("itunes_book", true).apply();
                }
                else {
                    editor.putBoolean("itunes_book", false).apply();
                }
            }
        });
        ////////////////////////////
        tg_update.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(tg_update.isChecked()) {
                    editor.putBoolean("itunes_update", true).apply();
                }
                else {
                    editor.putBoolean("itunes_update", false).apply();
                }
            }
        });
        ////////////////////////////
        tg_sota_data.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(tg_sota_data.isChecked()) {
                    editor.putBoolean("itunes_sota_data", true).apply();
                }
                else {
                    editor.putBoolean("itunes_sota_data", false).apply();
                }
            }
        });
        ////////////////////////////
    }

    private void get_tg() {
        tg_music.setChecked(mSettings.getBoolean("itunes_music", true));
        tg_apps.setChecked(mSettings.getBoolean("itunes_apps", true));
        tg_book.setChecked(mSettings.getBoolean("itunes_book", true));
        tg_update.setChecked(mSettings.getBoolean("itunes_update", true));
        tg_sota_data.setChecked(mSettings.getBoolean("itunes_sota_data", true));
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

            case R.id.Button01:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://appleid.apple.com/#!&page=signin"));
                SwipeBackActivityHelper.activityBuilder(this)
                        .intent(browserIntent)
                        .needParallax(false)
                        .needBackgroundShadow(false)
                        .startActivity();

                break;
            default:
                break;
        }

    }
}
