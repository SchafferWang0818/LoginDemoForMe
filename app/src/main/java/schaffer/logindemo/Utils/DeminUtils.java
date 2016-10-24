package schaffer.logindemo.utils;

import schaffer.logindemo.base.MyApplication;

/**
 * Created by SchafferW on 2016/10/19.
 */

public class DeminUtils {
    public static int dp2px(int dpvalue) {
        return (int) (MyApplication.app.getResources().getDisplayMetrics().density * dpvalue + 0.5f);
    }

}
