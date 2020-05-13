package com.fancy.fancyapi.manager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fancy.adsdk.lib.component.FancyDislikeDialogAbstract;
import com.fancy.adsdk.lib.constants.AdConstant;
import com.fancy.adsdk.lib.constants.FancyAdconstant;
import com.fancy.adsdk.lib.interf.FancyAdDislike;
import com.fancy.adsdk.lib.interf.FancyAppDownloadListener;
import com.fancy.adsdk.lib.interf.FancyDownloadStatusController;
import com.fancy.adsdk.lib.interf.FancyFeedAd;
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

public class FancyFeedAdManager {
    private static final String TAG = "FancySplashAdManager";
    //开屏广告加载超时时间,建议大于3000,这里为了冷启动第一次加载到广告并且展示,示例设置了3000ms
    private static final int AD_TIME_OUT = 3000;
    private String mCodeId = "801121648";

    Context context;
    Activity activity;

    public FancyFeedAdManager() {
//        this.context = context;

    }


    /**
     * 加载自渲染信息流广告
     */
    public void loadFeedAd(final Context context, AdSlot adSlot, final FancyAdNative.FeedAdListener fancyFeedAdListener) {

        FancyAdProxy.getFeedAd(adSlot, new Callback<AdObject>() {
            @Override
            public void onSuccess(final AdObject object) {
                MainLooper.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<FancyFeedAd> adList = new ArrayList<>();
                        List<Ad> ads = object.getAd();
                        for (int i = 0; i < ads.size(); i++) {
                            final Ad fancyAd = ads.get(i);
                            FancyFeedAd ad = new FancyFeedAd() {

                                @Override
                                public void setVideoAdListener(VideoAdListener listener) {

                                }

                                @Override
                                public double getVideoDuration() {
                                    return fancyAd.getDuration();
                                }

                                @Nullable
                                @Override
                                public FancyImage getVideoCoverImage() {
                                    return null;
                                }

                                @Override
                                public Bitmap getAdLogo() {
                                    return null;
                                }

                                @Override
                                public String getTitle() {
                                    return fancyAd.getTitle();
                                }

                                @Override
                                public String getDescription() {
                                    return fancyAd.getDesc();
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
                                    return fancyAd.getSource();
                                }

                                @Override
                                public FancyImage getIcon() {
                                    return null;
                                }

                                @Override
                                public List<FancyImage> getImageList() {
                                    List<FancyImage> fancyImages = new ArrayList<>();
                                    FancyImage fancyImage = new FancyImage(0, 0, fancyAd.getSrc());
                                    fancyImages.add(fancyImage);
                                    List<String> strings = fancyAd.getExt_urls();
                                    if (strings != null && strings.size() > 0) {
                                        for (int i = 0; i < strings.size(); i++) {
                                            FancyImage img = new FancyImage(0, 0, strings.get(i));
                                            fancyImages.add(img);
                                        }
                                    }
                                    return fancyImages;
                                }

                                @Override
                                public int getInteractionType() {
                                    return FancyAdconstant.INTERACTION_TYPE_LANDING_PAGE;
                                }

                                @Override
                                public int getImageMode() {
                                    return FancyAdconstant.FEED_AD;
                                }

                                @Override
                                public List<FancyFilterWord> getFilterWords() {
                                    return null;
                                }

                                @Override
                                public FancyDownloadStatusController getDownloadStatusController() {
                                    return null;
                                }

                                @Override
                                public void registerViewForInteraction(@NonNull ViewGroup var1, @NonNull View var2, AdInteractionListener var3) {
                                }

                                @Override
                                public void registerViewForInteraction(@NonNull ViewGroup var1, @NonNull List<View> var2, @Nullable List<View> var3, AdInteractionListener var4) {

                                }


                                @Override
                                public void setDownloadListener(FancyAppDownloadListener var1) {

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

                        fancyFeedAdListener.onFeedAdLoad(adList.get(0));
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
