package com.fancy.fancyapi.manager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fancy.adsdk.lib.component.FancyDislikeDialogAbstract;
import com.fancy.adsdk.lib.constants.FancyAdconstant;
import com.fancy.adsdk.lib.interf.FancyAdDislike;
import com.fancy.adsdk.lib.interf.FancyAppDownloadListener;
import com.fancy.adsdk.lib.interf.FancyDownloadStatusController;
import com.fancy.adsdk.lib.interf.FancyFeedAd;
import com.fancy.adsdk.lib.interf.FancyNativeExpressAd;
import com.fancy.adsdk.lib.model.Ad;
import com.fancy.adsdk.lib.model.AdError;
import com.fancy.adsdk.lib.model.AdObject;
import com.fancy.adsdk.lib.model.AdSlot;
import com.fancy.adsdk.lib.model.Callback;
import com.fancy.adsdk.lib.model.FancyFilterWord;
import com.fancy.adsdk.lib.model.FancyImage;
import com.fancy.adsdk.lib.provider.FancyAdNative;
import com.fancy.fancyapi.FancyAdProxy;
import com.fancy.fancyapi.utils.MainLooper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FancyFeedExpressAdManager {
    private static final String TAG = "FancySplashAdManager";
    //开屏广告加载超时时间,建议大于3000,这里为了冷启动第一次加载到广告并且展示,示例设置了3000ms
    private static final int AD_TIME_OUT = 3000;
    private String mCodeId = "801121648";

    Context context;
    Activity activity;

    public FancyFeedExpressAdManager() {
//        this.context = context;

    }

    public void loadNativeExpressAd(final Context context, AdSlot adSlot, final FancyAdNative.NativeExpressAdListener fancyFeedAdListener) {

        FancyAdProxy.getFeedAd(adSlot, new Callback<AdObject>() {

            @Override
            public void onSuccess(final AdObject object) {
                MainLooper.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<FancyNativeExpressAd> adList = new ArrayList<>();
                        List<Ad> ads = object.getAd();
                        for (int i = 0; i < ads.size(); i++) {
                            final Ad fancyAd = ads.get(i);
                            final NativeExpressAdManager feedManager = new NativeExpressAdManager(context);
                            feedManager.setAd(fancyAd);
                            FancyNativeExpressAd fancyNativeExpressAd=new FancyNativeExpressAd() {
                                @Override
                                public View getExpressAdView() {
                                    return feedManager.getExpressAdView();
                                }

                                @Override
                                public int getImageMode() {
                                    return 0;
                                }

                                @Override
                                public List<FancyFilterWord> getFilterWords() {
                                    return null;
                                }

                                @Override
                                public void setExpressInteractionListener(ExpressAdInteractionListener var1) {

                                }

                                @Override
                                public void setExpressInteractionListener(AdInteractionListener var1) {

                                }

                                @Override
                                public void setDownloadListener(FancyAppDownloadListener var1) {

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
                                public void setDislikeCallback(Activity var1, FancyAdDislike.DislikeInteractionCallback var2) {

                                }

                                @Override
                                public void setDislikeDialog(FancyDislikeDialogAbstract var1) {

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
                            adList.add(fancyNativeExpressAd);
                        }
                        fancyFeedAdListener.onNativeExpressAdLoad(adList.get(0));

                    }
                });
            }

            @Override
            public void onError(AdError error) {
                fancyFeedAdListener.onError(error.getErrorCode(), error.getMessage());

            }
        });
    }


}
