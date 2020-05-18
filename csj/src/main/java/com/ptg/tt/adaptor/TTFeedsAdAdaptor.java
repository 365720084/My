package com.ptg.tt.adaptor;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bytedance.sdk.openadsdk.TTFeedAd;
import com.bytedance.sdk.openadsdk.TTImage;
import com.bytedance.sdk.openadsdk.TTNativeAd;
import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.interf.PtgDownloadStatusController;
import com.ptg.adsdk.lib.interf.PtgFeedAd;
import com.ptg.adsdk.lib.model.PtgFilterWord;
import com.ptg.adsdk.lib.model.PtgImage;
import com.ptg.tt.utils.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TTFeedsAdAdaptor implements PtgFeedAd {
    static final String TAG = "TTFeedsAdAdaptor";

    TTFeedAd ttFeedAd;

    PtgImage videoCoverageImage;
    PtgImage icon;
    List<PtgImage> imageList;

    public TTFeedsAdAdaptor(TTFeedAd ttFeedAd) {
        this.ttFeedAd = ttFeedAd;
        this.build();
    }

    private void build() {
        this.videoCoverageImage = Transformer.TTImage2PtgImage(ttFeedAd.getVideoCoverImage());
        this.icon = Transformer.TTImage2PtgImage(ttFeedAd.getIcon());

        if (ttFeedAd.getImageList() != null) {
            this.imageList = new ArrayList<>();
            for (TTImage img : ttFeedAd.getImageList()) {
                this.imageList.add(Transformer.TTImage2PtgImage(img));
            }
        }
    }

    @Override
    public void setVideoAdListener(VideoAdListener listener) {

    }

    @Override
    public double getVideoDuration() {
        return 0;
    }

    @Nullable
    @Override
    public PtgImage getVideoCoverImage() {
        return this.videoCoverageImage;
    }

    @Override
    public Bitmap getAdLogo() {
        return ttFeedAd.getAdLogo();
    }

    @Override
    public String getTitle() {
        return ttFeedAd.getTitle();
    }

    @Override
    public String getDescription() {
        return ttFeedAd.getDescription();
    }

    @Override
    public String getButtonText() {
        return ttFeedAd.getButtonText();
    }

    @Override
    public int getAppScore() {
        return ttFeedAd.getAppScore();
    }

    @Override
    public int getAppCommentNum() {
        return ttFeedAd.getAppCommentNum();
    }

    @Override
    public int getAppSize() {
        return ttFeedAd.getAppSize();
    }

    @Override
    public String getSource() {
        return ttFeedAd.getSource();
    }

    @Override
    public PtgImage getIcon() {
        return this.icon;
    }

    @Override
    public List<PtgImage> getImageList() {
        return this.imageList;
    }

    @Override
    public int getInteractionType() {
        return Transformer.PtgInteractionType(ttFeedAd.getInteractionType());
    }

    @Override
    public int getImageMode() {
        return Transformer.PtgImageMode(ttFeedAd.getImageMode());
    }

    @Override
    public List<PtgFilterWord> getFilterWords() {
        return Transformer.ptgFilterWordList(ttFeedAd.getFilterWords());
    }

    @Override
    public void registerViewForInteraction(@NonNull ViewGroup container, @NonNull View clickView, AdInteractionListener listener) {
        ttFeedAd.registerViewForInteraction(container, clickView, createAdInteractionListener(listener));
    }

    @Override
    public void registerViewForInteraction(@NonNull ViewGroup container, @NonNull List<View> clickViews, @Nullable List<View> creativeViews, AdInteractionListener listener) {
        ttFeedAd.registerViewForInteraction(container, clickViews, creativeViews, createAdInteractionListener(listener));
    }

    @Override
    public PtgDownloadStatusController getDownloadStatusController() {
        return new PtgDownloadStatusController() {
            @Override
            public void changeDownloadStatus() {
                ttFeedAd.getDownloadStatusController().changeDownloadStatus();
            }

            @Override
            public void cancelDownload() {
                ttFeedAd.getDownloadStatusController().cancelDownload();
            }
        };
    }

    @Override
    public void setDownloadListener(PtgAppDownloadListener listener) {
        ttFeedAd.setDownloadListener(Transformer.TTAppDownloadListener(listener));
    }

    @Override
    public void setActivityForDownloadApp(@NonNull Activity activity) {
        ttFeedAd.setActivityForDownloadApp(activity);
    }

    @Override
    public View getAdView() {
        return ttFeedAd.getAdView();
    }

    @Override
    public Map<String, Object> getMediaExtraInfo() {
        return ttFeedAd.getMediaExtraInfo();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void resume() {

    }

    private TTFeedAd.VideoAdListener createVideoAdListener(final VideoAdListener listener) {
        final TTFeedsAdAdaptor self = this;

        return new TTFeedAd.VideoAdListener() {
            @Override
            public void onVideoLoad(TTFeedAd ttFeedAd) {
                listener.onVideoLoad(self);
            }

            @Override
            public void onVideoError(int errorCode, int extraCode) {
                listener.onVideoError(errorCode, extraCode);
            }

            @Override
            public void onVideoAdStartPlay(TTFeedAd ttFeedAd) {
                listener.onVideoAdStartPlay(self);
            }

            @Override
            public void onVideoAdPaused(TTFeedAd ttFeedAd) {
                listener.onVideoAdPaused(self);
            }

            @Override
            public void onVideoAdContinuePlay(TTFeedAd ttFeedAd) {
                listener.onVideoAdContinuePlay(self);
            }

            @Override
            public void onProgressUpdate(long l, long l1) {
                listener.onProgressUpdate(l, l1);
            }

            @Override
            public void onVideoAdComplete(TTFeedAd ttFeedAd) {
                listener.onVideoAdComplete(self);
            }
        };
    }

    private TTNativeAd.AdInteractionListener createAdInteractionListener(final AdInteractionListener listener) {
        final TTFeedsAdAdaptor self = this;

        return new TTNativeAd.AdInteractionListener() {

            @Override
            public void onAdClicked(View view, TTNativeAd ttNativeAd) {
                listener.onAdClicked(view, self);
            }

            @Override
            public void onAdCreativeClick(View view, TTNativeAd ttNativeAd) {
                listener.onAdCreativeClick(view, self);
            }

            @Override
            public void onAdShow(TTNativeAd ttNativeAd) {
                listener.onAdShow(self);
            }
        };
    }
}
