package net.devwiki.permission;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * room信息获取工具
 * @author DevWiki
 */
public class RomUtils {

    private static final String TAG = "RomUtils";

    public static final String MIUI_V6 = "V6";
    public static final String MIUI_V7 = "V7";
    public static final String MIUI_V8 = "V8";
    public static final String MIUI_V9 = "V9";
    public static final String MIUI_V10 = "V10";

    /**
     * 是否华为系统
     */
    public static boolean isHuaWei() {
        return Build.MANUFACTURER.toLowerCase().contains("HUAWEI")
                || Build.MANUFACTURER.toLowerCase().contains("honor");
    }

    /**
     * 获取 emui 版本号
     * @return emui 版本号
     */
    public static String getEMUIVersion() {
        String version = "4.0";
        try {
            String emuiVersion = getSystemProperty("ro.build.version.emui");
            if (emuiVersion != null) {
                version = emuiVersion.substring(emuiVersion.indexOf("_") + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 是否小米系统
     */
    public static boolean isMIUI() {
        return !TextUtils.isEmpty(getSystemProperty("ro.miui.ui.version.name"));
    }

    /**
     * 获取小米 rom 版本号，获取失败返回 -1
     */
    public static String getMIUIVersion() {
        String version = getSystemProperty("ro.miui.ui.version.name");
        if (version != null) {
            try {
                return version;
            } catch (Exception e) {
                Log.e(TAG, "get miui version code error, version : " + version);
            }
        }
        return "";
    }

    /**
     * 是否魅族Flyme系统
     */
    public static boolean isMeizu() {
        String meizuFlymeOSFlag  = getSystemProperty("ro.build.display.id");
        if (TextUtils.isEmpty(meizuFlymeOSFlag)){
            return false;
        } else {
            return meizuFlymeOSFlag.toLowerCase().contains("flyme");
        }
    }

    public static boolean isVIVO() {
        return Build.MANUFACTURER.toLowerCase().contains("vivo");
    }

    public static boolean isOPPO() {
        return Build.MANUFACTURER.toLowerCase().contains("oppo");
    }

    public static boolean isOnePlus() {
        return Build.MANUFACTURER.toLowerCase().contains("oneplus");
    }

    /**
     * 是否 360 系统
     */
    public static boolean isQihoo() {
        return Build.MANUFACTURER.toLowerCase().contains("qiku");
    }

    public static boolean isSamsung() {
        return Build.MANUFACTURER.toLowerCase().contains("samsung");
    }

    public static boolean isLG() {
        return Build.MANUFACTURER.toLowerCase().contains("lg");
    }

    public static boolean isSony() {
        return Build.MANUFACTURER.toLowerCase().contains("sony");
    }

    /**
     * System.getProperties()不返回与 getprop 相同的属性。要获取 getprop 属性，请尝试使用 Runtime.exec() 执行 getprop 并读取其标准输出。
     *
     * @param propName prop name
     * @return prop value
     */
    public static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            Log.e(TAG, "Unable to read sys prop " + propName, ex);
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Log.e(TAG, "Exception while closing InputStream", e);
                }
            }
        }
        return line;
    }


    /**
     * 判断 Intent 是否有效
     */
    public static boolean isIntentAvailable(Context context, Intent intent) {
        if (intent == null) {
            return false;
        }
        return context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }
}
