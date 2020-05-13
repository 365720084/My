package com.fancy.fancyapi.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.reflect.Method;
import java.util.Map;

public class FancyWebView extends WebView {
    public FancyWebView(Context context) {
        super(context);
        this.init(context);
    }

    public FancyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);

    }

    public FancyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context);

    }


    private void init(Context var1) {
        b(var1);
        this.getSettings().setSavePassword(false);
        this.a();
    }

    public void setNetworkAvailable(boolean var1) {
        try {
            super.setNetworkAvailable(var1);
        } catch (Exception var3) {
        }

    }

    @TargetApi(19)
    public void loadUrl(String var1, Map<String, String> var2) {
        try {
            super.loadUrl(var1, var2);
        } catch (Exception var4) {
        }

    }

    public void loadUrl(String var1) {
        try {
            if (!TextUtils.isEmpty(var1)) {
                if (var1.startsWith("file://")) {
                    this.getSettings().setJavaScriptEnabled(false);
                } else {
                    this.getSettings().setJavaScriptEnabled(true);
                }
            }

            super.loadUrl(var1);
        } catch (Exception var3) {
        }

    }

    public void postUrl(String var1, byte[] var2) {
        try {
            super.postUrl(var1, var2);
        } catch (Exception var4) {
        }

    }

    public void loadData(String var1, String var2, String var3) {
        try {
            super.loadData(var1, var2, var3);
        } catch (Exception var5) {
        }

    }

    public void loadDataWithBaseURL(String var1, String var2, String var3, String var4, String var5) {
        try {
            super.loadDataWithBaseURL(var1, var2, var3, var4, var5);
        } catch (Exception var7) {
        }

    }

    public void stopLoading() {
        try {
            super.stopLoading();
        } catch (Exception var2) {
        }

    }

    public void reload() {
        try {
            super.reload();
        } catch (Exception var2) {
        }

    }

    public boolean canGoBack() {
        try {
            return super.canGoBack();
        } catch (Exception var2) {
            return false;
        }
    }

    public void goBack() {
        try {
            super.goBack();
        } catch (Exception var2) {
        }

    }

    public boolean canGoForward() {
        try {
            return super.canGoForward();
        } catch (Exception var2) {
            return false;
        }
    }

    public void goForward() {
        try {
            super.goForward();
        } catch (Exception var2) {
        }

    }

    public boolean canGoBackOrForward(int var1) {
        try {
            return super.canGoBackOrForward(var1);
        } catch (Exception var3) {
            return false;
        }
    }

    public void goBackOrForward(int var1) {
        try {
            super.goBackOrForward(var1);
        } catch (Exception var3) {
        }

    }

    public String getUrl() {
        try {
            return super.getUrl();
        } catch (Exception var2) {
            return null;
        }
    }

    public String getOriginalUrl() {
        try {
            String var1 = super.getOriginalUrl();
            if (var1 != null && var1.startsWith("data:text/html")) {
                String var2 = super.getUrl();
                if (var2 != null && var2.startsWith("file://")) {
                    var1 = var2;
                }
            }

            return var1;
        } catch (Exception var3) {
            return null;
        }
    }

    public int getProgress() {
        try {
            return super.getProgress();
        } catch (Exception var2) {
            return 100;
        }
    }

    public int getContentHeight() {
        try {
            return super.getContentHeight();
        } catch (Exception var2) {
            return 1;
        }
    }

    public void clearCache(boolean var1) {
        try {
            super.clearCache(var1);
        } catch (Exception var3) {
        }

    }

    public void clearFormData() {
        try {
            super.clearFormData();
        } catch (Exception var2) {
        }

    }

    public void clearHistory() {
        try {
            super.clearHistory();
        } catch (Exception var2) {
        }

    }

    public void setWebViewClient(WebViewClient var1) {
        try {
            super.setWebViewClient(var1);
        } catch (Exception var3) {
        }

    }

    public void setDownloadListener(DownloadListener var1) {
        try {
            super.setDownloadListener(var1);
        } catch (Exception var3) {
        }

    }

    public void setWebChromeClient(WebChromeClient var1) {
        try {
            super.setWebChromeClient(var1);
        } catch (Exception var3) {
        }

    }

    public void setBackgroundColor(int var1) {
        try {
            super.setBackgroundColor(var1);
        } catch (Exception var3) {
        }

    }

    public void computeScroll() {
        try {
            super.computeScroll();
        } catch (Exception var2) {
        }

    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent var1) {
        try {
            return super.onTouchEvent(var1);
        } catch (Throwable var3) {
            return false;
        }
    }

    public void setLayerType(int var1, Paint var2) {
        try {
            super.setLayerType(var1, var2);
        } catch (Throwable var4) {
        }

    }

    public void setOverScrollMode(int var1) {
        try {
            super.setOverScrollMode(var1);
        } catch (Exception var3) {
        }

    }

    private static void b(Context var0) {
        if (Build.VERSION.SDK_INT == 17 && var0 != null) {
            try {
                AccessibilityManager var1 = (AccessibilityManager)var0.getSystemService("accessibility");
                if (var1 == null || !var1.isEnabled()) {
                    return;
                }

                Method var2 = var1.getClass().getDeclaredMethod("setState", Integer.TYPE);
                var2.setAccessible(true);
                var2.invoke(var1, 0);
            } catch (Throwable var3) {
            }
        }

    }

    private void a() {
        try {
            this.removeJavascriptInterface("searchBoxJavaBridge_");
            this.removeJavascriptInterface("accessibility");
            this.removeJavascriptInterface("accessibilityTraversal");
        } catch (Throwable var2) {
        }

    }


}
