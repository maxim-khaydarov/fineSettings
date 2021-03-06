package ua.mkh.settings.full;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.bluzwong.swipeback.SwipeBackActivityHelper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by Maxim Khaydarov on 01.04.2017.
 */

public class ActivityAppleID extends Activity implements View.OnClickListener{

    Typeface typefaceRoman, typefaceMedium, typefaceBold;
    SharedPreferences mSettings;

    private int PICK_IMAGE_REQUEST = 1;

    int center_to_right, center_to_right2;
    int center_to_left, center_to_left2;

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_text_size = "txt_size";
    public static final String APP_PREFERENCES_bold_text = "bold_txt";
    public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
    public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";

    TextView name, email;
    ImageView photo;
    Button b1, b2, b3, b4, b5, b6;
    Button btn_back;

    SwipeBackActivityHelper helper = new SwipeBackActivityHelper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_appleid);
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
        TextView textStatus = (TextView)findViewById(R.id.textOk);
        btn_back = (Button) findViewById(R.id.buttonBack);

        textStatus.setText(R.string.apple_id);
        btn_back.setText(R.string.app_name_desk);



        photo = (ImageView) findViewById(R.id.roundedImageView);
        photo.setOnClickListener(this);

        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);

        b1 = (Button) findViewById(R.id.b1);
        b1.setOnClickListener(this);
        b2 = (Button) findViewById(R.id.b2);
        b2.setOnClickListener(this);
        b3 = (Button) findViewById(R.id.b3);
        b3.setOnClickListener(this);
        b4 = (Button) findViewById(R.id.b4);
        b4.setOnClickListener(this);
        b5 = (Button) findViewById(R.id.b5);
        b5.setOnClickListener(this);
        b6 = (Button) findViewById(R.id.b6);


        name.setTypeface(typefaceRoman);
        email.setTypeface(typefaceRoman);

        b1.setTypeface(typefaceRoman);
        b2.setTypeface(typefaceRoman);
        b3.setTypeface(typefaceRoman);
        b4.setTypeface(typefaceRoman);
        b5.setTypeface(typefaceRoman);
        b6.setTypeface(typefaceRoman);

        textStatus.setTypeface(typefaceBold);
        btn_back.setTypeface(typefaceMedium);

    }

    protected void onResume() {
        super.onResume();

        get_user_photo();
        get_user_name();
        get_user_email();

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

    private void get_user_photo() {
        File dir = new File(Environment.getExternalStorageDirectory() + "/fineSettings/iCloud/001.png");
        if(!dir.exists()) {
        photo.setImageDrawable(getResources().getDrawable(R.drawable.default_user));
        }
        else{
            String iconsStoragePath = Environment.getExternalStorageDirectory() + "/fineSettings/iCloud/001.png";
            Bitmap bmp = BitmapFactory.decodeFile(iconsStoragePath.toString());
            photo.setImageBitmap(bmp);
        }
    }

    private void get_user_name() {
        if(!mSettings.contains("name")){
            //go to registration
            Intent n = new Intent (this, ActivityNameAppleID.class);
            startActivity(n);
            overridePendingTransition(center_to_left, center_to_left2);
        }
        else{
            name.setText(mSettings.getString("name", "Maxim") + " " + mSettings.getString("surname", "Khaydarov"));
        }

    }

    private void get_user_email(){
        if(!mSettings.contains("email")){
            //go to registration
            Intent n = new Intent (this, ActivityNameAppleID.class);
            startActivity(n);
            overridePendingTransition(center_to_left, center_to_left2);
        }
        else{
            email.setText(mSettings.getString("email", "nikto@yandex.ru"));
        }
    }

    private void save_user_photo(Bitmap bitmap){
        String iconsStoragePath = Environment.getExternalStorageDirectory() + "/fineSettings/iCloud";
        File sdIconStorageDir = new File(iconsStoragePath);

        //create storage directories, if they don't exist
        sdIconStorageDir.mkdirs();

        try {
            String filePath = sdIconStorageDir.toString() + "/001.png";
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);

            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

            ///////////////////

            //////////////////
            //choose another format if PNG doesn't suit you
            Bitmap bitmap_new_scale = scaleDown(bitmap, 300, false);
            Bitmap bitmap_new_rotate = RotateBitmap(bitmap_new_scale, 90);

            bitmap_new_rotate.compress(Bitmap.CompressFormat.PNG, 50, bos);

            bos.flush();
            bos.close();

            photo.setImageBitmap(bitmap_new_rotate);

        } catch (FileNotFoundException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());

        } catch (IOException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());

        }

    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }


    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
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

            case R.id.roundedImageView:
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                break;

            case R.id.b1:
                Intent n1111 = new Intent (this, ActivityNameAppleID.class);
                SwipeBackActivityHelper.activityBuilder(this)
                        .intent(n1111)
                        .needParallax(false)
                        .needBackgroundShadow(false)
                        .startActivity();


                break;

            case R.id.b2:
                Intent n111 = new Intent (this, ActivityPasswordAppleID.class);
                SwipeBackActivityHelper.activityBuilder(this)
                        .intent(n111)
                        .needParallax(false)
                        .needBackgroundShadow(false)
                        .startActivity();
                break;

            case R.id.b3:
                Intent n11 = new Intent (this, ActivityPlatezh.class);
                SwipeBackActivityHelper.activityBuilder(this)
                        .intent(n11)
                        .needParallax(false)
                        .needBackgroundShadow(false)
                        .startActivity();
                break;

            case R.id.b4:
                Intent n121 = new Intent (this, ActivityiCloud.class);
                SwipeBackActivityHelper.activityBuilder(this)
                        .intent(n121)
                        .needParallax(false)
                        .needBackgroundShadow(false)
                        .startActivity();
                break;

            case R.id.b5:
                Intent n1211 = new Intent (this, ActivityiTunes.class);
                SwipeBackActivityHelper.activityBuilder(this)
                        .intent(n1211)
                        .needParallax(false)
                        .needBackgroundShadow(false)
                        .startActivity();
                break;

            default:
                break;
        }

    }


@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

        Uri uri = data.getData();
        try {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            save_user_photo(bitmap);
        } catch (Exception e) {
        e.printStackTrace();
        }
        }
        }
}
