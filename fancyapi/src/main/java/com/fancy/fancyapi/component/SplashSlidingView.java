package com.fancy.fancyapi.component;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ClipDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.fancyapi.R;
import com.fancy.adsdk.lib.constants.AdConstant;
import com.fancy.adsdk.lib.model.Ad;
import com.fancy.fancyapi.utils.DownloadImageTask;
import com.fancy.fancyapi.utils.ShakeUtil;
import com.fancy.fancyapi.utils.UIUtils;

public class SplashSlidingView extends BaseCustomView {
    private static String TAG = "ChangeWidthView";
    ImageView img1;
    ImageView img2;
    ImageView icon;
    TextView hint;
    LinearLayout buyNow;
    public static int divider = 90;
    public static int accerate = 1;
    public static int acceratePer = divider / accerate;
    private float rateAngel = 5000 / acceratePer;//每偏移1个角度，图片的drawable的level变化量
    private float transtionX = 0;
    private float rateTransitonX;//每偏移1个角度，中间view位移的距离
    private int oriention = 1;
    private static final int CHANGE_LEVEL = 99;
    private float screenWidthDp;
    private ImageView iv;
    private ClipDrawable mClipDrawable;
    private boolean isExpand = false;
    ClipDrawable imageDrawable;

    public SplashSlidingView(Context context) {
        super(context);
    }


