package com.fancy.fancyapi.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fancy.adsdk.lib.interf.FancyAppDownloadListener;
import com.fancy.fancyapi.utils.LayoutUtil;
import com.fancy.fancyapi.webview.FancyWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicBoolean;

public class FancyLandingPageActivity extends Activity {
    private static final String b = FancyLandingPageActivity.class.getSimpleName();
    private FancyWebView fancyWebView;
    private ImageView backIcon;
    private ImageView closeIcon;
    private TextView titleDefault;
    private Context context;
    private int sdkVersion;
    private ViewStub titleBar;
    private ViewStub titleBarBlack;
    private ViewStub downLoadStub;
    private Button downLoadButton;
    private ProgressBar progressBar;
    private String adid;
    private String log_extra;
    private int source;
    private String event_tag;
    private String url;
    private AtomicBoolean u = new AtomicBoolean(true);
    private JSONArray jsonArray = null;
    private String downLoadText = "立即下载";
    private FancyAppDownloadListener z = new FancyAppDownloadListener() {
        public void onIdle() {
            FancyLandingPageActivity.this.updateDownLoadText(FancyLandingPageActivity.this.b());
        }

        public void onDownloadActive(long var1, long var3, String var5, String var6) {
            FancyLandingPageActivity.this.updateDownLoadText("下载中...");
        }

        public void onDownloadPaused(long var1, long var3, String var5, String var6) {
            FancyLandingPageActivity.this.updateDownLoadText("暂停");
        }

        public void onDownloadFailed(long var1, long var3, String var5, String var6) {
            FancyLandingPageActivity.this.updateDownLoadText("下载失败");
        }

        public void onDownloadFinished(long var1, String var3, String var4) {
            FancyLandingPageActivity.this.updateDownLoadText("点击安装");
        }

        public void onInstalled(String var1, String var2) {
            FancyLandingPageActivity.this.updateDownLoadText("点击打开");
        }
    };

    public FancyLandingPageActivity() {
    }

    protected void onCreate(@Nullable Bundle var1) {
        super.onCreate(var1);
        this.setContentView(LayoutUtil.f(this, "fancy_activity_landingpage"));
        this.initView();
        this.context = this;
        Intent intent = this.getIntent();
        this.sdkVersion = intent.getIntExtra("sdk_version", 1);
        this.adid = intent.getStringExtra("adid");
        this.log_extra = intent.getStringExtra("log_extra");
        this.source = intent.getIntExtra("source", -1);
        String var3 = intent.getStringExtra("url");
        this.url = var3;
        String var4 = intent.getStringExtra("web_title");
        String var5 = intent.getStringExtra("icon_url");
        this.event_tag = intent.getStringExtra("event_tag");
        JSONObject var11 = new JSONObject();

        try {
            var11.put("adid", this.adid);
            var11.put("url", var3);
            var11.put("web_title", var4);
//            var11.put("is_multi_process", com.bytedance.sdk.openadsdk.multipro.oepnDebug.oepnDebug());
            var11.put("event_tag", this.event_tag);
        } catch (JSONException var8) {
        }

//        this.updateDownLoadText.updateDownLoadText(var11);
//        this.w();
        this.fancyWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                try {
                    if (FancyLandingPageActivity.this.progressBar != null && !FancyLandingPageActivity.this.isFinishing()) {
                        FancyLandingPageActivity.this.progressBar.setVisibility(View.GONE);
                    }
                } catch (Throwable var4) {
                }
            }
        });
//        this.fancyWebView.getSettings().setUserAgentString(com.bytedance.sdk.openadsdk.utils.q.updateDownLoadText(this.fancyWebView, this.sdkVersion));
        if (Build.VERSION.SDK_INT >= 21) {
            this.fancyWebView.getSettings().setMixedContentMode(0);
        }

        this.fancyWebView.loadUrl(var3);
        this.fancyWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (FancyLandingPageActivity.this.progressBar != null && !FancyLandingPageActivity.this.isFinishing()) {
                    if (newProgress == 100 && FancyLandingPageActivity.this.progressBar.isShown()) {
                        FancyLandingPageActivity.this.progressBar.setVisibility(View.GONE);
                    } else {
                        FancyLandingPageActivity.this.progressBar.setProgress(newProgress);
                    }
                }
            }
        });
        this.fancyWebView.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

            }

        });
