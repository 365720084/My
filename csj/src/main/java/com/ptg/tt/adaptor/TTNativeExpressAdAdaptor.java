package com.ptg.tt.adaptor;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.bytedance.sdk.openadsdk.TTAdDislike;
import com.bytedance.sdk.openadsdk.TTDislikeDialogAbstract;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.ptg.adsdk.lib.component.PtgDislikeDialogAbstract;
import com.ptg.adsdk.lib.interf.PtgAdDislike;
import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.interf.PtgNativeExpressAd;
import com.ptg.adsdk.lib.model.PtgFilterWord;
import com.ptg.tt.utils.Transformer;

import java.util.List;
import java.util.Map;

public class TTNativeExpressAdAdaptor implements PtgNativeExpressAd {
    static final String TAG = "TTNativeExpressAdAdaptor";

    TTNativeExpressAd ttNativeExpressAd;

    public TTNativeExpressAdAdaptor(TTNativeExpressAd ttNativeExpressAd) {
        this.ttNativeExpressAd = ttNativeExpressAd;
        this.build();
    }

    private void build() {

    }

    @Override
    public View getExpressAdView() {
        return ttNativeExpressAd.getExpressAdView();
    }

    @Override
    public int getImageMode() {
        return Transformer.PtgImageMode(ttNativeExpressAd.getImageMode());
    }

    @Override
    public List<PtgFilterWord> getFilterWords() {
        return Transformer.ptgFilterWordList(ttNativeExpressAd.getFilterWords());
    }

    @Override
    public void setExpressInteractionListener(ExpressAdInteractionListener listener) {
        ttNativeExpressAd.setExpressInteractionListener(createExpressAdInteractionListener(listener));
    }

    @Override
    public void setExpressInteractionListener(AdInteractionListener listener) {
        ttNativeExpressAd.setExpressInteractionListener(createAdInteractionListener(listener));
    }

    @Override
    public void setDownloadListener(PtgAppDownloadListener listener) {
        ttNativeExpressAd.setDownloadListener(Transformer.TTAppDownloadListener(listener));
    }

    @Override
    public int getInteractionType() {
        return Transformer.PtgInteractionType(ttNativeExpressAd.getInteractionType());
    }

    @Override
    public void render() {
        ttNativeExpressAd.render();
    }

    @Override
    public void destroy() {
        ttNativeExpressAd.destroy();
    }

    @Override
    public void setDislikeCallback(Activity activity, final PtgAdDislike.DislikeInteractionCallback callback) {
        ttNativeExpressAd.setDislikeCallback(activity, new TTAdDislike.DislikeInteractionCallback() {
            @Override
            public void onSelected(int i, String s) {
                callback.onSelected(i, s);
            }

            @Override
            public void onCancel() {
                callback.onCancel();
            }
        });
    }

    @Override
    public void setDislikeDialog(final PtgDislikeDialogAbstract dislikeDialog) {
        ttNativeExpressAd.setDislikeDialog(new TTDislikeDialogAbstract(dislikeDialog.getContext()) {

            @Override
            public int getLayoutId() {
                return dislikeDialog.getLayoutId();
            }

            @Override
            public int[] getTTDislikeListViewIds() {
                return dislikeDialog.getPtgDislikeListViewIds();
            }

            @Override
            public ViewGroup.LayoutParams getLayoutParams() {
                return dislikeDialog.getLayoutParams();
            }
        });
    }

    @Override
    public void showInteractionExpressAd(Activity activity) {
        ttNativeExpressAd.showInteractionExpressAd(activity);
    }

    @Override
    public void setSlideIntervalTime(int intervalTime) {
        ttNativeExpressAd.setSlideIntervalTime(intervalTime);
    }

    @Override
    public void setVideoAdListener(ExpressVideoAdListener listener) {
        ttNativeExpressAd.setVideoAdListener(createVideoAdListener(listener));
    }

    @Override
    public void setCanInterruptVideoPlay(boolean canInterruptVideoPlay) {
        ttNativeExpressAd.setCanInterruptVideoPlay(canInterruptVideoPlay);
    }

    @Override
    public Map<String, Object> getMediaExtraInfo() {
        return ttNativeExpressAd.getMediaExtraInfo();
    }

    private TTNativeExpressAd.ExpressAdInteractionListener createExpressAdInteractionListener(final ExpressAdInteractionListener listener) {
        return new TTNativeExpressAd.ExpressAdInteractionListener() {

            @Override
            public void onAdClicked(View view, int type) {
                listener.onAdClicked(view, Transformer.PtgInteractionType(type));
            }

            @Override
            public void onAdShow(View view, int type) {
                listener.onAdShow(view, Transformer.PtgInteractionType(type));
            }

            @Override
            public void onRenderFail(View view, String msg, int code) {
                listener.onRenderFail(view, msg, code);
            }

            @Override
            public void onRenderSuccess(View view, float width, float height) {
                listener.onRenderSuccess(view, width, height);
            }
        };
    }

    private TTNativeExpressAd.AdInteractionListener createAdInteractionListener(final AdInteractionListener listener) {
        return new TTNativeExpressAd.AdInteractionListener() {

            @Override
            public void onAdDismiss() {
                listener.onAdDismiss();
            }

            @Override
            public void onAdClicked(View view, int type) {
                listener.onAdClicked(view, Transformer.PtgInteractionType(type));
            }

            @Override
            public void onAdShow(View view, int type) {
                listener.onAdShow(view, Transformer.PtgInteractionType(type));
            }

            @Override
            public void onRenderFail(View view, String msg, int code) {
                listener.onRenderFail(view, msg, code);
            }

            @Override
            public void onRenderSuccess(View view, float width, float height) {
                listener.onRenderSuccess(view, width, height);
            }
        };
    }

    private TTNativeExpressAd.ExpressVideoAdListener createVideoAdListener(final ExpressVideoAdListener listener) {
        return new TTNativeExpressAd.ExpressVideoAdListener() {

            @Override
            public void onVideoLoad() {
                listener.onVideoLoad();
            }

            @Override
            public void onVideoError(int errorCode, int extraCode) {
                listener.onVideoError(errorCode, extraCode);
            }

            @Override
            public void onVideoAdStartPlay() {
                listener.onVideoAdStartPlay();
            }

            @Override
            public void onVideoAdPaused() {
                listener.onVideoAdPaused();
            }

            @Override
            public void onVideoAdContinuePlay() {
                listener.onVideoAdContinuePlay();
            }

            @Override
            public void onProgressUpdate(long current, long duration) {
                listener.onProgressUpdate(current, duration);
            }

            @Override
            public void onVideoAdComplete() {
                listener.onVideoAdComplete();
            }

            @Override
            public void onClickRetry() {
                listener.onClickRetry();
            }
        };
    }
}
