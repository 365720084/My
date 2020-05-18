package com.ptg.adsdk.lib.interf;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ptg.adsdk.lib.model.PtgFilterWord;
import com.ptg.adsdk.lib.model.PtgImage;

import java.util.List;
import java.util.Map;

public interface PtgNativeAd {
    @Nullable
    PtgImage getVideoCoverImage();

    Bitmap getAdLogo();

    String getTitle();

    String getDescription();

    String getButtonText();

    int getAppScore();

    int getAppCommentNum();

    int getAppSize();

    String getSource();

    PtgImage getIcon();

    List<PtgImage> getImageList();

    int getInteractionType();

    int getImageMode();

    List<PtgFilterWord> getFilterWords();

    void registerViewForInteraction(@NonNull ViewGroup container, @NonNull View clickView, AdInteractionListener listener);

    void registerViewForInteraction(@NonNull ViewGroup container, @NonNull List<View> clickViews, @Nullable List<View> creativeViews, AdInteractionListener listener);

    PtgDownloadStatusController getDownloadStatusController();

    void setDownloadListener(PtgAppDownloadListener listener);

    void setActivityForDownloadApp(@NonNull Activity activity);

    View getAdView();

    Map<String, Object> getMediaExtraInfo();

    public interface AdInteractionListener {
        void onAdClicked(View view, PtgNativeAd ad);

        void onAdCreativeClick(View view, PtgNativeAd ad);

        void onAdShow(PtgNativeAd ad);
    }

    void destroy();

    void resume();
}
