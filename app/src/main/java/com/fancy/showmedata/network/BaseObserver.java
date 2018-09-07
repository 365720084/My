package com.fancy.showmedata.network;

import android.content.Context;

import com.fancy.showmedata.utils.log.LogUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * BaseObserver
 * Created by jaycee on 2017/6/23.
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    private static final String TAG = "BaseObserver";
    private Context mContext;

    protected BaseObserver(Context context) {
        this.mContext = context.getApplicationContext();
    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseResponse<T> value) {
        if (value.getCode()==0) {
            T t = value.getData();
            onHandleSuccess(t);
        } else {
            onHandleFail(value.getMessage());
        }
    }

    @Override
    public void onError(Throwable e) {
        onHandleError(e.toString());
        LogUtils.i(TAG, "onError:" + e.getCause());
    }

    @Override
    public void onComplete() {
        LogUtils.i(TAG, "onComplete");
    }

    protected abstract void onHandleSuccess(T t);

    protected abstract void onHandleError(String msg);

    protected abstract void onHandleFail(String msg);
}