//        if (this.titleDefault != null) {
//            this.titleDefault.setText(TextUtils.isEmpty(var4) ? LayoutUtil.a(this, "fancy_web_title_default") : var4);
//        }

        this.initDownLoad();
    }

    private void initDownLoad() {
//        if (this.s != null && this.s.B() == 4) {
//            this.downLoadStub.setVisibility(0);
//            this.downLoadButton = (Button)this.findViewById(LayoutUtil.oepnDebug(this, "fancy_browser_download_btn"));
//            if (this.downLoadButton != null) {
//                this.updateDownLoadText(this.oepnDebug());
//                if (this.w == null) {
//                    String var1 = TextUtils.isEmpty(this.event_tag) ? ah.updateDownLoadText(this.source) : this.event_tag;
//                    this.w = com.bytedance.sdk.openadsdk.downloadnew.updateDownLoadText.updateDownLoadText(this, this.s, var1);
//                    this.w.updateDownLoadText(this.z, false);
//                }
//
//                this.w.updateDownLoadText(this);
//                com.bytedance.sdk.openadsdk.core.updateDownLoadText.updateDownLoadText var2 = new com.bytedance.sdk.openadsdk.core.updateDownLoadText.updateDownLoadText(this, this.s, this.event_tag, this.source);
//                var2.updateDownLoadText(false);
//                this.downLoadButton.setAdClickListener(var2);
//                this.downLoadButton.setOnTouchListener(var2);
//                var2.updateDownLoadText(this.w);
//            }
//        }

    }

    private String b() {
//        if (this.s != null && !TextUtils.isEmpty(this.s.L())) {
//            this.downLoadText = this.s.L();
//        }

        return this.downLoadText;
    }

    private void updateDownLoadText(final String var1) {
        if (!TextUtils.isEmpty(var1)) {
            if (this.downLoadButton != null) {
                this.downLoadButton.post(new Runnable() {
                    public void run() {
                        if (FancyLandingPageActivity.this.downLoadButton != null && !FancyLandingPageActivity.this.isFinishing()) {
                            FancyLandingPageActivity.this.downLoadButton.setText(var1);
                        }

                    }
                });
            }

        }
    }

    public void onConfigurationChanged(Configuration var1) {
        super.onConfigurationChanged(var1);
//        this.v();
    }

    private void initView() {
        this.fancyWebView = (FancyWebView) this.findViewById(LayoutUtil.e(this, "fancy_browser_webview"));
        this.downLoadStub = (ViewStub) this.findViewById(LayoutUtil.e(this, "fancy_browser_download_btn_stub"));
        this.titleBar = (ViewStub) this.findViewById(LayoutUtil.e(this, "fancy_browser_titlebar_view_stub"));
        this.titleBarBlack = (ViewStub) this.findViewById(LayoutUtil.e(this, "fancy_browser_titlebar_dark_view_stub"));
//        switch(com.bytedance.sdk.openadsdk.core.h.checkDebugOpen().j()) {
//            case -1:
//            default:
//                break;
//            case 0:
//                this.titleBar.setVisibility(0);
//                break;
//            case 1:
//                this.titleBarBlack.setVisibility(0);
//        }
        this.titleBar.setVisibility(View.VISIBLE);

        this.backIcon = (ImageView) this.findViewById(LayoutUtil.e(this, "fancy_titlebar_back"));
        if (this.backIcon != null) {
            this.backIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View var1) {
                    if (FancyLandingPageActivity.this.fancyWebView != null) {
                        if (FancyLandingPageActivity.this.fancyWebView.canGoBack()) {
                            FancyLandingPageActivity.this.fancyWebView.goBack();
                        } else {
                            FancyLandingPageActivity.this.finish();
                        }
                    }

                }
            });
        }

        this.closeIcon = (ImageView) this.findViewById(LayoutUtil.e(this, "fancy_titlebar_close"));
        if (this.closeIcon != null) {
            this.closeIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View var1) {
                    FancyLandingPageActivity.this.finish();
                }
            });
        }

        this.titleDefault = (TextView) this.findViewById(LayoutUtil.e(this, "fancy_titlebar_title"));
        this.progressBar = (ProgressBar) this.findViewById(LayoutUtil.e(this, "fancy_browser_progress"));
    }


    protected void onResume() {
        super.onResume();

    }

    protected void onStop() {
        super.onStop();

    }

    protected void onPause() {
        super.onPause();

    }


    private JSONArray b(String var1) {
        if (this.jsonArray != null && this.jsonArray.length() > 0) {
            return this.jsonArray;
        } else if (TextUtils.isEmpty(var1)) {
            return null;
        } else {
            int var2 = var1.indexOf("?id=");
            int var3 = var1.indexOf("&");
            if (var2 != -1 && var3 != -1 && var2 + 4 < var3) {
                String var4 = var1.substring(var2 + 4, var3);
                if (TextUtils.isEmpty(var4)) {
                    return null;
                } else {
                    JSONArray var5 = new JSONArray();
                    var5.put(var4);
                    return var5;
                }
            } else {
                return null;
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();

        try {
            if (this.getWindow() != null) {
                ViewGroup var1 = (ViewGroup) this.getWindow().getDecorView();
                if (var1 != null) {
                    var1.removeAllViews();
                }
            }
        } catch (Throwable var3) {
        }


    }

}
