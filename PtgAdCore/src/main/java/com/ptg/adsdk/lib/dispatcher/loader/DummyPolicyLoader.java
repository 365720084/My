package com.ptg.adsdk.lib.dispatcher.loader;

import com.ptg.adsdk.lib.constants.AdProviderType;
import com.ptg.adsdk.lib.dispatcher.policy.DispatchPolicy;
import com.ptg.adsdk.lib.dispatcher.policy.DispatchPolicyItem;

public class DummyPolicyLoader implements PolicyLoader {
    @Override
    public void Start(PolicyLoaderCallback callback) {
        Load(callback);
    }

    @Override
    public void Load(PolicyLoaderCallback callback) {
        DispatchPolicy dispatchPolicy = new DispatchPolicy();

        DispatchPolicyItem policy = new DispatchPolicyItem();
        policy.setAdKey(123);
        policy.setImpTracking("http://www.baidu.com");
        policy.setClickTracking("https://test.ptgapi.com");
        policy.setWeight(1);
        policy.setConsumerType(AdProviderType.TT);
        policy.getTargetingGeo().add(new Long(500));
        policy.setConsumerSlotId("945136028");


        dispatchPolicy.addPolicy("testFeeds", policy);

        callback.OnSuccess(dispatchPolicy);
    }
}
