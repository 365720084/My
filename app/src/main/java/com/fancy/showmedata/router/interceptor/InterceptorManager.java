package com.fancy.showmedata.router.interceptor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fancy.showmedata.application.Constants;
import com.fancy.showmedata.router.activity.SchemeFilterActivity;
import com.fancy.showmedata.router.mode.InterceptorInfo;
import com.fancy.showmedata.router.thread.MainLooper;
import com.google.gson.Gson;
import com.fancy.showmedata.utils.log.LogUtils;

import java.util.ArrayList;
import java.util.Set;


/**
 * Created by bruce on 2018/3/1.
 */
@Interceptor(priority = 7)
public class InterceptorManager implements IInterceptor {

    private String Tag = "InterceptorManager";
    private Context mContext;

    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        LogUtils.d("arouter", "Uri:" + postcard.getUri());
        Bundle bundle = postcard.getExtras();

        //正常的頁面跳轉是沒有Uri的，通過H5、Weex交互或者第三方應用的跳轉才會有Uri
        Uri uri = postcard.getUri();
        if (uri != null) {
            //将uri中的参数传给bundle
            Set<String> url = uri.getQueryParameterNames();
            for (String s : url) {
                String a = uri.getQueryParameter(s);
                if(s.equals(Constants.FIELD_TARGET_TAB)){
                    int tabIndex=0;
                    bundle.putInt(s,tabIndex);
                }else {
                    bundle.putString(s, a);
                }
                if (a.equals("cid")) {
                    //这个用在唤醒应用时
                    String ss = uri.toString();
                }
            }
            postcard.with(bundle);

            //做拦截处理
            String hasLogin = uri.getQueryParameter("needLogin");
            String interceptor = uri.getQueryParameter("interceptor");
            String hh = bundle.getString("needLogin");
            if (interceptor != null) {
                InterceptorInfo interceptorInfo = new Gson().fromJson(interceptor, InterceptorInfo.class);
                final AlertDialog.Builder ab = new AlertDialog.Builder(SchemeFilterActivity.getThis());
                ab.setCancelable(false);
                ab.setTitle(interceptorInfo.getTitle());
                ab.setMessage(interceptorInfo.getText());
                ArrayList<InterceptorInfo.SelectInfo> selectInfos = interceptorInfo.getSelect();
                for (final InterceptorInfo.SelectInfo selectInfo : selectInfos) {
                    if (selectInfo.getUrl() == null || selectInfo.getUrl().equals("null") || selectInfo.getUrl().equals("")) {
                        ab.setNegativeButton(selectInfo.getText(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                callback.onInterrupt(null);
                                SchemeFilterActivity.getThis().finish();
                            }
                        });
                    } else {
                        ab.setPositiveButton(selectInfo.getText(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ARouter.getInstance().build(Uri.parse(selectInfo.getUrl())).navigation();
                                SchemeFilterActivity.getThis().finish();
                            }
                        });
                    }
                }
                MainLooper.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ab.create().show();
                        } catch (Exception e) {
                        }
                    }
                });

            } else {
                callback.onContinue(postcard);
            }

        } else {
            callback.onContinue(postcard);
        }


    }

    @Override
    public void init(Context context) {
        this.mContext = context;
    }
}

