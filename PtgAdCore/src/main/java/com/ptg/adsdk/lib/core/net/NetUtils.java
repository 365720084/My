package com.ptg.adsdk.lib.core.net;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.ptg.adsdk.lib.core.CoreConstant;
import com.ptg.adsdk.lib.core.exception.NetAPIException;
import com.ptg.adsdk.lib.core.util.ContainerUtils;
import com.ptg.adsdk.lib.utils.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.NotActiveException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NetUtils {
    public static final String CRLF = "\r\n";
    private static final int API_DEFAULT_TIMEOUT = 15000;
    public static final int STATUS_FAILURE = 0;
    public static final int STATUS_OK = 1;
    public static final DumbHttpCallbackListener dumbListener = new DumbHttpCallbackListener();
    public static final Runnable jobReportRunner = new Runnable() {
        public void run() {
            while (true) {
                try {
                    NetUtils.simpleRequest(NetUtils.queueReport.take());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private static final Runnable jobRunner = new Runnable() {
        public void run() {
            while (true) {
                try {
                    NetUtils.callbackRequest(NetUtils.queue.take());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private static Thread mThread = null;
    private static Thread mThreadReport = null;
    private static BlockingQueue<HttpJobMsg> queue = new LinkedBlockingQueue<>();
    private static BlockingQueue<HttpJobMsg> queueReport = new LinkedBlockingQueue<>();

    static class DumbHttpCallbackListener implements HttpCallbackListener {
        DumbHttpCallbackListener() {
        }

        public boolean onPreRequest(HttpJobMsg msg) {
            return false;
        }

        public void onResult(HttpJobMsg msg, int status, String result) {
        }
    }

    public static void asyncFormRequest(String url, Map<String, Object> formData, HttpCallbackListener listener) {
        HttpJobMsg msg = new HttpJobMsg(url);
        msg.formData = formData;
        msg.method = HttpJobMsg.POST;
        msg.callbackListener = listener;
        asyncRequest(msg);
    }
    public static void asyncFormRequestGet(String url, HttpCallbackListener listener) {
//        url="http://g.test.amnetapi.com/s2s?mid=109&v=1.2.2&stype=8&size=1080*1600&si=17014&app_version=1.0.1&ip=180.169.129.212&pkg_name=com.dmzj.manhua&mimes=mp4&app_name=dmzj_andriod&ua=Mozilla%5c%2f5.0+(Linux%3b+Android+5.1.1%3b+SM-J3110+Build%5c%2fLMY47X%3b+wv)+AppleWebKit%5c%2f537.36+(KHTML%2c+like+Gecko)+Version%5c%2f4.0+Chrome%5c%2f55.0.2883.91+Mobile+Safari%5c%2f537.36i&device={%22width%22%3a1080%2c%22android_id%22%3a%229b30b83461f47403%22%2c%22model%22%3a%22mi-6%22%2c%22density%22%3a%222.0%22%2c%22udid%22%3a%22868930023056301%22%2c%22os%22%3a1%2c%22identify_type%22%3a%22imei%22%2c%22mac%22%3a%2288%3a6a%3ab1%3a97%3ad0%3af8%22%2c%22network%22%3a1%2c%22os_version%22%3a%225.1.1%22%2c%22operator%22%3a1%2c%22height%22%3a1920%2c%22vendor%22%3a%22xiaomi%22}&debug=1&magic_debug=1";
        HttpJobMsg msg = new HttpJobMsg(url);
        msg.method = HttpJobMsg.GET;
        msg.callbackListener = listener;
        asyncRequest(msg);
    }

    public static void asyncRequest(String url, String postData, HttpCallbackListener listener) {
        HttpJobMsg msg = new HttpJobMsg(url);
        if (!TextUtils.isEmpty(postData)) {
            msg.postData = postData;
            msg.method = HttpJobMsg.POST;
        }
        msg.callbackListener = listener;
        asyncRequest(msg);
    }

    public static void asyncRequest(String url, String postData) {
        asyncRequest(url, postData, dumbListener);
    }

    public static void asyncRequest(HttpJobMsg msg) {
        if (mThread == null) {
            synchronized (NetUtils.class) {
                Log.i("PtgAd", "perform thread start");
                mThread = new Thread(jobRunner);
                mThread.start();
            }
        }
        queue.offer(msg);
    }

    public static void asyncSimpleReport(String url, Map<String, String> extraHeader) {
        if (mThreadReport == null) {
            synchronized (NetUtils.class) {
                Log.i("PtgAd", "perform thread start");
                mThreadReport = new Thread(jobReportRunner);
                mThreadReport.start();
            }
        }
        HttpJobMsg msg = new HttpJobMsg(url);
        if (!ContainerUtils.isEmptyMap(extraHeader)) {
            msg.header = extraHeader;
        }
        queueReport.offer(msg);
    }

    public static void asyncSimpleReport(Map<String, String> extraHeader, String... urls) {
        if (urls != null && urls.length > 0) {
            for (String AsyncSimpleReport : urls) {
                asyncSimpleReport(AsyncSimpleReport, extraHeader);
            }
        }
    }

    public static void asyncSimpleReport(String... urls) {
        asyncSimpleReport(new HashMap<String, String>(), urls);
    }

    public static void asyncSimpleReport(List<String> urls) {
        asyncSimpleReport(new HashMap<String, String>(), urls);
    }

    public static void asyncSimpleReport(Map<String, String> extraHeader, List<String> urls) {
        if (urls != null && urls.size() > 0) {
            asyncSimpleReport(extraHeader, (String[]) urls.toArray(new String[urls.size()]));
        }
    }

    public static void simpleRequest(HttpJobMsg msg) throws IOException {
        if (msg != null && !TextUtils.isEmpty(msg.url)) {
            if (msg.url.startsWith("http://") || msg.url.startsWith("https://")) {
                Log.d("PtgAd", "SimpleRequest \"" + msg.url);
                URLConnection urlconn = new URL(msg.url).openConnection();
                if (!ContainerUtils.isEmptyMap(msg.header)) {
                    for (Entry<String, String> entry : msg.header.entrySet()) {
                        String key = entry.getKey();
                        String val = entry.getValue();
                        if (!(TextUtils.isEmpty(key) || TextUtils.isEmpty(val))) {
                            urlconn.setRequestProperty(key, val);
                        }
                    }
                }
                urlconn.connect();
                int rcode = ((HttpURLConnection) urlconn).getResponseCode();
                if (rcode != 200) {
                    Log.i("PtgAd", "ERROR " + rcode + " on url \"" + msg.url + "\"");
                }
            }
        }
    }

    public static void simpleRequest(HttpJobMsg... msgs) throws IOException {
        if (!ContainerUtils.isEmptyArray(msgs)) {
            for (HttpJobMsg simpleRequest : msgs) {
                simpleRequest(simpleRequest);
            }
        }
    }

    public static HttpURLConnection createStraightURLConnection(URL url, Map<String, String> header) throws IOException, IllegalFormatException {
        if (url == null) {
            return null;
        }
        URLConnection conn = url.openConnection();
        if (!ContainerUtils.isEmptyMap(header)) {
            for (Entry<String, String> entry : header.entrySet()) {
                conn.setRequestProperty(String.valueOf(entry.getKey()), entry.getValue());
            }
        }
        if (conn instanceof HttpURLConnection) {
            int response_code = ((HttpURLConnection) conn).getResponseCode();
            if (response_code >= 200 && response_code < 300) {
                return (HttpURLConnection) conn;
            }
            if (response_code == 301 || response_code == 302) {
                String newLoc = conn.getHeaderField("Location");
                if (!TextUtils.isEmpty(newLoc)) {
                    Log.i("StraightURLConnection", "Redirect ==> \"" + newLoc + "\"");
                    return createStraightURLConnection(new URL(newLoc), header);
                }
            }
            return null;
        }
        throw new IllegalArgumentException("Seems not getAdManager vaild HTTP url");
    }

    public static void callbackRequest(HttpJobMsg httpJobMsg) throws IOException {
        try {
            if (!httpJobMsg.callbackListener.onPreRequest(httpJobMsg)) {
                String result = request(httpJobMsg);
                if (TextUtils.isEmpty(result)) {
                    httpJobMsg.callbackListener.onResult(httpJobMsg, STATUS_FAILURE, null);
                } else {
                    httpJobMsg.callbackListener.onResult(httpJobMsg, STATUS_OK, result);
                }
            }
        } catch (IOException e) {
            String msg = String.valueOf(e.getMessage());
            if (CoreConstant.ILLEGAL_PATH.equals(msg) || CoreConstant.ILLEGAL_REQUEST.equals(msg)) {
                httpJobMsg.callbackListener.onResult(httpJobMsg, STATUS_FAILURE, msg);
                return;
            }
            throw e;
        } catch (NetAPIException e2) {
            httpJobMsg.callbackListener.onResult(httpJobMsg, STATUS_FAILURE, String.valueOf(e2.getMessage()));
        } catch (Exception e3) {
            httpJobMsg.callbackListener.onResult(httpJobMsg, STATUS_FAILURE, String.valueOf(e3.getMessage()));
        }
    }
    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";

    public static String request(HttpJobMsg httpJobMsg) throws IOException, NetAPIException {
        String encodedUrl = Uri.encode(httpJobMsg.url, ALLOWED_URI_CHARS);
        URL url = new URL(encodedUrl);
        Logger.d( "request :" + url);
        try {
            HttpURLConnection urlhandle = (HttpURLConnection) url.openConnection();
            for (Entry<String, String> entry : httpJobMsg.header.entrySet()) {
                urlhandle.setRequestProperty(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
            urlhandle.setRequestMethod(httpJobMsg.method);
            urlhandle.setDoInput(true);
            urlhandle.setConnectTimeout(API_DEFAULT_TIMEOUT);
            urlhandle.setReadTimeout(API_DEFAULT_TIMEOUT);
            if (HttpJobMsg.POST.equals(httpJobMsg.method)) {
                urlhandle.setDoOutput(true);
                if (!TextUtils.isEmpty(httpJobMsg.postData)) {
                    urlhandle.getOutputStream().write(httpJobMsg.postData.getBytes());
                    urlhandle.getOutputStream().flush();
                    urlhandle.getOutputStream().close();
                } else if (!ContainerUtils.isEmptyMap(httpJobMsg.formData)) {
                    String boundaryFlag = "BOUNDARY" + Long.toHexString(System.currentTimeMillis());
                    String boundary = "--" + boundaryFlag + CRLF;
                    urlhandle.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundaryFlag);
                    urlhandle.connect();
                    PrintWriter pw = new PrintWriter(urlhandle.getOutputStream());
                    pw.write(CRLF);
                    pw.write(boundary);
                    for (Entry<String, Object> entry : httpJobMsg.formData.entrySet()) {
                        String key = entry.getKey();
                        Object val = entry.getValue();
                        if (!TextUtils.isEmpty(key)) {
                            pw.write("Content-Disposition: form-data; name=\"" + key + "\"" + CRLF);
                            pw.write(CRLF);
                            pw.write(String.valueOf(val));
                            pw.write(CRLF);
                            pw.write(boundary);
                        }
                    }
                    pw.flush();
                }
            }
            int response_code = urlhandle.getResponseCode();
            switch (response_code) {
                case -1:
                    return null;
                case 200:
                    if ((httpJobMsg.msgFlags & 1) != 0) {
                        return "";
                    }
                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlhandle.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = reader.readLine();
                        if (line != null) {
                            sb.append(line);
                        } else {
                            reader.close();
                            return sb.toString();
                        }
                    }
                case 204:
                    throw new NotActiveException();
                case 400:
                    throw new IOException(CoreConstant.ILLEGAL_REQUEST);
                case 404:
                    throw new IOException(CoreConstant.ILLEGAL_PATH);
                default:
                    throw new NetAPIException("API_RESPONSE_" + response_code, urlhandle);
            }
        } catch (SocketTimeoutException e) {
            throw new NetAPIException("TIMEOUT_API_RESPONSE: " + e.getMessage());
        } catch (Exception e) {
            throw new NetAPIException("Unknown: " + e.getMessage());
        }
    }
}
