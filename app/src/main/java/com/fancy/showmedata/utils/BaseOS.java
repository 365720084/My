package com.fancy.showmedata.utils;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

//import com.meituan.android.walle.WalleChannelReader;

/**
 * Created by yananh on 2017/12/4.
 */
public class BaseOS {
    private static String deviceId;
    private static String dtu;
    private static int versionCode = -1;
    private static String versionName;
    private static String mCurProcessName;
    private static Boolean isMainProcess = null;





    // 设备Android版本
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    // 获取手机型号
    public static String getPhoneMode() {
        return Build.MODEL;
    }

    // 获取手机厂商
    public static String getPhoneManufacturer() {
        return Build.MANUFACTURER;
    }

//    public static String getDtu(Context context) {
//        if (!TextUtils.isEmpty(dtu)) {
//            return dtu;
//        }
//        String resultData = "";
//        try {
//            resultData = WalleChannelReader.getChannel(context, "d001");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (TextUtils.isEmpty(resultData)) {
//            resultData = "d001";
//        }
//        if (resultData.startsWith("d")) {
//            resultData = resultData.substring(1);
//        }
//        dtu = resultData;
//        return dtu;
//    }


    // 当前网络情况
    public static String getNetwork(Context context) {
        String netType = "0";
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!TextUtils.isEmpty(extraInfo)) {
                netType = "3g";
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = "wifi";
        }
        return netType;
    }

    public static boolean isOPPO() {
        return "OPPO".equalsIgnoreCase(Build.BRAND);
    }

    public static boolean isHuaWei() {
        return "HUAWEI".equalsIgnoreCase(Build.MANUFACTURER) || "HONOR".equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean isMIUI() {
        return "Xiaomi".equals(Build.MANUFACTURER);
    }

    public static boolean isSamsung() {
        return "samsung".equals(Build.MANUFACTURER);
    }

    public static boolean isMeiZu() {
        return "Meizu".equals(Build.MANUFACTURER);
    }

    public static String getOperator(Context context) {
        int checkPhonePermission = context
                .checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE);
        if (checkPhonePermission == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager telManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String operator = telManager.getSimOperator();
            if (operator != null) {
                if (operator.equals("46000") || operator.equals("46002")
                        || operator.equals("46007")) {
                    return "1";
                } else if (operator.equals("46001")) {
                    return "2";
                } else if (operator.equals("46003")) {
                    return "3";
                }
            }
            return "0";
        }
        Log.e("adeaz-ads", "request permission READ_PHONE_STATE failed");
        return "0";

    }

    public static String getType(Context context) {
        if (context == null) {
            return "1";
        }
        int checkPhonePermission = context
                .checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE);
        if (checkPhonePermission == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager telephony = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            int type = telephony.getPhoneType();
            if (type == TelephonyManager.PHONE_TYPE_NONE) {
                return "1";
            } else {
                return "2";
            }
        }
        Log.e("adeaz-ads", "request permission READ_PHONE_STATE failed");
        return "1";
    }

    public static String getOreintation(Context context) {
        if (context == null) {
            return "";
        }
        return context.getResources().getConfiguration().orientation + "";
    }

    /**
     * 是否是主进程
     *
     * @return
     */
    public static boolean isMainProcess(Context context) {
        if (null != isMainProcess) {
            return isMainProcess;
        }
        String packageName = context.getPackageName();
        String processName = getProcessName(android.os.Process.myPid());
        isMainProcess = (processName == null || processName.equals(packageName));
        return isMainProcess;
    }

    /**
     * 获取当前进程名
     *
     * @param context ref
     * @return process name
     */
    public static String getCurProcessName(Context context) {
        if (!TextUtils.isEmpty(mCurProcessName))
            return mCurProcessName;
        try {
            int pid = android.os.Process.myPid();
            //获取系统的ActivityManager服务
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (am == null)
                return mCurProcessName;
            for (ActivityManager.RunningAppProcessInfo appProcess : am.getRunningAppProcesses()) {
                if (appProcess.pid == pid) {
                    mCurProcessName = appProcess.processName;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mCurProcessName;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @date 2017/12/14 上午10:27
     * @author sqq
     * @desc 获取华为emui系统版本号
     */
    public static String getEMUI() {
        Class<?> classType = null;
        String buildVersion = "0";
        try {
            classType = Class.forName("android.os.SystemProperties");
            Method getMethod = classType.getDeclaredMethod("get", new Class<?>[]{String.class});
            buildVersion = (String) getMethod.invoke(classType, new Object[]{"ro.build.version.emui"});
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(buildVersion) && buildVersion.contains("EmotionUI_") && buildVersion.length() >= 13) {
            buildVersion = buildVersion.substring(10, 13);
        }
        return buildVersion;
    }

    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    /**
     * 检测通知是否开启--仅支持4.4+
     *
     * @param context 上下文
     * @return app是否开启通知
     */
    public static boolean isNotificationEnabled(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            return true;
        try {
            AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            ApplicationInfo appInfo = context.getApplicationInfo();
            String pkg = context.getApplicationContext().getPackageName();
            int uid = appInfo.uid;
            Class appOpsClass = null; /* Context.APP_OPS_MANAGER */
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value = (int) opPostNotificationValue.get(Integer.class);
            return ((int) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    //用户的通知开关是否打开  适用4.4及以上
    public static boolean notificationIsOpen(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//API19+
            return notificationCheckFor19Up(context);
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static boolean notificationCheckFor19Up(Context context) {
        try {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            String pkg = context.getApplicationContext().getPackageName();
            int uid = applicationInfo.uid;
            Class appOpsClass;
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION");
            int op = (int) opPostNotificationValue.get(Integer.class);
            return ((int) checkOpNoThrowMethod.invoke(appOpsManager, op, uid, pkg) == AppOpsManager.MODE_ALLOWED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @date 2017/12/7 下午4:36
     * @author sqq
     * @desc 5.0以上跳转到通知设置界面
     */

    public static void openAppNotifySettingWithApi21(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent mIntent = new Intent();

            mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            mIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
            context.startActivity(mIntent);

        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                Intent intent = new Intent();
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("app_package", context.getPackageName());
                intent.putExtra("app_uid", context.getApplicationInfo().uid);
                context.startActivity(intent);
            } catch (Exception e) {
                if (OS.isOPPO()) {
                    try {
                        openOppoNotifySetting(context);
                    } catch (Exception e1) {
                        openNormalAppSetting(context);
                    }
                    return;
                }
                openNormalAppSetting(context);
            }
        } else {
            if (OS.isOPPO()) {
                try {
                    openOppoNotifySetting(context);
                } catch (Exception e) {
                    openNormalAppSetting(context);
                }
                return;
            }
            openNormalAppSetting(context);
        }
    }
    /**
     * 打开oppo通知设置页面
     *
     * @param context 上下文
     */
    public static void openOppoNotifySetting(Context context) {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.oppo.notification.center",
                "com.oppo.notification.center.AppDetailActivity");
        intent.setComponent(componentName);
        intent.setAction("com.oppo.notification.center.app.detail");
        intent.setFlags(0x10800000);
        intent.putExtra("pkg_name", context.getPackageName());
        intent.putExtra("app_name", "趣头条");
        context.startActivity(intent);
    }

    public static void openNormalAppSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(uri);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long getAppFirstInstallTime(Context context) {
        long firstInstallTime = 0;
        try {
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            //应用装时间
            firstInstallTime = packageInfo.firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return firstInstallTime;
    }
}
