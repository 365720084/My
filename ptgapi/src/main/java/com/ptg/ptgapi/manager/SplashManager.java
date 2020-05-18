package com.ptg.ptgapi.manager;

import android.content.Context;
import android.view.View;

import com.ptg.ptgapi.component.SplashGalleryView;
import com.ptg.ptgapi.component.SplashView;
import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.interf.PtgNativeExpressAd;
import com.ptg.adsdk.lib.interf.PtgSplashAd;
import com.ptg.adsdk.lib.model.Ad;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.ptgapi.utils.ShakeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SplashManager implements PtgSplashAd {

    SplashView splashView;
    Context context;
    SplashGalleryView splashGalleryView;
    int ptgType;
    List<Ad> adList = new ArrayList<>();
    Ad ad;
    AdSlot adSlot;


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
        this.init(ptgType);
    }

    private void init(int ptgType) {
        ShakeUtil.INSTANCE.initShakeListener(context, ptgType);
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

    public void setDownloadListener(PtgAppDownloadListener var1) {
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

    public void renderExpressAd(PtgNativeExpressAd.ExpressAdInteractionListener var1) {
        if (splashView != null) {
            splashView.setExpressAdInteractionListener(var1);
        }
    }

}
