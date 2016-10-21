package schaffer.logindemo.Utils;

import android.util.Log;

import schaffer.logindemo.Base.MApplication;

/**
 * Created by SchafferW on 2016/10/19.
 */

public class LogUtils {

    public static void w(String content) {
        if (MApplication.isPrint_logCat)
            Log.w("TAG", content);
    }

    public static void e(String content) {
        if (MApplication.isPrint_logCat)
            Log.e("TAG", content);
    }

}
