package schaffer.logindemo.utils;

import android.widget.Toast;

import schaffer.logindemo.base.MyApplication;

/**
 * Created by SchafferW on 2016/10/19.
 */

public class ToastUtils {
    public static void shortNotify(String content) {
        Toast.makeText(MyApplication.app, content, Toast.LENGTH_SHORT).show();
    }
}
