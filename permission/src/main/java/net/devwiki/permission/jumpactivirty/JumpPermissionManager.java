package net.devwiki.permission.jumpactivirty;

import android.content.Context;

/**
 * 跳转权限管理界面
 * @author DevWiki
 */
public class JumpPermissionManager {

    private JumpAllPermission allPermission;
    private JumpFloatPermission floatPermission;
    private JumpNotificationPermission notificationPermission;

    private static class InstanceHolder {
        private static final JumpPermissionManager INSTANCE = new JumpPermissionManager();
    }

    private JumpPermissionManager() {
    }

    public static JumpPermissionManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void init(Context context) {
        allPermission = new JumpAllPermission(context);
        notificationPermission = new JumpNotificationPermission(context);
        floatPermission = new JumpFloatPermission(context);
    }

    public void jumpAllPermissionPage() {
        allPermission.goPage();
    }

    public void jumpNotificationPermissionPage() {
        notificationPermission.goPage();
    }

    public void jumpFloatPermissionPage() {
        floatPermission.goPage();
    }
}