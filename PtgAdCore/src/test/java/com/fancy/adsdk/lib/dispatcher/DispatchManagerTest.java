package com.ptg.adsdk.lib.dispatcher;

import com.ptg.adsdk.lib.dispatcher.filter.GroupFilter;
import com.ptg.adsdk.lib.dispatcher.loader.DummyPolicyLoader;
import com.ptg.adsdk.lib.dispatcher.policy.DispatchPolicyCandidate;

import org.junit.Before;
import org.junit.Test;


public class DispatchManagerTest {
    DispatchManager manager;

    @Before
    public void setUp() throws Exception {
        manager = new DispatchManager(
                new DummyPolicyLoader(),
                new GroupFilter()
        );
    }


    @Test
    public void start() {
        manager.start();
    }

    @Test
    public void dispatchPolicy() {
        manager.start();
        DispatchPolicyCandidate result = manager.dispatchPolicy("test");

//        Assert.assertEquals(result.getCandidates().size(), 1);
    }

    @Test
    public void yieldPolicy() {
    }

    @Test
    public void weightedRandomSelect() {
    }

    @Test
    public void asyncSelect() {
    }
}