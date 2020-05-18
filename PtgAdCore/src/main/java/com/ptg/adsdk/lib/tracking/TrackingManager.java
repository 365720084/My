package com.ptg.adsdk.lib.tracking;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.ptg.adsdk.lib.core.model.AppVersion;
import com.ptg.adsdk.lib.core.model.DeviceInfo;
import com.ptg.adsdk.lib.core.net.NetUtils;
import com.ptg.adsdk.lib.utils.CommonUtil;

public class TrackingManager {
    static final String TAG = "TrackingManager";
    private static TrackingManager trackingManager;
    private static DeviceInfo deviceInfo;
    private static AppVersion appVersion;

    private TrackingManager() {
        deviceInfo = new DeviceInfo();
        appVersion = new AppVersion();
    }

    public void init(Context context, DeviceInfo deviceInfo) {
        TrackingManager.deviceInfo = deviceInfo;
        appVersion = AppVersion.generate(context);
        trackingManager = this;
    }

    public static TrackingManager get() {
        if (trackingManager == null) {
            trackingManager = new TrackingManager();
        }
        return trackingManager;
    }

    public void DoTracking(String url, TrackingData data) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        url = urlMacroReplace(url, data);
        Log.d(TAG, String.format("DoTracking url:%s", url));

        NetUtils.asyncSimpleReport(url);
    }


    // TODO refactoring later, performance issue
    private String urlMacroReplace(String url, TrackingData data) {
        data.setDeviceInfo(deviceInfo);
        data.setAppVersion(appVersion);

        url = doUrlMacroReplace(url, TrackingConstant.MacroKeyErrorCode, String.format("%d", data.getErrorCode()));
        url = doUrlMacroReplace(url, TrackingConstant.MacroKeyOS, TrackingConstant.MacroOSFieldAndroid);
        url = doUrlMacroReplace(url, TrackingConstant.MacroKeyIMEI, data.getMd5IMEI());
        url = doUrlMacroReplace(url, TrackingConstant.MacroKeyMAC, data.getMd5MAC());
        url = doUrlMacroReplace(url, TrackingConstant.MacroKeyAndroidID, data.getMd5MAC());
        url = doUrlMacroReplace(url, TrackingConstant.MacroKeyRequestId, data.getRequestId());
        url = doUrlMacroReplace(url, TrackingConstant.MacroKeyAPP, data.getAppPackageName());
        url = doUrlMacroReplace(url, TrackingConstant.MacroKeyAD, String.format("%d", data.getAdKey()));
        url = doUrlMacroReplace(url, TrackingConstant.MacroKeyTimestamp, String.format("%d", data.getUnixTimestamp()));
        url = doUrlMacroReplace(url, TrackingConstant.MacroKeySlotId, data.getPtgSlotId());
        url = doUrlMacroReplace(url, TrackingConstant.MacroKeyConsumerSlotId, data.getCodeId());
        url = doUrlMacroReplace(url, TrackingConstant.MacroKeyOAID, data.getOaid());

        return url;
    }

    private String doUrlMacroReplace(String Url, String macro, String value) {
        if (TextUtils.isEmpty(value)) {
            return Url;
        }

        return Url.replaceAll(macro, CommonUtil.encodingUTF8(value));
    }
}
