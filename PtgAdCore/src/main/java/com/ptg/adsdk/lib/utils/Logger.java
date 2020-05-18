package com.ptg.adsdk.lib.utils;

//
// Source code recreated from getAdManager .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.util.Log;

public class Logger {
    private static boolean a = false;
    private static String TAG = "PtgAdSdk";
    private static int logLevel = 4;

    //设置最高日志等级
    public static void setLogLevel(int var0) {
        logLevel = var0;
    }

    public static boolean v() {
        return logLevel <= 3;
    }

    public static void oepnDebug() {
        a = true;
        setLogLevel(3);
    }

    public static boolean checkDebugOpen() {
        return a;
    }

    public static void v(String var0, String var1) {
        if (checkDebugOpen()) {
            if (var1 != null) {
                if (logLevel <= 2) {
                    Log.v(var0, var1);
                }

            }
        }
    }

    public static void v(String var0) {
        if (checkDebugOpen()) {
            v(TAG, var0);
        }
    }

//    public static void w(String var0, String var1) {
//        if (checkDebugOpen()) {
//            if (var1 != null) {
//                if (logLevel <= 3) {
//                    Log.w(var0, var1);
//                }
//
//            }
//        }
//    }


    public static void i(String var0, String var1) {
        if (checkDebugOpen()) {
            if (var1 != null) {
                if (logLevel <= 4) {
                    Log.i(var0, var1);
                }

            }
        }
    }

    public static void w(String var0, String var1) {
        if (checkDebugOpen()) {
            if (var1 != null) {
                if (logLevel <= 5) {
                    Log.w(var0, var1);
                }

            }
        }
    }
    public static void w(String var1) {
        w(TAG,var1);
    }

    public static void d(String var1) {
        d(TAG,var1);
    }

    public static void d(String var0, String var1) {
        if (checkDebugOpen()) {
            if (var1 != null ) {
                if (logLevel <= 5) {
                    Log.d(var0, var1);
                }

            }
        }
    }
    public static void d(String var0, String var1, Throwable var2) {
        if (checkDebugOpen()) {
            if (var1 != null || var2 != null) {
                if (logLevel <= 5) {
                    Log.d(var0, var1, var2);
                }

            }
        }
    }

    public static void w(String var0, String var1, Throwable var2) {
        if (checkDebugOpen()) {
            if (var1 != null || var2 != null) {
                if (logLevel <= 5) {
                    Log.w(var0, var1, var2);
                }

            }
        }
    }

    public static void e(String var0) {
        if (checkDebugOpen()) {
            e(TAG, var0);
        }
    }

    public static void e(String var0, String var1) {
        if (checkDebugOpen()) {
            if (var1 != null) {
                if (logLevel <= 6) {
                    Log.e(var0, var1);
                }

            }
        }
    }

    public static void e(String var0, String var1, Throwable var2) {
        if (checkDebugOpen()) {
            if (var1 != null || var2 != null) {
                if (logLevel <= 6) {
                    Log.e(var0, var1, var2);
                }

            }
        }
    }
}
