package com.ptg.adsdk.lib.core.model;

import android.content.Context;

import com.ptg.adsdk.lib.core.util.AppUtil;

import org.json.JSONException;
import org.json.JSONObject;


public class AppVersion {

    private String appVersion;

    private String appName;
    private String pkgName;

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }


    public static AppVersion generate(Context context) {
        AppVersion info = new AppVersion();
        info.setAppVersion(AppUtil.getAppVersion(context));
        info.setAppName(AppUtil.getAppName(context));
        info.setPkgName(AppUtil.getPackageName(context));
        return info;
    }

    @Override
    public String toString() {
        return getJson().toString();
    }

    public JSONObject getJson() {
        JSONObject object = new JSONObject();
        try {
            object.put("app_version", appVersion);
            object.put("app_name", appName);
            object.put("pkg_name", pkgName);
        } catch (JSONException ignored) {
        }
        return object;
    }
}
