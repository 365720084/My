package com.fancy.showmedata.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.Unbinder;


/**
 * Created by bruce on 2017/9/21.
 */

public abstract class BaseRelativeLayout extends RelativeLayout {
    protected VideoUIDelegate mUIDelegate;
    protected Context mContext;
    protected Unbinder unbinder;
    public BaseRelativeLayout(Context context) {
        this(context,null);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context);
    }

    public void setDelegate(VideoUIDelegate delegate) {
        mUIDelegate = delegate;
    }

    protected abstract void initView(Context context);

    public abstract void updateView();

    public abstract void destroy();

    protected <T extends View> T findsViewById(int id) {
        //return返回view时,加上泛型T
        return (T) findViewById(id);
    }
}

