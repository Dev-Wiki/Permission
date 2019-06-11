## 权限跳转

### 1. 使用 JumpPermissionManager

```java

JumpPermissionManager manager = JumpPermissionManager.getInstance();
manager.init(context);

public void jumpAllPermissionPage(View view) {
    manager.jumpAllPermissionPage();
}

public void jumpNotificationPermissionPage(View view) {
    manager.jumpNotificationPermissionPage();
}

public void jumpFloatPermissionPage(View view) {
    manager.jumpFloatPermissionPage();
}
```

### 2. 使用单个功能

#### 2.1 跳转到所有权限

```java
JumpPermission permission = new JumpAllPermission(context);
permission.goPage();
```

#### 2.2 跳转到通知权限

```java
JumpPermission permission = new JumpNotificationPermission(context);
permission.goPage();
```

#### 2.3 跳转到悬浮窗权限

```java
JumpPermission permission = new JumpFloatPermission(context);
permission.goPage();
```

## 后续根据需要再添加修正