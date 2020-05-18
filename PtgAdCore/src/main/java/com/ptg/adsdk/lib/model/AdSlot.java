package com.ptg.adsdk.lib.model;

import com.ptg.adsdk.lib.dispatcher.policy.DispatchPolicyItem;

import android.view.View;
import android.view.ViewGroup;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdSlot {
    public static final int TYPE_BANNER = 1;
    public static final int TYPE_INTERACTION_AD = 2;
    public static final int TYPE_SPLASH = 3;
    public static final int TYPE_CACHED_SPLASH = 4;
    public static final int TYPE_FEED = 5;
    public static final int TYPE_REWARD_VIDEO = 7;
    public static final int TYPE_FULL_SCREEN_VIDEO = 8;
    public static final int TYPE_DRAW_FEED = 9;
    private String ptgSlotID;
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
    private boolean needSkipView;
    private String  splashType;
    private DispatchPolicyItem dispatchPolicyItem;
    // 用于广告渲染的ViewGroup
    private ViewGroup adContainer;

    // 可点击View，针对Feeds 有效
    private List<View> clickView;

    // 素材View，针对Feeds 有效
    private List<View> creativeView;

    public String getSplashType() {
        return this.splashType;
    }

    private AdSlot() {
        this.autoPlay = true;
        this.needSkipView=true;
    }

    public String getCodeId() {
        return this.codeID;
    }

    public void setCodeID(String codeID) {
        this.codeID = codeID;
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

    public String getPtgSlotID() {
        return this.ptgSlotID;
    }

    public boolean isAutoPlay() {
        return this.autoPlay;
    }

    public boolean needSkipView() {
        return needSkipView;
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

    public DispatchPolicyItem getDispatchPolicyItem() {
        return dispatchPolicyItem;
    }

    public void setDispatchPolicyItem(DispatchPolicyItem dispatchPolicyItem) {
        this.dispatchPolicyItem = dispatchPolicyItem;
    }

    public ViewGroup getAdContainer() {
        return adContainer;
    }

    public List<View> getClickView() {
        return clickView;
    }

    public List<View> getCreativeView() {
        return creativeView;
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
        private String ptgSlotID;
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
        private boolean autoPlay = true;
        private boolean needSkipView = true;
        private String  splashType;
        private ViewGroup  adContainer;
        private List<View> clickView;
        private List<View> creativeView;


        public Builder() {
        }

        public AdSlot.Builder setPtgSlotId(String slotId) {
            this.ptgSlotID = slotId;
            return this;
        }

        public AdSlot.Builder setNeedSkipView(boolean var1) {
            this.needSkipView = var1;
            return this;
        }

        public AdSlot.Builder setIsAutoPlay(boolean var1) {
            this.autoPlay = var1;
            return this;
        }

        public AdSlot.Builder setCodeId(String codeID) {
            this.codeID = codeID;
            return this;
        }
        public AdSlot.Builder setAdContainer(ViewGroup  adContainer) {
            this.adContainer = adContainer;
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

        public AdSlot.Builder setClickView(View ...view) {
            this.clickView = new ArrayList<>();
            for (View v : view) {
                this.clickView.add(v);
            }
            return this;
        }

        public AdSlot.Builder setCreativeView(View ...view) {
            this.creativeView = new ArrayList<>();
            for (View v : view) {
                this.creativeView.add(v);
            }
            return this;
        }

        public AdSlot build() {
            AdSlot adSlot = new AdSlot();
            adSlot.ptgSlotID = this.ptgSlotID;
            adSlot.codeID = this.codeID;
            adSlot.adCount = this.adCount;
            adSlot.supportDeepLink = this.supportDeepLink;
            adSlot.imgAcceptedWidth = this.imgAcceptedWidth;
            adSlot.imgAcceptedHeight = this.imgAcceptedHeight;
            adSlot.expressViewAcceptedWidth = this.expressViewAcceptedWidth;
            adSlot.expressViewAcceptedHeight = this.expressViewAcceptedHeight;
            adSlot.rewardName = this.rewardName;
            adSlot.rewardAmount = this.rewardAmount;
            adSlot.mediaExtra = this.mediaExtra;
            adSlot.userID = this.userID;
            adSlot.orientation = this.orientation;
            adSlot.nativeAdType = this.nativeAdType;
            adSlot.autoPlay = this.autoPlay;
            adSlot.splashType=this.splashType;
            adSlot.adContainer=this.adContainer;
            adSlot.needSkipView=this.needSkipView;
            adSlot.clickView = this.clickView;
            adSlot.creativeView = this.creativeView;
            return adSlot;
        }
    }
}

