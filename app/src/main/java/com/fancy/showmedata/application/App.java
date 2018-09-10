package com.fancy.showmedata.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fancy.showmedata.BuildConfig;
import com.fancy.showmedata.router.ActivityPath;
import com.fancy.showmedata.router.RouterUtils;
import com.fancy.showmedata.utils.ToastUtils;
import com.fancy.showmedata.utils.cockroach.Cockroach;
import com.fancy.showmedata.utils.cockroach.ExceptionHandler;
import com.fancy.showmedata.utils.log.LogFileStyle;
import com.fancy.showmedata.utils.log.LogUtils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;

import org.mym.plog.DebugPrinter;
import org.mym.plog.PLog;
import org.mym.plog.config.PLogConfig;


/**
 * Created by bruce on 2017/11/7.
 */

public class App extends Application {
    private static Context sAppCtx;
    private static Activity sActivity;
    private int mFinalCount;
//    public static WXSDKInstance mWXSDKInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppCtx = this;
        initARouter();
        initBugly();
        registLifeListener();
        initPLog();
        installCockroach();

    }

    private void registLifeListener() {
        QKActivityLifecycleCallbacks   lifecycleCallbacks = new QKActivityLifecycleCallbacks();
        registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    private void initPLog() {
        PLogConfig config = PLogConfig.newBuilder()
                .emptyMsg("empty log")
                .emptyMsgLevel(Log.VERBOSE)
                .globalStackOffset(1)
                .useAutoTag(true)
                .globalTag(LogUtils.TAG)
                .build();
        DebugPrinter debugPrinter = new DebugPrinter(BuildConfig.LOG_DEBUG);
        debugPrinter.setStyle(new LogFileStyle());
        PLog.prepare(debugPrinter);

        PLog.init(config);
    }

    private void installCockroach() {
        Cockroach.install(new ExceptionHandler() {
            @Override
            protected void onUncaughtExceptionHappened(Thread thread, Throwable throwable) {

                //TODO 会自动上报 确认后可以取消下面的上报
                CrashReport.postCatchedException(throwable);
//                RouterUtils.routerGoWithOutParma(ActivityPath.NATIVE_MAIN);

                //存到本地
                if(BuildConfig.LOG_DEBUG) {
                    //打印崩溃
                    throwable.printStackTrace();
                    ToastUtils.showToastCenter(App.getAppContext(), "发生了崩溃,回到主页");
                }
            }

            @Override
            protected void onBandageExceptionHappened(Throwable throwable) {
                //主动上报
                CrashReport.postCatchedException(throwable);
//                RouterUtils.routerGoWithOutParma(ActivityPath.NATIVE_MAIN);
                //存到本地
                if(BuildConfig.LOG_DEBUG) {
                    //打印崩溃
                    throwable.printStackTrace();
                    ToastUtils.showToastCenter(App.getAppContext(), "发生了崩溃,回到主页");
                }
            }

            @Override
            protected void onEnterSafeMode() {

            }

        });

    }



    private void initARouter() {
        ARouter.init(this);
    }







    private void initBugly() {
        Beta.initDelay = 1 * 1000;
        Beta.autoCheckUpgrade = false;
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Context context = getApplicationContext();
        // 获取当前包名
//        String packageName = context.getPackageName();
//        // 获取当前进程名
//        String processName = getProcessName(android.os.Process.myPid());
//        // 设置是否为上报进程
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
//        strategy.setUploadProcess(processName == null || processName.equals(packageName));

        // 设置是否开启热更新能力，默认为true
        Beta.enableHotfix = true;
        // 设置是否自动下载补丁
        Beta.canAutoDownloadPatch = true;
        // 设置是否自动合成补丁
        Beta.canAutoPatch = true;

        Bugly.init(this, "d80156d94e", false);
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String channel = appInfo.metaData.getString("UMENG_CHANNEL", "developer");
//            PrefUtil.setKey(LoginUtil.CHANEL, channel);
            Bugly.setAppChannel(context, channel);
        } catch (Exception e) {
            CrashReport.postCatchedException(e);
            Log.e("Error", e.getMessage());
        }
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getAppContext());
        CrashReport.initCrashReport(getAppContext(), "d80156d94e", false, strategy);
    }

    public static Context getAppContext() {
        return sAppCtx;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        Beta.installTinker();
    }

    public static Activity getActivity() {
        return sActivity;
    }

}
