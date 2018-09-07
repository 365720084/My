package com.fancy.showmedata.network;


import android.util.Log;

import com.fancy.showmedata.BuildConfig;
import com.fancy.showmedata.application.App;
import com.fancy.showmedata.utils.OS;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * QQ:1981367757
 */

public class NetManager {

    private static final String CACHE_CONTROL = "Cache_Control";
    private static final String TAG = NetManager.class.getName();
    private static NetManager instance;
    private static Interceptor cacheInterceptor;
    private static OkHttpClient client;
    private static OkHttpClient clientWithParam;
    private int outOfNetCacheTime = 60 * 60 * 24 * 7;
    private static long maxCache = 1024 * 1024 * 10;
    private int netWorkCacheTime = 60;
    private static final int TIME_OUT = 10;
//    private static File cacheFileDir = new File(App.getAppContext()().getCacheDir(), "tx_cache");
//    private static Cache sCache = new Cache(cacheFileDir, maxCache);


    private static Interceptor sLoggerInterceptor;

    public static NetManager getInstance() {
        if (instance == null) {
            instance = new NetManager();
        }
        return instance;
    }


    private NetManager() {
        if (cacheInterceptor == null) {
            cacheInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    if (OS.isNetworkConnected(App.getAppContext())) {
                        return response.newBuilder().removeHeader("program").removeHeader(CACHE_CONTROL)
                                .addHeader(CACHE_CONTROL, "public, max-age=" + netWorkCacheTime).build();
                    } else {
                        return response.newBuilder().removeHeader("program").removeHeader(CACHE_CONTROL)
                                .addHeader(CACHE_CONTROL, "public, only-if-cached, max-stale=" + outOfNetCacheTime).build();
                    }
                }
            };
        }
        if (sLoggerInterceptor == null) {
            sLoggerInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    if (request.url() != null) {
                        Log.e(TAG, "请求的url" + request.url().toString()
                        );
                    } else {
                        Log.e(TAG, "请求的url为空");
                    }
                    RequestBody requestBody = request.body();
                    Buffer buffer = new Buffer();
                    if (requestBody != null) {
                        requestBody.writeTo(buffer);
                        Log.e(TAG, parseContent(requestBody, buffer));
                    } else {
                        Log.e(TAG, "request_body is null");
                    }
                    return chain.proceed(request);
                }
            };
        }
        if (client == null) {
            initClient();
        }
    }

    private String parseContent(RequestBody requestBody, Buffer buffer) {
        try {
            if (requestBody.contentType() != null && requestBody.contentType().toString().equals("multipart")) {
                return URLDecoder.decode(buffer.readUtf8(), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            CrashReport.postCatchedException(e);
//            Log.printStackTrace(e);
        }
        return "";
    }


    public OkHttpClient getClient() {
        if (client != null) {
            return client;
        }
        initClient();
        return client;
    }

    private void initClient() {


        try {
            // 自定义一个信任所有证书的TrustManager，添加SSLSocketFactory的时候要用到
            final X509TrustManager trustAllCert =
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    };
            final SSLSocketFactory sslSocketFactory = new SSLSocketFactoryCompat(trustAllCert);
            OkHttpClient.Builder builder =  new OkHttpClient.Builder()
                    .connectionPool(SharedOkHttpConnectPool.getInst())
                    .addInterceptor(new BaseInterceptor())
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false)
                    .sslSocketFactory(sslSocketFactory, trustAllCert);
//                .cache(sCache)
            clientWithParam = builder.build();
        } catch (Exception e) {
            CrashReport.postCatchedException(e);
            throw new RuntimeException(e);
        }


    }


    public <T> T getApi(String baseUrl, Class<T> api) {
        if (baseUrl == null) {
            return null;
        }
        if (stringRetrofitMap.get(api.toString() + baseUrl) == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MyConvert.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(clientWithParam).build();
            stringRetrofitMap.put(api.toString() + baseUrl, retrofit);

        }
        return stringRetrofitMap.get(api.toString() + baseUrl).create(api);
    }



    public <T> T getApi(Class<T> api) {
        String baseUrl =EnviromentManager.getInstance().getCurrentUrl();
        if (stringRetrofitMap.get(api.toString() + baseUrl) == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MyConvert.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(clientWithParam).build();
            stringRetrofitMap.put(api.toString() + baseUrl, retrofit);

        }
        return stringRetrofitMap.get(api.toString() + baseUrl).create(api);
    }


    public void removeApi(Class api, String baserUrl) {
        if (stringRetrofitMap.containsKey(api.toString() + baserUrl)) {
            stringRetrofitMap.remove(api.toString() + baserUrl);
        }
    }

    private static Map<String, Retrofit> stringRetrofitMap = new HashMap<>();

}
