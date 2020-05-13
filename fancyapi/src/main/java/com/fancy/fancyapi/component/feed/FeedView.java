package com.fancy.fancyapi.component.feed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fancyapi.R;
import com.fancy.adsdk.lib.constants.AdConstant;
import com.fancy.adsdk.lib.interf.AdClickListener;
import com.fancy.adsdk.lib.interf.FancyAppDownloadListener;
import com.fancy.adsdk.lib.interf.FancyNativeExpressAd;
import com.fancy.adsdk.lib.model.Ad;
import com.fancy.adsdk.lib.model.AdSlot;
import com.fancy.adsdk.lib.utils.Logger;
import com.fancy.fancyapi.activity.FancyLandingPageActivity;
import com.fancy.fancyapi.component.BaseCustomView;
import com.fancy.fancyapi.component.SplashCubeView;
import com.fancy.fancyapi.component.SplashGalleryView;
import com.fancy.fancyapi.component.SplashSlidingView;
import com.fancy.fancyapi.component.SplashSwipeView;
import com.fancy.fancyapi.component.SplashVideoView;
import com.fancy.fancyapi.utils.LayoutUtil;
import com.fancy.fancyapi.utils.ScreenUtils;
import com.fancy.fancyapi.utils.ShakeUtil;

public class FeedView extends RelativeLayout {
    private final Context context;
    //    private GifView oepnDebug;
//    private TTCountdownView checkDebugOpen;
    private ImageView imageView;
    AdClickListener adClickListener;
    FancyNativeExpressAd.ExpressAdInteractionListener expressAdInteractionListener;
    FancyAppDownloadListener appDownloadListener;
    Ad ad;
    AdSlot adSlot;
    BaseCustomView baseCustomView;


    public void setAdSlot(AdSlot adSlot) {
        this.adSlot = adSlot;
    }


    public void setDownloadListener(FancyAppDownloadListener appDownloadListener) {
        this.appDownloadListener = appDownloadListener;
    }


    public void setExpressAdInteractionListener(FancyNativeExpressAd.ExpressAdInteractionListener expressAdInteractionListener) {
        this.expressAdInteractionListener = expressAdInteractionListener;
    }

    public void setAdClickListener(AdClickListener adClickListener) {
        this.adClickListener = adClickListener;
    }

    public FeedView(@NonNull Context context) {
        super(context);
        this.context = context;
        this.init();
    }

    public FeedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.init();

    }

    public FeedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.init();

    }


    private void init() {
        this.setLayoutParams(new LayoutParams(-1, -1));
        View root = inflate(this.context, LayoutUtil.f(this.context, "fancy_feed_view"), this);

    }



    public void setAd(final Ad ad) {
        this.ad = ad;
//        switch (ad.getFeedType()){
//
//        }
        baseCustomView=new NativeExpressADView(context) ;
        ((NativeExpressADView) baseCustomView).setAd(ad);
        addView(baseCustomView);

    }

    AdClickListener adClick = new AdClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getContext(), FancyLandingPageActivity.class);
            intent.putExtra("url", ad.getUrl());
            getContext().startActivity(intent);
            if (adClickListener != null) {
                adClickListener.onClick(view);
            }
        }
    };





}
