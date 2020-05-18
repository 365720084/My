package com.ptg.adsdk.lib.dispatcher.policy;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DispatchPolicyCandidate {
    int totalWeight = 0;
    List<DispatchPolicyItem> candidates;

    public DispatchPolicyCandidate() {
        this.candidates = new ArrayList<>();
    }

    public void addCandidate(@NotNull DispatchPolicyItem item) {
        totalWeight += item.getWeight();
        candidates.add(item);
    }

    public List<DispatchPolicyItem> getCandidates() {
        return this.candidates;
    }

    public boolean isEmpty() {
        return this.candidates.isEmpty();
    }

    public boolean isAsync() {
        // TODO 目前还不支持异步
        return false;
    }

    public int getTotalWeight() {
        return this.totalWeight;
    }
}
