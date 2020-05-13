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
import com.fancy.fancyapi.utils.ScreenUtils;

public class SplashImageView extends BaseCustomView {


    private View mRoot;
    private CustomVideoView mAdVideoView;
    private ImageView image;


    public SplashImageView(Context context) {
        super(context);
        initAttr(null, 0);
    }

    public SplashImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs, 0);
    }

    @SuppressLint("NewApi")
    public SplashImageView(Context context, AttributeSet attrs, int defStyle) {
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
        mRoot = LayoutInflater.from(context).inflate(R.layout.fancy_splash_img_view, null);
        image = mRoot.findViewById(R.id.image);
        addView(mRoot, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void setAd(Ad ad) {
//        image.setImageResource(R.drawable.swipe_foreground);
        int width = (int) (ScreenUtils.getScreenWidth(context));
        int height = (int) (ScreenUtils.getScreenWidth(context) * 1334 / 750);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adClickListener !=null){
                    adClickListener.onClick(view);
                }
            }
        });
    }



}





