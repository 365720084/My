package com.ptg.adsdk.lib.base;

import java.util.HashMap;
import java.util.Map;

public abstract class PtgSecAbs {
    public PtgSecAbs() {
    }

    public String NM_getAppKey() {
        return "df979cdb-05a7-448c-bece-92d5005a1247";
    }

    public String NM_getDeviceId() {
        return  null;
//        return j.getAdManager(EnviromentHelper.getAdManager());
    }

    public String NM_getInstallId() {
        return null;
    }

    public Map<String, Object> NM_getCustomInfo() {
//        if (h.checkDebugOpen().oepnDebug() != null) {
//            HashMap var1 = new HashMap();
//            var1.put("app_id", h.checkDebugOpen().oepnDebug());
//            return var1;
//        } else {
//            return null;
//        }
        return null;
    }

    public abstract String NM_pullSg();

    public abstract String NM_pullVer(String var1);

    public abstract void NM_setParams(String var1);

    public abstract void NM_reportNow(String var1);
}
