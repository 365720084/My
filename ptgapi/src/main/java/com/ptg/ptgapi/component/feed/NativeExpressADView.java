package com.ptg.ptgapi.component.feed;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;

import com.example.ptgapi.R;
import com.ptg.adsdk.lib.model.Ad;
import com.ptg.ptgapi.activity.PtgLandingPageActivity;
import com.ptg.ptgapi.component.BaseCustomView;
import com.ptg.ptgapi.utils.DownloadImageTask;
import com.ptg.ptgapi.utils.ScreenUtils;


public class NativeExpressADView extends BaseCustomView {


    boolean scrollEnd = false;
    private View itemView;
    LinearLayout ll_item;
    TextView title;
    ImageView cover;
    TextView video_duration;

    RelativeLayout rl_media;
    RelativeLayout Rl_bot1, rl_video;
    TextView tips, comment, time;
    ImageView close;
    TextView source;
    View line;

    RelativeLayout Rl_bot2;
    VideoView videoView;

    public NativeExpressADView(Context context) {
        super(context);
        initAttr(null, 0);
    }

    public NativeExpressADView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs, 0);
    }

    @SuppressLint("NewApi")
    public NativeExpressADView(Context context, AttributeSet attrs, int defStyle) {
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
        itemView = LayoutInflater.from(context).inflate(R.layout.new_item_video, null);
        source = itemView.findViewById(R.id.source);
        line = itemView.findViewById(R.id.line);

        videoView = itemView.findViewById(R.id.videoView);
        ll_item = itemView.findViewById(R.id.ll_item);
        title = itemView.findViewById(R.id.tv_title);
        cover = itemView.findViewById(R.id.img_video);
        rl_video = itemView.findViewById(R.id.ll_videoCenter);
        video_duration = itemView.findViewById(R.id.tv_video_duration);
        tips = itemView.findViewById(R.id.tv_1);
        comment = itemView.findViewById(R.id.tv_2);
        time = itemView.findViewById(R.id.tv_3);
        close = itemView.findViewById(R.id.img_close);
        Rl_bot1 = itemView.findViewById(R.id.rl_bot1);
        Rl_bot2 = itemView.findViewById(R.id.rl_bot2);
        rl_media = itemView.findViewById(R.id.rl_media);

        addView(itemView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }


    @Override
    protected void setAd(final Ad ad) {
        //设置播放加载路径
        try {

            source.setText("ptg");
            title.setText(ad.getTitle());

            Rl_bot1.setVisibility(View.VISIBLE);
            tips.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);

            final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rl_media.getLayoutParams();
            params.width = ScreenUtils.getScreenWidth(context);
            //如果是视频，格式是360*640,如果是图片，格式是360*690
            params.height = params.width * 360 / 690;
//
//        if (!TextUtils.isEmpty(m.v_url)) {
//            params.height = params.width * 360 / 640;
//        } else {
//        }

            rl_media.setLayoutParams(params);
            rl_video.setVisibility(View.GONE);
            new DownloadImageTask(cover).execute(ad.getSrc());
            tips.setBackgroundResource(R.color.white);
            tips.setTextColor(Color.parseColor("#979797"));
            tips.setText("广告");
            ll_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PtgLandingPageActivity.class);
                    intent.putExtra("url", ad.getUrl());
                    context.startActivity(intent);
                    videoView.pause();
                    if(adClickListener!=null){
                        adClickListener.onClick(v);
                    }
                }
            });
            videoView.setVisibility(View.GONE);

        } catch (Exception e) {


        }

    }


}






