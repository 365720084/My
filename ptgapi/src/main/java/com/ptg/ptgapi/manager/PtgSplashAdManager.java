package com.ptg.ptgapi.manager;

import android.app.Activity;
import android.view.View;

import com.ptg.adsdk.lib.constants.ProviderError;
import com.ptg.ptgapi.PtgAdProxy;
import com.ptg.ptgapi.utils.Logger;
import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.interf.PtgSplashAd;
import com.ptg.adsdk.lib.model.Ad;
import com.ptg.adsdk.lib.model.AdError;
import com.ptg.adsdk.lib.model.AdObject;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.model.Callback;
import com.ptg.adsdk.lib.provider.PtgAdNative;

import java.util.List;

public class PtgSplashAdManager  {
    private static final String TAG = "PtgSplashAdManager";

    Activity activity;
    SplashManager manager;

    public PtgSplashAdManager(Activity activity) {
        this.activity =  activity;
    }

    public void loadSplashAd(final AdSlot adSlot, final PtgAdNative.SplashAdListener ptgSplashAdListener) {

        PtgAdProxy.getSplashAd(activity,adSlot, new Callback<AdObject>() {
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
                        PtgSplashAd ad = new PtgSplashAd() {

                            @Override
                            public int getInteractionType() {
                                return manager.getInteractionType();
                            }

                            @Override
                            public void setDownloadListener(PtgAppDownloadListener var1) {
                                manager.setDownloadListener(var1);
                            }

                            @Override
                            public void setSplashInteractionListener(final AdInteractionListener listener) {
                                manager.setSplashInteractionListener(new AdInteractionListener() {

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
                            ptgSplashAdListener.onError(ProviderError.SDK_PARAM_ERR, "not container provide");
                            return;
                        }

                        adSlot.getAdContainer().removeAllViews();
                        adSlot.getAdContainer().addView(manager.getSplashView());
                        ptgSplashAdListener.onSplashAdLoad(ad);
                    }
                });


            }

            @Override
            public void onError(AdError error) {
                ptgSplashAdListener.onError(error.getErrorCode(),error.getMessage());
                Logger.e(TAG,error.getMessage());

            }
        });



    }



}
