package schaffer.logindemo.Base;

import android.app.Application;

/**
 * Created by SchafferW on 2016/10/19.
 */

public class MApplication extends Application {
    public static MApplication app;
    //是否处于测试状态?
    public static boolean test = false;
    //是否打印logcat
    public static boolean isPrint_logCat = true;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
