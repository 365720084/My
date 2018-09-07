package com.fancy.showmedata.network;

import io.reactivex.functions.Consumer;

/**
 * 当返回的数据不继承basereponse时使用这个
 * Created by sxx on 2017/7/4.
 */

public abstract class BaseResponsePrase<T> implements Consumer<T> {


    @Override
    public void accept(T t) throws Exception {
        if (t instanceof BaseResponse) {
            BaseResponse response = (BaseResponse) t;
            if (response.getCode() == 0) {
                myOnResponse(t);
            } else {
                myOnError(response.getMessage(), response.getCode());
            }
        } else {
            myOnResponse(t);
        }
    }

    protected abstract void myOnResponse(T response);

    //    protected abstract void myOnFailure(Throwable t);
//
    protected abstract void myOnError(String message, int status);

}
