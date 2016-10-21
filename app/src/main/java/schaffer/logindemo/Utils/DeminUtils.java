package schaffer.logindemo.Utils;

import schaffer.logindemo.Base.MApplication;

/**
 * Created by SchafferW on 2016/10/19.
 */

public class DeminUtils {
    public static int dp2px(int dpvalue) {
        return (int) (MApplication.app.getResources().getDisplayMetrics().density * dpvalue + 0.5f);
    }

}
