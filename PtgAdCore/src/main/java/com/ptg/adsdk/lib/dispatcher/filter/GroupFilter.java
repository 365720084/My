package com.ptg.adsdk.lib.dispatcher.filter;

import com.ptg.adsdk.lib.dispatcher.policy.DispatchPolicyItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GroupFilter implements PolicyFilter {
    List<PolicyFilter> filters;

    public GroupFilter() {
        this.filters = new ArrayList<>();
    }

    @Override
    public boolean MatchPolicyItem(DispatchPolicyItem item) {
        for (PolicyFilter filter : this.filters) {
            if (!filter.MatchPolicyItem(item)) {
                return false;
            }
        }

        return true;
    }

    public GroupFilter addFilter(@NotNull PolicyFilter filter) {
        this.filters.add(filter);
        return this;
    }
}
