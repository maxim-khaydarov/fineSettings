package ua.mkh.settings.full;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by 1 on 04.05.2017.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }
}
