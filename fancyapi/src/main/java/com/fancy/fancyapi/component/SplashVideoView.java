package com.fancy.fancyapi.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.fancyapi.R;
import com.fancy.adsdk.lib.constants.AdConstant;
import com.fancy.adsdk.lib.model.Ad;

public class SplashVideoView extends BaseCustomView {


    private View mRoot;
    private CustomVideoView mAdVideoView;
    private ImageView image;

    VideoPreparedListen preparedListen;

    public SplashVideoView(Context context) {
        super(context);
        initAttr(null, 0);
    }

    public SplashVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs, 0);
    }

    @SuppressLint("NewApi")
    public SplashVideoView(Context context, AttributeSet attrs, int defStyle) {
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
        mRoot = LayoutInflater.from(context).inflate(R.layout.fancy_splash_video_view, null);
        mAdVideoView = mRoot.findViewById(R.id.viedoView);
        image = mRoot.findViewById(R.id.image);
        mAdVideoView.setOnClickListener(new OnClickListener() {
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
        String path = "http://cdn.inspired-mobile.cn/videos/25June19/Dior%20Joy_OpenScreen_extra%20assets.mp4";

        if (ad.getNeedApi() == AdConstant.NEEDAPI) {
            try {
                path = ad.getSrc();
            } catch (Exception e) {

            }
        }

//        mAdVideoView.setVideoPath(path);

//        String mImgUrl=ad.getUrl();
        mAdVideoView.setVideoPath(path);
        mAdVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                image.setVisibility(View.GONE);
                if (preparedListen != null) {
                    preparedListen.onPrepared(mp);
                }
                mp.start();
                mp.setLooping(true);
            }
        });


        try {

        } catch (Exception e) {

        }
    }

    public interface VideoPreparedListen {
        void onPrepared(MediaPlayer mp);
    }

    private void startPlayVideo() {
        Toast.makeText(context, "开始播放", Toast.LENGTH_LONG);
        image.setVisibility(GONE);
        mAdVideoView.start();
    }

    public void setPreparedListen(VideoPreparedListen preparedListen) {
        this.preparedListen = preparedListen;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }


}





