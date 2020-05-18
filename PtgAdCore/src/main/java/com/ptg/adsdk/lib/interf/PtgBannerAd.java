package com.ptg.adsdk.lib.interf;

import android.view.View;


import java.util.Map;

public interface PtgBannerAd {

    View getBannerView();

    void setBannerInteractionListener(AdInteractionListener var1);

    void setDownloadListener(PtgAppDownloadListener var1);

    int getInteractionType();

    void setShowDislikeIcon(PtgAdDislike.DislikeInteractionCallback var1);

    PtgAdDislike getDislikeDialog(PtgAdDislike.DislikeInteractionCallback var1);

    void setSlideIntervalTime(int var1);

    Map<String, Object> getMediaExtraInfo();

    public interface AdInteractionListener {
        void onAdClicked(View var1, int var2);

        void onAdShow(View var1, int var2);
    }

}
