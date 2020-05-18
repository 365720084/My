package com.ptg.gdt.provider;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.ptg.adsdk.lib.PtgAdSdk;
import com.ptg.adsdk.lib.constants.ProviderError;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.provider.PtgAdNative;
import com.ptg.gdt.adapter.GdtNativeADUnifiedAdapter;
import com.ptg.gdt.adapter.GdtSplashADAdapter;
import com.ptg.gdt.adapter.NativeExpressADAdapter;
import com.ptg.gdt.common.GdtAdapterHelper;
import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.ads.nativ.ADSize;
import com.qq.e.ads.nativ.NativeExpressAD;
import com.qq.e.ads.nativ.NativeUnifiedAD;
import com.qq.e.ads.splash.SplashAD;

public class GdtProvider implements PtgAdNative {
    static final String providerName = "gdt";

    public static final int MIN_VIDEO_DURATION = 5;
    public static final int MAX_VIDEO_DURATION = 60;

    @Override
    public void init(Context context) {
    }

    @Override
    public String getName() {
        return providerName;
    }

    @Override
    public void loadFeedAd(Context context, AdSlot slot, @NonNull FeedAdListener listener) {
        NativeUnifiedAD nativeUnifiedAD = new NativeUnifiedAD(context, PtgAdSdk.getConfig().getGdtAppId(), slot.getCodeId(), new GdtNativeADUnifiedAdapter(context, slot.getClickView(), listener));
        nativeUnifiedAD.setVideoADContainerRender(VideoOption.VideoADContainerRender.SDK);
        if (slot.isAutoPlay()) {
            nativeUnifiedAD.setVideoPlayPolicy(VideoOption.VideoPlayPolicy.AUTO);
        }
        nativeUnifiedAD.setMinVideoDuration(MIN_VIDEO_DURATION);
        nativeUnifiedAD.setMaxVideoDuration(MAX_VIDEO_DURATION);
        nativeUnifiedAD.loadData(1);
    }

    @Override
    public void loadSplashAd(Activity activity, AdSlot slot, @NonNull final SplashAdListener listener) {
        ViewGroup adContainer = slot.getAdContainer();
        if (null == adContainer) {
            listener.onError(ProviderError.SDK_PARAM_ERR, "no container provide");
            return;
        }

        SplashAD splashAD = new SplashAD(activity, PtgAdSdk.getConfig().getGdtAppId(), slot.getCodeId(), new GdtSplashADAdapter(adContainer, listener));
        splashAD.fetchAndShowIn(adContainer);
    }

    @Override
    public void loadNativeExpressAd(Context context, AdSlot slot, @NonNull NativeExpressAdListener listener) {
        float expressViewAcceptedWidth = slot.getExpressViewAcceptedWidth();
        float expressViewAcceptedHeight = slot.getExpressViewAcceptedHeight();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w = (GdtAdapterHelper.dip2px(context, expressViewAcceptedWidth) < dm.widthPixels ? (int) expressViewAcceptedWidth : ADSize.FULL_WIDTH);
        int h = (expressViewAcceptedHeight <= 0 ? ADSize.AUTO_HEIGHT : (int) expressViewAcceptedHeight);

        NativeExpressAD nativeExpressAD = new NativeExpressAD(context, new ADSize(w, h), PtgAdSdk.getConfig().getGdtAppId(), slot.getCodeId(), new NativeExpressADAdapter(listener));
        // 注意：如果您在平台上新建原生模板广告位时，选择了支持视频，那么可以进行个性化设置（可选）
        nativeExpressAD.setVideoOption(new VideoOption.Builder()
                .setAutoPlayPolicy(VideoOption.AutoPlayPolicy.WIFI) // WIFI 环境下可以自动播放视频
                .setAutoPlayMuted(true) // 自动播放时为静音
                .build());
        nativeExpressAD.setVideoPlayPolicy(VideoOption.VideoPlayPolicy.AUTO); // 本次拉回的视频广告，从用户的角度看是自动播放的
        nativeExpressAD.setMinVideoDuration(MIN_VIDEO_DURATION);
        nativeExpressAD.setMaxVideoDuration(MAX_VIDEO_DURATION);
        nativeExpressAD.loadAD(1);
    }
}
