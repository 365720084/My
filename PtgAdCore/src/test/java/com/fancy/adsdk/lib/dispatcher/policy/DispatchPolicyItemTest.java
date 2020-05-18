package com.ptg.adsdk.lib.dispatcher.policy;

import org.junit.Test;

import static org.junit.Assert.*;

public class DispatchPolicyItemTest {

    @Test
    public void unmarshalJson() {
        String data = "{\"adKey\":4000048,\"geoCode\":[1156130000,1156110000],\"weight\":30,\"consumer\":{\"timeOut\":50,\"consumerType\":\"tt\", \"consumerSlotId\":\"haha\"},\"trackingData\":{\"impTracking\":\"http://log.test.amnetapi.com/imp?id=1\",\"clickTracking\":\"http://log.test.amnetapi.com/click?id=1\"}}";

        DispatchPolicyItem item = new DispatchPolicyItem();
        boolean result = item.UnmarshalJson(data);

//        assertEquals(true, result);
//        assertEquals(30, item.getWeight());
//        assertEquals(4000048, item.getAdKey());
//        assertEquals(2, item.getTargetingGeo().size());
//        assertEquals("tt", item.getConsumerType());
//        assertEquals("haha", item.getConsumerSlotId());
//        assertEquals("http://log.test.amnetapi.com/click?id=1", item.getClickTracking());
//        assertEquals("http://log.test.amnetapi.com/imp?id=1", item.getImpTracking());
    }
}