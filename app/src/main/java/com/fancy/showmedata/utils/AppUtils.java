package com.fancy.showmedata.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.fancy.showmedata.utils.log.LogUtils;
import com.tencent.bugly.crashreport.CrashReport;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by tanglin on 16/2/23.
 */
public class AppUtils {


    public static boolean intentIsAvailable(Context ctx, Intent intent) {
        final PackageManager mgr = ctx.getPackageManager();
        List<ResolveInfo> list =
                mgr.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    private static final int MIN_CLICK_DELAY_TIME = 200;
    private static long lastClickTime;

    public static boolean isNotFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if (curClickTime < lastClickTime || (curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
            lastClickTime = curClickTime;
        }

        return flag;
    }

    public static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }

        InputMethodManager inputMethodManager = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager == null) {
            return;
        }

        String[] viewArray = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field filed;
        Object filedObject;

        for (String view : viewArray) {
            try {
                filed = inputMethodManager.getClass().getDeclaredField(view);
                if (!filed.isAccessible()) {
                    filed.setAccessible(true);
                }
                filedObject = filed.get(inputMethodManager);
                if (filedObject != null && filedObject instanceof View) {
                    View fileView = (View) filedObject;
                    if (fileView.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        filed.set(inputMethodManager, null); // 置空，破坏掉path to gc节点
                    } else {
                        break;// 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                    }
                }
            } catch (Throwable t) {
                CrashReport.postCatchedException(t);
                t.printStackTrace();
            }
        }
    }

    public static boolean isFastClick(long internal) {
        boolean ret = false;
        long curClickTime = System.currentTimeMillis();
        if (curClickTime > lastClickTime && curClickTime - lastClickTime <= internal) {
            ret = true;
        } else {
            lastClickTime = curClickTime;
        }
        return ret;
    }

    public static boolean isDebugVersion(Context context) {
//        if (true) {
//            //true 使用测试环境
//            //false 使用线上环境
//            return PrefUtil.getKeyBoolean(LoginUtil.IS_DEBUG,false);
////            return false;
//        }
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            CrashReport.postCatchedException(e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断应用是否已经启动
     * @param context 一个context
     * @return boolean
     */
    public static boolean isAppAlive(Context context){
        String packageName=context.getPackageName();
        ActivityManager activityManager =
                (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();
            for(int i = 0; i < processInfos.size(); i++){
                if(processInfos.get(i).processName.equals(packageName)){
                    LogUtils.i("app_run","true");
                    return true;
                }
            }
        }
        LogUtils.i("app_run","false");
        return false;
    }
}
