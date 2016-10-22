package schaffer.logindemo.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import schaffer.logindemo.Base.MApplication;

/**
 * Created by SchafferW on 2016/10/21.
 */

public class BitmapUtils {
    //计算缩放比
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
                                            int reqheight) {
        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;
        int inSampleSize = 1;
        if (originalWidth > reqWidth || originalHeight > reqheight) {
            int halfWidth = originalWidth / 2;
            int halfHeight = originalHeight / 2;
            while ((halfWidth / inSampleSize > reqWidth)
                    && (halfHeight / inSampleSize > reqheight)) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap res2Bitmap(int resId, int reqWidth, int reqheight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //开始测量
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(MApplication.app.getResources(), resId, options);
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqheight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(MApplication.app.getResources(), resId, options);
    }


}
