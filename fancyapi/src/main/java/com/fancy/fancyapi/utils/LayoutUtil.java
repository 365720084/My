package com.fancy.fancyapi.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class LayoutUtil {


    private static String a = null;
    private static Resources b = null;

    private static String a(Context var0) {
        if (a == null) {
            a = var0.getPackageName();
        }

        return a;
    }

    private static int a(Context var0, String var1, String var2) {
        if (b == null) {
            b = var0.getResources();
        }

        return b.getIdentifier(var1, var2, a(var0));
    }

    public static String a(Context var0, String var1) {
        return var0.getResources().getString(b(var0, var1));
    }

    public static int b(Context var0, String var1) {
        return a(var0, var1, "string");
    }

    public static Drawable c(Context var0, String var1) {
        return var0.getResources().getDrawable(d(var0, var1));
    }

    public static int d(Context var0, String var1) {
        return a(var0, var1, "drawable");
    }

    public static int e(Context var0, String var1) {
        return a(var0, var1, "id");
    }

    public static int f(Context var0, String var1) {
        return a(var0, var1, "layout");
    }

    public static int g(Context var0, String var1) {
        return a(var0, var1, "style");
    }

    public static int h(Context var0, String var1) {
        return a(var0, var1, "dimen");
    }

    public static int i(Context var0, String var1) {
        return var0.getResources().getColor(j(var0, var1));
    }

    public static int j(Context var0, String var1) {
        return a(var0, var1, "color");
    }

    public static int k(Context var0, String var1) {
        return a(var0, var1, "integer");
    }

    public static int l(Context var0, String var1) {
        return var0.getResources().getInteger(k(var0, var1));
    }

}
