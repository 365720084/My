package com.fancy.fancyapi.manager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fancy.adsdk.lib.component.FancyDislikeDialogAbstract;
import com.fancy.adsdk.lib.interf.FancyAdDislike;
import com.fancy.adsdk.lib.interf.FancyAppDownloadListener;
import com.fancy.adsdk.lib.interf.FancyDownloadStatusController;
import com.fancy.adsdk.lib.interf.FancyFeedAd;
import com.fancy.adsdk.lib.interf.FancyNativeExpressAd;
import com.fancy.adsdk.lib.model.Ad;
import com.fancy.adsdk.lib.model.FancyFilterWord;
import com.fancy.adsdk.lib.model.FancyImage;
import com.fancy.fancyapi.component.feed.FeedView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NativeExpressAdManager implements FancyNativeExpressAd {

    FeedView feedView;
    List<Ad> adList = new ArrayList<>();
    Ad ad;

    public void setAd(Ad ad) {
        this.ad = ad;
        feedView.setAd(ad);

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
}
