package com.fancy.fancyapi.component;

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
import com.fancy.adsdk.lib.constants.FancyAdconstant;
import com.fancy.adsdk.lib.interf.AdClickListener;
import com.fancy.adsdk.lib.interf.FancyAppDownloadListener;
import com.fancy.adsdk.lib.interf.FancyNativeExpressAd;
import com.fancy.adsdk.lib.interf.FancySplashAd;
import com.fancy.adsdk.lib.model.Ad;
import com.fancy.adsdk.lib.model.AdSlot;
import com.fancy.adsdk.lib.utils.Logger;
import com.fancy.fancyapi.FancyAdProxy;
import com.fancy.fancyapi.activity.FancyLandingPageActivity;
import com.fancy.fancyapi.constants.AdParseConstant;
import com.fancy.fancyapi.interf.InterActionListener;
import com.fancy.fancyapi.utils.LayoutUtil;
import com.fancy.fancyapi.utils.ScreenUtils;
import com.fancy.fancyapi.utils.ShakeUtil;

public class SplashView extends RelativeLayout {
    private final Context context;
    private ImageView imageView;
    private RelativeLayout fancy_splash_express_container;
    private BaseCustomView baseCustomView;
    private TextView fancy_ad_logo;
    private TextView sec;
    private LinearLayout adll;
    AdClickListener adClickListener;
    FancyNativeExpressAd.ExpressAdInteractionListener expressAdInteractionListener;
    FancySplashAd.AdInteractionListener interActionListener;
    FancyAppDownloadListener appDownloadListener;
    Ad ad;
    AdSlot adSlot;
    boolean needCountDown = true;
    CountDownTimer timer;



    public void setAdSlot(AdSlot adSlot) {
        this.adSlot = adSlot;
        setSkipView();
    }

    public void setSkipView() {
        this.needCountDown = adSlot.needSkipView();
        if (this.adll != null) {
            if (needCountDown) {
                this.adll.setVisibility(VISIBLE);
            } else {
                this.adll.setVisibility(GONE);

            }
        }
    }

    public void setDownloadListener(FancyAppDownloadListener appDownloadListener) {
        this.appDownloadListener = appDownloadListener;
    }

    public void setAdInteractionListener(FancySplashAd.AdInteractionListener adInteractionListener) {
        this.interActionListener = adInteractionListener;
    }

    public void setExpressAdInteractionListener(FancyNativeExpressAd.ExpressAdInteractionListener expressAdInteractionListener) {
        this.expressAdInteractionListener = expressAdInteractionListener;
    }

    public void setAdClickListener(AdClickListener adClickListener) {
        this.adClickListener = adClickListener;
    }

    public SplashView(@NonNull Context context) {
        super(context);
        this.context = context;
        this.init();
    }

