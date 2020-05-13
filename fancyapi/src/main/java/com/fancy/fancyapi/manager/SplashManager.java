package com.fancy.fancyapi.manager;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.fancy.adsdk.lib.provider.FancyAdNative;
import com.fancy.fancyapi.FancyAdProxy;
import com.fancy.adsdk.lib.interf.AdClickListener;
import com.fancy.adsdk.lib.interf.FancyAppDownloadListener;
import com.fancy.adsdk.lib.interf.FancyNativeExpressAd;
import com.fancy.adsdk.lib.interf.FancySplashAd;
import com.fancy.adsdk.lib.model.Ad;
import com.fancy.adsdk.lib.model.AdSlot;
import com.fancy.fancyapi.component.SplashGalleryView;
import com.fancy.fancyapi.component.SplashView;
import com.fancy.fancyapi.interf.InterActionListener;
import com.fancy.fancyapi.utils.ShakeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SplashManager implements FancySplashAd {

    SplashView splashView;
    Context context;
    SplashGalleryView splashGalleryView;
    int fancyType;
    List<Ad> adList = new ArrayList<>();
    Ad ad;
    AdSlot adSlot;

    InterActionListener interActionListener;

    public void setInterActionListener(InterActionListener interActionListener) {
        this.interActionListener = interActionListener;
    }

    public void setAdSlot(AdSlot adSlot) {
        this.adSlot = adSlot;
        if (splashView != null) {
            splashView.setAdSlot(this.adSlot);
        }

    }

    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        if (adList == null || adList.size() == 0) {
//            Ad ad = new Ad();
//            splashView.setAd(ad);
            return;
        }

        this.adList = adList;
        this.ad = adList.get(0);
        splashView.setAd(ad);

    }

    public SplashManager(Context context) {
        this.context = context;
        this.init(fancyType);
    }

    private void init(int fancyType) {
        ShakeUtil.INSTANCE.initShakeListener(context, fancyType);
        this.splashView = new SplashView(context);
    }

    public View getSplashView() {
        return splashView == null ? null : splashView;
    }

    public int getInteractionType() {
        int type = 0;
        if (ad != null) {
            type = ad.getAction();
        }
        return type;
    }

//    public void setSplashInteractionListener(AdInteractionListener var1) {
//        if (splashView != null) {
//            splashView.setAdInteractionListener(var1);
//        }
//    }

    public void setDownloadListener(FancyAppDownloadListener var1) {
        if (splashView != null) {
            splashView.setDownloadListener(var1);
        }
    }

    @Override
    public void setSplashInteractionListener(AdInteractionListener listener) {
        if (splashView != null) {
            splashView.setAdInteractionListener(listener);
        }
    }


    public Map<String, Object> getMediaExtraInfo() {
        return null;
    }

    public void renderExpressAd(FancyNativeExpressAd.ExpressAdInteractionListener var1) {
        if (splashView != null) {
            splashView.setExpressAdInteractionListener(var1);
        }
    }

}
