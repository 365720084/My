package com.ptg.gdt.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ptg.adsdk.lib.constants.InteractionType;
import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.interf.PtgDownloadStatusController;
import com.ptg.adsdk.lib.interf.PtgFeedAd;
import com.ptg.adsdk.lib.interf.PtgNativeAd;
import com.ptg.adsdk.lib.model.PtgFilterWord;
import com.ptg.adsdk.lib.model.PtgImage;
import com.ptg.adsdk.lib.provider.PtgAdNative;
import com.ptg.gdt.common.GdtAdapterHelper;
import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeADEventListener;
import com.qq.e.ads.nativ.NativeADMediaListener;
import com.qq.e.ads.nativ.NativeADUnifiedListener;
import com.qq.e.ads.nativ.NativeUnifiedADData;
import com.qq.e.ads.nativ.widget.NativeAdContainer;
import com.qq.e.comm.constants.AdPatternType;
import com.qq.e.comm.util.AdError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GdtNativeADUnifiedAdapter implements NativeADUnifiedListener, NativeADEventListener, NativeADMediaListener {
    private Context context;
    private List<View> clickableViews;
    private PtgAdNative.FeedAdListener listener;

    private PtgFeedAd ptgFeedAd;
    private PtgFeedAd.VideoAdListener videoAdListener;
    private ViewGroup viewForInteraction;
    private PtgNativeAd.AdInteractionListener adInteractionListener;

    public GdtNativeADUnifiedAdapter(Context context, List<View> clickableViews, PtgAdNative.FeedAdListener listener) {
        this.context = context;
        this.clickableViews = clickableViews;
        this.listener = listener;
    }

    @Override
    public void onADLoaded(List<NativeUnifiedADData> list) {
        if (null == list || list.isEmpty()) {
            return;
        }

        final NativeUnifiedADData adData = list.get(0);
        final NativeAdContainer mContainer = new NativeAdContainer(context);
        adData.bindAdToView(context, mContainer, null, clickableViews);
        adData.setNativeAdEventListener(this);

        if (adData.getAdPatternType() == AdPatternType.NATIVE_VIDEO) {
            MediaView mMediaView = new MediaView(context);
            // 视频广告需对MediaView进行绑定，MediaView必须为容器mContainer的子View
            mContainer.addView(mMediaView);
            adData.bindMediaView(
                    mMediaView
                    , new VideoOption.Builder().setAutoPlayMuted(true).setAutoPlayPolicy(VideoOption.AutoPlayPolicy.WIFI).build()
                    , this
            );
        }

        ptgFeedAd = new PtgFeedAd() {
            @Override
            public void setVideoAdListener(VideoAdListener listener) {
                videoAdListener = listener;
            }
            @Override
            public double getVideoDuration() {
                return adData.getVideoDuration();
            }
            @Nullable
            @Override
            public PtgImage getVideoCoverImage() {
                return null;
            }
            @Override
            public Bitmap getAdLogo() {
                return null;
            }
            @Override
            public String getTitle() {
                return adData.getTitle();
            }
            @Override
            public String getDescription() {
                return adData.getDesc();
            }
            @Override
            public String getButtonText() {
                return null;
            }
            @Override
            public int getAppScore() {
                return adData.getAppScore();
            }
            @Override
            public int getAppCommentNum() {
                return -1;
            }
            @Override
            public int getAppSize() {
                return -1;
            }
            @Override
            public String getSource() {
                return null;
            }
            @Override
            public PtgImage getIcon() {
                if (adData.getAdPatternType() == AdPatternType.NATIVE_2IMAGE_2TEXT) {
                    // TODO icon size ?
                    return new PtgImage(16, 16, adData.getIconUrl());
                }
                return null;
            }
            @Override
            public List<PtgImage> getImageList() {
                List<PtgImage> imageList = null;
                switch (adData.getAdPatternType()) {
                    case AdPatternType.NATIVE_1IMAGE_2TEXT:
                    case AdPatternType.NATIVE_2IMAGE_2TEXT:
                        imageList = new ArrayList<PtgImage>(1);
                        // TODO image size ?
                        imageList.add(new PtgImage(adData.getPictureWidth(), adData.getPictureHeight(), adData.getImgUrl()));
                        break;
                    case AdPatternType.NATIVE_3IMAGE:
                        List<String> imgList = adData.getImgList();
                        if (imgList != null && !imgList.isEmpty()) {
                            imageList = new ArrayList<PtgImage>(imageList.size());
                            // TODO image size ?
                            int w = adData.getPictureWidth();
                            int h = adData.getPictureHeight();
                            for (String imgUrl : imgList) {
                                imageList.add(new PtgImage(w, h, imgUrl));
                            }
                        }
                        break;
                    default:
                }
                return imageList;
            }
            @Override
            public int getInteractionType() {
                /*if (adData.isAppAd()) {
                    return InteractionType.AppDownload;
                }*/
                return InteractionType.LANDING_PAGE;
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
            public void registerViewForInteraction(@NonNull ViewGroup container, @NonNull View clickView, AdInteractionListener listener) {
                viewForInteraction = container;
                adInteractionListener = listener;
            }
            @Override
            public void registerViewForInteraction(@NonNull ViewGroup container, @NonNull List<View> clickViews, @Nullable List<View> creativeViews, AdInteractionListener listener) {
                viewForInteraction = container;
                adInteractionListener = listener;
            }
            @Override
            public PtgDownloadStatusController getDownloadStatusController() {
                return null;
            }
            @Override
            public void setDownloadListener(PtgAppDownloadListener listener) {
            }
            @Override
            public void setActivityForDownloadApp(@NonNull Activity activity) {
            }
            @Override
            public View getAdView() {
                return mContainer;
            }
            @Override
            public Map<String, Object> getMediaExtraInfo() {
                return null;
            }
            @Override
            public void destroy() {
                adData.destroy();
            }
            @Override
            public void resume() {
                adData.resume();
            }
        };
        listener.onFeedAdLoad(ptgFeedAd);
    }

    @Override
    public void onNoAD(AdError adError) {
        listener.onError(adError.getErrorCode(), adError.getErrorMsg());
    }

    @Override
    public void onADExposed() {
        if (adInteractionListener != null) {
            adInteractionListener.onAdShow(ptgFeedAd);
        }
    }

    @Override
    public void onADClicked() {
        if (adInteractionListener != null) {
            adInteractionListener.onAdClicked(viewForInteraction, ptgFeedAd);
        }
    }

    @Override
    public void onADError(AdError adError) {
        listener.onError(adError.getErrorCode(), adError.getErrorMsg());
    }

    @Override
    public void onADStatusChanged() {
    }

    @Override
    public void onVideoInit() {
    }

    @Override
    public void onVideoLoading() {
    }

    @Override
    public void onVideoReady() {
    }

    @Override
    public void onVideoLoaded(int videoDuration) {
        if (videoAdListener != null) {
            videoAdListener.onVideoLoad(ptgFeedAd);
        }
    }

    @Override
    public void onVideoStart() {
        if (videoAdListener != null) {
            videoAdListener.onVideoAdStartPlay(ptgFeedAd);
        }
    }

    @Override
    public void onVideoPause() {
        if (videoAdListener != null) {
            videoAdListener.onVideoAdPaused(ptgFeedAd);
        }
    }

    @Override
    public void onVideoResume() {
        if (videoAdListener != null) {
            videoAdListener.onVideoAdContinuePlay(ptgFeedAd);
        }
    }

    @Override
    public void onVideoCompleted() {
        if (videoAdListener != null) {
            videoAdListener.onVideoAdComplete(ptgFeedAd);
        }
    }

    @Override
    public void onVideoError(AdError adError) {
        if (videoAdListener != null) {
            // TODO define video error code ?
            videoAdListener.onVideoError(adError.getErrorCode(), 0);
        }
    }

    @Override
    public void onVideoStop() {
    }

    @Override
    public void onVideoClicked() {
        if (adInteractionListener != null) {
            adInteractionListener.onAdClicked(viewForInteraction, ptgFeedAd);
        }
    }
}
