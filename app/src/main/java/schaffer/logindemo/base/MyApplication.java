package schaffer.logindemo.base;

import android.app.Application;

import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by SchafferW on 2016/10/19.
 */

public class MyApplication extends Application {
    public static MyApplication app;
    //是否处于测试状态?
    public static boolean test = false;
    //是否打印logcat
    public static boolean isPrint_logCat = true;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        AutoLayoutConifg.getInstance().useDeviceSize();
    }
}