    public SplashSlidingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SplashSlidingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initAttr(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SplashSlidingView, defStyle, 0);
//        bgColor = getAdManager.getColor(R.styleable.SplashAnimationView_funcbarBackColor, Color.WHITE);
        a.recycle();
    }

    @Override
    protected void startAnimation() {
        startTransLate();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void init(Context context) {
        mRoot = LayoutInflater.from(context).inflate(R.layout.fancy_splash_sliding_view, null);
        img1 = mRoot.findViewById(R.id.img1);
        img2 = mRoot.findViewById(R.id.img2);
        icon = mRoot.findViewById(R.id.img3);
        hint = mRoot.findViewById(R.id.hint);
        buyNow = mRoot.findViewById(R.id.buyNow);

        screenWidthDp = UIUtils.getScreenWidthDp(context);
        rateTransitonX = screenWidthDp / 2 / acceratePer * context.getResources().getDisplayMetrics().density;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(context.getResources(), R.drawable.sliding01, options);
        int width = options.outWidth;
        int height = options.outHeight;
        Log.d(TAG, width + "," + height + "");
        //截取(10，10，100，100)范围的图像
//        Bitmap sourceBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sliding01);
//        img1.setImageBitmap(sourceBitmap);

        mRoot.setOnClickListener(new OnClickListener() {
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
        changeImage(img2);

    }

    @Override
    protected void setAd(Ad ad) {
        if (ad.getNeedApi() == AdConstant.NEEDAPI) {
            try {
                new DownloadImageTask(img1).execute(ad.getSrc());
                new DownloadImageTask(img2).execute(ad.getExt_urls().get(0));
                new DownloadImageTask(icon).execute(ad.getExt_urls().get(1));
            } catch (Exception e) {

            }
        } else {

        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void changeImage(View view) {
        //截取(10，10，100，100)范围的图像
//        Bitmap sourceBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sly02);
//        img2.setImageBitmap(sourceBitmap);
//        imageDrawable = (ClipDrawable) img2.getDrawable();

//        if (img2.getDrawable() != null) {
//            img2.setBackground(img2.getDrawable());
//        }
        ClipDrawable drawable = (ClipDrawable) getResources().getDrawable(R.drawable.clip_drawable);
        imageDrawable = new ClipDrawable(drawable, Gravity.RIGHT, ClipDrawable.HORIZONTAL);

        img2.setImageDrawable(imageDrawable);
        imageDrawable.setLevel(5000);

        Log.i("rateTransitonX", rateTransitonX + "");


    }


    boolean repeatStart = false;


    public void startTransLate() {
        final int grade = 500;//分成500份
        float rate = 0.85f;//偏移0.35
        final float maxLevel = 5000 * rate;
        final float totalX = screenWidthDp / 2 * context.getResources().getDisplayMetrics().density;
        final float maxX = totalX * rate;
        final float totalTime1 = 1000 * 18 / 24;
        final float per_timemills_level1 = maxLevel / totalTime1;// 18/24秒完成动作
        final float per_timemills_x1 = maxX / totalTime1;


        float rate2 = 0.85f + 0.4f;
        final float maxLevel2 = 5000 * rate2;
        final float maxX2 = totalX * rate2;
        final float totalTime2 = totalTime1;

        final float per_timemills_level2 = maxLevel2 / totalTime2;
        final float per_timemills_x2 = maxX2 / totalTime2;

        float rate3 = 0.4f;
        final float maxLevel3 = 5000 * rate3;
        final float remain_Level3 = 5000 - maxLevel3;
        final float maxX3 = totalX * rate3;
        final float remain_maxX3 = totalX - maxX3;
        final float totalTime3 = 1000 * 12 / 24;

        final float per_timemills_level3 = maxLevel3 / totalTime3;
        final float per_timemills_x3 = maxX3 / totalTime3;

        ObjectAnimator animator = ObjectAnimator.ofFloat(hint, "alpha", 0F, 1F);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i("Animation", "onAnimationUpdate" + animation.getCurrentPlayTime());
                long currentPlayTime = animation.getCurrentPlayTime();
                //触发倾斜时，从中间到最右边再回到中间
                //总共2秒,48帧，从0-18帧到达中间偏左30%的位置，18-36帧，从中间偏左30%到达中间偏右20%，36到48帧，从中间偏右20%回到中间
                if (currentPlayTime <= (2000 / 48) * 18) {
                    //从0-18帧到达中间偏左30%的位置
                    imageDrawable.setLevel((int) (5000 + currentPlayTime * per_timemills_level1));
                    icon.setTranslationX(-currentPlayTime * per_timemills_x1);
                } else if ((2000 / 48) * 36 >= currentPlayTime && currentPlayTime > (2000 / 48) * 18) {
                    long risetime = currentPlayTime - (2000 / 48) * 18;
                    imageDrawable.setLevel((int) (5000 + maxLevel - risetime * per_timemills_level2));
                    icon.setTranslationX(-maxX + risetime * per_timemills_x2);
                } else if ((2000 / 48) * 36 < currentPlayTime && currentPlayTime <= 2000) {
                    long risetime = currentPlayTime - (2000 / 48) * 36;
                    imageDrawable.setLevel((int) (remain_Level3 + risetime * per_timemills_level3));
                    icon.setTranslationX(maxX3 - risetime * per_timemills_x3);
                }

            }
        });
        animator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                Log.i("Animation", "onAnimationRepeat");
                repeatStart = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.i("Animation", "onAnimationStart");
//                hint.setVisibility(GONE);
                imageDrawable.setLevel(5000);
                icon.setTranslationX(0);
                onSensor();
            }
        });
        animator.setDuration(1000);
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }

    public void startTransLateTwo() {
        int grade = 500;//分成500份
        float rate = 0.9f;
        hint.setVisibility(GONE);
        buyNow.setVisibility(VISIBLE);
        final float max = 5000 * rate;
        final float perlever = max / grade;
        final float totalX = screenWidthDp / 2 * context.getResources().getDisplayMetrics().density;
        final float perX = totalX / grade * rate;

        ObjectAnimator animator = ObjectAnimator.ofFloat(buyNow, "scaleX", 1F, 1F, 1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i("Animation1", "onAnimationUpdate" + animation.getCurrentPlayTime());
                long currentPlayTime = animation.getCurrentPlayTime();
                //触发倾斜时，从中间到最右边再回到中间
                long remain = currentPlayTime;
                if (remain <= 500) {
                    imageDrawable.setLevel((int) (5000 - remain * perlever));
                    icon.setTranslationX(remain * perX);
                } else {
                    imageDrawable.setLevel((int) (5000 - max + (remain - 500) * perlever));
                    icon.setTranslationX(perX * 500 - (remain - 500) * perX);
                }
            }
        });
        animator.setDuration(1000);
        animator.start();
    }


    final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == CHANGE_LEVEL) {
                int angel = msg.arg1;
                handleAngel(angel);

            }
        }
    };

    boolean firstTime = true;
    int startAngel;
    private int lastAngel;
    boolean scrollEnd = false;

    private void handleAngel(int angel) {
        if (firstTime) {
            startAngel = angel;
            firstTime = false;
        }
        if (scrollEnd) {
            return;
        }

        if (angel - startAngel > ShakeUtil.SHAKE) {
            startTransLateTwo();
            scrollEnd = true;
        }
    }

    public void onSensor() {
        ShakeUtil.INSTANCE.setAngelChangeListener(new ShakeUtil.AngelChangeListener() {
            @Override
            public void onshake(int offset) {
                Message message = new Message();
                message.arg1 = offset;
                message.what = CHANGE_LEVEL;
                mHandler.sendMessage(message);
            }

        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ShakeUtil.INSTANCE.unRegistSensor();

    }
}
