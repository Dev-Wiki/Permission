package net.devwiki.permission;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.devwiki.permission.jumpactivirty.JumpPermissionManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openActivity(View view) {
        JumpPermissionManager manager = new JumpPermissionManager(this);
        manager.jumpPermissionPage();
    }
}
