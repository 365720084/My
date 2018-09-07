package com.fancy.showmedata.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.fancy.showmedata.R;
import com.fancy.showmedata.application.Constants;
import com.fancy.showmedata.utils.log.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import butterknife.internal.Utils;

import static android.content.Context.CLIPBOARD_SERVICE;

public class OS {
    private static String deviceId;
    private static String dtu;
    private static final String[] DEBUG_DEVICES = {"81233312312312", "832325123123",
            "83434343439434", "8454545457545", "856565636565656", "867676767626767",
            "8787878787873878", "889898978989898989", "890909091090990", "8010101002101010"};

    // 唯一标识
    @SuppressLint("HardwareIds")
    public static String getDeviceCode(ContextWrapper context) {
        if (TextUtils.isEmpty(deviceId) && context != null) {
            try {
                TelephonyManager tm = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                deviceId = tm.getDeviceId();

            } catch (Exception e) {
                e.printStackTrace();
            }

//            if (BuildConfig.LOG_DEBUG)
//                deviceId = DEBUG_DEVICES[(int) (Math.random() * 9)];
        }
        return deviceId;
    }

    public static String getDeviceInfo(Activity context) {
        return "[brand=" + android.os.Build.MANUFACTURER + ",model="
                + android.os.Build.MODEL + ",ratio=" + getDeviceWidth(context) + "*"
                + getDeviceHeight(context) + "]";
    }

    public static String getDeviceWidth(Activity context) {
        Display display = context.getWindowManager()
                .getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        float width = displayMetrics.widthPixels;// 得到宽度
        return Float.toString(width);
    }

    public static String getLocalMacAddress(ContextWrapper ctx) {
        WifiManager wifi = (WifiManager) ctx.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }



    /**
     * 获取手机的MAC地址
     *
     * @return
     */
    public static String getMac() {
        String str = "";
        String macSerial = "";
        try {
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if ("".equals(macSerial)) {
            try {
                return loadFileAsString("/sys/class/net/eth0/address")
                        .toUpperCase().substring(0, 17);
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
        return macSerial;
    }

    public static String loadFileAsString(String fileName) throws Exception {
        FileReader reader = new FileReader(fileName);
        String text = loadReaderAsString(reader);
        reader.close();
        return text;
    }

    public static String loadReaderAsString(Reader reader) throws Exception {
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[4096];
        int readLength = reader.read(buffer);
        while (readLength >= 0) {
            builder.append(buffer, 0, readLength);
            readLength = reader.read(buffer);
        }
        return builder.toString();
    }


    public static String getDeviceHeight(Activity context) {
        Display display = context.getWindowManager()
                .getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        float height = displayMetrics.heightPixels;// 得到宽度
        return Float.toString(height);
    }

    public static String getToken(Context context) {
        return (String) SharedPreferencesUtils.getParam(context, Constants.KEY_USER_TOKEN, "");
    }

    public static void setToken(Context context, String token) {
        Log.d("setToken", token + "count");
        SharedPreferencesUtils.setParam(context, Constants.KEY_USER_TOKEN, token);
        if (TextUtils.isEmpty(token))
            SharedPreferencesUtils.setParam(context, Constants.KEY_USER_MENU_LIST_ALL, "");
    }

    public static void logout(Context context) {
        OS.setToken(context, "");
        SharedPreferencesUtils.setParam(context, Constants.KEY_WX_OPENID, "");
        SharedPreferencesUtils.setParam(context, Constants.KEY_UID, "");
    }


    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static int getAppVersion() {
//        try {
//            PackageManager manager = context.getPackageManager();
//            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
//            return info.versionName;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "1.0.0";
        return Constants.APP_VERSION;
    }

    // 系统平台 ios 1, android 2, windowsphone 3
    public static String getOSName() {
        return "2";
    }

    // 设备系统版本
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取渠道号
     *
     * @param context
     * @return
     */
    public static String getDtu(Context context) {
        if (true) {
            Log.i("channel", getChanel(context));
            return getChanel(context);
        }
        if (!TextUtils.isEmpty(dtu))
            return dtu;
        String resultData = "";
        if (!TextUtils.isEmpty(dtu))
            return dtu;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager
                        .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString("CUS_DTU");
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(resultData)) {
            resultData = "google";
        }
//        if (resultData.startsWith("d")) {
//            resultData = resultData.substring(1);
//        }
        dtu = resultData;
        return dtu;
    }

    private static String getChanel(Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            String channel = appInfo.metaData.getString("UMENG_CHANNEL", "google");
//            PrefUtil.setKey(LoginUtil.CHANEL, channel);
            return channel;
        } catch (Exception e) {
//            CrashReport.postCatchedException(e);
            Log.e("Error", e.getMessage());
            return "";
        }
    }


    /**
     * 获取定位的城市名
     *
     * @param context 上下文
     * @return 定位的城市名
     */
    public static String getLocationCity(Context context) {
        return (String) SharedPreferencesUtils.getParam(
                context, Constants.KEY_LOCATION_CITY, "");
    }

    public static void setBDLocation(Context context, double lat, double lon, String city) {
        SharedPreferencesUtils.setParam(context, Constants.KEY_LATITUDE, lat);
        SharedPreferencesUtils.setParam(context, Constants.KEY_LONGITUDE, lon);
        SharedPreferencesUtils.setParam(context, Constants.KEY_LOCATION_TIME,
                System.currentTimeMillis());
        SharedPreferencesUtils.setParam(context, Constants.KEY_LOCATION_CITY, city);
    }

    // 当前网络情况
    public static String getNetwork(ContextWrapper context) {
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


    // 是否wifi
    public static boolean isWifi(ContextWrapper context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 获取内网Ip
     * wifi/3g/4g/有线网络
     *
     * @param context
     * @return
     */
    public static String getIpAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            // 3/4g网络
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                try {
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                //  wifi网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());
                return ipAddress;
            } else if (info.getType() == ConnectivityManager.TYPE_ETHERNET) {
                // 有限网络
                return getLocalIp();
            }
        }
        return null;
    }

    private static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }


