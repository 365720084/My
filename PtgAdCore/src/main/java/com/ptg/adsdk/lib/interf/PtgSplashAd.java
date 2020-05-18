package com.ptg.adsdk.lib.interf;

import android.view.View;

import androidx.annotation.NonNull;


import java.util.Map;

public interface PtgSplashAd{
    /* 当前广告的互动类型 */
    int getInteractionType();

    void setDownloadListener(PtgAppDownloadListener downloadListener);

    void setSplashInteractionListener(AdInteractionListener listener);

    public interface AdInteractionListener {
        void onAdClicked(View view, int type);

        void onAdShow(View view, int type);

        void onAdSkip();

        void onAdTimeOver();
    }
}
