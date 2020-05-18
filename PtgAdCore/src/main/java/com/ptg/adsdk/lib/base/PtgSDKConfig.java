package com.ptg.adsdk.lib.base;

import com.ptg.adsdk.lib.utils.Logger;

public class PtgSDKConfig {

    private String policyUrl;
    private String appName;
    private String ptgAppId;
    private String ttAppId;
    private String gdtAppId;
    private boolean debug;
    private String[] providers;

    public String getPolicyUrl() {
        return policyUrl;
    }

    public void setPolicyUrl(String policyUrl) {
        this.policyUrl = policyUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPtgAppId() {
        return ptgAppId;
    }

    public void setPtgAppId(String ptgAppId) {
        this.ptgAppId = ptgAppId;
    }

    public String getTtAppId() {
        return ttAppId;
    }

    public void setTtAppId(String ttAppId) {
        this.ttAppId = ttAppId;
    }

    public String getGdtAppId() {
        return gdtAppId;
    }

    public void setGdtAppId(String gdtAppId) {
        this.gdtAppId = gdtAppId;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
        if(debug){
            Logger.oepnDebug();
        }

    }

    public String[] getProviders() {
        return providers;
    }

    public void setProviders(String... providers) {
        this.providers = providers;
    }



    public static class Builder {
        private String policyUrl;
        private String appName;
        private String ptgAppId;
        private String ttAppId;
        private String gdtAppId;
        private boolean debug;
        private String[] providers;

        public Builder setPolicyUrl(String policyUrl) {
            this.policyUrl = policyUrl;
            return this;
        }

        public Builder setAppName(String appName) {
            this.appName = appName;
            return this;
        }

        public Builder setPtgAppId(String ptgAppId) {
            this.ptgAppId = ptgAppId;
            return this;
        }

        public Builder setTtAppId(String ttAppId) {
            this.ttAppId = ttAppId;
            return this;
        }

        public Builder setGdtAppId(String gdtAppId) {
            this.gdtAppId = gdtAppId;
            return this;
        }

        public Builder setDebug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Builder setProviders(String... providers) {
            this.providers = providers;
            return this;
        }

        public PtgSDKConfig build() {
            PtgSDKConfig config = new PtgSDKConfig();
            config.setPolicyUrl(this.policyUrl);
            config.setAppName(this.appName);
            config.setPtgAppId(this.ptgAppId);
            config.setTtAppId(this.ttAppId);
            config.setGdtAppId(this.gdtAppId);
            config.setDebug(this.debug);
            config.setProviders(this.providers);
            return config;
        }
    }
}
