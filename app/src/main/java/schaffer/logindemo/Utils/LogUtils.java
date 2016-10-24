package schaffer.logindemo.utils;

import android.util.Log;

import schaffer.logindemo.base.MyApplication;

/**
 * Created by SchafferW on 2016/10/19.
 */

public class LogUtils {

    public static void w(String content) {
        if (MyApplication.isPrint_logCat)
            Log.w("TAG", content);
    }

    public static void e(String content) {
        if (MyApplication.isPrint_logCat)
            Log.e("TAG", content);
    }

}
