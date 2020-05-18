package com.ptg.adsdk.lib.base;

public abstract class PtgCustomController {
    public PtgCustomController() {
    }

    public boolean isCanUseLocation() {
        return true;
    }

    public PtgLocation getTTLocation() {
        return null;
    }

    public boolean alist() {
        return true;
    }

    public boolean isCanUsePhoneState() {
        return true;
    }

    public String getDevImei() {
        return null;
    }

    public boolean isCanUseWifiState() {
        return true;
    }

    public boolean isCanUseWriteExternal() {
        return true;
    }

    public String getDevOaid() {
        return null;
    }
}
