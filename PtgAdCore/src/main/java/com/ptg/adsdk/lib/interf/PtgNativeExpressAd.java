package com.ptg.adsdk.lib.interf;

import android.app.Activity;
import android.view.View;

import androidx.annotation.MainThread;

import com.ptg.adsdk.lib.component.PtgDislikeDialogAbstract;
import com.ptg.adsdk.lib.model.PtgFilterWord;

import java.util.List;
import java.util.Map;

public interface PtgNativeExpressAd {
    View getExpressAdView();

    int getImageMode();

    List<PtgFilterWord> getFilterWords();

    void setExpressInteractionListener(ExpressAdInteractionListener var1);

    void setExpressInteractionListener(AdInteractionListener var1);

    void setDownloadListener(PtgAppDownloadListener var1);

    int getInteractionType();

    void render();

    void destroy();

    void setDislikeCallback(Activity var1, PtgAdDislike.DislikeInteractionCallback var2);

    void setDislikeDialog(PtgDislikeDialogAbstract var1);

    @MainThread
    void showInteractionExpressAd(Activity var1);

    void setSlideIntervalTime(int var1);

    void setVideoAdListener(ExpressVideoAdListener var1);

    void setCanInterruptVideoPlay(boolean var1);

//    getAdManager getVideoModel();

    Map<String, Object> getMediaExtraInfo();

    public interface ExpressVideoAdListener {
        void onVideoLoad();

        void onVideoError(int errorCode, int extraCode);

        void onVideoAdStartPlay();

        void onVideoAdPaused();

        void onVideoAdContinuePlay();

        void onProgressUpdate(long current, long duration);

        void onVideoAdComplete();

        void onClickRetry();
    }

    public interface AdInteractionListener extends ExpressAdInteractionListener {
        void onAdDismiss();
    }

    public interface ExpressAdInteractionListener {
        void onAdClicked(View view, int type);

        void onAdShow(View view, int type);

        void onRenderFail(View view, String msg, int code);

        void onRenderSuccess(View view, float width, float height);
    }
}
