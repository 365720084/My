package com.ptg.adsdk.lib.dispatcher.loader;

import android.util.Log;

import com.ptg.adsdk.lib.core.net.HttpCallbackListener;
import com.ptg.adsdk.lib.core.net.HttpJobMsg;
import com.ptg.adsdk.lib.core.net.NetUtils;
import com.ptg.adsdk.lib.dispatcher.policy.DispatchPolicy;

public class HttpConfigPolicyLoader implements PolicyLoader {
    static final String TAG = "HttpConfigPolicyLoader" ;
    String url;

    public HttpConfigPolicyLoader(String url) {
        this.url = url;
    }


    @Override
    public void Start(PolicyLoaderCallback callback) {
        Load(callback);
    }

    @Override
    public void Load(final PolicyLoaderCallback callback) {
        final HttpConfigPolicyLoader self = this;

        NetUtils.asyncFormRequestGet(this.url, new HttpCallbackListener() {

            @Override
            public boolean onPreRequest(HttpJobMsg httpJobMsg) {
                return false;
            }

            @Override
            public void onResult(HttpJobMsg httpJobMsg, int i, String str) {
                Log.d(HttpConfigPolicyLoader.TAG, String.format("Load with code:%d, result:%s", i, str));

                if (i != NetUtils.STATUS_OK) {
                    callback.OnError(String.format("HttpConfigPolicyLoader Load failed on url:%s", self.url));
                    return;
                }

                DispatchPolicy policy = new DispatchPolicy();
                if (!policy.UnmarshalJson(str)) {
                    callback.OnError(String.format("HttpConfigPolicyLoader Unmarshal Json failed on url:%s, body:%s", self.url, str));
                    return;
                }

                callback.OnSuccess(policy);
            }
        });
    }


}
