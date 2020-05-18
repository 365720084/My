package com.ptg.adsdk.lib.dispatcher;

import android.util.Log;

import com.ptg.adsdk.lib.dispatcher.filter.PolicyFilter;
import com.ptg.adsdk.lib.dispatcher.policy.DispatchPolicy;
import com.ptg.adsdk.lib.dispatcher.policy.DispatchPolicyCandidate;
import com.ptg.adsdk.lib.dispatcher.policy.DispatchPolicyItem;
import com.ptg.adsdk.lib.dispatcher.loader.PolicyLoader;
import com.ptg.adsdk.lib.dispatcher.loader.PolicyLoaderCallback;

import java.util.List;
import java.util.Random;

public class DispatchManager {
    static final String TAG = "DispatchManager";

    PolicyLoader loader;
    PolicyFilter policyFilter;
    DispatchPolicy policy;


    public DispatchManager(PolicyLoader loader, PolicyFilter policyFilter) {
        this.loader = loader;
        this.policyFilter = policyFilter;
    }

    public void start() {
        final DispatchManager self = this;
        this.loader.Start(new PolicyLoaderCallback() {
            @Override
            public void OnSuccess(DispatchPolicy policy) {
                Log.d(DispatchManager.TAG, "policy updated");
                self.policy = policy;
            }

            @Override
            public void OnError(String message) {
                Log.d(DispatchManager.TAG, String.format("Loader fail: %s", message));
            }
        });
    }

    public DispatchPolicyCandidate dispatchPolicy(String slot) {
        if (this.policy == null) {
            return null;
        }

        List<DispatchPolicyItem> candidate = this.policy.getPolicy(slot);
        if (candidate == null) {
            return null;
        }

        DispatchPolicyCandidate result = new DispatchPolicyCandidate();
        for (DispatchPolicyItem item : candidate) {
            if (this.policyFilter.MatchPolicyItem(item)) {
                result.addCandidate(item);
            }
        }

        return yieldPolicy(result);
    }


    /**
     * 在所有可用集合上选择最终的执行策略
     *  当结果集中没有并行策略时：
     *      以结果中的Weight值为权重进行随机
     *  当结果集中有并行策略时：
     *      对于有展现率要求的消耗方进行权重随机，对于其余消耗方全部推送当前策略（未实现）
     * @param candidates 可用的策略集合
     * @return
     */
    public DispatchPolicyCandidate yieldPolicy(DispatchPolicyCandidate candidates) {
        if (candidates.isEmpty()) {
            return null;
        }

        if (candidates.isAsync()){
            return asyncSelect(candidates);
        } else {
            return weightedRandomSelect(candidates);
        }
    }

    public DispatchPolicyCandidate weightedRandomSelect(DispatchPolicyCandidate candidates) {
        int totalWeight = candidates.getTotalWeight();
        Random ran = new Random();
        int needle = ran.nextInt(totalWeight);

        DispatchPolicyCandidate result = new DispatchPolicyCandidate();

        int totalSum = 0;
        for (DispatchPolicyItem item : candidates.getCandidates()) {
            totalSum += item.getWeight();
            if (totalSum > needle) {
                result.addCandidate(item);
                break;
            }
        }

        return result;
    }

    public DispatchPolicyCandidate asyncSelect(DispatchPolicyCandidate candidates) {
        // TODO
        return new DispatchPolicyCandidate();
    }
}
