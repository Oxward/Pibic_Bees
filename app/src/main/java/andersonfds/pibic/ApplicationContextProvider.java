package andersonfds.pibic;

import android.app.Application;
import android.content.Context;

public class ApplicationContextProvider extends Application {

    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }
}