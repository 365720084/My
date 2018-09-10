package com.fancy.showmedata.base;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

/**
 * Created by sxx on 2018/3/2.
 */

public interface BaseSchedulerProvider {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
