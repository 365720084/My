package com.ptg.adsdk.lib.interf;


public interface PtgFeedAd extends PtgNativeAd {
    void setVideoAdListener(VideoAdListener listener);

    double getVideoDuration();

    public interface VideoAdListener {
        void onVideoLoad(PtgFeedAd var1);

        void onVideoError(int var1, int var2);

        void onVideoAdStartPlay(PtgFeedAd var1);

        void onVideoAdPaused(PtgFeedAd var1);

        void onVideoAdContinuePlay(PtgFeedAd var1);

        void onProgressUpdate(long var1, long var3);

        void onVideoAdComplete(PtgFeedAd var1);
    }


}
