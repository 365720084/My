package com.ptg.ptgapi.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ptgapi.R;
import com.ptg.adsdk.lib.model.Ad;
import com.ptg.ptgapi.utils.ScreenUtils;
import com.ptg.ptgapi.utils.ShakeUtil;

public class SplashCubeView extends BaseCustomView {


    boolean scrollEnd = false;
    private View mRoot;
    private TextView hint;
    private CubeLayout cubeLayout;
    private RelativeLayout middle;
    private TextView buyNow;
    private View mask;
    private LottieAnimationView lottie;

    private static final int MOVE = 99;
    private int lastAngel;


    public SplashCubeView(Context context) {
        super(context);
        initAttr(null, 0);
    }

    public SplashCubeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs, 0);
    }

    @SuppressLint("NewApi")
    public SplashCubeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttr(attrs, defStyle);
    }

    @Override
    protected void initAttr(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SplashGalleryView, defStyle, 0);
        a.recycle();
    }

    @Override
    protected void startAnimation() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void init(Context context) {
        Log.e("custom func", "init");
        mRoot = LayoutInflater.from(context).inflate(R.layout.ptg_splash_cube_view, null);
        hint = mRoot.findViewById(R.id.hint);
        middle = mRoot.findViewById(R.id.middle);
        buyNow = mRoot.findViewById(R.id.buyNow);
        cubeLayout = mRoot.findViewById(R.id.cubeLayout);
        mask = mRoot.findViewById(R.id.mask);
        lottie = mRoot.findViewById(R.id.lottie);
        int width = (int) (ScreenUtils.getScreenWidth(context) * 1.1);
        int height = (int) (ScreenUtils.getScreenWidth(context) * 1.1 * 1334 / 750);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        mRoot.setTranslationX((float) (-ScreenUtils.getScreenWidth(context) * 0.05));
        mRoot.setTranslationY((float) (-ScreenUtils.getScreenWidth(context) * 0.05 * 1334 / 750));
        middle.setTranslationY((float) (-ScreenUtils.getScreenWidth(context) * 0.025 * 1334 / 750));
        lottie.setTranslationY((float) (-ScreenUtils.getScreenWidth(context) * 0.025 * 1334 / 750));
        firstTime = true;
        scrollEnd = false;

        middle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adClickListener != null) {
                    adClickListener.onClick(view);
                }
            }
        });
        buyNow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adClickListener != null) {
                    adClickListener.onClick(view);
                }
            }
        });
        addView(mRoot, layoutParams);
//        addView(mRoot, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

    }

    @Override
    protected void setAd(Ad ad) {
        cubeLayout.setAd(ad);
        lottie.playAnimation();
        onSensor();
    }


    public void startBlink() {
        AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.8f, 0.0f);
        alphaAnimation1.setDuration(1000);
        alphaAnimation1.setRepeatCount(1);
        alphaAnimation1.setFillAfter(true);
//        alphaAnimation1.setRepeatMode(Animation.RESTART);
        mask.setAnimation(alphaAnimation1);
        alphaAnimation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                onSensor();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
//        alphaAnimation1.start();
        mask.startAnimation(alphaAnimation1);
    }


    final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MOVE) {
                int angel = msg.arg1;
                handleAngel(angel);

            }
        }
    };

    int count = 0;//累加的次数
    boolean firstTime = true;
    int startAngel;

    private void handleAngel(int angel) {
        if (firstTime) {
            startAngel = angel;
            firstTime = false;
        }
        if (lastAngel >= angel) {
            return;
        }
        if (scrollEnd) {
            return;
        }
        int offsetAngel = angel - lastAngel;//角度和上次的增加量
        int firstOffset = angel - startAngel;
        lastAngel = angel;
        int width = context.getResources().getDisplayMetrics().widthPixels;
        Log.i("offsetAngel", angel + "");

        if (firstOffset > ShakeUtil.SHAKE) {
            count = 0;
            hint.setVisibility(GONE);
            scrollEnd = true;
            lottie.setVisibility(GONE);
            this.startBlink();
            cubeLayout.startBlink();
        }

        if (!scrollEnd) {
            count++;
        }
    }


    public void onSensor() {
        ShakeUtil.INSTANCE.setAngelChangeListener(new ShakeUtil.AngelChangeListener() {
            @Override
            public void onshake(int offset) {
                Message message = new Message();
                message.arg1 = offset;
                message.what = MOVE;
                mHandler.sendMessage(message);
            }

        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }


}





