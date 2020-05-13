package com.fancy.fancyapi.interf;

import android.view.View;

import androidx.annotation.MainThread;

import com.fancy.adsdk.lib.interf.FancySplashAd;

public interface InterActionListener {
    void onTimeout();


    void onAdClicked(View view, int type);

    void onAdShow(View view, int type);

    void onAdSkip();

    void onAdTimeOver();
}
