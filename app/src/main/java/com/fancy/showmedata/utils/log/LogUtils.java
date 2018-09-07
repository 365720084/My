package com.fancy.showmedata.utils.log;

import android.text.TextUtils;
import android.util.Log;

import com.fancy.showmedata.BuildConfig;

import org.mym.plog.Category;
import org.mym.plog.PLog;


/**
 * Smile<lijian@adeaz.com>
 * 2015-1-28
 */
public class LogUtils {
    public static String TAG = "QuKan";
    public static boolean DEBUG = BuildConfig.LOG_DEBUG;

    public static void v(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            PLog.v(msg);
        }
    }

    public static void d(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            PLog.d(msg);
        }
    }

    public static void i(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            PLog.i(msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            PLog.e(msg);
        }
    }

    public static void w(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            PLog.w(msg);
        }
    }

    public static void v(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            PLog.tag(tag).level(Log.VERBOSE).msg(msg).execute();
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            PLog.tag(tag).level(Log.DEBUG).msg(msg).execute();
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            PLog.tag(tag).level(Log.INFO).msg(msg).execute();
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            PLog.tag(tag).level(Log.ERROR).msg(msg).execute();
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            PLog.tag(tag).level(Log.WARN).msg(msg).execute();
        }
    }

    public static void v(String tag, Category category, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            PLog.tag(tag).level(Log.VERBOSE).category(category).msg(msg).execute();
        }
    }

    public static void d(String tag, Category category, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            PLog.tag(tag).level(Log.DEBUG).category(category).msg(msg).execute();
        }
    }

    public static void i(String tag, Category category, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            PLog.tag(tag).level(Log.INFO).category(category).msg(msg).execute();
        }
    }

    public static void e(String tag, Category category, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            PLog.tag(tag).level(Log.ERROR).category(category).msg(msg).execute();
        }
    }

    public static void w(String tag, Category category, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            PLog.tag(tag).level(Log.WARN).category(category).msg(msg).execute();
        }
    }

}
