package com.ptg.ptgapi;

import android.content.Context;
import android.text.TextUtils;

import com.ptg.adsdk.lib.PtgAdSdk;
import com.ptg.adsdk.lib.core.SdkConfig;
import com.ptg.adsdk.lib.core.model.AdErrorImpl;
import com.ptg.adsdk.lib.core.net.HttpCallbackListener;
import com.ptg.adsdk.lib.core.net.HttpJobMsg;
import com.ptg.adsdk.lib.core.net.NetUtils;
import com.ptg.adsdk.lib.core.util.AppUtil;
import com.ptg.adsdk.lib.model.Ad;
import com.ptg.adsdk.lib.model.AdObject;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.model.AppInfo;
import com.ptg.adsdk.lib.model.Callback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InnerTextApi {
    public static final String API_DOMAIN = "http://g.ptgapi.com/s2s?";
    public static final String API_DOMAIN_TEST = "http://g.test.amnetapi.com/s2s?";
    public static final String URL_CHAPTER_KEYWORDS = API_DOMAIN_TEST + "/s2s?";
    //    private static final String URL_CHAPTER_KEYWORDS = "http://192.168.38.61:3000/getKeyword";
    public static final String URL_KEYWORDS_AD = API_DOMAIN + "/keyword?method=GetAd";

    //    private static final String URL_KEYWORDS_AD = "http://192.168.38.61:3000/getAd";
    private InnerTextApi() {
    }

    static InnerTextApi get() {
        return Holder.INSTANCE;
    }


    void getAd(Context context,int stype, AdSlot adSlot, final Callback<AdObject> callback) {
        String url=InnerTextApi.API_DOMAIN;
//        String url=InnerTextApi.API_DOMAIN_TEST;
        StringBuilder builder = new StringBuilder(url);
        builder.append("mid=")
                .append(SdkConfig.mid)
                .append("&ip=")
                .append(SdkConfig.ip)
                .append("&ua=")
                .append(SdkConfig.ua)
                .append("&device_type=")
                .append(SdkConfig.device_type)
                .append("&device=")
                .append(URLEncoder.encode(SdkConfig.getDevice(PtgAdSdk.getContext()).toString()))
                .append("&v=")
                .append(SdkConfig.v)
                .append("&si=")
                .append(adSlot.getCodeId())
                .append("&stype=")
                .append(stype)
                .append("&pkg_name=")
                .append(AppUtil.getPackageName(PtgAdSdk.getContext()))
                .append("&app_name=")
                .append(AppUtil.getAppName(PtgAdSdk.getContext()))
                .append("&app_version=")
                .append(AppUtil.getAppVersion(PtgAdSdk.getContext()))
                .append("&mimes=")
                .append(SdkConfig.mimes);

        NetUtils.asyncFormRequestGet( builder.toString(), new HttpCallbackListener() {
            @Override
            public boolean onPreRequest(HttpJobMsg httpJobMsg) {
                return false;
            }

            @Override
            public void onResult(HttpJobMsg httpJobMsg, int i, String str) {
                if (i == 0 || TextUtils.isEmpty(str)) {

                    callback.onError(AdErrorImpl.responseError(  str));
                    return;
                }
                AdObject adObject = new AdObject();
                try {
                    JSONObject object = new JSONObject(str);
                    adObject.setCode(object.optInt("code"));
                    adObject.setError_message(object.optString("error_message"));
                    adObject.setProcess_time(object.optLong("process_time"));
                    adObject.setReqid(object.optString("reqid"));
                    adObject.setReqid(object.optString("reqid"));
                    adObject.setVersion(object.optString("version"));
                    JSONArray array = object.optJSONArray("ad");
                    List<Ad> ads = new ArrayList<>();
                    if (array != null) {
                        for (int j = 0; j < array.length(); j++) {
                            Ad ad = new Ad();
                            JSONObject adJson = array.optJSONObject(j);
                            if (adJson != null) {
                                ad.setAction(adJson.optInt("action"));
                                ad.setAd_id(adJson.optString("ad_id"));
                                ad.setAid(adJson.optInt("aid"));
                                ad.setSrc(adJson.optString("src"));
                                ad.setTitle(adJson.optString("title"));
                                ad.setDesc(adJson.optString("desc"));
                                ad.setFeature_tag(adJson.optString("feature_tag"));
                                ad.setPrice(adJson.optInt("price"));
                                ad.setDuration(adJson.optInt("duration"));
                                ad.setStyle(adJson.optString("style"));
                                ad.setMime(adJson.optString("mime"));
                                ad.setWidth(adJson.optInt("width"));
                                ad.setHeight(adJson.optInt("height"));
                                ad.setDp_url(adJson.optString("dp_url"));
                                ad.setUrl(adJson.optString("url"));
                                ad.setLanding_url(adJson.optString("landing_url"));

                                JSONObject appJson = adJson.optJSONObject("app");
                                if (appJson != null) {
                                    AppInfo app = new AppInfo();
                                    app.setIcon_url(appJson.optString("icon_url"));
                                    app.setName(appJson.optString("name"));
                                    app.setPackage_name(appJson.optString("package_name"));
                                    ad.setApp(app);
                                }

                                JSONArray clickArray = adJson.optJSONArray("click_tracking");
                                List<String> clickTracking = new ArrayList<>();
                                if (clickArray != null) {
                                    for (int k = 0; k < clickArray.length(); k++) {
                                        clickTracking.add(clickArray.optString(k));
                                    }
                                }
                                ad.setClick_tracking(clickTracking);

                                //dp_clk
                                JSONArray dpArray = adJson.optJSONArray("dp_clk");
                                List<String> dpClk = new ArrayList<>();
                                if (dpArray != null) {
                                    for (int k = 0; k < dpArray.length(); k++) {
                                        dpClk.add(dpArray.optString(k));
                                    }
                                }
                                ad.setDp_clk(dpClk);

                                //clk
                                JSONArray clkArray = adJson.optJSONArray("clk");
                                List<String> clk = new ArrayList<>();
                                if (clkArray != null) {
                                    for (int k = 0; k < clkArray.length(); k++) {
                                        clk.add(clkArray.optString(k));
                                    }
                                }
                                ad.setClk(clk);

                                //ext_urls
                                JSONArray extArray = adJson.optJSONArray("ext_urls");
                                List<String> exts = new ArrayList<>();
                                if (extArray != null) {
                                    for (int k = 0; k < extArray.length(); k++) {
                                        exts.add(extArray.optString(k));
                                    }
                                }
                                ad.setExt_urls(exts);

                                JSONArray impArray = adJson.optJSONArray("imp_tracking");
                                List<String> imp = new ArrayList<>();
                                if (impArray != null) {
                                    for (int k = 0; k < impArray.length(); k++) {
                                        imp.add(impArray.optString(k));
                                    }
                                }
                                ad.setImp_tracking(imp);

                                JSONObject impJson = adJson.optJSONObject("imp");
                                if(impJson!=null){
                                    JSONArray impJson0 = impJson.optJSONArray("0");
                                    List<String> imps = new ArrayList<>();
                                    if (impJson0 != null) {
                                        for (int k = 0; k < impJson0.length(); k++) {
                                            imps.add(impJson0.optString(k));
                                        }
                                    }
                                    ad.setImp(imps);
                                }



                                JSONObject videoimpJson = adJson.optJSONObject("video_imp");
                                if(videoimpJson!=null){
                                    JSONArray videoimpJson0 = videoimpJson.optJSONArray("0");
                                    JSONArray videoimpJson1 = videoimpJson.optJSONArray("1");
                                    List<String> imps0 = new ArrayList<>();
                                    List<String> imps1 = new ArrayList<>();

                                    if (videoimpJson0 != null) {
                                        for (int k = 0; k < videoimpJson0.length(); k++) {
                                            imps0.add(videoimpJson0.optString(k));
                                        }
                                    }
                                    if (videoimpJson1 != null) {
                                        for (int k = 0; k < videoimpJson1.length(); k++) {
                                            imps1.add(videoimpJson1.optString(k));
                                        }
                                    }
                                    Map<String, List<String>> video_imp=new HashMap<>();
                                    video_imp.put("0",imps0);
                                    video_imp.put("1",imps1);
                                    ad.setVideo_imp(video_imp);
                                }



                            }

                            ads.add(ad);
                        }
                    }
                    adObject.setAd(ads);
                    if(adObject.getAd().size()>0){
                        callback.onSuccess(adObject);
                        trackImp(adObject);
                    }else {
                        callback.onError(AdErrorImpl.noADError());
                    }
                } catch (Exception e) {
                    callback.onError(AdErrorImpl.responseError( e.getMessage()));
                }
            }
        });

    }

    private void trackImp(AdObject adObject) {
        List<String> urls = new ArrayList<>();
        if (adObject.getAd() == null) return;
        for (Ad ad : adObject.getAd()) {
            if (ad.getImp() == null || ad.getImp().size() == 0) continue;
            urls.addAll(ad.getImp());
        }
        NetUtils.asyncSimpleReport(urls);
    }


    private static class Holder {
        private static final InnerTextApi INSTANCE = new InnerTextApi();
    }
}
