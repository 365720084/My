package com.ptg.ptgapi.component.feed;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.ptg.adsdk.lib.interf.AdClickListener;
import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.interf.PtgNativeExpressAd;
import com.ptg.adsdk.lib.model.Ad;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.ptgapi.activity.PtgLandingPageActivity;
import com.ptg.ptgapi.component.BaseCustomView;
import com.ptg.ptgapi.utils.LayoutUtil;

public class FeedView extends RelativeLayout {
    private final Context context;
    //    private GifView oepnDebug;
//    private TTCountdownView checkDebugOpen;
    private ImageView imageView;
    AdClickListener adClickListener;
    PtgNativeExpressAd.ExpressAdInteractionListener expressAdInteractionListener;
    PtgAppDownloadListener appDownloadListener;
    Ad ad;
    AdSlot adSlot;
    BaseCustomView baseCustomView;


    public void setAdSlot(AdSlot adSlot) {
        this.adSlot = adSlot;
    }


    public void setDownloadListener(PtgAppDownloadListener appDownloadListener) {
        this.appDownloadListener = appDownloadListener;
    }


    public void setExpressAdInteractionListener(PtgNativeExpressAd.ExpressAdInteractionListener expressAdInteractionListener) {
        this.expressAdInteractionListener = expressAdInteractionListener;
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
        View root = inflate(this.context, LayoutUtil.f(this.context, "ptg_feed_view"), this);

    }



    public void setAd(final Ad ad) {
        this.ad = ad;
//        switch (ad.getFeedType()){
//
//        }
        baseCustomView=new NativeExpressADView(context) ;
        ((NativeExpressADView) baseCustomView).setAd(ad);
        ((NativeExpressADView) baseCustomView).setAdClickListener(adClick);
        addView(baseCustomView);

    }

    AdClickListener adClick = new AdClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getContext(), PtgLandingPageActivity.class);
            intent.putExtra("url", ad.getUrl());
            getContext().startActivity(intent);
            if (expressAdInteractionListener != null) {
                expressAdInteractionListener.onAdClicked(view,0);
            }
        }
    };





}
