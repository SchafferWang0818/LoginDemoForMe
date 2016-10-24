package schaffer.logindemo.utils;

import android.content.Context;

import schaffer.logindemo.base.MyApplication;

/**
 * Created by SchafferW on 2016/10/21.
 */

public class SharePrefsUtils {

    public static void saveConfig(String key, String value) {
        save("config", key, value);
    }

    public static void save(String type, String key, String value) {
        boolean config = MyApplication.app.getSharedPreferences(type, Context.MODE_PRIVATE).edit().putString(key, value).commit();
        if (config) {
            LogUtils.w("数据存储成功!");
        }
    }

    public static String getConfig(String key) {
        return get("config", key);
    }

    public static String get(String config, String key) {
        String value = MyApplication.app.getSharedPreferences(config, Context.MODE_PRIVATE).getString(key, "");
        return value;
    }


}
