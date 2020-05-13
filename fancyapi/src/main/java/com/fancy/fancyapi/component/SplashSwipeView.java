package com.fancy.fancyapi.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.airbnb.lottie.LottieAnimationView;
import com.example.fancyapi.R;
import com.fancy.adsdk.lib.constants.AdConstant;
import com.fancy.adsdk.lib.model.Ad;
import com.fancy.fancyapi.utils.DownloadImageTask;


public class SplashSwipeView extends BaseCustomView {


    boolean scrollEnd = false;
    private View mRoot;
    private TextView hint;
    private CustomVideoView viedoView;
    private Button buyNow;
    private ImageView image;
    private LottieAnimationView lottie;


    public SplashSwipeView(Context context) {
        super(context);
        initAttr(null, 0);
    }

    public SplashSwipeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs, 0);
    }

    @SuppressLint("NewApi")
    public SplashSwipeView(Context context, AttributeSet attrs, int defStyle) {
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
        mRoot = LayoutInflater.from(context).inflate(R.layout.fancy_splash_swipe_view, null);
        viedoView = mRoot.findViewById(R.id.viedoView);
        image = mRoot.findViewById(R.id.image);
        lottie = mRoot.findViewById(R.id.lottie);
        buyNow = mRoot.findViewById(R.id.buyNow);
//        container = (LinearLayout) mRoot.findViewById(R.id.horizontalScrollViewItemContainer);
        hint = (TextView) mRoot.findViewById(R.id.hint);
        viedoView.setOnClickListener(new OnClickListener() {
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

        addView(mRoot, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void setAd(Ad ad) {
        //设置播放加载路径
//        String path = "android.resource://" + context.getPackageName() + "/" + R.raw.extra;
        String path = "http://cdn.inspired-mobile.cn/videos/25June19/Dior%20Joy_OpenScreen_extra%20assets.mp4";

//        image.setImageResource(R.drawable.swipe_foreground);

        if (ad.getNeedApi() == AdConstant.NEEDAPI) {
            try {
                new DownloadImageTask(image).execute(ad.getSrc());
                path = ad.getSrc();
            } catch (Exception e) {

            }
        }
        viedoView.setVideoPath(path);
        viedoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                lottie.setVisibility(VISIBLE);
                lottie.playAnimation();
            }
        });
    }

    //    public void startBlink() {
//        lottie.setVisibility(VISIBLE);
//        lottie.playAnimation();
//    }
    private float mPosX, mPosY, mCurPosX, mCurPosY;//记录mListViewDevice 滑动的位置
    private static final int FLING_MIN_DISTANCE = 20;//mListView  滑动最小距离
    private static final int FLING_MIN_VELOCITY = 200;//mListView 滑动最大速度

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        // TODO Auto-generated method stub
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mPosX = event.getX();
                mPosY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurPosX = event.getX();
                mCurPosY = event.getY();

                break;
            case MotionEvent.ACTION_UP:
                if (mCurPosX - mPosX > 0
                        && (Math.abs(mCurPosX - mPosX) > 25)) {
                    //向右滑動

                } else if (mCurPosX - mPosX < 0
                        && (Math.abs(mCurPosX - mPosX) > 25)) {
                    //向左滑动
                    startPlayVideo();

                }

                break;
        }
        return true;
    }


    private void startPlayVideo() {

        Toast.makeText(context, "开始播放", Toast.LENGTH_LONG);
        image.setVisibility(GONE);
        lottie.setVisibility(GONE);
        viedoView.start();
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }


}





