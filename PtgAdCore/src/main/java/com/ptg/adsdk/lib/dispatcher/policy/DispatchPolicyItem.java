package com.ptg.adsdk.lib.dispatcher.policy;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class DispatchPolicyItem {
    static final String TAG = "DispatchPolicyItem";

    /**
     * PtgSDK 广告位ID，并非消耗方广告位ID
     */
    private String slotId = "";

    /**
     * 策略ID
     */
    private long adKey = 0;
    private String consumerType;
    private int weight = 1;
    private Set<Long> targetingGeo;
    private Set<Long> targetingHour;

    private String bidTracking;
    private String impTracking;
    private String clickTracking;

    private String errorTracking;

    /**
     * 消耗方广告位ID
     */
    private String consumerSlotId;

    public DispatchPolicyItem() {
        this.targetingGeo = new HashSet<>();
        this.targetingHour = new HashSet<>();
    }

    public boolean UnmarshalJson(String str) {
        try {
            JSONObject object = new JSONObject(str);
            return UnmarshalJsonObject(object);
        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
            return false;
        }
    }

    public boolean UnmarshalJsonObject(JSONObject object) {
        try {
            this.setAdKey(object.getLong("adKey"));
            this.setWeight(object.getInt("weight"));

            JSONObject consumer = object.optJSONObject("consumer");
            this.setConsumerType(consumer.getString("consumerType"));
            this.setConsumerSlotId(consumer.getString("consumerSlotId"));

            JSONArray geoArray = object.optJSONArray("geoCode");
            if (geoArray != null) {
                for (int i = 0; i != geoArray.length(); i++) {
                    this.getTargetingGeo().add(geoArray.getLong(i));
                }
            }

            JSONArray hourArray = object.optJSONArray("hour");
            if (hourArray != null) {
                for (int i = 0; i != hourArray.length(); i++) {
                    this.getTargetingHour().add(hourArray.getLong(i));
                }
            }

            JSONObject trackingData = object.optJSONObject("trackingData");
            if (trackingData != null) {
                setImpTracking(trackingData.getString("impTracking"));
                setClickTracking(trackingData.getString("clickTracking"));

                setBidTracking(trackingData.optString("bidTracking"));
                setErrorTracking(trackingData.optString("errorTracking"));
            }

        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
            return false;
        }

        return true;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public long getAdKey() {
        return adKey;
    }

    public void setAdKey(long adKey) {
        this.adKey = adKey;
    }

    public String getConsumerType() {
        return consumerType;
    }

    public void setConsumerType(String consumerType) {
        this.consumerType = consumerType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Set<Long> getTargetingHour() {
        return targetingHour;
    }

    public void setTargetingHour(Set<Long> targetingHour) {
        this.targetingHour = targetingHour;
    }

    public String getImpTracking() {
        return impTracking;
    }

    public void setImpTracking(String impTracking) {
        this.impTracking = impTracking;
    }

    public String getClickTracking() {
        return clickTracking;
    }

    public void setClickTracking(String clickTracking) {
        this.clickTracking = clickTracking;
    }

    public Set<Long> getTargetingGeo() {
        return targetingGeo;
    }

    public void setTargetingGeo(Set<Long> targetingGeo) {
        this.targetingGeo = targetingGeo;
    }

    public String getConsumerSlotId() {
        return consumerSlotId;
    }

    public void setConsumerSlotId(String consumerSlotId) {
        this.consumerSlotId = consumerSlotId;
    }

    public String getBidTracking() {
        return bidTracking;
    }

    public void setBidTracking(String bidTracking) {
        this.bidTracking = bidTracking;
    }

    public String getErrorTracking() {
        return errorTracking;
    }

    public void setErrorTracking(String errorTracking) {
        this.errorTracking = errorTracking;
    }
}

