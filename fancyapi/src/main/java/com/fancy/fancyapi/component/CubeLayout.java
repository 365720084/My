package com.fancy.fancyapi.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.BaseInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.fancyapi.R;
import com.fancy.adsdk.lib.constants.AdConstant;
import com.fancy.adsdk.lib.model.Ad;
import com.fancy.fancyapi.animation.CubeLeftOutAnimation;
import com.fancy.fancyapi.animation.CubeRightInAnimation;
import com.fancy.fancyapi.utils.DownloadImageTask;


/**
 * Created by bruce on 19/9/24.
 */
public class CubeLayout extends RelativeLayout {
    ImageView foregroundView;
    ImageView backgroundView;

    private BaseInterpolator mInterpolator = new AccelerateDecelerateInterpolator();

    public CubeLayout(Context context) {
        this(context, null);
    }

    public CubeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CubeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        foregroundView = (ImageView) getChildAt(0);
        backgroundView = (ImageView) getChildAt(1);


    }

    public void setAd(Ad ad) {
//        foregroundView.setImageResource(R.drawable.cube01);
//        backgroundView.setImageResource(R.drawable.cube02);

        if(ad.getNeedApi()== AdConstant.NEEDAPI){
            try {
                new DownloadImageTask(foregroundView).execute(ad.getSrc());
                new DownloadImageTask(backgroundView).execute(ad.getExt_urls().get(0));
            } catch (Exception e) {

            }
        }


    }


    public void startBlink() {
        AlphaAnimation alphaAnimation1 = new AlphaAnimation(1f, 1.0f);
        alphaAnimation1.setDuration(1000);
        alphaAnimation1.setFillAfter(true);
        alphaAnimation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        backgroundView.startAnimation(alphaAnimation1);
    }

    public void startAnimation() {
        CubeLeftOutAnimation cubeLeftOutAnimation = new CubeLeftOutAnimation();
        cubeLeftOutAnimation.setDuration(1000);
        cubeLeftOutAnimation.setFillAfter(true);

        CubeRightInAnimation cubeRightInAnimation = new CubeRightInAnimation();
        cubeRightInAnimation.setDuration(1000);
        cubeRightInAnimation.setFillAfter(true);

        foregroundView.startAnimation(cubeLeftOutAnimation);
        backgroundView.startAnimation(cubeRightInAnimation);

    }


}

