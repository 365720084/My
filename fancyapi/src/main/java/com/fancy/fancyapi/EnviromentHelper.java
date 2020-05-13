package com.fancy.fancyapi;



import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.os.Process;
import android.os.Build.VERSION;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.fancy.adsdk.lib.utils.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnviromentHelper {
    public static void check(Context context) {
        Context var0 = context.getApplicationContext();
        if (var0 != null) {
            if (Logger.checkDebugOpen()) {
                String var1 = getProcessName(var0);
                Logger.e("FancyAdSdk-check", "==当前进程名：" + var1);
                Logger.e("FancyAdSdk-check", "==FancyAdsdk接入，环境为debug，初始化配置检测开始==");
                String var2 = var0.getPackageName();
                int var3 = var0.getApplicationInfo().targetSdkVersion;
                PackageManager var4 = var0.getPackageManager();
                List var5 = var4.queryContentProviders(var0.getApplicationInfo().processName, var0.getApplicationInfo().uid, 131072);
                boolean var6 = false;
                boolean var7 = false;
                Iterator var8 = var5.iterator();

                while(true) {
                    while(true) {
                        int var13;
                        while(var8.hasNext()) {
                            ProviderInfo var9 = (ProviderInfo)var8.next();
                            if ("com.bytedance.sdk.openadsdk.multipro.TTMultiProvider".equals(var9.name)) {
                                var6 = true;
                                String var25 = var2 + ".TTMultiProvider";
                                if (!TextUtils.isEmpty(var25) && var25.equals(var9.authority)) {
                                    Logger.e("FancyAdSdk-check", "AndroidManifest.xml中TTMultiProvider配置正常");
                                } else {
                                    Logger.e("FancyAdSdk-check", "AndroidManifest.xml中TTMultiProvider配置异常：android:authorities，请参考接入文档");
                                }
                            } else if (var9.authority.equals(var2 + ".TTFileProvider")) {
                                var7 = true;
                                if (VERSION.SDK_INT >= 24 && var3 >= 24) {
                                    if (var9.exported) {
                                        Logger.e("FancyAdSdk-check", "AndroidManifest.xml中TTFileProvider配置异常：android:exported，请参考接入文档");
                                    }

                                    if (!var9.grantUriPermissions) {
                                        Logger.e("FancyAdSdk-check", "AndroidManifest.xml中TTFileProvider配置异常：android:grantUriPermissions，请参考接入文档");
                                    }

                                    try {
                                        ComponentName var10 = new ComponentName(var2, var9.name);
                                        ProviderInfo var11 = var4.getProviderInfo(var10, 128);
                                        Object var12 = var11.metaData.get("android.support.FILE_PROVIDER_PATHS");
                                        var13 = Integer.valueOf(String.valueOf(var12));
                                        List var14 = check(var0, var13);
                                        if (var14 != null && !var14.isEmpty()) {
                                            List var15 = c();
                                            List var16 = d();
                                            Iterator var17 = var14.iterator();

                                            EnviromentHelper.a var18;
                                            while(var17.hasNext()) {
                                                var18 = (EnviromentHelper.a)var17.next();
                                                if (var18 != null) {
                                                    var15.remove(var18);
                                                    var16.remove(var18);
                                                }
                                            }

                                            if (var15.isEmpty() && var16.isEmpty()) {
                                                Logger.e("FancyAdSdk-check", "AndroidManifest.xml中TTFileProvider配置正常");
                                            } else {
                                                var17 = var15.iterator();

                                                while(var17.hasNext()) {
                                                    var18 = (EnviromentHelper.a)var17.next();
                                                    Logger.e("FancyAdSdk-check", "    TTFileProvider缺少必要路径：" + var18.toString());
                                                }

                                                var17 = var16.iterator();

                                                while(var17.hasNext()) {
                                                    var18 = (EnviromentHelper.a)var17.next();
                                                    Logger.e("FancyAdSdk-check", "    TTFileProvider缺少可选路径：" + var18.toString());
                                                }
                                            }
                                        } else {
                                            Logger.e("FancyAdSdk-check", "AndroidManifest.xml中TTFileProvider中路径配置异常，请参考接入文档");
                                        }
                                    } catch (Throwable var21) {
                                        Logger.e("FancyAdSdk-check", "AndroidManifest.xml中TTFileProvider配置错误，请参考接入文档", var21);
                                    }
                                } else {
                                    Logger.e("FancyAdSdk-check", "TTFileProvider不需要适配：target=" + var3 + "&phone=" + VERSION.SDK_INT + ", require=" + 24);
                                }
                            }
                        }

                        boolean var22 = false;

                        try {
                            var22 = true;
                            PackageInfo var23 = var4.getPackageInfo(var2, 4096);
                            String[] var26 = var23.requestedPermissions;
                            if (var26 != null && var26.length > 0) {
                                List var28 = b();
                                String[] var30 = var26;
                                var13 = var26.length;

                                for(int var34 = 0; var34 < var13; ++var34) {
                                    String var35 = var30[var34];
                                    if (var35 != null) {
                                        var28.remove(var35);
                                    }
                                }

                                if (var28.isEmpty()) {
                                    Logger.e("FancyAdSdk-check", "AndroidManifest.xml中权限配置正常");
                                } else {
                                    Iterator var31 = var28.iterator();

                                    while(var31.hasNext()) {
                                        String var33 = (String)var31.next();
                                        Logger.e("FancyAdSdk-check", "    可能缺少权限：" + var33 + "，请参考接入文档");
                                    }
                                }
                            } else {
                                Logger.e("FancyAdSdk-check", "AndroidManifest.xml中uses-permission配置丢失，请参考接入文档");
                            }
                        } catch (Throwable var20) {
                            Logger.e("FancyAdSdk-check", "AndroidManifest.xml中uses-permission配置错误，请参考接入文档", var20);
                        }

                        try {
//                            if (VERSION.SDK_INT >= 23 && var3 >= 23) {
//                                boolean var24 = w.check().check(var0, "android.permission.READ_PHONE_STATE");
//                                boolean var27 = w.check().check(var0, "android.permission.ACCESS_COARSE_LOCATION");
//                                boolean var29 = w.check().check(var0, "android.permission.ACCESS_FINE_LOCATION");
//                                boolean var32 = w.check().check(var0, "android.permission.WRITE_EXTERNAL_STORAGE");
//                                if (!var24) {
//                                    Logger.oepnDebug("FancyAdSdk-check", "动态权限没有获取，可能影响转化：android.permission.READ_PHONE_STATE");
//                                } else {
//                                    Logger.oepnDebug("FancyAdSdk-check", "动态权限正常：android.permission.READ_PHONE_STATE");
//                                }
//
//                                if (!var27) {
//                                    Logger.oepnDebug("FancyAdSdk-check", "动态权限没有获取，可能影响转化：android.permission.ACCESS_COARSE_LOCATION");
//                                } else {
//                                    Logger.oepnDebug("FancyAdSdk-check", "动态权限正常：android.permission.ACCESS_COARSE_LOCATION");
//                                }
//
//                                if (!var29) {
//                                    Logger.oepnDebug("FancyAdSdk-check", "动态权限没有获取，可能影响转化：android.permission.ACCESS_FINE_LOCATION");
//                                } else {
//                                    Logger.oepnDebug("FancyAdSdk-check", "动态权限正常：android.permission.ACCESS_FINE_LOCATION");
//                                }
//
//                                if (!var32) {
//                                    Logger.oepnDebug("FancyAdSdk-check", "动态权限没有获取，可能影响转化：android.permission.WRITE_EXTERNAL_STORAGE");
//                                } else {
//                                    Logger.oepnDebug("FancyAdSdk-check", "动态权限正常：android.permission.WRITE_EXTERNAL_STORAGE");
//                                }
//                            } else {
//                                Logger.oepnDebug("FancyAdSdk-check", "动态权限不需要适配：target=" + var3 + "&phone=" + VERSION.SDK_INT + ", require=" + 23);
//                            }
                        } catch (Throwable var19) {
                            Logger.e("FancyAdSdk-check", "动态权限获取异常，请检查并详细阅读接入文档", var19);
                        }

                        if (!var6) {
                            Logger.e("FancyAdSdk-check", "××您没有配置TTMultiProvider，请参考接入文档，否则影响转化××");
                        }

                        if (!var7) {
                            Logger.e("FancyAdSdk-check", "××您没有配置TTFileProvider，请参考接入文档，否则影响转化××");
                        }

                        if (!var22) {
                            Logger.e("FancyAdSdk-check", "××您没有配置permission，请参考接入文档，否则影响转化××");
                        }

                        Logger.e("FancyAdSdk-check", "==FancyAdsdk初始化配置检测结束==");
                        return;
                    }
                }
            }
        }
    }

    private static String getProcessName(Context var0) {
        try {
            int var1 = Process.myPid();
            ActivityManager var2 = (ActivityManager)var0.getSystemService("activity");
            if (var2 != null) {
                Iterator var3 = var2.getRunningAppProcesses().iterator();

                while(var3.hasNext()) {
                    RunningAppProcessInfo var4 = (RunningAppProcessInfo)var3.next();
                    if (var4.pid == var1) {
                        return var4.processName;
                    }
                }
            }
        } catch (Throwable var5) {
        }

        return "unknown";
    }

    private static List<String> b() {
        ArrayList var0 = new ArrayList();
        var0.add("android.permission.INTERNET");
        var0.add("android.permission.ACCESS_NETWORK_STATE");
        var0.add("android.permission.ACCESS_WIFI_STATE");
        var0.add("android.permission.READ_PHONE_STATE");
        var0.add("android.permission.WRITE_EXTERNAL_STORAGE");
        var0.add("android.permission.REQUEST_INSTALL_PACKAGES");
        var0.add("android.permission.GET_TASKS");
        var0.add("android.permission.WAKE_LOCK");
        var0.add("android.permission.ACCESS_COARSE_LOCATION");
        var0.add("android.permission.ACCESS_FINE_LOCATION");
        return var0;
    }

    private static List<EnviromentHelper.a> c() {
        ArrayList var0 = new ArrayList();
        var0.add(new EnviromentHelper.a("external-path", "tt_external_download", "Download"));
        var0.add(new EnviromentHelper.a("external-files-path", "tt_external_files_download", "Download"));
        var0.add(new EnviromentHelper.a("files-path", "tt_internal_file_download", "Download"));
        var0.add(new EnviromentHelper.a("cache-path", "tt_internal_cache_download", "Download"));
        return var0;
    }

    private static List<EnviromentHelper.a> d() {
        ArrayList var0 = new ArrayList();
        var0.add(new EnviromentHelper.a("external-path", "tt_external_root", "."));
        return var0;
    }

    @Nullable
    private static List<EnviromentHelper.a> check(Context var0, int var1) {
        try {
            ArrayList var2 = new ArrayList();
            XmlResourceParser var3 = var0.getResources().getXml(var1);

            for(int var4 = var3.getEventType(); var4 != 1; var4 = var3.next()) {
                String var5;
                String var6;
                String var7;
                int var8;
                int var9;
                switch(var4) {
                    case 0:
                    case 1:
                    case 3:
                    default:
                        continue;
                    case 2:
                        var5 = var3.getName();
                        var6 = null;
                        var7 = null;
                        var8 = var3.getAttributeCount();
                        var9 = 0;
                }

                for(; var9 < var8; ++var9) {
                    String var10 = var3.getAttributeName(var9);
                    if (var10.equals("name")) {
                        var6 = var3.getAttributeValue(var9);
                    } else if (var10.equals("path")) {
                        var7 = var3.getAttributeValue(var9);
                    }
                }

                if (!TextUtils.isEmpty(var5) && !TextUtils.isEmpty(var6) && !TextUtils.isEmpty(var7)) {
                    var2.add(new EnviromentHelper.a(var5, var6, var7));
                }
            }

            return var2;
        } catch (Throwable var11) {
            return null;
        }
    }

    private static class a {
        String a;
        String b;
        String c;

        a(String var1, String var2, String var3) {
            this.a = var1;
            this.b = var2;
            this.c = var3;
        }

        public boolean equals(Object var1) {
            if (!(var1 instanceof EnviromentHelper.a)) {
                return super.equals(var1);
            } else {
                EnviromentHelper.a var2 = (EnviromentHelper.a)var1;
                return this.a != null && this.a.equals(var2.a) && this.c != null && this.c.equals(var2.c);
            }
        }

        public String toString() {
            try {
                return "<" + this.a + " name=\"" + this.b + "\" path=\"" + this.c + "\" />";
            } catch (Throwable var2) {
                return super.toString();
            }
        }
    }
}

