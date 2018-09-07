package com.fancy.showmedata.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

import com.fancy.showmedata.application.Constants;
import com.fancy.showmedata.utils.log.LogUtils;

import java.lang.ref.SoftReference;

/**
 * User: Smile(lijianhy1990@gmail.com)
 * Date: 2016-09-21
 * Time: 19:33
 */
public class ScreenBrightnessHelper {

    private SoftReference<Context> contextRef;
    private int screenMode;
    private int screenBrightness;

    public ScreenBrightnessHelper(Context context) {
        contextRef = new SoftReference<>(context);
        screenBrightness = (int) SharedPreferencesUtils.getParam(context,
                Constants.KEY_SETTING_SCREEN_BRIGHTNESS, -1);
        try {
            screenMode = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(boolean checkPermission) {
        readScreenSettings();
        if (!checkPermission)
            return;
//        Context context = contextRef.get();
//        if (context == null)
//            return;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (!Settings.System.canWrite(context)) {
//                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
//                intent.setData(Uri.parse("package:" + context.getPackageName()));
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//                ToastUtils.showToast(context.getApplicationContext(),
//                        "请先允许修改系统设置", ToastUtils.Type.WARNING);
//            }
//        }
    }

    public int getScreenMode() {
        return screenMode;
    }

    public int getScreenBrightness() {
        return screenBrightness;
    }

    public int[] readScreenSettings() {
        /** 获得当前屏幕亮度的模式
         * SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
         * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
         */
        int[] result = new int[2];
        try {
            Context context = contextRef.get();
            if (context == null)
                return null;
            ContentResolver contentResolver = context.getContentResolver();
            screenMode = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE);
            result[0] = screenMode;
            LogUtils.i("screenMode = " + screenMode);
            // 获得当前屏幕亮度值 0--255
            if (screenBrightness < 0)
                screenBrightness = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS);
            result[1] = screenBrightness;
            LogUtils.i("screenBrightness = " + screenBrightness);
//            int saveMode = (int) SharedPreferencesUtils.getParam(context, Constants.KEY_SETTING_SCREEN_BRIGHTNESS_MODE, -1);
//            int saveBrightness = (int) SharedPreferencesUtils.getParam(context, Constants.KEY_SETTING_SCREEN_BRIGHTNESS, -1);
//            if (saveMode < 0 && saveBrightness < 0) {
//                SharedPreferencesUtils.setParam(context, Constants.KEY_SETTING_SCREEN_BRIGHTNESS_MODE, screenMode);
//                SharedPreferencesUtils.setParam(context, Constants.KEY_SETTING_SCREEN_BRIGHTNESS, screenBrightness);
//            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void seekScreenValue(Activity parent, int value) {
        if (screenMode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
            setScreenMode(Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        }
        changeScreenBrightness(parent, value);
    }

    private void setScreenMode(int value) {
        Context context = contextRef.get();
        if (context == null)
            return;
        screenMode = value;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                !Settings.System.canWrite(context))
            return;
        try {
            Settings.System.putInt(context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置当前屏幕亮度值 0--255，并使之生效
     */
    private void changeScreenBrightness(Activity parent, float value) {
        Window mWindow = parent.getWindow();
        WindowManager.LayoutParams mParams = mWindow.getAttributes();
        mParams.screenBrightness = value / 255.0f;
        mWindow.setAttributes(mParams);
        setScreenBrightness((int) value);
    }

    private void setScreenBrightness(int value) {
        Context context = contextRef.get();
        if (context == null)
            return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                !Settings.System.canWrite(context))
            return;
        try {
            Settings.System.putInt(context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