    public SplashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

    }

    public SplashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

    }


    private void init() {
        this.setLayoutParams(new LayoutParams(-1, -1));
        View var1 = inflate(this.context, LayoutUtil.f(this.context, "fancy_splash_view"), this);
        this.fancy_splash_express_container = var1.findViewById(LayoutUtil.e(this.context, "fancy_splash_express_container"));
        this.fancy_ad_logo = var1.findViewById(LayoutUtil.e(this.context, "fancy_ad_logo"));
        this.adll = var1.findViewById(LayoutUtil.e(this.context, "adll"));
        this.sec = var1.findViewById(LayoutUtil.e(this.context, "sec"));


    }

    /**
     * 倒计时显示
     */
    public void startCountDown() {
        if (!needCountDown) {
            return;
        }
        this.adll.setVisibility(VISIBLE);
        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
//                Logger.v(millisUntilFinished+"millisUntilFinished");
                sec.setText((millisUntilFinished - 1) / 1000 + "");
            }

            @Override
            public void onFinish() {
                if (interActionListener != null) {
                    interActionListener.onAdTimeOver();
                }
            }
        }.start();
        adll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interActionListener != null) {
                    interActionListener.onAdSkip();
                }
            }
        });
    }


    public void setAd(final Ad ad) {
        this.ad = ad;
//        if (!TextUtils.isEmpty(adSlot.getSplashType()) && !adSlot.getSplashType().equals(AdConstant.SPLASH_AD_API)) {
//            ad.setStyle(adSlot.getSplashType());
//        }
//        if (adSlot.getSplashType().equals(AdConstant.SPLASH_AD_API)) {
//            ad.setNeedApi(AdConstant.NEEDAPI);
//        } else {
//            ad.setNeedApi(AdConstant.NEEDAPI_NO);
//        }
//        String mImgUrl = ad.getUrl();
//        if (!TextUtils.isEmpty(mImgUrl) && mImgUrl.contains("mp4")) {
//            splashVideoView.setPreparedListen(new SplashVideoView.VideoPreparedListen() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    startCountDown();
//                }
//            });
//            splashVideoView.setAd(ad);
//            return;
//        }
        if (ad == null) {
            return;
        }

        if (TextUtils.isEmpty(ad.getStyle())) {

            if (ad.getMime().equals(AdParseConstant.MINME_TYPE_FLV) || ad.getMime().equals(AdParseConstant.MINME_TYPE_MP4)) {
                baseCustomView = new SplashVideoView(context);
                ((SplashVideoView) baseCustomView).setPreparedListen(new SplashVideoView.VideoPreparedListen() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        startCountDown();
                    }
                });
                baseCustomView.setAd(ad);
                baseCustomView.setAdClickListener(adClick);
                addView(baseCustomView, 0);

            } else {
                baseCustomView = new SplashImageView(context);
                baseCustomView.setAd(ad);
                baseCustomView.setAdClickListener(adClick);
                addView(baseCustomView, 0);
                startCountDown();
                needCountDown = false;
            }


            return;
        }

        switch (ad.getStyle()) {
            case AdConstant.SPLASH_AD_VIDEO:
                baseCustomView = new SplashVideoView(context);
                addView(baseCustomView, 0);
//                splashVideoView.setPreparedListen(new SplashVideoView.VideoPreparedListen() {
//                    @Override
//                    public void onPrepared(MediaPlayer mp) {
//                        startCountDown();
//                    }
//                });
                baseCustomView.setAd(ad);
                baseCustomView.setAdClickListener(adClick);
                break;
            case AdConstant.SPLASH_AD_NORMAL:
                baseCustomView = new SplashImageView(context);
                baseCustomView.setAd(ad);
                baseCustomView.setAdClickListener(adClick);
                addView(baseCustomView, 0);
                startCountDown();
                needCountDown = false;
                break;
            case AdConstant.SPLASH_AD_Sliding:
                baseCustomView = new SplashSlidingView(context);
                addView(baseCustomView, 0);
                baseCustomView.setAd(ad);
                baseCustomView.setAdClickListener(adClick);

                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        baseCustomView.startAnimation();
                    }
                }, 400);
                break;
            case AdConstant.SPLASH_AD_Swipe:
                baseCustomView = new SplashSwipeView(context);
                addView(baseCustomView, 0);
                baseCustomView.setAd(ad);
                baseCustomView.setAdClickListener(adClick);

                break;
            case AdConstant.SPLASH_AD_Gallery:
                baseCustomView = new SplashGalleryView(context);
                baseCustomView.setAd(ad);
                baseCustomView.setAdClickListener(adClick);
                addView(baseCustomView, 0);

                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        baseCustomView.startAnimation();
                    }
                }, 400);
                break;
            case AdConstant.SPLASH_AD_Cube:
                baseCustomView = new SplashCubeView(context);
                addView(baseCustomView, 0);
                baseCustomView.setAd(ad);
                baseCustomView.setAdClickListener(adClick);

                break;
            default:
                baseCustomView = new SplashCubeView(context);
                addView(baseCustomView, 0);
                baseCustomView.setAd(ad);
                baseCustomView.setAdClickListener(adClick);

                break;
        }
        if (interActionListener != null) {
            interActionListener.onAdShow(baseCustomView, 0);
        }
        postDelayed(new Runnable() {
            @Override
            public void run() {
//                if (!ad.getStyle().equals(AdConstant.SPLASH_AD_VIDEO)) {
                    startCountDown();
//                }
            }
        }, 1000);

    }

    AdClickListener adClick = new AdClickListener() {
        @Override
        public void onClick(View view) {
            if (ad.getAction() == 1) {
                FancyAdProxy.onAdClicked(ad);
                Intent intent = new Intent(getContext(), FancyLandingPageActivity.class);
                if (TextUtils.isEmpty(ad.getDp_url())) {
                    intent.putExtra("url", ad.getUrl());
                } else {
                    intent.putExtra("url", ad.getDp_url());
                }

                getContext().startActivity(intent);

                if (interActionListener != null) {
                    interActionListener.onAdClicked(view, 0);
                }
            }
        }
    };


    void setSplashAnimationView(SplashGalleryView var1) {
        if (var1 != null) {
//            this.splashAnimationView = var1;
//            this.splashVideoView.addView(this.splashAnimationView);
        }

    }


//    void setCountDownTime(int var1) {
//        if (this.checkDebugOpen != null) {
//            this.checkDebugOpen.setCountDownTime(var1);
//        }
//
//    }

//    void setSkipIconVisibility(int var1) {
//        ai.getAdManager(this.checkDebugOpen, var1);
//    }

    RelativeLayout getVideoContainer() {
        return this.fancy_splash_express_container;
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ShakeUtil.INSTANCE.unRegistSensor();
        if (timer != null) {
            timer.cancel();
        }

    }

    public final void setOnClickListener(@Nullable OnClickListener var1) {
    }

    public final void setOnTouchListener(OnTouchListener var1) {
    }

    @SuppressLint({"ClickableViewAccessibility"})
    final void setOnTouchListenerInternal(OnTouchListener var1) {
        super.setOnTouchListener(var1);
    }

    final void setOnClickListenerInternal(@Nullable OnClickListener var1) {
        super.setOnClickListener(var1);
    }

//    final void setSkipListener(OnClickListener var1) {
//        if (this.checkDebugOpen != null) {
//            this.checkDebugOpen.setAdClickListener(var1);
//        }
//
//    }

    final void setVoiceViewListener(OnClickListener var1) {
        if (this.imageView != null) {
            this.imageView.setOnClickListener(var1);
        }

    }

    final void setVoiceViewImageResource(@DrawableRes int var1) {
        if (this.imageView != null) {
            this.imageView.setImageResource(var1);
        }
    }


}
