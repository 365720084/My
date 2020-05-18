package com.ptg.adsdk.lib.interf;

import android.content.Context;

import com.ptg.adsdk.lib.base.PtgCustomController;
import com.ptg.adsdk.lib.base.PtgSecAbs;
import com.ptg.adsdk.lib.provider.PtgAdNative;

public interface PtgAdManager {

    PtgAdManager setAppId(String var1);

    PtgAdManager setName(String var1);

    PtgAdManager setPaid(boolean var1);

    PtgAdManager setKeywords(String var1);

    PtgAdManager setData(String var1);

    PtgAdManager setTitleBarTheme(int var1);

    PtgAdManager setAllowShowNotifiFromSDK(boolean var1);

    PtgAdManager openDebugMode();

    PtgAdManager setAllowLandingPageShowWhenScreenLock(boolean var1);

    PtgAdManager setGlobalAppDownloadListener(PtgGlobalAppDownloadListener var1);

    PtgGlobalAppDownloadController getGlobalAppDownloadController(Context var1);

    PtgAdManager setDirectDownloadNetworkType(int... var1);

    PtgAdNative createAdNative(Context var1);

    PtgAdManager isUseTextureView(boolean var1);

    PtgAdManager setPtgDownloadEventLogger(PtgDownloadEventLogger var1);

    PtgAdManager setPtgSecAbs(PtgSecAbs var1);

    PtgAdManager setCustomController(PtgCustomController var1);

    PtgAdManager setNeedClearTaskReset(String[] var1);

    void requestPermissionIfNecessary(Context var1);

    boolean tryShowInstallDialogWhenExit(Context var1, ExitInstallListener var2);

    String getSDKVersion();

    boolean onlyVerityPlayable(String var1, int var2, String var3, String var4, String var5);


}

