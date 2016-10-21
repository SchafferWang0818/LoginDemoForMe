package schaffer.logindemo.Utils;

import android.widget.Toast;

import schaffer.logindemo.Base.MApplication;

/**
 * Created by SchafferW on 2016/10/19.
 */

public class ToastUtils {
    public static void shortNotify(String content) {
        Toast.makeText(MApplication.app, content, Toast.LENGTH_SHORT).show();
    }

    public static void longNotify(String content) {
        Toast.makeText(MApplication.app, content, Toast.LENGTH_LONG).show();
    }

}
