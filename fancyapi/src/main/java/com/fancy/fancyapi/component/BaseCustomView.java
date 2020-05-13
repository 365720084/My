package com.fancy.fancyapi.component;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.fancy.adsdk.lib.interf.AdClickListener;
import com.fancy.adsdk.lib.model.Ad;

public abstract class BaseCustomView extends LinearLayout {
    public   Context context;
    protected View mRoot;
    protected AdClickListener adClickListener;

    public void setAdClickListener(AdClickListener adClickListener) {
        this.adClickListener = adClickListener;
    }

    public BaseCustomView(Context context) {
        super(context);
        this.context=context;
        initAttr(null, 0);
        init(context);
    }


    public BaseCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;

        initAttr(attrs, 0);
        init(context);
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;

        initAttr(attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context=context;

        initAttr(attrs, defStyleAttr);
        init(context);

    }

    protected abstract void initAttr(AttributeSet attrs, int defStyle);
    protected abstract void startAnimation();

    protected abstract void init(Context context);
    protected abstract void setAd(Ad ad);

}
