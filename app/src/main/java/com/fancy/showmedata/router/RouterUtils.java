package com.fancy.showmedata.router;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fancy.showmedata.utils.AppUtils;


/**
 * Created by bruce on 2018/3/7.
 */

public class RouterUtils {
    private static String oldPath = "";
    private static Uri oldUri;
    private static long internal=1000;


    /**
     * path页面路径，一般用于app内部做页面跳转
     * @param path a页面path
     */
    public static void routerGoWithOutParma(String path) {

        if (TextUtils.isEmpty(path)) {
            return;
        }
        if (AppUtils.isFastClick(internal)) {
            return;
        }
        oldPath = path;
        try {
            ARouter.getInstance().build(path).navigation();
        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public static void routerGoWithParma(String path, Bundle bundle) {
        if (TextUtils.isEmpty(path)) {
            return;
        }
        if (AppUtils.isFastClick(internal)) {
            return;
        }
        oldPath = path;
        ARouter.getInstance().build(path).with(bundle).navigation();
    }

    /**
     * 传入完整的一个url路径，一般用于接口返回、h5交互时传参跳转
     * @param url
     */
    public static void goWithUri(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (AppUtils.isFastClick(internal)) {
            return;
        }
        oldPath = url;
        Uri uri = Uri.parse(url);
        ARouter.getInstance().build(uri).navigation();
    }

    /**
     *
     * @param url
     * @param bundle
     */
    public static void goWithUriWithParms(String url, Bundle bundle) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (AppUtils.isFastClick(internal)) {
            return;
        }
        oldPath = url;
        ARouter.getInstance().build(url).with(bundle).navigation();
    }



}
