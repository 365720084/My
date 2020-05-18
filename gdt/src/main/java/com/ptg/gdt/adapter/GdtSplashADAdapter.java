package com.ptg.gdt.adapter;

import android.os.SystemClock;
import android.view.View;

import com.ptg.adsdk.lib.constants.InteractionType;
import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.interf.PtgSplashAd;
import com.ptg.adsdk.lib.provider.PtgAdNative;
import com.qq.e.ads.splash.SplashADListener;
import com.qq.e.comm.util.AdError;

public class GdtSplashADAdapter implements SplashADListener {
    private View adContainer;
    private PtgAdNative.SplashAdListener listener;
    private volatile long millisUntilFinished;

    private PtgSplashAd.AdInteractionListener adInteractionListener;

    public GdtSplashADAdapter(View adContainer, PtgAdNative.SplashAdListener listener) {
        this.adContainer = adContainer;
        this.listener = listener;
    }

    @Override
    public void onADDismissed() {
        if (millisUntilFinished > 0 && adInteractionListener != null) {
            adInteractionListener.onAdSkip();
        }
    }

    @Override
    public void onNoAD(AdError adError) {
        listener.onError(adError.getErrorCode(), adError.getErrorMsg());
    }

    @Override
    public void onADPresent() {
    }

    @Override
    public void onADClicked() {
        if (adInteractionListener != null) {
            adInteractionListener.onAdClicked(adContainer, InteractionType.LANDING_PAGE);
        }
    }

    @Override
    public void onADTick(long millisUntilFinished) {
        this.millisUntilFinished = millisUntilFinished;
        if (millisUntilFinished <= 0 && adInteractionListener != null) {
            adInteractionListener.onAdTimeOver();
        }
    }

    @Override
    public void onADExposure() {
        if (adInteractionListener != null) {
            adInteractionListener.onAdShow(adContainer, InteractionType.LANDING_PAGE);
        }
    }

    @Override
    public void onADLoaded(long expireTimestamp) {
        if (SystemClock.elapsedRealtime() < expireTimestamp) {
            listener.onSplashAdLoad(new PtgSplashAd() {
                @Override
                public int getInteractionType() {
                    return InteractionType.LANDING_PAGE;
                }
                @Override
                public void setDownloadListener(PtgAppDownloadListener downloadListener) {
                }
                @Override
                public void setSplashInteractionListener(AdInteractionListener listener) {
                    adInteractionListener = listener;
                }
            });
        } else {
            listener.onTimeout();
        }
    }
}
