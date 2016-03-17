package com.sumauto.common.support;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.sp.lib.R;

import java.util.List;

public class IntentFactory {
    /**
     * 打电话
     *
     * @param phone 电话
     */
    public static Intent callPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone));
        return intent;
    }

    public static Intent share(String title, String content) throws ActivityNotFoundException {
        Intent intent = new Intent(Intent.ACTION_SEND); // 启动分享发送的属性
        intent.setType("text/plain"); // 分享发送的数据类型
        //        intent.setPackage(context.getPackageName());
        intent.putExtra(Intent.EXTRA_SUBJECT, title); // 分享的主题
        intent.putExtra(Intent.EXTRA_TEXT, content); // 分享的内容
        return intent;
    }

    public static List<ResolveInfo> getShareAPPs(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND, null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setType("text/plain");
        //      intent.setType("*/*");
        PackageManager pManager = context.getPackageManager();

        return pManager.queryIntentActivities(intent,
                PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
    }

    /**
     *  * 需要以下权限：
     * <!-- 添加快捷方式 -->
     * <uses-permission android:name="com.android.launcher.permission.INSTALL_SHORTCUT" />
     * <!-- 移除快捷方式 -->
     * <uses-permission android:name="com.android.launcher.permission.UNINSTALL_SHORTCUT" />
     * <!-- 查询快捷方式 -->
     * <uses-permission android:name="com.android.launcher.permission.READ_SETTINGS" />
     */
    public static void addShortcut(Context context, String name, Class<? extends Activity> activityClass) {
        Intent addShortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

        // 不允许重复创建
        addShortcutIntent.putExtra("duplicate", false);// 经测试不是根据快捷方式的名字判断重复的
        // 应该是根据快链的Intent来判断是否重复的,即Intent.EXTRA_SHORTCUT_INTENT字段的value
        // 但是名称不同时，虽然有的手机系统会显示Toast提示重复，仍然会建立快链
        // 屏幕上没有空间时会提示
        // 注意：重复创建的行为MIUI和三星手机上不太一样，小米上似乎不能重复创建快捷方式

        // 名字
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // 图标
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(context,
                        R.drawable.ic_launcher));

        // 设置关联程序
        Intent launcherIntent = new Intent(Intent.ACTION_MAIN);
        launcherIntent.setClass(context, activityClass);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        addShortcutIntent
                .putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);

        // 发送广播
        context.sendBroadcast(addShortcutIntent);
    }

}
