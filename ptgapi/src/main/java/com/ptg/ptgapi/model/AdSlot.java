package com.ptg.ptgapi.model;

import org.json.JSONObject;

public class AdSlot {
    public static final int TYPE_BANNER = 1;
    public static final int TYPE_INTERACTION_AD = 2;
    public static final int TYPE_SPLASH = 3;
    public static final int TYPE_CACHED_SPLASH = 4;
    public static final int TYPE_FEED = 5;
    public static final int TYPE_REWARD_VIDEO = 7;
    public static final int TYPE_FULL_SCREEN_VIDEO = 8;
    public static final int TYPE_DRAW_FEED = 9;
    private String codeID;
    private int imgAcceptedWidth;
    private int imgAcceptedHeight;
    private float expressViewAcceptedWidth;
    private float expressViewAcceptedHeight;
    private int adCount;
    private boolean supportDeepLink;
    private String rewardName;
    private int rewardAmount;
    private String mediaExtra;
    private String userID;
    private int orientation;
    private int nativeAdType;
    private boolean autoPlay;
    private String  splashType;

    public String getSplashType() {
        return this.splashType;
    }

    private AdSlot() {
        this.autoPlay = true;
    }

    public String getCodeId() {
        return this.codeID;
    }

    public static int getPosition(int var0) {
        switch(var0) {
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
            case 4:
            case 7:
            case 8:
                return 5;
            case 5:
            case 9:
                return 3;
            case 6:
            default:
                return 3;
        }
    }

    public boolean isAutoPlay() {
        return this.autoPlay;
    }

    public int getImgAcceptedWidth() {
        return this.imgAcceptedWidth;
    }

    public int getImgAcceptedHeight() {
        return this.imgAcceptedHeight;
    }

    public float getExpressViewAcceptedWidth() {
        return this.expressViewAcceptedWidth;
    }

    public float getExpressViewAcceptedHeight() {
        return this.expressViewAcceptedHeight;
    }

    public boolean isSupportDeepLink() {
        return this.supportDeepLink;
    }

    public int getAdCount() {
        return this.adCount;
    }

    public void setAdCount(int adCount) {
        this.adCount = adCount;
    }

    public String getRewardName() {
        return this.rewardName;
    }

    public int getRewardAmount() {
        return this.rewardAmount;
    }

    public String getMediaExtra() {
        return this.mediaExtra;
    }

    public String getUserID() {
        return this.userID;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public int getNativeAdType() {
        return this.nativeAdType;
    }

    public void setNativeAdType(int nativeAdType) {
        this.nativeAdType = nativeAdType;
    }

    public String toString() {
        return "AdSlot{mCodeId='" + this.codeID + '\'' + ", mImgAcceptedWidth=" + this.imgAcceptedWidth + ", mImgAcceptedHeight=" + this.imgAcceptedHeight + ", mExpressViewAcceptedWidth=" + this.expressViewAcceptedWidth + ", mExpressViewAcceptedHeight=" + this.expressViewAcceptedHeight + ", mAdCount=" + this.adCount + ", mSupportDeepLink=" + this.supportDeepLink + ", mRewardName='" + this.rewardName + '\'' + ", mRewardAmount=" + this.rewardAmount + ", mMediaExtra='" + this.mediaExtra + '\'' + ", mUserID='" + this.userID + '\'' + ", mOrientation=" + this.orientation + ", mNativeAdType=" + this.nativeAdType + ", mIsAutoPlay=" + this.autoPlay + '}';
    }

    public JSONObject toJsonObj() {
        JSONObject var1 = new JSONObject();

        try {
            var1.put("mCodeId", this.codeID);
            var1.put("mIsAutoPlay", this.autoPlay);
            var1.put("mImgAcceptedWidth", this.imgAcceptedWidth);
            var1.put("mImgAcceptedHeight", this.imgAcceptedHeight);
            var1.put("mExpressViewAcceptedWidth", (double)this.expressViewAcceptedWidth);
            var1.put("mExpressViewAcceptedHeight", (double)this.expressViewAcceptedHeight);
            var1.put("mAdCount", this.adCount);
            var1.put("mSupportDeepLink", this.supportDeepLink);
            var1.put("mRewardName", this.rewardName);
            var1.put("mRewardAmount", this.rewardAmount);
            var1.put("mMediaExtra", this.mediaExtra);
            var1.put("mUserID", this.userID);
            var1.put("mOrientation", this.orientation);
            var1.put("mNativeAdType", this.nativeAdType);
        } catch (Exception var3) {
        }

        return var1;
    }


    public static class Builder {

        private String codeID;
        private int imgAcceptedWidth = 640;
        private int imgAcceptedHeight = 320;
        private boolean supportDeepLink;
        private int adCount = 1;
        private String rewardName;
        private int rewardAmount;
        private String mediaExtra;
        private String userID;
        private int orientation;
        private int nativeAdType;
        private float expressViewAcceptedWidth;
        private float expressViewAcceptedHeight;
        private boolean n = true;
        private String  splashType;


        public Builder() {
        }

        public AdSlot.Builder setIsAutoPlay(boolean var1) {
            this.n = var1;
            return this;
        }

        public AdSlot.Builder setCodeId(String codeID) {
            this.codeID = codeID;
            return this;
        }

        public AdSlot.Builder setImageAcceptedSize(int w, int h) {
            this.imgAcceptedWidth = w;
            this.imgAcceptedHeight = h;
            return this;
        }

        public AdSlot.Builder setExpressViewAcceptedSize(float w, float h) {
            this.expressViewAcceptedWidth = w;
            this.expressViewAcceptedHeight = h;
            return this;
        }

        public AdSlot.Builder setSupportDeepLink(boolean value) {
            this.supportDeepLink = value;
            return this;
        }

        public AdSlot.Builder setAdCount(int value) {
            this.adCount = value;
            return this;
        }

        public AdSlot.Builder setSplashType(String splashType) {
            this.splashType = splashType;
            return this;

        }

        public AdSlot.Builder setRewardName(String var1) {
            this.rewardName = var1;
            return this;
        }

        public AdSlot.Builder setRewardAmount(int var1) {
            this.rewardAmount = var1;
            return this;
        }

        public AdSlot.Builder setMediaExtra(String var1) {
            this.mediaExtra = var1;
            return this;
        }

        public AdSlot.Builder setUserID(String var1) {
            this.userID = var1;
            return this;
        }

        public AdSlot.Builder setOrientation(int var1) {
            this.orientation = var1;
            return this;
        }

        public AdSlot.Builder setNativeAdType(int var1) {
            this.nativeAdType = var1;
            return this;
        }

        public AdSlot build() {
            AdSlot var1 = new AdSlot();
            var1.codeID = this.codeID;
            var1.adCount = this.adCount;
            var1.supportDeepLink = this.supportDeepLink;
            var1.imgAcceptedWidth = this.imgAcceptedWidth;
            var1.imgAcceptedHeight = this.imgAcceptedHeight;
            var1.expressViewAcceptedWidth = this.expressViewAcceptedWidth;
            var1.expressViewAcceptedHeight = this.expressViewAcceptedHeight;
            var1.rewardName = this.rewardName;
            var1.rewardAmount = this.rewardAmount;
            var1.mediaExtra = this.mediaExtra;
            var1.userID = this.userID;
            var1.orientation = this.orientation;
            var1.nativeAdType = this.nativeAdType;
            var1.autoPlay = this.n;
            var1.splashType=this.splashType;
            return var1;
        }
    }
}

