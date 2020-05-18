package com.ptg.adsdk.lib.interf;

import android.app.Activity;

import androidx.annotation.MainThread;


import java.util.Map;

public interface PtgInteractionAd {
    void setAdInteractionListener(AdInteractionListener var1);

    void setDownloadListener(PtgAppDownloadListener var1);

    int getInteractionType();

    Map<String, Object> getMediaExtraInfo();

    @MainThread
    void showInteractionAd(Activity var1);

    public interface AdInteractionListener {
        void onAdClicked();

        void onAdShow();

        void onAdDismiss();
    }
}
