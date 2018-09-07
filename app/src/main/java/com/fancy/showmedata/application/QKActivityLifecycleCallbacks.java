package com.fancy.showmedata.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.fancy.showmedata.utils.MyActivityManager;
import com.fancy.showmedata.utils.SharedPreferencesUtils;
import com.fancy.showmedata.utils.log.LogUtils;

/**
 * User: Smile(lijianhy1990@gmail.com)
 * Date: 2016-08-23
 * Time: 13:56
 */
public class QKActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    public static final String LIFECYCLE = "ActivityLife";
    public static int foregroundCount = 0; // 位于前台的 Activity 的数目
    private static Activity sActivity;
    private int mFinalCount;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        LogUtils.d("ActivityLife", activity + "onActivityCreated");
        MyActivityManager.getInstance().addActivity(activity);
    }


    @Override
    public void onActivityStarted(Activity activity) {
        LogUtils.d("ActivityLife", activity + "onActivityStarted");
        mFinalCount++;
        //如果mFinalCount ==1，说明是从后台到前台
        if (mFinalCount == 1) {
            //说明从后台回到了前台
        }
        LogUtils.e("onActivityStarted", foregroundCount + "");
        foregroundCount++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        LogUtils.d("ActivityLife", activity + "onActivityResumed");
        MyActivityManager.getInstance().setCurrentActivity(activity);

    }


    @Override
    public void onActivityPaused(Activity activity) {
        LogUtils.d("ActivityLife", activity + "onActivityPaused");

    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.d("ActivityLife", activity + "onActivityStopped");
        mFinalCount--;
        //如果mFinalCount ==0，说明是前台到后台
        Log.d("onActivityStopped", mFinalCount + "");
        if (mFinalCount == 0) {
            //说明从前台回到了后台
            LogUtils.d("ActivityLife", activity + "onActivityBackGroud");

        }

        foregroundCount--;
        LogUtils.d("onActivityStopped", foregroundCount + "");
        if (foregroundCount <= 0) {
            SharedPreferencesUtils.setParam(activity, Constants.KEY_PAUSE_TIME, System.currentTimeMillis());
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        LogUtils.d("ActivityLife", activity + "onActivitySaveInstanceState");

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtils.d("ActivityLife", activity + "onActivityDestroyed");
        MyActivityManager.getInstance().removeActivity(activity);

    }



}
