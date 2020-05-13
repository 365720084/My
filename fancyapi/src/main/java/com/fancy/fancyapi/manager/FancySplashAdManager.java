package com.fancy.fancyapi.manager;

import android.app.Activity;
import android.view.View;

import com.fancy.adsdk.lib.constants.ProviderError;
import com.fancy.fancyapi.FancyAdProxy;
import com.fancy.adsdk.lib.interf.FancyAppDownloadListener;
import com.fancy.adsdk.lib.interf.FancySplashAd;
import com.fancy.adsdk.lib.model.Ad;
import com.fancy.adsdk.lib.model.AdError;
import com.fancy.adsdk.lib.model.AdObject;
import com.fancy.adsdk.lib.model.AdSlot;
import com.fancy.adsdk.lib.model.Callback;
import com.fancy.adsdk.lib.provider.FancyAdNative;
import com.fancy.fancyapi.interf.InterActionListener;
import com.fancy.fancyapi.utils.Logger;

import java.util.List;

public class FancySplashAdManager  {
    private static final String TAG = "FancySplashAdManager";

    Activity activity;
    SplashManager manager;

    public FancySplashAdManager(Activity activity) {
        this.activity =  activity;
    }

    public void loadSplashAd(final AdSlot adSlot, final FancyAdNative.SplashAdListener fancySplashAdListener) {

        FancyAdProxy.getSplashAd(adSlot, new Callback<AdObject>() {
            @Override
            public void onSuccess(final AdObject object) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        manager = new SplashManager(activity);
                        manager.setAdSlot(adSlot);
                        final List<Ad> adList = object.getAd();
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                manager.setAdList(adList);

                            }
                        });
                        FancySplashAd ad = new FancySplashAd() {

                            @Override
                            public int getInteractionType() {
                                return manager.getInteractionType();
                            }

                            @Override
                            public void setDownloadListener(FancyAppDownloadListener var1) {
                                manager.setDownloadListener(var1);
                            }

                            @Override
                            public void setSplashInteractionListener(final AdInteractionListener listener) {
                                manager.setInterActionListener(new InterActionListener() {
                                    @Override
                                    public void onTimeout() {

                                    }

                                    @Override
                                    public void onAdClicked(View view, int type) {
                                       listener.onAdClicked(view,type);

                                    }

                                    @Override
                                    public void onAdShow(View view, int type) {
                                        listener.onAdShow(view,type);

                                    }

                                    @Override
                                    public void onAdSkip() {
                                        listener.onAdSkip();


                                    }

                                    @Override
                                    public void onAdTimeOver() {
                                        listener.onAdTimeOver();

                                    }
                                });

                            }

                        };

                        if (adSlot.getAdContainer() == null) {
                            fancySplashAdListener.onError(ProviderError.SDK_PARAM_ERR, "not container provide");
                            return;
                        }

                        adSlot.getAdContainer().removeAllViews();
                        adSlot.getAdContainer().addView(manager.getSplashView());
                        fancySplashAdListener.onSplashAdLoad(ad);
                    }
                });


            }

            @Override
            public void onError(AdError error) {
                fancySplashAdListener.onError(error.getErrorCode(),error.getMessage());
                Logger.e(TAG,error.getMessage());

            }
        });



    }



}
