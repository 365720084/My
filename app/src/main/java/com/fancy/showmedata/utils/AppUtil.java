package com.fancy.showmedata.utils;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.tencent.bugly.crashreport.CrashReport;

import java.util.List;
import java.util.UUID;

/**
 * 跟App相关的辅助类
 * Created by xiongz on 2017/3/1.
 */
public class AppUtil {

    private AppUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (NameNotFoundException e) {
            CrashReport.postCatchedException(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (NameNotFoundException e) {
            CrashReport.postCatchedException(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本号信息]
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);

            return packInfo.versionCode;
        } catch (Exception e) {
            CrashReport.postCatchedException(e);
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 应用程序是否已安装
     *
     * @param context
     * @param packageName 应用程序的包名
     * @return
     */
    public static boolean isInstalled(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> mPacks = pm.getInstalledPackages(0);
        for (PackageInfo info : mPacks) {
            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == 0) {
                if (packageName.equals(info.packageName)) {
                    return true;
                }
            }
        }
        return false;
    }


    /********************* 硬件设备辅助方法 **********************/

    /**
     * 获取设备唯一标识码
     *
     * @param context
     * @return
     */
    public static String getUUID(Context context) {
        //注意需要添加权限 <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
        String uniqueId = "";
        final TelephonyManager tm = (TelephonyManager) ((ContextWrapper) context).getBaseContext().getSystemService(
                Context.TELEPHONY_SERVICE);
        String tmDevice = tm.getDeviceId();//获取IME标识
        String tmSerial = tm.getSimSerialNumber();//获取sim卡的序号
        String androidId = android.provider.Settings.Secure.getString(context.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
        if (tmDevice == null) {
            tmDevice = "";
        }
        if (tmSerial == null) {
            tmSerial = "";
        }
        if (androidId == null) {
            androidId = "";
        }
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        uniqueId = deviceUuid.toString();
        return uniqueId;
    }

    /**
     * 获取设备的唯一标识deviceId
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        if (deviceId == null) {
            return null;
        } else {
            return deviceId;
        }
    }

    /**
     * 获取手机品牌
     *
     * @return
     */
    public static String getPhoneBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return Build.MODEL;
    }
    /**
     * 获取设备厂商
     * <p>如Xiaomi</p>
     *
     * @return 设备厂商
     */

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取手机Android API等级（22、23 ...）
     *
     * @return
     */
    public static int getBuildLevel() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机Android 版本（4.4、5.0、5.1 ...）
     *
     * @return
     */
    public static String getBuildVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * android系统版本号（4.4、5.0、5.1 ...）
     *
     * @return
     */
    public static String getPlatVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 手机imei号
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String tmDevice, tmSerial, androidId;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return getAndroidId(context);
        }
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(
                context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(),
                ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = deviceUuid.toString();
        return deviceId;
    }


    /**
     * 手机imei号
     * @param context
     * @return
     */
    public static String getAndroidId(Context context) {
        String androidId;
        androidId = "" + android.provider.Settings.Secure.getString(
                context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        return androidId;
    }

    public static int getSign(Context context) {
        try {
            String pkgname = context.getPackageName();
            PackageManager manager = context.getPackageManager();
            /** 通过包管理器获得指定包名包含签名的包信息 **/
            PackageInfo packageInfo = manager.getPackageInfo(pkgname, PackageManager.GET_SIGNATURES);
            /******* 通过返回的包信息获得签名数组 *******/
            Signature[] signatures = packageInfo.signatures;
            /******* 循环遍历签名数组拼接应用签名 *******/
//            builder.append(signatures[0].toCharsString());
//            for (Signature signature : signatures) {
//                builder.append(signature.toCharsString());
//            }
            /************** 得到应用签名 **************/
            return signatures[0].toCharsString().hashCode();
        } catch (Throwable e) {
//            Tools.e(e);
        }
        return -1;
    }

    public static String getSignS(Context context) {
        try {
            String pkgname = context.getPackageName();
            PackageManager manager = context.getPackageManager();
            PackageInfo packageInfo = manager.getPackageInfo(pkgname, PackageManager.GET_SIGNATURES);
            Signature[] signatures = packageInfo.signatures;
            String str=signatures[0].toCharsString();
            return str;
        } catch (Throwable e) {
            Log.e("aaaaa","出错1:   "+e.toString());
        }
        return "error";
    }
}
