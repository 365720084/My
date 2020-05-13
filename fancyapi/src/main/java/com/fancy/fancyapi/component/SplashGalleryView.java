package com.fancy.fancyapi.component;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.example.fancyapi.R;
import com.fancy.adsdk.lib.constants.AdConstant;
import com.fancy.adsdk.lib.model.Ad;
import com.fancy.fancyapi.utils.DownloadImageTask;
import com.fancy.fancyapi.utils.ShakeUtil;

public class SplashGalleryView extends BaseCustomView {


    boolean scrollEnd = false;
    private View mRoot;
    private TextView hint;
    private Button button;
    private ViewPager viewPager;
    //    private HorizontalScrollView horizontalScrollView;
    private HorizontalView horizontalScrollView;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView icon;
    LottieAnimationView lottie;
    LinearLayout buyNow;

    private static final int MOVE = 99;
    private int lastAngel;

//    private static int[] mImgIds = new int[]{R.drawable.ad_lankou01, R.drawable.ad_lankou02, R.drawable.ad_lankou03};

    public SplashGalleryView(Context context) {
        super(context);
        initAttr(null, 0);
    }

    public SplashGalleryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs, 0);
    }

    @SuppressLint("NewApi")
    public SplashGalleryView(Context context, AttributeSet attrs, int defStyle) {
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
        lottie.playAnimation();
        lottie.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                onSensor();

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void init(Context context) {
        Log.e("custom func", "init");
        mRoot = LayoutInflater.from(context).inflate(R.layout.fancy_splash_gallery_view, null);
        horizontalScrollView = mRoot.findViewById(R.id.horizontalScrollView);
        img1 = mRoot.findViewById(R.id.img1);
        img2 = mRoot.findViewById(R.id.img2);
        img3 = mRoot.findViewById(R.id.img3);
        icon = mRoot.findViewById(R.id.icon);
        buyNow = mRoot.findViewById(R.id.buyNow);
        lottie = mRoot.findViewById(R.id.lottie);
//        container = (LinearLayout) mRoot.findViewById(R.id.horizontalScrollViewItemContainer);
//        hint = (TextView) mRoot.findViewById(R.id.hint);

//        img1.setImageResource(R.drawable.gallery01);
//        img2.setImageResource(R.drawable.gallery02);
//        img3.setImageResource(R.drawable.gallery03);
        firstTime = true;
        buyNow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adClickListener !=null){
                    adClickListener.onClick(view);
                }
            }
        });
//        startBlink();
//        bindHZSWData();
        addView(mRoot, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void setAd(Ad ad) {

        if(ad.getNeedApi()== AdConstant.NEEDAPI){

            try {
                new DownloadImageTask(img1).execute(ad.getSrc());
                new DownloadImageTask(img2).execute(ad.getExt_urls().get(0));
                new DownloadImageTask(img3).execute(ad.getExt_urls().get(1));
                new DownloadImageTask(icon).execute(ad.getExt_urls().get(2));
            } catch (Exception e) {

            }
        }

    }

    public void startBlink() {
//        AlphaAnimation alphaAnimation1 = new AlphaAnimation(1f, 1.0f);
//        alphaAnimation1.setDuration(1000);
//        alphaAnimation1.setRepeatCount(1);
////        alphaAnimation1.setRepeatMode(Animation.RESTART);
////        hint.setAnimation(alphaAnimation1);
//        alphaAnimation1.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                onSensor();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        alphaAnimation1.start();
//        lottie.startAnimation(alphaAnimation1);

    }

    //将集合中的数据绑定到HorizontalScrollView上
    private void bindHZSWData() {    //为布局中textview设置好相关属性
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        int width = context.getResources().getDisplayMetrics().widthPixels;
        int height = context.getResources().getDisplayMetrics().heightPixels;
        layoutParams.width = width;
        layoutParams.height = height;

//        layoutParams.gravity = Gravity.CENTER;
//        layoutParams.setMargins(20, 10, 20, 10);

//        for (int i = 0; i < mImgIds.length; i++) {
//            ImageView imgView = new ImageView(context);
//            imgView.setImageResource(mImgIds[i]);
//            if (i == 1) {
//                layoutParams.width = width / 2;
//                layoutParams.height = height;
//                imgView.setLayoutParams(layoutParams);
//            } else {
//                layoutParams.width = width;
//                layoutParams.height = height;
//            }
//            horizontalScrollView.addView(imgView, layoutParams);
////            container.invalidate();
//        }
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

    int perAngel = 45;//数量越小，动画越明显
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
            lottie.setVisibility(GONE);
            scrollEnd = true;
            autoScroll(2);

        }

        if (!scrollEnd) {
            count++;
        }
    }


    // 实现Horizon自动滚动居中
    private void autoScroll(int i) {
        // Width of the screen
        DisplayMetrics metrics = getResources()
                .getDisplayMetrics();
        int widthScreen = metrics.widthPixels;

        // Width of one child (Button)
        View view = horizontalScrollView.getChildAt(i);
        int widthChild = horizontalScrollView.getChildAt(i).getWidth(); // 获取对应位置的子View的宽度
        if (widthChild == 0) {
            return;
        }

        // Nb children in screen
        int nbChildInScreen = widthScreen / widthChild;

        // Child position left
        int positionLeftChild = horizontalScrollView.getChildAt(i).getLeft(); // 获取对应位置的子View的左边位置 - 坐标

        // Auto scroll to the middle
        horizontalScrollView.smoothScrollTo((positionLeftChild - ((nbChildInScreen * widthChild) / 2) + widthChild / 2), 0, 2000);
    }

    public void onSensor() {
//        ShakeUtil.INSTANCE.initShakeListener(getContext());
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





