package com.ptg.ptgapi.manager;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.ptg.adsdk.lib.component.PtgDislikeDialogAbstract;
import com.ptg.adsdk.lib.interf.PtgAdDislike;
import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.interf.PtgNativeExpressAd;
import com.ptg.adsdk.lib.model.Ad;
import com.ptg.adsdk.lib.model.AdError;
import com.ptg.adsdk.lib.model.AdObject;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.model.Callback;
import com.ptg.adsdk.lib.model.PtgFilterWord;
import com.ptg.adsdk.lib.provider.PtgAdNative;
import com.ptg.ptgapi.PtgAdProxy;
import com.ptg.ptgapi.utils.MainLooper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PtgFeedExpressAdManager {
    private static final String TAG = "PtgSplashAdManager";
    //开屏广告加载超时时间,建议大于3000,这里为了冷启动第一次加载到广告并且展示,示例设置了3000ms
    private static final int AD_TIME_OUT = 3000;
    private String mCodeId = "801121648";

    Context context;
    Activity activity;

    public PtgFeedExpressAdManager() {
//        this.context = context;

    }

    public void loadNativeExpressAd(final Context context, final AdSlot adSlot, final PtgAdNative.NativeExpressAdListener ptgFeedAdListener) {

        PtgAdProxy.getFeedAd(context,adSlot, new Callback<AdObject>() {

            @Override
            public void onSuccess(final AdObject object) {
                MainLooper.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<PtgNativeExpressAd> adList = new ArrayList<>();
                        List<Ad> ads = object.getAd();
                        for (int i = 0; i < ads.size(); i++) {
                            final Ad ptgAd = ads.get(i);
                            final NativeExpressAdManager feedManager = new NativeExpressAdManager(context);
                            feedManager.setAd(ptgAd);
                            feedManager.setAdSlot(adSlot);
                            PtgNativeExpressAd ptgNativeExpressAd=new PtgNativeExpressAd() {
                                @Override
                                public View getExpressAdView() {
                                    return feedManager.getExpressAdView();
                                }

                                @Override
                                public int getImageMode() {
                                    return 0;
                                }

                                @Override
                                public List<PtgFilterWord> getFilterWords() {
                                    return null;
                                }

                                @Override
                                public void setExpressInteractionListener(ExpressAdInteractionListener var1) {
                                    feedManager.setExpressInteractionListener(var1);
                                }

                                @Override
                                public void setExpressInteractionListener(AdInteractionListener var1) {
                                    feedManager.setExpressInteractionListener(var1);

                                }

                                @Override
                                public void setDownloadListener(PtgAppDownloadListener var1) {
                                    feedManager.setDownloadListener(var1);

                                }

                                @Override
                                public int getInteractionType() {
                                    return 0;
                                }

                                @Override
                                public void render() {

                                }

                                @Override
                                public void destroy() {

                                }

                                @Override
                                public void setDislikeCallback(Activity var1, PtgAdDislike.DislikeInteractionCallback var2) {

                                }

                                @Override
                                public void setDislikeDialog(PtgDislikeDialogAbstract var1) {

                                }

                                @Override
                                public void showInteractionExpressAd(Activity var1) {

                                }

                                @Override
                                public void setSlideIntervalTime(int var1) {

                                }

                                @Override
                                public void setVideoAdListener(ExpressVideoAdListener var1) {

                                }

                                @Override
                                public void setCanInterruptVideoPlay(boolean var1) {

                                }

                                @Override
                                public Map<String, Object> getMediaExtraInfo() {
                                    return null;
                                }
                            };
                            adList.add(ptgNativeExpressAd);
                        }
                        ptgFeedAdListener.onNativeExpressAdLoad(adList.get(0));

                    }
                });
            }

            @Override
            public void onError(AdError error) {
                ptgFeedAdListener.onError(error.getErrorCode(), error.getMessage());

            }
        });
    }


}
