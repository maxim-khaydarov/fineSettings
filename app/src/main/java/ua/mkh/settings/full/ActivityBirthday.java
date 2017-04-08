package ua.mkh.settings.full;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.aigestudio.wheelpicker.WheelPicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import kankan.wheel.widget.adapters.NumericWheelAdapter;
import kankan.wheel.widget.adapters.WheelViewAdapter;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by 1 on 07.04.2017.
 */

public class ActivityBirthday extends Activity implements WheelPicker.OnItemSelectedListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_birthday);
        String roman = "fonts/Regular.otf";
        String medium = "fonts/Medium.otf";
        String bold = "fonts/Bold.otf";



        typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
        typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
        typefaceBold = Typeface.createFromAsset(getAssets(), bold);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        TextView textStatus = (TextView)findViewById(R.id.textOk);
        btn_back = (Button) findViewById(R.id.buttonBack);
        btn_save = (Button) findViewById(R.id.buttonSave);

        textStatus.setText(R.string.name_name);
        btn_back.setText(R.string.back);
        btn_save.setText(R.string.ready);

        textStatus.setTypeface(typefaceBold);
        btn_back.setTypeface(typefaceMedium);
        btn_save.setTypeface(typefaceMedium);


        WheelPicker wheelPickerday = (WheelPicker) findViewById(R.id.day);
        wheelPickerday.setOnItemSelectedListener(this);
        WheelPicker wheelPickermonth = (WheelPicker) findViewById(R.id.month);
        wheelPickermonth.setOnItemSelectedListener(this);
        WheelPicker wheelPickeryear = (WheelPicker) findViewById(R.id.year);
        wheelPickeryear.setOnItemSelectedListener(this);

        wheelPickeryear.setTypeface(typefaceRoman);
        wheelPickermonth.setTypeface(typefaceRoman);
        wheelPickerday.setTypeface(typefaceRoman);


        List<String> data_day = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            if(i<10){
                data_day.add("0" + String.valueOf(i));
            }
            else
            data_day.add(String.valueOf(i));
        }
        wheelPickerday.setData(data_day);



        String [] fiilliste= getResources().getStringArray(R.array.array_month);
        List<String> stringList = new ArrayList<String>(Arrays.asList(fiilliste));

        wheelPickermonth.setData(stringList);


        List<String> data_year = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        for (int i = 1970; i < year+1; i++) {

                data_year.add(String.valueOf(i));
        }
        wheelPickeryear.setData(data_year);



    }

    @Override

    public void onItemSelected(WheelPicker picker, Object data, int position) {

        String day = "";
        String month = "";
        String year = "";

        switch (picker.getId()) {

            case R.id.day:
                day = String.valueOf(data);

                break;

            case R.id.month:
                month = String.valueOf(data);
                break;

            case R.id.year:
                year = String.valueOf(data);

        }

        Toast.makeText(this, day + "." + month + "." + year, Toast.LENGTH_SHORT).show();

    }





    protected void onResume() {
        super.onResume();

        int speed = mSettings.getInt(APP_PREFERENCES_ANIM_SPEED, 1);
        if (speed == 1){
            center_to_right = R.anim.slide_center_to_right_short;
            center_to_right2 = R.anim.slide_center_to_right2_short;
            center_to_left = R.anim.slide_center_to_left_short;
            center_to_left2 = R.anim.slide_center_to_left2_short;
        }
        if (speed == 2){
            center_to_right = R.anim.slide_center_to_right_medium;
            center_to_right2 = R.anim.slide_center_to_right2_medium;
            center_to_left = R.anim.slide_center_to_left_medium;
            center_to_left2 = R.anim.slide_center_to_left2_medium;
        }
        if (speed == 3){
            center_to_right = R.anim.slide_center_to_right_long;
            center_to_right2 = R.anim.slide_center_to_right2_long;
            center_to_left = R.anim.slide_center_to_left_long;
            center_to_left2 = R.anim.slide_center_to_left2_long;
        }



        if (mSettings.contains(APP_PREFERENCES_bold_text)) {
            Boolean bold = mSettings.getBoolean(APP_PREFERENCES_bold_text, true);
            if (bold == true){


            }
        }

        if (mSettings.contains(APP_PREFERENCES_text_size)) {
            String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
            if (size .contains( "Small")){

            }
            if (size .contains( "Normal")){

            }
            if (size .contains( "Large")){

            }
            if (size .contains( "xLarge")){

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


                Intent intent18 = new Intent(this, ActivityNameAppleID.class);
                startActivity(intent18);
                overridePendingTransition(center_to_right, center_to_right2);





    }

}
