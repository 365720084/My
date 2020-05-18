package com.ptg.gdt.adapter;

import android.app.Activity;
import android.view.View;

import com.ptg.adsdk.lib.component.PtgDislikeDialogAbstract;
import com.ptg.adsdk.lib.constants.InteractionType;
import com.ptg.adsdk.lib.interf.PtgAdDislike;
import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.interf.PtgNativeExpressAd;
import com.ptg.adsdk.lib.model.PtgFilterWord;
import com.ptg.adsdk.lib.provider.PtgAdNative;
import com.ptg.gdt.common.GdtAdapterHelper;
import com.qq.e.ads.nativ.NativeExpressAD;
import com.qq.e.ads.nativ.NativeExpressADView;
import com.qq.e.ads.nativ.NativeExpressMediaListener;
import com.qq.e.comm.constants.AdPatternType;
import com.qq.e.comm.pi.AdData;
import com.qq.e.comm.util.AdError;

import java.util.List;
import java.util.Map;

public class NativeExpressADAdapter implements NativeExpressAD.NativeExpressADListener, NativeExpressMediaListener {
    private PtgAdNative.NativeExpressAdListener listener;

    private PtgNativeExpressAd.ExpressAdInteractionListener expressAdInteractionListener;
    private PtgNativeExpressAd.AdInteractionListener adInteractionListener;
    private PtgNativeExpressAd.ExpressVideoAdListener videoAdListener;

    public NativeExpressADAdapter(PtgAdNative.NativeExpressAdListener listener) {
        this.listener = listener;
    }

    @Override
    public void onADLoaded(List<NativeExpressADView> list) {
        if (null == list || list.isEmpty()) {
            return;
        }
        final NativeExpressADView nativeExpressADView = list.get(0);
        final AdData adData = nativeExpressADView.getBoundData();
        if (adData.getAdPatternType() == AdPatternType.NATIVE_VIDEO) {
            nativeExpressADView.setMediaListener(this);
        }

        listener.onNativeExpressAdLoad(new PtgNativeExpressAd() {
            @Override
            public View getExpressAdView() {
                return nativeExpressADView;
            }
            @Override
            public int getImageMode() {
                return GdtAdapterHelper.transformAdPatternType2ImageMode(adData.getAdPatternType());
            }
            @Override
            public List<PtgFilterWord> getFilterWords() {
                return null;
            }
            @Override
            public void setExpressInteractionListener(ExpressAdInteractionListener listener) {
                expressAdInteractionListener = listener;
            }
            @Override
            public void setExpressInteractionListener(AdInteractionListener listener) {
                adInteractionListener = listener;
                expressAdInteractionListener = listener;
            }
            @Override
            public void setDownloadListener(PtgAppDownloadListener downloadListener) {
            }
            @Override
            public int getInteractionType() {
                return InteractionType.LANDING_PAGE;// TODO
            }
            @Override
            public void render() {
                nativeExpressADView.render();
            }
            @Override
            public void destroy() {
                nativeExpressADView.destroy();
            }
            @Override
            public void setDislikeCallback(Activity activity, PtgAdDislike.DislikeInteractionCallback callback) {
            }
            @Override
            public void setDislikeDialog(PtgDislikeDialogAbstract dialog) {
            }
            @Override
            public void showInteractionExpressAd(Activity activity) {
            }
            @Override
            public void setSlideIntervalTime(int intervalTime) {
            }
            @Override
            public void setVideoAdListener(ExpressVideoAdListener listener) {
                videoAdListener = listener;
            }
            @Override
            public void setCanInterruptVideoPlay(boolean canInterruptVideoPlay) {
            }
            @Override
            public Map<String, Object> getMediaExtraInfo() {
                return null;
            }
        });
    }

    @Override
    public void onRenderFail(NativeExpressADView nativeExpressADView) {
        if (expressAdInteractionListener != null) {
            expressAdInteractionListener.onRenderFail(nativeExpressADView, "渲染广告失败", 5011);
        }
    }

    @Override
    public void onRenderSuccess(NativeExpressADView nativeExpressADView) {
        if (expressAdInteractionListener != null) {
            expressAdInteractionListener.onRenderSuccess(nativeExpressADView, nativeExpressADView.getWidth(), nativeExpressADView.getHeight());
        }
    }

    @Override
    public void onADExposure(NativeExpressADView nativeExpressADView) {
        if (expressAdInteractionListener != null) {
            expressAdInteractionListener.onAdShow(nativeExpressADView, InteractionType.LANDING_PAGE);// TODO
        }
    }

    @Override
    public void onADClicked(NativeExpressADView nativeExpressADView) {
        if (expressAdInteractionListener != null) {
            expressAdInteractionListener.onAdClicked(nativeExpressADView, InteractionType.LANDING_PAGE);// TODO
        }
    }

    @Override
    public void onADClosed(NativeExpressADView nativeExpressADView) {
        nativeExpressADView.setVisibility(View.GONE);
        nativeExpressADView.destroy();
        if (adInteractionListener != null) {
            adInteractionListener.onAdDismiss();
        }
    }

    @Override
    public void onADLeftApplication(NativeExpressADView nativeExpressADView) {
    }

    @Override
    public void onADOpenOverlay(NativeExpressADView nativeExpressADView) {
    }

    @Override
    public void onADCloseOverlay(NativeExpressADView nativeExpressADView) {
    }

    @Override
    public void onNoAD(AdError adError) {
        listener.onError(adError.getErrorCode(), adError.getErrorMsg());
    }

    @Override
    public void onVideoInit(NativeExpressADView nativeExpressADView) {
    }

    @Override
    public void onVideoLoading(NativeExpressADView nativeExpressADView) {
    }

    @Override
    public void onVideoCached(NativeExpressADView nativeExpressADView) {
    }

    @Override
    public void onVideoReady(NativeExpressADView nativeExpressADView, long videoDuration) {
        if (videoAdListener != null) {
            videoAdListener.onVideoLoad();
        }
    }

    @Override
    public void onVideoStart(NativeExpressADView nativeExpressADView) {
        if (videoAdListener != null) {
            videoAdListener.onVideoAdStartPlay();
        }
    }

    @Override
    public void onVideoPause(NativeExpressADView nativeExpressADView) {
        if (videoAdListener != null) {
            videoAdListener.onVideoAdPaused();
        }
    }

    @Override
    public void onVideoComplete(NativeExpressADView nativeExpressADView) {
        if (videoAdListener != null) {
            videoAdListener.onVideoAdComplete();
        }
    }

    @Override
    public void onVideoError(NativeExpressADView nativeExpressADView, AdError adError) {
        if (videoAdListener != null) {
            videoAdListener.onVideoError(adError.getErrorCode(), 0);// TODO extra error code ?
        }
    }

    @Override
    public void onVideoPageOpen(NativeExpressADView nativeExpressADView) {
    }

    @Override
    public void onVideoPageClose(NativeExpressADView nativeExpressADView) {
    }
}
