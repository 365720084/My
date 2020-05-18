package com.ptg.tt.adaptor;

import android.view.View;

import com.bytedance.sdk.openadsdk.TTSplashAd;
import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.interf.PtgSplashAd;
import com.ptg.tt.utils.Transformer;

public class TTSplashAdAdaptor implements PtgSplashAd {
    static final String TAG = "TTSplashAdAdaptor";
    TTSplashAd ttSplashAd;

    public TTSplashAdAdaptor(TTSplashAd ttSplashAd) {
        this.ttSplashAd = ttSplashAd;
    }

    @Override
    public int getInteractionType() {
        return Transformer.PtgInteractionType(ttSplashAd.getInteractionType());
    }

    @Override
    public void setDownloadListener(PtgAppDownloadListener downloadListener) {
        ttSplashAd.setDownloadListener(Transformer.TTAppDownloadListener(downloadListener));
    }

    @Override
    public void setSplashInteractionListener(AdInteractionListener listener) {
        ttSplashAd.setSplashInteractionListener(createAdInteractionListener(listener));
    }

    private TTSplashAd.AdInteractionListener createAdInteractionListener(final AdInteractionListener listener) {
        return new TTSplashAd.AdInteractionListener() {

            @Override
            public void onAdClicked(View view, int i) {
                listener.onAdClicked(view, Transformer.PtgInteractionType(i));
            }

            @Override
            public void onAdShow(View view, int i) {
                listener.onAdShow(view, Transformer.PtgInteractionType(i));
            }

            @Override
            public void onAdSkip() {
                listener.onAdSkip();
            }

            @Override
            public void onAdTimeOver() {
                listener.onAdTimeOver();
            }
        };
    }
}