    // 获取有限网IP
    private static String getLocalIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {

        }
        return "0.0.0.0";
    }

    /**
     * 获取外网IP地址
     *
     * @return
     */
    public static String getNetIp() {
        URL infoUrl = null;
        InputStream inStream = null;
        String line = "";
        try {
            infoUrl = new URL("http://pv.sohu.com/cityjson?ie=utf-8");
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                while ((line = reader.readLine()) != null)
                    strber.append(line + "\n");
                inStream.close();
                // 从反馈的结果中提取出IP地址
                int start = strber.indexOf("{");
                int end = strber.indexOf("}");
                String json = "";
                if (-1 != start && start < strber.length() && -1 != end && end < strber.length() && start < end) {
                    json = strber.substring(start, end + 1);
                }
                if (!TextUtils.isEmpty(json)) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        line = jsonObject.optString("cip");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    /**
     * 检测网络是否连接
     *
     * @return network state
     */
    public static boolean isNetworkConnected(Context context) {
        if (context == null)
            return false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm == null)
                return false;
            NetworkInfo info = cm.getActiveNetworkInfo();
            return !(info == null || !info.isAvailable() || !info.isConnectedOrConnecting());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 获取运营商信息
    public static String getMobile(ContextWrapper context) {
        TelephonyManager telManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String operator = telManager.getSimOperator();
        if (operator != null) {
            switch (operator) {
                case "46000":
                case "46002":
                case "46007":
                    // 中国移动
                    operator = "10086";
                    break;
                case "46001":
                    // 中国联通
                    operator = "10010";
                    break;
                case "46003":
                    // 中国电信
                    operator = "10000";
                    break;
            }
        }
        return operator;
    }

    @SuppressWarnings("deprecation")
    public static boolean isBackground(final Context context) {
        try {
            ActivityManager am = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            List<RunningTaskInfo> tasks = am.getRunningTasks(1);
            if (!tasks.isEmpty()) {
                ComponentName topActivity = tasks.get(0).topActivity;
                if (!topActivity.getPackageName().equals(context.getPackageName())) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isAppRun(Context context, String packageName) {
        boolean isAppRunning = false;
        try {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<RunningTaskInfo> list = am.getRunningTasks(100);
            for (RunningTaskInfo info : list) {
                if (info.topActivity.getPackageName().equals(packageName) && info.baseActivity.getPackageName().equals(packageName)) {
                    isAppRunning = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAppRunning;
    }

    public static boolean startAPP(Context context, String appPackageName) {
        if (TextUtils.isEmpty(appPackageName))
            return false;
        try {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(appPackageName);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            ToastUtils.showToast(context, context.getResources().getString(R.string.str_no_install), ToastUtils.Type.ERROR);
        }
        return false;
    }

    public static boolean checkAPP(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName))
            return false;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            return packageInfo != null;
        } catch (Exception e) {
            return false;
        }
    }

    public static void openApk(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static void openAppSetting(Context context) {
        if (isMIUI()) {
            openMiuiAppPermission(context);
            return;
        }
        openNormalAppSetting(context);
    }

    public static boolean isOPPO() {
        return "OPPO".equalsIgnoreCase(Build.BRAND
        );
    }

    public static String getUUID(Context context) {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        String oldUUID = (String) SharedPreferencesUtils.getParam(context, Constants.KEY_UUID, "");
        if (TextUtils.isEmpty(oldUUID)) {
            SharedPreferencesUtils.setParam(context, Constants.KEY_UUID, uuid);
            return uuid;
        }
        return oldUUID;
    }


    public static boolean isMIUI() {
        return "Xiaomi".equals(Build.MANUFACTURER);
    }

//    public static boolean isMeiZu() {
//        return "Meizu".equals(Build.MANUFACTURER);
//    }

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

    private static void openMiuiAppPermission(Context context) {
        try {
            Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            localIntent.putExtra("extra_pkgname", context.getPackageName());
            context.startActivity(localIntent);
        } catch (Exception e) {
            openNormalAppSetting(context);
        }
    }

    public static boolean checkPermissions(Context context, String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED)
                continue;
            return false;
        }
        return true;
    }

    public static long getAppInstallTime(Context context, String packageName) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            if (packageInfo != null) {
                return packageInfo.lastUpdateTime;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static List<PackageInfo> getAppInstallList(Context context) {
        List<PackageInfo> result = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        if (pm == null) {
            return result;
        }
        List<PackageInfo> packages = new ArrayList<>();
        try {
            packages = pm.getInstalledPackages(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (packages == null || packages.isEmpty())
            return result;
        for (PackageInfo packageInfo : packages) {
            // 判断系统/非系统应用
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                // 非系统应用
                result.add(packageInfo);
            }
        }
        return result;
    }

    /**
     * 没鸟用
     *
     * @param context
     */
    public static void openAppNotifiyManager(Context context) {
        PackageInfo info;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (info == null)
                return;
            Intent intent = new Intent();
            intent.setClassName("com.android.settings", "com.android.settings.Settings$NotificationFilterActivity");
            intent.putExtra("extra_pkgname", context.getPackageName());
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 判断是否具有ROOT权限
    public static boolean isRooted() {
        boolean res = false;
        try {
            res = !((!new File("/system/bin/su").exists())
                    && (!new File("/system/xbin/su").exists()));
        } catch (Exception e) {
        }
        return res;
    }

    public static boolean dealDeepLink(Context context, String deeplinkUrl) {
        if (context == null || TextUtils.isEmpty(deeplinkUrl)) {
            return false;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(deeplinkUrl));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getClipboardText(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData.Item item = null;

        //无数据时直接返回
        if (!clipboard.hasPrimaryClip()) {
            return "";
        }

        //如果是文本信息或者网页复制
        if (clipboard.getPrimaryClipDescription().hasMimeType(
                ClipDescription.MIMETYPE_TEXT_PLAIN) || clipboard.getPrimaryClipDescription().hasMimeType(
                ClipDescription.MIMETYPE_TEXT_HTML)) {
            ClipData cdText = clipboard.getPrimaryClip();
            item = cdText.getItemAt(0);
            //此处是TEXT文本信息
            if (item.getText() == null) {
                return "";
            } else {
                return item.getText().toString();
            }
        }
        return "";
    }

    //执行linux命令并且输出结果
    public static String execRootCmd(String tag, String paramString) {
        BufferedReader br = null;
//        paramString = "ping -c 5 -w 5 " + paramString;
        StringBuilder sb = null;
        try {
            LogUtils.e(tag, "=======cmd begin========");
            Process p = Runtime.getRuntime().exec(paramString);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
                LogUtils.e(tag, line);
            }
            LogUtils.e(tag, "cmd result:" + sb.toString());
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        LogUtils.e(tag, "cmd result:" + sb.toString());
        return sb.toString();
    }

    public static void captureScreen(Activity context) {
        View cv = context.getWindow().getDecorView();
        Bitmap bmp = Bitmap.createBitmap(cv.getWidth(), cv.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        cv.draw(canvas);
        String dir = Constants.PATH_ROOT + "/capture/";
        String name = "test";
        if (bmp != null)
            SaveBitmapUtils.saveMyBitmap(dir, name, bmp);
        bmp.recycle();
    }

    public static void captureWebView(WebView webView) {
        Picture snapShot = webView.capturePicture();

        Bitmap bmp = Bitmap.createBitmap(snapShot.getWidth(),
                snapShot.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bmp);
        snapShot.draw(canvas);
        String dir = Constants.PATH_ROOT + "/capture/";
        String name = "test";
        if (bmp != null)
            SaveBitmapUtils.saveMyBitmap(dir, name, bmp);
        bmp.recycle();
    }

    /**
     * 跳转到app应用详情页
     *
     * @param context
     * @param packageName
     */
    public static void goAppInfoPage(Context context, String packageName) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", packageName, null));
        context.startActivity(intent);
    }



    public static String getAndroidId(Context context) {
        return Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
    }

    public static boolean isSamsung() {
        return "samsung".equals(Build.MANUFACTURER);
    }

}
