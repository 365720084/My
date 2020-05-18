package com.ptg.adsdk.lib;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.ptg.adsdk.lib.base.PtgSDKConfig;
import com.ptg.adsdk.lib.core.SdkConfig;
import com.ptg.adsdk.lib.dispatcher.DispatchManager;
import com.ptg.adsdk.lib.dispatcher.filter.GroupFilter;
import com.ptg.adsdk.lib.dispatcher.loader.DummyPolicyLoader;
import com.ptg.adsdk.lib.dispatcher.loader.HttpConfigPolicyLoader;
import com.ptg.adsdk.lib.dispatcher.loader.PolicyLoader;
import com.ptg.adsdk.lib.provider.PtgAdNative;
import com.ptg.adsdk.lib.provider.PtgDispatchProvider;
import com.ptg.adsdk.lib.tracking.TrackingData;
import com.ptg.adsdk.lib.tracking.TrackingManager;

public class PtgAdSdk {
    static private final String TAG = "PtgAdSdk";
    static private Context context;
    static private PtgAdNative globalProvider;
    static private PtgSDKConfig config;
    static private boolean init = false;


    /**
     * ptgAdsdk初始化入口
     *
     * @param context 必须是application context
     * @param config  初始化配置信息，必要参数
     * @param providers 需要分发的广告SDK,包括PtgApiProvider、TTProvider、GdtProvider等
     */
    public static void init(@NonNull Context context, PtgSDKConfig config, PtgAdNative... providers) {
        if (init) {
            Log.e(TAG, "SDK already init");
            return;
        }

        PtgAdSdk.context = context;
        PtgAdSdk.config = config;

        DispatchManager dispatchManager = initDispatchManager(context);

        PtgDispatchProvider dispatchProvider = new PtgDispatchProvider();
        dispatchProvider.setDispatchManager(dispatchManager);

        for (PtgAdNative provider : providers) {
            dispatchProvider.addProvider(provider);
        }

        dispatchProvider.init(context);
        SdkConfig.initConfig(context, config);

        PtgAdSdk.globalProvider = dispatchProvider;
        PtgAdSdk.init = true;

        initTracking(context);
    }

    private static DispatchManager initDispatchManager(Context context) {
        PolicyLoader loader;
        if (TextUtils.isEmpty(PtgAdSdk.config.getPolicyUrl())) {
            loader = new DummyPolicyLoader();
        } else {
            loader = new HttpConfigPolicyLoader(PtgAdSdk.config.getPolicyUrl());
        }

        DispatchManager dispatchManager = new DispatchManager(loader, new GroupFilter());
        dispatchManager.start();

        return dispatchManager;
    }

    private static void initTracking(Context context) {
        final String trackingUrl = "https://l.ptgapi.com/action?action=inAppInit&imei=__IMEI__&os=__OS__&rid=__REQUESTID__&androidid=__ANDROIDID__&app=__APP__&mac=__MAC__&lbs=__LBS__&ts=__TS__&ad=__AD__&err=__ERR__";

        TrackingManager.get().init(context, SdkConfig.getDevice());

        TrackingData trackingData = new TrackingData(config.getAppName(), config.getPtgAppId());
        TrackingManager.get().DoTracking(trackingUrl, trackingData);
    }

    public static PtgAdNative get() {
        return globalProvider;
    }

    public static PtgSDKConfig getConfig() {
        return config;
    }

    public static Context getContext() {
        return context;
    }

    public static boolean isInit() {
        return init;
    }
}
