package com.ptg.adsdk.lib.core.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.location.Location;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.ptg.adsdk.lib.core.util.Connectivity;
import com.ptg.adsdk.lib.core.util.LocationHelper;
import com.ptg.adsdk.lib.core.util.MacHelper;
import com.ptg.adsdk.lib.uniquecode.AppIdsUpdater;
import com.ptg.adsdk.lib.uniquecode.OAIDHelper;
import com.ptg.adsdk.lib.utils.Logger;
import com.ptg.adsdk.lib.utils.SharedPreferencedUtil;

import org.json.JSONObject;

import java.util.Date;

public class DeviceInfo {
    private String udid;
    private String oaid;
    private String imei;
    private String identify_type = "imei";
    private String android_id;
    private String mac;
    private String vendor;
    private String model;
    private int os = 1;
    private String os_version;
    private int network;
    private int operator;
    private GpsObject gps;
    private int width;
    private int height;

    public String getOaid() {
        return oaid;
    }

    public String getImei() {
        return imei;
    }

    public String getUdid() {
        return udid;
    }

    public String getIdentify_type() {
        return identify_type;
    }

    public String getAndroid_id() {
        return android_id;
    }

    public String getMac() {
        return mac;
    }

    public String getVendor() {
        return vendor;
    }

    public String getModel() {
        return model;
    }

    public int getOs() {
        return os;
    }

    public String getOs_version() {
        return os_version;
    }

    public int getNetwork() {
        return network;
    }

    public int getOperator() {
        return operator;
    }

    public GpsObject getGps() {
        return gps;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setIdentify_type(String identify_type) {
        this.identify_type = identify_type;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setOs(int os) {
        this.os = os;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public void setNetwork(int network) {
        this.network = network;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public void setGps(GpsObject gps) {
        this.gps = gps;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static DeviceInfo generate(final Context context, final AppIdsUpdater appIdsUpdater) {
        final DeviceInfo info = new DeviceInfo();

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            info.imei = tm.getDeviceId();
        } catch (Exception ignored) {
        }
//        OAIDHelper helper = new OAIDHelper(new AppIdsUpdater() {
//            @Override
//            public void OnIdsAvalid(@NonNull final String ids) {
//                Logger.e("OnIdsAvalid:"+ids);
//                if(appIdsUpdater!=null){
//                    appIdsUpdater.OnIdsAvalid(ids);
//                }
//                info.oaid= ids;
//                SharedPreferencedUtil.putString(context,
//                        SharedPreferencedUtil.SP_NAME_CONFIG,
//                        SharedPreferencedUtil.SP_UNIQUEID, ids);
//            }
//        });
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            OAIDHelper.getOAid(context, new AppIdsUpdater() {
                @Override
                public void OnIdsAvalid(@NonNull String ids) {
                    Logger.e("OnIdsAvalid:"+ids);
                    if(appIdsUpdater!=null){
                        appIdsUpdater.OnIdsAvalid(ids);
                    }
                    info.oaid= ids;
                    SharedPreferencedUtil.putString(context,
                            SharedPreferencedUtil.SP_NAME_CONFIG,
                            SharedPreferencedUtil.SP_UNIQUEID, ids);
                }
            });
        }

//        helper.getOAid(context);
        info.android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

        try {
            info.mac = MacHelper.getMac(context);
        } catch (Exception ignored) {
        }

        info.vendor = Build.BRAND;
        info.model= Build.MODEL;
        info.os_version = Build.VERSION.RELEASE;

        try {
            info.network = Connectivity.getConnectionType(context);
        } catch (Exception ignored) {
        }

        try {
            info.operator = Connectivity.getProvidersName(context);
        } catch (Exception ignored) {
        }

        try {
            LocationHelper.getGps(context, new LocationHelper.LocationCallback() {
                @Override
                public void onSuccess(Location location) {
                    GpsObject object = new GpsObject();
                    object.setLat(location.getLatitude());
                    object.setLng(location.getLongitude());
                    object.setTimestamp(new Date().getTime() / 1000);
                    info.gps = object;
                }
            });
        } catch (Exception ignored) {
        }

        try {
            Point p = new Point();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                wm.getDefaultDisplay().getRealSize(p);
            } else {
                wm.getDefaultDisplay().getSize(p);
            }
            info.width = p.x;
            info.height = p.y;
        } catch (Exception ignored) {
        }

        return info;
    }


    public JSONObject getJson(Context context) {
        JSONObject object = new JSONObject();
        try {
            object.put("oaid",   SharedPreferencedUtil.getString(context,
                    SharedPreferencedUtil.SP_NAME_CONFIG,
                    SharedPreferencedUtil.SP_UNIQUEID));
            object.put("imei", imei);
            object.put("android_id", android_id);
            object.put("mac", mac);
            object.put("vendor", vendor);
            object.put("model", model);
            object.put("os", os);
            object.put("os_version", os_version);
            object.put("network", network);
            object.put("operator", operator);
            object.put("width", width);
            object.put("height", height);
            if (gps != null) {
                JSONObject gpsObj = new JSONObject();
                gpsObj.put("lat", gps.getLat());
                gpsObj.put("lng", gps.getLng());
                gpsObj.put("timestamp", gps.getTimestamp());
                object.put("gps", gpsObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
