package ua.mkh.settings.full;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar.LayoutParams;
 
public class ActivityApps extends ListActivity {
    private PackageManager packageManager = null;
    private List<ApplicationInfo> applist = null;
    private ApplicationAdapterUsage listadaptor = null;
    Button Button08, Button09, btn_back;
    TextView text_app_main, usage, aviable;
    int number = 0;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
    public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	
    int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   SharedPreferences mSettings;
	   
	   
	   Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	   
	   ProgressBar pg1;
	   
	   ListView u;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_apps_osnova);
        
        String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.otf";
		Typeface typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		Typeface typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
        text_app_main = (TextView)findViewById(R.id.textOk);
	      text_app_main.setText(R.string.manage_storage);
	      btn_back = (Button) findViewById(R.id.buttonBack);
			btn_back.setText(R.string.button_usage);
	      
	      usage = (TextView) findViewById(R.id.TextView07);
	      aviable = (TextView) findViewById(R.id.TextView08);
	      
	      Button08 = (Button) findViewById(R.id.Button08);
	      Button09 = (Button) findViewById(R.id.Button09);
	      
	      u = getListView();
	      
	      pg1 = (ProgressBar) findViewById(R.id.progressBar1);
	      pg1.setVisibility(View.GONE);
	      
	      btn_back.setTypeface(typefaceMedium);
	      text_app_main.setTypeface(typefaceBold);
	      usage.setTypeface(typefaceRoman);
	      aviable.setTypeface(typefaceRoman);
	      Button08.setTypeface(typefaceRoman);
	      Button09.setTypeface(typefaceRoman);
	      
	      Intent intent = getIntent();
	      
	      usage.setText(intent.getStringExtra("used"));
	      aviable.setText(intent.getStringExtra("aviable"));
	      
	      
	      mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

	      
	      
 
        packageManager = getPackageManager();
 
        new LoadApplications().execute();
    }
 
    protected void onResume() {
        super.onResume();
        
        
        memory();

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
				// Получаем число из настроек
	        	 Boolean bold = mSettings.getBoolean(APP_PREFERENCES_bold_text, true);
				if (bold == true){
					text_app_main.setTypeface(typefaceBold);
	    			btn_back.setTypeface(typefaceBold);
	    			usage.setTypeface(typefaceBold);
	    		      aviable.setTypeface(typefaceBold);
	    		      Button08.setTypeface(typefaceBold);
	    		      Button09.setTypeface(typefaceBold);
					
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {

	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					usage.setTextSize(13);
	    		      aviable.setTextSize(13);
	    		      Button08.setTextSize(13);
	    		      Button09.setTextSize(13);
				}
				if (size .contains( "Normal")){
					usage.setTextSize(15);
	    		      aviable.setTextSize(15);
	    		      Button08.setTextSize(15);
	    		      Button09.setTextSize(15);
				}
				if (size .contains( "Large")){
					usage.setTextSize(18);
	    		      aviable.setTextSize(18);
	    		      Button08.setTextSize(18);
	    		      Button09.setTextSize(18);
				}
				if (size .contains( "xLarge")){
					usage.setTextSize(20);
	    		      aviable.setTextSize(20);
	    		      Button08.setTextSize(20);
	    		      Button09.setTextSize(20);
				}
	       }
       
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
 
        ApplicationInfo app = applist.get(position);
        
        ///////////////////////VERSION
        	
        PackageInfo pInfo = null;
		try {
			pInfo = getPackageManager().getPackageInfo(app.packageName, 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String version = pInfo.versionName;
        
        
        
        //////////////////////////SIZE APK
        
        ApplicationInfo tmpInfo = null;
		try {
			tmpInfo = getPackageManager().getApplicationInfo(app.packageName,-1);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        long size = new File(tmpInfo.sourceDir).length();
        
        
        //////////////////////////NAME APK
        
        
        PackageManager packageManager= getApplicationContext().getPackageManager();
        String appName = "Unknow";
        try {
			appName = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(app.packageName, PackageManager.GET_META_DATA));
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
        
       /////////////////////////////
        
        Intent intent18 = new Intent(this, ActivityUsageInfo.class);
        intent18.putExtra("title", appName);
        intent18.putExtra("version", version);
        intent18.putExtra("apk_size", size);
        intent18.putExtra("code", app.packageName);
    	 startActivity(intent18);
    	 
    	

		overridePendingTransition(center_to_left, center_to_left2);
        
        /*
          Intent intent = packageManager
                    .getLaunchIntentForPackage(app.packageName);
                startActivity(intent);
            */
    }
    
    public  String getFileSize(long size) {
        if (size <= 0)
            return "0";
        //final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        final String[] unit = getResources().getStringArray(R.array.units);
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + unit[digitGroups];
    }
    
 
    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
        for (ApplicationInfo info : list) {
            try {
                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
                	if(isSystemPackage(packageManager.getPackageInfo(info.packageName, PackageManager.GET_ACTIVITIES)) == false){
                    	applist.add(info);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
 
        return applist;
    }
 
    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
                : false;
    }
    
    
    private class LoadApplications extends AsyncTask<Void, Void, Void> {
       // private ProgressDialog progress = null;
 
        @Override
        protected void onPreExecute() {
            //progress = ProgressDialog.show(ActivityApps.this, null,"Download");
            super.onPreExecute();
            pg1.setVisibility(View.VISIBLE);
        }
        
        @Override
        protected Void doInBackground(Void... params) {
            applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
            listadaptor = new ApplicationAdapterUsage(ActivityApps.this,
                    R.layout.snippet_list_row_del, applist);
 
            
            
            return null;
        }
 
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
 
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            setListAdapter(listadaptor);
            setListViewHeightBasedOnChildren(u);
            //progress.dismiss();
            pg1.setVisibility(View.GONE);
        }
 
       
 
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    
    
    
    
    
    
    public void BackClick(View v)  
    {  
    	Intent intent18 = new Intent(this, ActivityUsage.class);
     	 startActivity(intent18);

		overridePendingTransition(center_to_right, center_to_right2);
   	 }

	
	
	public void memory (){
		  
		    //usage.setText(getFileSize(getTotalInternalMemorySize()));
		    //aviable.setText(getFileSize(getAvailableInternalMemorySize()));
		     
	  }
	  
	  public static boolean externalMemoryAvailable() {
	        return //android.os.Environment.getExternalStorageState().equals(
	               // android.os.Environment.MEDIA_MOUNTED);
	        android.os.Environment.getRootDirectory().equals(android.os.Environment.MEDIA_MOUNTED);
	    }

	    public static long getAvailableInternalMemorySize() {
	        File path = Environment.getDataDirectory();
	        StatFs stat = new StatFs(path.getPath());
	        long blockSize = stat.getBlockSize();
	        long availableBlocks = stat.getAvailableBlocks();
	        return availableBlocks * blockSize;
	    }

	    public static long getTotalInternalMemorySize() {
	        File path = Environment.getDataDirectory();
	        StatFs stat = new StatFs(path.getPath());
	        long blockSize = stat.getBlockSize();
	        long totalBlocks = stat.getBlockCount();
	        return totalBlocks * blockSize;
	    }

	    static long ERROR = 0;
	    
	    public static long getAvailableExternalMemorySize() {
	        if (externalMemoryAvailable()) {
	            File path = Environment.getExternalStorageDirectory();
	            StatFs stat = new StatFs(path.getPath());
	            long blockSize = stat.getBlockSize();
	            long availableBlocks = stat.getAvailableBlocks();
	            return availableBlocks * blockSize;
	        } else {
	            return  ERROR;
	        }
	    }

	    public static long getTotalExternalMemorySize() {
	        if (externalMemoryAvailable()) {
	            File path = Environment.getExternalStorageDirectory();
	            StatFs stat = new StatFs(path.getPath());
	            long blockSize = stat.getBlockSize();
	            long totalBlocks = stat.getBlockCount();
	            return totalBlocks * blockSize;
	        } else {
	            return  ERROR;
	        }
	    }

	
	public static void setListViewHeightBasedOnChildren(ListView lv) {
        ListAdapter listAdapter = lv.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = MeasureSpec.makeMeasureSpec(lv.getWidth(), MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, lv);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = totalHeight + (lv.getDividerHeight() * (listAdapter.getCount() - 1));
        lv.setLayoutParams(params);
        lv.requestLayout();
    }

	 @Override
	    public boolean onKeyDown(int keycode, KeyEvent e) {
	        switch(keycode) {
	           
	            case KeyEvent.KEYCODE_BACK:
	            	Intent intent18 = new Intent(this, ActivityUsage.class);
	             	 startActivity(intent18);

	       		overridePendingTransition(center_to_right, center_to_right2);
	                return true;
	            
	        }
	        return super.onKeyDown(keycode, e);
	   }
	 
}
