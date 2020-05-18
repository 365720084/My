package com.ptg.ptgapi.manager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ptg.adsdk.lib.constants.PtgAdconstant;
import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.interf.PtgDownloadStatusController;
import com.ptg.adsdk.lib.interf.PtgFeedAd;
import com.ptg.adsdk.lib.model.Ad;
import com.ptg.adsdk.lib.model.AdError;
import com.ptg.adsdk.lib.model.AdObject;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.model.Callback;
import com.ptg.adsdk.lib.model.PtgFilterWord;
import com.ptg.adsdk.lib.model.PtgImage;
import com.ptg.adsdk.lib.provider.PtgAdNative;
import com.ptg.ptgapi.PtgAdProxy;
import com.ptg.ptgapi.utils.MainLooper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PtgFeedAdManager {
    private static final String TAG = "PtgSplashAdManager";
    //开屏广告加载超时时间,建议大于3000,这里为了冷启动第一次加载到广告并且展示,示例设置了3000ms
    private static final int AD_TIME_OUT = 3000;
    private String mCodeId = "801121648";

    Context context;
    Activity activity;

    public PtgFeedAdManager() {
//        this.context = context;

    }


    /**
     * 加载自渲染信息流广告
     */
    public void loadFeedAd(final Context context, AdSlot adSlot, final PtgAdNative.FeedAdListener ptgFeedAdListener) {

        PtgAdProxy.getFeedAd(context,adSlot, new Callback<AdObject>() {
            @Override
            public void onSuccess(final AdObject object) {
                MainLooper.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<PtgFeedAd> adList = new ArrayList<>();
                        List<Ad> ads = object.getAd();
                        for (int i = 0; i < ads.size(); i++) {
                            final Ad ptgAd = ads.get(i);
                            PtgFeedAd ad = new PtgFeedAd() {

                                @Override
                                public void setVideoAdListener(VideoAdListener listener) {

                                }

                                @Override
                                public double getVideoDuration() {
                                    return ptgAd.getDuration();
                                }

                                @Nullable
                                @Override
                                public PtgImage getVideoCoverImage() {
                                    return null;
                                }

                                @Override
                                public Bitmap getAdLogo() {
                                    return null;
                                }

                                @Override
                                public String getTitle() {
                                    return ptgAd.getTitle();
                                }

                                @Override
                                public String getDescription() {
                                    return ptgAd.getDesc();
                                }

                                @Override
                                public String getButtonText() {
                                    return null;
                                }

                                @Override
                                public int getAppScore() {
                                    return 0;
                                }

                                @Override
                                public int getAppCommentNum() {
                                    return 0;
                                }

                                @Override
                                public int getAppSize() {
                                    return 0;
                                }

                                @Override
                                public String getSource() {
                                    return ptgAd.getSource();
                                }

                                @Override
                                public PtgImage getIcon() {
                                    return null;
                                }

                                @Override
                                public List<PtgImage> getImageList() {
                                    List<PtgImage> ptgImages = new ArrayList<>();
                                    PtgImage ptgImage = new PtgImage(0, 0, ptgAd.getSrc());
                                    ptgImages.add(ptgImage);
                                    List<String> strings = ptgAd.getExt_urls();
                                    if (strings != null && strings.size() > 0) {
                                        for (int i = 0; i < strings.size(); i++) {
                                            PtgImage img = new PtgImage(0, 0, strings.get(i));
                                            ptgImages.add(img);
                                        }
                                    }
                                    return ptgImages;
                                }

                                @Override
                                public int getInteractionType() {
                                    return PtgAdconstant.INTERACTION_TYPE_LANDING_PAGE;
                                }

                                @Override
                                public int getImageMode() {
                                    return PtgAdconstant.FEED_AD;
                                }

                                @Override
                                public List<PtgFilterWord> getFilterWords() {
                                    return null;
                                }

                                @Override
                                public PtgDownloadStatusController getDownloadStatusController() {
                                    return null;
                                }

                                @Override
                                public void registerViewForInteraction(@NonNull ViewGroup var1, @NonNull View var2, AdInteractionListener var3) {
                                }

                                @Override
                                public void registerViewForInteraction(@NonNull ViewGroup var1, @NonNull List<View> var2, @Nullable List<View> var3, AdInteractionListener var4) {

                                }


                                @Override
                                public void setDownloadListener(PtgAppDownloadListener var1) {

                                }

                                @Override
                                public void setActivityForDownloadApp(@NonNull Activity var1) {

                                }

                                @Override
                                public View getAdView() {
                                    return null;
                                }

                                @Override
                                public Map<String, Object> getMediaExtraInfo() {
                                    return null;
                                }

                                @Override
                                public void destroy() {

                                }

                                @Override
                                public void resume() {

                                }
                            };
                            adList.add(ad);
                        }

                        ptgFeedAdListener.onFeedAdLoad(adList.get(0));
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
