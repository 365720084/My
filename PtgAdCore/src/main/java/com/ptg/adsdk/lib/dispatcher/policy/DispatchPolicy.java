package com.ptg.adsdk.lib.dispatcher.policy;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatchPolicy {
    static final String TAG = "DispatchPolicy";
    Map<String /*SlotId*/, List<DispatchPolicyItem>> policies;

    public DispatchPolicy() {
        policies = new HashMap<String, List<DispatchPolicyItem>>();
    }

    public void addPolicy(String slot, DispatchPolicyItem policy) {
        List<DispatchPolicyItem> list = policies.get(slot);
        if (list == null) {
            list = new ArrayList<DispatchPolicyItem>();
            policies.put(slot, list);
        }

        list.add(policy);
    }

    public List<DispatchPolicyItem> getPolicy(String slot) {
        return policies.get(slot);
    }

    public boolean UnmarshalJson(String str) {
        try {
            JSONObject object = new JSONObject(str);
            JSONArray slotArray = object.optJSONArray("slotBiddings");

            for (int i = 0; i < slotArray.length(); i++) {
                JSONObject slotObject = slotArray.optJSONObject(i);
                String slotId = slotObject.getString("slotId");

                JSONArray slotBiddingArray = slotObject.getJSONArray("slotBidding");
                for (int j = 0; j < slotBiddingArray.length(); j++) {
                    JSONObject biddingObject = slotBiddingArray.optJSONObject(j);

                    DispatchPolicyItem item = new DispatchPolicyItem();
                    if (!item.UnmarshalJsonObject(biddingObject)) {
                        return false;
                    }

                    item.setSlotId(slotId);

                    this.addPolicy(slotId, item);
                }
            }
        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
            return false;
        }

        return true;
    }
}
