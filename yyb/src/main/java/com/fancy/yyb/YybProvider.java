package com.fancy.yyb;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.ptg.adsdk.lib.constants.PtgAdconstant;
import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.interf.PtgRewardVideoAd;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.provider.PtgAdNative;
import com.tencent.unionsdkpublic.IInterface.ICommonAd;
import com.tencent.unionsdkpublic.IInterface.IVideoADListener;
import com.tencent.unionsdkpublic.model.UnionSDKParam;
import com.tencent.unionsdkshell.UnionSdk;

import java.util.Map;

public class YybProvider implements PtgAdNative {
    static final String providerName = "yyb";

    public YybProvider() {
    }

    @Override
    public void init(Context context) {
        UnionSDKParam param=new UnionSDKParam();
        param.appKey="";
        param.appSecret="";

        if(context instanceof Application){
            UnionSdk.get().init((Application) context,param);
        }
    }

    @Override
    public String getName() {
        return providerName;
    }

    @Override
    public void loadFeedAd(Context context, AdSlot slot, @NonNull FeedAdListener listener) {
    }

    @Override
    public void loadSplashAd(Activity activity, AdSlot slot, @NonNull SplashAdListener listener) {
    }

    @Override
    public void loadNativeExpressAd(Context context, AdSlot adSlot, @NonNull NativeExpressAdListener listener) {
    }

    @Override
    public void loadRewardVideoAd(Context context, AdSlot var1, @NonNull final RewardVideoAdListener listener) {
        final ICommonAd ad= UnionSdk.get().getVideoAd(context, new IVideoADListener() {
                @Override
                public void onPlayFinish() {
                }

                @Override
                public void onADLoadSuccess() {
                    listener.onRewardVideoCached();

                }

                @Override
                public void onADLoadFailed(int i, String s) {
                    listener.onError(i,s);
                }

                @Override
                public void onADClose() {
                }
            });
        PtgRewardVideoAd ptgRewardVideoAd=new PtgRewardVideoAd() {
            @Override
            public void setRewardAdInteractionListener(RewardAdInteractionListener var1) {

            }

            @Override
            public void setDownloadListener(PtgAppDownloadListener var1) {

            }

            @Override
            public int getInteractionType() {
                return 0;
            }

            @Override
            public void showRewardVideoAd(Activity var1) {
                ad.showAD();
            }

            @Override
            public Map<String, Object> getMediaExtraInfo() {
                return null;
            }

            @Override
            public void showRewardVideoAd(Activity var1, PtgAdconstant.RitScenes var2, String var3) {
                ad.showAD();

            }

            @Override
            public void setShowDownLoadBar(boolean var1) {

            }

            @Override
            public int getRewardVideoAdType() {
                return 0;
            }
        };
        ad.loadAD();
        listener.onRewardVideoAdLoad(ptgRewardVideoAd);
    }
}
