package net.devwiki.permission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.devwiki.permission.jumpactivirty.JumpPermissionManager;

/**
 * demo主界面
 * @author DevWiki
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private JumpPermissionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = JumpPermissionManager.getInstance();
        manager.init(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void jumpAllPermissionPage(View view) {
        manager.jumpAllPermissionPage();
    }

    public void jumpNotificationPermissionPage(View view) {
        manager.jumpNotificationPermissionPage();
    }

    public void jumpFloatPermissionPage(View view) {
        manager.jumpFloatPermissionPage();
    }
}
