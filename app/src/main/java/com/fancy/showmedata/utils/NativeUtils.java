package com.fancy.showmedata.utils;


import com.tencent.bugly.crashreport.CrashReport;

/**
 * 类描述：获取sign
 * Created by 殴打小熊猫 on 2017/8/9.
 */

public class NativeUtils {
    static {
        try {
            System.loadLibrary("NativeExample");
        } catch (Throwable throwable) {
//            String memberId = (String) SharedPreferencesUtils.getParam(App.getAppContext()(), Constants.KEY_UID, "0");
//            MobclickAgent.reportError(App.getAppContext()(), "签名错误：memberId: " + memberId + "\n错误详情：\n" + throwable.toString());
        }
    }

    public static String getInnoSoInfo(String unSign, int reqType) {
        try {
            return innoSign(unSign);
        } catch (Throwable throwable) {
//            String memberId = (String) SharedPreferencesUtils.getParam(App.getAppContext()(), Constants.KEY_UID, "0");
//            MobclickAgent.reportError(App.getAppContext()(), "签名错误：memberId：" + memberId + "类型: " + reqType + "\n错误详情：\n" + throwable.toString());
        }
        return null;
    }

    public static String getInnoSoInfo(String unSign) {
        try {
            return innoSign(unSign);
        } catch (Throwable throwable) {
            CrashReport.postCatchedException(throwable);
        }
        return "";
    }

    public static native String innoSign(String unSign);
}
