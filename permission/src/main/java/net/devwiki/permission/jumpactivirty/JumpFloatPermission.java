package net.devwiki.permission.jumpactivirty;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import net.devwiki.log.DevLog;

import java.lang.reflect.Field;

/**
 * 跳转到悬浮窗权限界面
 * @author DevWiki
 */
public class JumpFloatPermission implements JumpPermission {

    private static final String TAG = "JumpFloatPermission";

    private Context context;

    JumpFloatPermission(Context context) {
        this.context = context;
    }

    @Override
    public void goPage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            gotoFloatAbove23();
        } else {
            DevLog.d(TAG, "not support float manage");
        }
    }

    private void gotoFloatAbove23() {
        try {
            Class clazz = Settings.class;
            Field field = clazz.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION");
            Intent intent = new Intent(field.get(null).toString());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }
}
