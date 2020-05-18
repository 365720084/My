package com.ptg.adsdk.lib.dispatcher.filter;

import com.ptg.adsdk.lib.dispatcher.policy.DispatchPolicyItem;

public interface PolicyFilter {
    boolean MatchPolicyItem(DispatchPolicyItem item);
}
