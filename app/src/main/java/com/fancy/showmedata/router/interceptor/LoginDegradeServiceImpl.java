package com.fancy.showmedata.router.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;


/**
 * Created by bruce on 2018/3/29.
 */

@Route(path = "/native/*")
public class LoginDegradeServiceImpl implements DegradeService {

    Context mContext;

    @Override
    public void onLost(Context context, Postcard postcard) {
//        LogDog.eLog("lost",postcard.getPath());
    }

    @Override
    public void init(Context context) {
        this.mContext = context ;
    }
}
