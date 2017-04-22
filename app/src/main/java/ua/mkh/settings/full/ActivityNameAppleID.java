package ua.mkh.settings.full;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.aigestudio.wheelpicker.WheelPicker;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by 1 on 01.04.2017.
 */

public class ActivityNameAppleID extends Activity implements View.OnClickListener, WheelPicker.OnItemSelectedListener{

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
    Button name, email, phone, adress, button_edit_phone_email;
    TextView birthday, edit_phone_email, name3;
    LinearLayout LinearAddPhoneEmail;
    ImageView info;
    int day = 0;
    int month = 0;
    String year = null;
    ToggleButton tg1, tg2;

    SwipeBackActivityHelper helper = new SwipeBackActivityHelper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_nameappleid);
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

        textStatus.setText(R.string.name_appleid);
        btn_back.setText(R.string.apple_id);


        name = (Button) findViewById(R.id.name);
        name.setOnClickListener(this);
        email = (Button) findViewById(R.id.email);
        phone = (Button) findViewById(R.id.phone);
        adress = (Button) findViewById(R.id.adress);
        adress.setOnClickListener(this);
        edit_phone_email = (TextView) findViewById(R.id.textView19);
        edit_phone_email.setOnClickListener(this);
        button_edit_phone_email = (Button) findViewById(R.id.button_edit_phone_email);
        button_edit_phone_email.setOnClickListener(this);

        tg1 = (ToggleButton) findViewById(R.id.ToggleButton013);
        tg1.setOnClickListener(this);
        tg2 = (ToggleButton) findViewById(R.id.ToggleButton01);
        tg2.setOnClickListener(this);

        birthday = (TextView) findViewById(R.id.birthday);
        name3 = (Button) findViewById(R.id.name3);
        name3.setOnClickListener(this);
        info = (ImageView) findViewById(R.id.imageView22);
        info.setOnClickListener(this);

        LinearAddPhoneEmail = (LinearLayout) findViewById(R.id.LinearAddPhoneEmail);
        LinearAddPhoneEmail.setVisibility(View.GONE);


        textStatus.setTypeface(typefaceBold);
        btn_back.setTypeface(typefaceMedium);
        name.setTypeface(typefaceRoman);
        email.setTypeface(typefaceRoman);
        phone.setTypeface(typefaceRoman);
        edit_phone_email.setTypeface(typefaceRoman);
        birthday.setTypeface(typefaceRoman);

    }

    @Override
    protected void onResume() {
        super.onResume();
        get_name_user();
        get_email_user();
        get_phone_user();
        get_birthday_user();
        get_adres_user();

        tg1.setChecked(mSettings.getBoolean("obyava", true));
        tg2.setChecked(mSettings.getBoolean("apple_music", true));


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
            phone.setText(Html.fromHtml(t2), TextView.BufferType.SPANNABLE);
        }

    }

    private void get_birthday_user (){
        birthday.setText(mSettings.getString("birthday_date", ""));
    }


    private void get_adres_user(){
        if(mSettings.contains("adress_full")){
            adress.setText(mSettings.getString("adress_full", ""));
        }
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.name:
                Intent n1111 = new Intent (this, ActivityName.class);
                SwipeBackActivityHelper.activityBuilder(this)
                        .intent(n1111)
                        .needParallax(false)
                        .needBackgroundShadow(false)
                        .startActivity();
                break;

            case R.id.textView19:
                if(LinearAddPhoneEmail.getVisibility() == View.GONE) {
                    LinearAddPhoneEmail.setVisibility(View.VISIBLE);
                    edit_phone_email.setText(getText(R.string.ready));
                }
                else{
                    LinearAddPhoneEmail.setVisibility(View.GONE);
                    edit_phone_email.setText(getText(R.string.edit));
                }
                break;

            case R.id.button_edit_phone_email:
                open_box_phone_email();

                break;

            case R.id.imageView22:
                open_box_phone();
                break;

            case R.id.adress:
                Intent n111 = new Intent (this, ActivityAdress.class);
                SwipeBackActivityHelper.activityBuilder(this)
                        .intent(n111)
                        .needParallax(false)
                        .needBackgroundShadow(false)
                        .startActivity();
                break;

            case R.id.name3:

                open_box_birthday();
                break;

            case R.id.ToggleButton013:
                SharedPreferences.Editor editorName = mSettings.edit();
                if(tg1.isChecked()){
                    editorName.putBoolean("obyava", true);
                }
                else{
                    editorName.putBoolean("obyava", false);
                }
                editorName.apply();
                break;

            case R.id.ToggleButton01:
                SharedPreferences.Editor editorNamew = mSettings.edit();
                if(tg2.isChecked()){
                    editorNamew.putBoolean("apple_music", true);
                }
                else{
                    editorNamew.putBoolean("apple_music", false);
                }
                editorNamew.apply();
                break;
            default:
                break;
        }

    }

    private void open_box_phone() {
        final Dialog Activation = new Dialog(ActivityNameAppleID.this,android.R.style.Theme_Translucent);
        Activation.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Activation.setContentView(R.layout.dialog_2_button);

        // set the custom dialog components - text, image and button

        Button dialogButton = (Button) Activation.findViewById(R.id.dialogButtonOK);
        Button dialogButtonCancel = (Button) Activation.findViewById(R.id.dialogButtonCancel);
        TextView text = (TextView)Activation.findViewById(R.id.textView1);
        TextView textver = (TextView)Activation.findViewById(R.id.textView2);

        dialogButton.setTypeface(typefaceMedium);
        dialogButtonCancel.setTypeface(typefaceRoman);
        textver.setTypeface(typefaceRoman);
        text.setTypeface(typefaceBold);



        dialogButtonCancel.setText(R.string.more);
        text.setText(R.string.info_phone_del_bold);
        textver.setText(R.string.info_phone_del_text);

        //textver.setTextSize(15);

        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Activation.dismiss();
            }
        });
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://selfsolve.apple.com/deregister-imessage/"));
                startActivity(browserIntent);
                Activation.dismiss();
            }
        });
        Activation.show();
    }

    private void open_box_phone_email() {
        final Dialog dialog = new Dialog(ActivityNameAppleID.this,android.R.style.Theme_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_menu);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        Button ButtonInfo = (Button)dialog.getWindow().findViewById(R.id.button1);
        Button ButtonMenuCancel = (Button)dialog.getWindow().findViewById(R.id.ButtonMenuCancel);
        Button ButtonMenuSettings = (Button)dialog.getWindow().findViewById(R.id.ButtonMenuSettings);
        ButtonMenuSettings.setTypeface(typefaceRoman);
        ButtonMenuCancel.setTypeface(typefaceBold);
        ButtonInfo.setTypeface(typefaceRoman);
        ButtonInfo.setTextSize(16);
        ButtonMenuSettings.setTextSize(16);
        ButtonInfo.setTextColor(Color.parseColor("#FF0071ED"));
        ButtonMenuSettings.setTextColor(Color.parseColor("#FF0071ED"));

        ButtonInfo.setText(R.string.edit_phone);
        ButtonMenuSettings.setText(R.string.edit_email);
        ButtonMenuCancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }});

        ButtonMenuSettings.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(ActivityNameAppleID.this, ActivityEmailAppleID.class);
                startActivity(intent);
                overridePendingTransition(center_to_left, center_to_left2);
            }});

        ButtonInfo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(ActivityNameAppleID.this, ActivityPhoneAppleID.class);
                startActivity(intent);
                overridePendingTransition(center_to_left, center_to_left2);
            }});

        dialog.show();

    }

    private void open_box_birthday() {
        final Dialog dialog = new Dialog(ActivityNameAppleID.this,android.R.style.Theme_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_birthday);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;



        WheelPicker wheelPickerday = (WheelPicker) dialog.findViewById(R.id.day);
        wheelPickerday.setOnItemSelectedListener(this);
        WheelPicker wheelPickermonth = (WheelPicker) dialog.findViewById(R.id.month);
        wheelPickermonth.setOnItemSelectedListener(this);
        WheelPicker wheelPickeryear = (WheelPicker) dialog.findViewById(R.id.year);
        wheelPickeryear.setOnItemSelectedListener(this);

        Button btn_save = (Button) dialog.findViewById(R.id.buttonSave);
        btn_save.setOnClickListener(ActivityNameAppleID.this);
        Button back = (Button) dialog.findViewById(R.id.buttonBack);
        TextView status = (TextView) dialog.findViewById(R.id.textOk);

        btn_save.setTypeface(typefaceMedium);
        btn_back.setTypeface(typefaceMedium);
        status.setTypeface(typefaceBold);
        status.setText(R.string.birthday_appleid_name);

        Calendar calendar = Calendar.getInstance();
        final int year_curent = calendar.get(Calendar.YEAR);
        final int month_curent = calendar.get(Calendar.MONTH);
        final int day_curent = calendar.get(Calendar.DAY_OF_MONTH);

        btn_save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(day ==0 || month == 0 || year == null) {
                    if (mSettings.contains("birthday_date")) {
                        String dtStart = mSettings.getString("birthday_date", "");
                        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                        try {
                            Date date = format.parse(dtStart);
                            System.out.println(date);
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(date);

                            int months = cal.get(Calendar.MONTH);
                            int days = cal.get(Calendar.DAY_OF_MONTH);
                            int years = cal.get(Calendar.YEAR);

                            if (day == 0) {
                                day = days;
                            }
                            if (month == 0) {
                                month = months+1;
                            }
                            if (year == null) {
                                year = String.valueOf(years);
                            }

                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                    else{
                        if (day == 0) {
                            day = day_curent;
                        }
                        if (month == 0) {
                            month = month_curent;
                        }
                        if (year == null) {
                            year = String.valueOf(year_curent);
                        }
                    }
                }

                    String dtStart;
                    if (month < 10) {
                        dtStart = String.valueOf(day) + "." + "0" + String.valueOf(month) + "." + year;
                    } else {
                        dtStart = String.valueOf(day) + "." + String.valueOf(month) + "." + year;
                    }
                    //final String[] unit = getResources().getStringArray(R.array.array_month);
                    //int digitGroups = month;

                    SharedPreferences.Editor editorName = mSettings.edit();
                    editorName.putString("birthday_date", dtStart);
                    editorName.apply();
                    Log.e("btn_save", dtStart);
                    get_birthday_user();


                dialog.dismiss();

            }});
        btn_back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }});







        List<String> data_day = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
                data_day.add(String.valueOf(i));
        }
        wheelPickerday.setData(data_day);



        String [] fiilliste= getResources().getStringArray(R.array.array_month);
        List<String> stringList = new ArrayList<String>(Arrays.asList(fiilliste));
        wheelPickermonth.setData(stringList);


        List<String> data_year = new ArrayList<>();
        for (int i = 1900; i < year_curent+1; i++) {
            data_year.add(String.valueOf(i));
        }
        wheelPickeryear.setData(data_year);



        if(!mSettings.contains("birthday_date")) {
            wheelPickeryear.setSelectedItemPosition(year_curent - 1900);
            wheelPickermonth.setSelectedItemPosition(month_curent);
            wheelPickerday.setSelectedItemPosition(day_curent - 1);
        }
        else{
            String dtStart = mSettings.getString("birthday_date", "");
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            try {
                Date date = format.parse(dtStart);
                System.out.println(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                int months = cal.get(Calendar.MONTH);
                int days = cal.get(Calendar.DAY_OF_MONTH)-1;
                int  years = cal.get(Calendar.YEAR);

                wheelPickeryear.setSelectedItemPosition(years-1900);
                wheelPickermonth.setSelectedItemPosition(months);
                wheelPickerday.setSelectedItemPosition(days);

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        dialog.show();

    }

    @Override

    public void onItemSelected(WheelPicker picker, Object data, int position) {



        switch (picker.getId()) {

            case R.id.day:
                day = position+1;

                break;

            case R.id.month:
                month = position+1;
                break;

            case R.id.year:
                year = data.toString();

        }

        //Toast.makeText(this, day + "." + month + "." + year, Toast.LENGTH_SHORT).show();

    }



    }



