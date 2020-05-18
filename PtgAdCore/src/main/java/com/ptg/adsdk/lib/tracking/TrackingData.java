package com.ptg.adsdk.lib.tracking;

import android.text.TextUtils;

import com.ptg.adsdk.lib.core.model.AppVersion;
import com.ptg.adsdk.lib.core.model.DeviceInfo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class TrackingData {
    private String ptgSlotId;
    private String codeId;

    private DeviceInfo deviceInfo;
    private AppVersion appVersion;

    private int errorCode;
    private String requestId;
    private String IMEI;
    private String md5IMEI;
    private String MAC;
    private String md5MAC;
    private String androidId;
    private String oaid;
    private String appPackageName;


    private long  adKey = 0;
    private boolean isFirstImp = true;

    public TrackingData(String ptgSlotId, String codeId) {
        this.ptgSlotId = ptgSlotId;
        this.codeId = codeId;
        this.requestId = genRequestId(ptgSlotId, codeId, this.getUnixTimestamp());
    }

    public String getPtgSlotId() {
        return ptgSlotId;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }


    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public void setAppVersion(AppVersion appVersion) {
        this.appVersion = appVersion;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
        this.md5IMEI = getMD5Encryption(IMEI);
    }

    public void setMd5IMEI(String md5IMEI) {
        this.md5IMEI = md5IMEI;
    }

    public String getMd5IMEI() {
        if (!TextUtils.isEmpty(this.md5IMEI)) {
            return this.md5IMEI;
        }

        if (deviceInfo != null &&  deviceInfo.getUdid()!=null&&deviceInfo.getUdid().length() != 0) {
            return getMD5Encryption(deviceInfo.getUdid());
        }

        return this.md5IMEI;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
        this.md5MAC = getMD5Encryption(MAC);
    }

    public void setMd5MAC(String md5MAC) {
        this.md5MAC = md5MAC;
    }

    public String getMd5MAC() {
        if (!TextUtils.isEmpty(this.md5MAC)) {
            return this.md5MAC;
        }

        if (deviceInfo != null && !TextUtils.isEmpty(deviceInfo.getMac())) {
            return getMD5Encryption(deviceInfo.getMac());
        }
        return this.md5MAC;
    }

    public String getAndroidId() {
        if (!TextUtils.isEmpty(this.androidId)) {
            return this.androidId;
        }

        if (deviceInfo != null && !TextUtils.isEmpty(deviceInfo.getAndroid_id())) {
            return deviceInfo.getAndroid_id();
        }
        return this.androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getAppPackageName() {
        if (!TextUtils.isEmpty(this.appPackageName)) {
            return this.appPackageName;
        }

        if (appVersion != null && !TextUtils.isEmpty(appVersion.getPkgName())) {
            return appVersion.getPkgName();
        }
        return this.appPackageName;
    }

    public void setAppPackageName(String appPackageName) {
        this.appPackageName = appPackageName;
    }

    public String getOaid() {
        if (!TextUtils.isEmpty(this.oaid)) {
            return this.oaid;
        }

        if (deviceInfo != null && !TextUtils.isEmpty(deviceInfo.getOaid())) {
            return deviceInfo.getOaid();
        }
        return this.oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public long getAdKey() {
        return adKey;
    }

    public void setAdKey(long adKey) {
        this.adKey = adKey;
    }


    public boolean isFirstImp() {
        return isFirstImp;
    }

    public void setFirstImp(boolean firstImp) {
        isFirstImp = firstImp;
    }

    public long getUnixTimestamp() {
        Long tsLong = System.currentTimeMillis();
        return tsLong.longValue();
    }

    public static String genRequestId(String ptgSlotId, String codeId, long timestamp) {
        Random ran = new Random();
        return String.format("%s-%s-%d-%d", ptgSlotId, codeId, timestamp, ran.nextInt(100));
    }


    public static String getMD5Encryption(String originString) {
        String result = "";
        if (originString != null) {
            try {
                // 指定加密的方式为MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                // 进行加密运算
                byte bytes[] = md.digest(originString.getBytes());
                StringBuilder sb = new StringBuilder(40);
                for (byte b : bytes) {
                    if ((b & 0xff) >> 4 == 0) {
                        sb.append("0").append(Integer.toHexString(b & 0xff));
                    } else {
                        sb.append(Integer.toHexString(b & 0xff));
                    }
                }
                result = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (Exception e) {

            }
        }
        return result;
    }
}
