package net.devwiki.permission.jumpactivirty;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import net.devwiki.log.DevLog;
import net.devwiki.permission.RomUtils;

/**
 * 跳转到所有权限界面
 * @author DevWiki
 */
public class JumpAllPermission implements JumpPermission{

    private static final String TAG = "JumpAllPermission";

    private String packageName;
    private Context context;

    JumpAllPermission(Context context) {
        this.context = context;
        packageName = context.getPackageName();
    }

    @Override
    public void goPage() {
        String name = Build.MANUFACTURER.toLowerCase();
        DevLog.e(TAG, "MANUFACTURER : " + name);
        if (RomUtils.isHuaWei()) {
            goHuaWei();
        } else if (RomUtils.isVIVO()) {
            goVIVO();
        } else if (RomUtils.isOPPO()) {
            goOPPO();
        } else if (RomUtils.isMeizu()) {
            goMeizu();
        } else if (RomUtils.isMIUI()) {
            goXiaoMi();
        } else if (RomUtils.isSamsung()) {
            goSangXin();
        } else if (RomUtils.isSony()) {
            goSony();
        } else if (RomUtils.isLG()) {
            goLG();
        } else {
            goIntentSetting();
        }
    }

    private void goLG(){
        try {
            Intent intent = new Intent(packageName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.Settings$AccessLockSummaryActivity");
            intent.setComponent(comp);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            goIntentSetting();
        }
    }

    private void goSony(){
        try {
            Intent intent = new Intent(packageName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.sonymobile.cta",
                    "com.sonymobile.cta.SomcCTAMainActivity");
            intent.setComponent(comp);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            goIntentSetting();
        }
    }

    private void goHuaWei() {
        try {
            Intent intent = new Intent(packageName);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager",
                    "com.huawei.permissionmanager.ui.MainActivity");
            intent.setComponent(comp);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            goIntentSetting();
        }
    }

    private void goXiaoMi() {
        String rom = RomUtils.getMIUIVersion();
        Intent intent=new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (RomUtils.MIUI_V6.equals(rom) || RomUtils.MIUI_V7.equals(rom)) {
            intent.setAction("miui.intent.action.APP_PERM_EDITOR");
            intent.setClassName("com.miui.securitycenter",
                    "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            intent.putExtra("extra_pkgname", packageName);
        } else if (RomUtils.MIUI_V8.equals(rom) || RomUtils.MIUI_V9.equals(rom)
                || RomUtils.MIUI_V10.equals(rom)) {
            intent.setAction("miui.intent.action.APP_PERM_EDITOR");
            intent.setClassName("com.miui.securitycenter",
                    "com.miui.permcenter.permissions.PermissionsEditorActivity");
            intent.putExtra("extra_pkgname", packageName);
        } else {
            goIntentSetting();
        }
        context.startActivity(intent);
    }

    private void goMeizu() {
        try {
            Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("packageName", packageName);
            context.startActivity(intent);
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            localActivityNotFoundException.printStackTrace();
            goIntentSetting();
        }
    }

    private void goSangXin() {
        goIntentSetting();
    }

    private void goIntentSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goOPPO() {
        DevLog.d(TAG, "gotoOPPOAllPermissionPage");
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.coloros.securitypermission",
                    "com.coloros.securitypermission.permission.PermissionAppAllPermissionActivity");
            intent.setComponent(comp);
            intent.putExtra("packagename", packageName);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            goIntentSetting();
        }
    }

    private void goVIVO() {
        DevLog.d(TAG, "goto vivo permission manager");
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.vivo.permissionmanager",
                    "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity");
            intent.setComponent(comp);
            intent.putExtra("packagename", packageName);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            goIntentSetting();
        }
    }
}
