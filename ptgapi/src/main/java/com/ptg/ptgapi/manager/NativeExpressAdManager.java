package com.ptg.ptgapi.manager;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.ptg.adsdk.lib.component.PtgDislikeDialogAbstract;
import com.ptg.adsdk.lib.interf.PtgAdDislike;
import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.interf.PtgNativeExpressAd;
import com.ptg.adsdk.lib.model.Ad;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.model.PtgFilterWord;
import com.ptg.ptgapi.component.feed.FeedView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NativeExpressAdManager implements PtgNativeExpressAd {

    FeedView feedView;
    List<Ad> adList = new ArrayList<>();
    Ad ad;
    AdSlot adSlot;

    public void setAd(Ad ad) {
        this.ad = ad;
        feedView.setAd(ad);

    }
    public void setAdSlot(AdSlot adSlot) {
        this.adSlot = adSlot;
        feedView.setAdSlot(adSlot);

    }
    public NativeExpressAdManager(Context context) {
        this.init(context);

    }

    private void init(Context context) {
        feedView=new FeedView(context);

    }

    @Override
    public View getExpressAdView() {
        return feedView==null?null:feedView;
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
        if(feedView!=null){

            feedView.setExpressAdInteractionListener(var1);
        }
    }

    @Override
    public void setExpressInteractionListener(AdInteractionListener var1) {

    }

    @Override
    public void setDownloadListener(PtgAppDownloadListener var1) {

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
}
