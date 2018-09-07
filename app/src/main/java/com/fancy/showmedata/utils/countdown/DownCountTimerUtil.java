package com.fancy.showmedata.utils.countdown;



/**
 * Created by Meng on 2018/1/22.
 */

public class DownCountTimerUtil extends AdvancedCountdownTimer{

    private DownCountTimerListener downCountTimerListener;

    public DownCountTimerUtil(long millisInFuture, long countDownInterval, DownCountTimerListener listener) {
        super(millisInFuture, countDownInterval);
        this.downCountTimerListener = listener;
    }

    /**
     * 刷新时间
     * @param millisUntilFinished
     */
//    @Override
//    public void onTick(long millisUntilFinished) {
//    }

    @Override
    public void onTick(long millisUntilFinished, int percent) {
        downCountTimerListener.downCountTimerUpdate(millisUntilFinished);

    }

    /**
     * 事物被注销
     */
    @Override
    public void onFinish() {
        downCountTimerListener.downCountTimerFinish();
    }
}
