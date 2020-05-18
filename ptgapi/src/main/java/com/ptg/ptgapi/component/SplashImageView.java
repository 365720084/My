package com.ptg.ptgapi.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;

import com.example.ptgapi.R;
import com.ptg.adsdk.lib.model.Ad;
import com.ptg.ptgapi.utils.DownloadImageTask;
import com.ptg.ptgapi.utils.ScreenUtils;

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
        mRoot = LayoutInflater.from(context).inflate(R.layout.ptg_splash_img_view, null);
        image = mRoot.findViewById(R.id.image);
        addView(mRoot, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void setAd(Ad ad) {
//        image.setImageResource(R.drawable.swipe_foreground);
        try {
            int width = (int) (ScreenUtils.getScreenWidth(context));
            int height = (int) (ScreenUtils.getScreenWidth(context) * ad.getHeight() /  ad.getWidth());
            LinearLayout.LayoutParams layoutParams = (LayoutParams) image.getLayoutParams();
            layoutParams.width=width;
            layoutParams.height=height;
            image.setLayoutParams(layoutParams);
            new DownloadImageTask(image).execute(ad.getSrc());
        }catch (Exception e){

        }
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





