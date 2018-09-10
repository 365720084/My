package com.fancy.showmedata.base;

/**
 * Created by sxx on 2018/3/2.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);

    boolean isActive();//是否在生命周期内
}
