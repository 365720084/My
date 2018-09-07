# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/Smile/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#保护匿名、异常、范型不被混淆
-keepattributes Exceptions,InnerClasses, SourceFile,Signature,LineNumberTable

-keep class * implements android.os.Parcelable{*;}
#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
#eventbus
-keep class de.greenrobot.event.**{*;}
-keepclassmembers class ** {
    public void onEvent*(**);
}
-keepclassmembers class ** {
public void onEventMainThread(com.chezhu.events.BaseEvent);
}
#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
-keep class com.squareup.** {*;}
-keep class okhttp3.** {*;}
-keep class okio.** {*;}
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn com.squareup.picasso.**
-dontwarn com.google.gson.jpush.**
-dontwarn cn.jpush.android.**
-keep class cn.jpush.** {*;}
#rxjava
-keep class rx.** {*;}
-dontwarn rx.**
#-keep class com.tencent.mm.sdk.** {*;}
-keep class com.kubik.news.widgets.smarttablayout.**{*;}
-keep class com.kubik.news.event.**{*;}
-keep class com.kubik.news.model.**{*;}
#广告
-keep class java.lang.Enum {
    <fields>;
    <methods>;
}
-keepclassmembers enum * {
    <fields>;
    <methods>;
}
-keep class com.adeaz.** {*;}
-dontwarn com.adeaz.**
-keep class com.baidu.** {   public protected *; }
-keep class com.qq.e.** {     public protected *; }
-keep class android.support.v4.app.NotificationCompat**{    public *;}
-keepclassmembers class * extends android.webkit.WebChromeClient{
   	public void openFileChooser(...);
}
#小米推送
-keep class com.kubik.news.receiver.MiPushReceiver {*;}
-dontwarn com.xiaomi.push.**
-keep class com.xiaomi.push.**

#talking data
-dontwarn com.tendcloud.tenddata.**
-keep class com.tendcloud.** {*;}
-keep public class com.tendcloud.tenddata.** { public protected *;}
-keepclassmembers class com.tendcloud.tenddata.**{
    public void *(***);
}
-keep class com.talkingdata.sdk.TalkingDataSDK {public *;}
-keep class com.apptalkingdata.** {*;}
#宾谷广告
-keep public class com.pingcoo.android.ny.api.**{*;}
#v5客服
-keep class **.R$* { <fields>; }
-keep class com.sina.** {*;}
# ProGuard configurations for NetworkBench Lens
-keep class com.networkbench.** { *; }
-dontwarn com.networkbench.**
-keepattributes Exceptions, Signature, InnerClasses
# End NetworkBench Lens
#支付宝支付
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
-dontwarn android.net.**
-keep class android.net.SSLCertificateSocketFactory{*;}

#umeng
-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**

-keepattributes *Annotation*

-keep class com.taobao.** {*;}
-keep class org.android.** {*;}
-keep class anet.channel.** {*;}
-keep class com.umeng.** {*;}
-keep class com.xiaomi.** {*;}
-keep class com.huawei.** {*;}
-keep class org.apache.thrift.** {*;}

-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}

-keep public class **.R$*{
   public static final int *;
}

#getui
-dontwarn com.igexin.**
-keep class com.igexin.** { *; }
-keep class org.json.** { *; }

#高德定位
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}
-dontwarn com.amap.**

#hotline
-dontwarn com.freshdesk.hotline.**
-dontwarn uk.co.chrishenx.calligraphy.**

#反作弊sdk
-keep class com.inno.innosdk.pb.** { *; }
-keep class com.inno.innosdk.bean.DeviceInfo { *; }

# 小米推送
-keep class com.kubik.news.receiver.MiPushReceiver {*;}
#可以防止一个误报的 warning 导致无法成功编译，如果编译使用的 Android 版本是 23。
-dontwarn com.xiaomi.push.**


-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-dontwarn com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
-dontwarn com.bumptech.glide.load.resource.bitmap.Downsampler
-dontwarn com.bumptech.glide.load.resource.bitmap.HardwareConfigState
-dontwarn com.bumptech.glide.manager.RequestManagerRetriever

# 反作弊相关混淆
-keep class cn.shuzilm.core.Main {
    public *;
}
-keep class cn.shuzilm.core.Listener {
    public *;
}
-keepclasseswithmembernames class cn.shuzilm.core.Main {
    native <methods>;
}

#growingio
#-keep class com.growingio.android.sdk.** {
#    *;
#}
#-dontwarn com.growingio.android.sdk.**
-keepnames class * extends android.view.View
-keep class * extends android.app.Fragment {
    public void setUserVisibleHint(boolean);
    public void onHiddenChanged(boolean);
    public void onResume();
    public void onPause();
}
-keep class android.support.v4.app.Fragment {
    public void setUserVisibleHint(boolean);
    public void onHiddenChanged(boolean);
    public void onResume();
    public void onPause();
}
-keep class * extends android.support.v4.app.Fragment {
    public void setUserVisibleHint(boolean);
    public void onHiddenChanged(boolean);
    public void onResume();
    public void onPause();
}

#Fresco
# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip
-keep,allowobfuscation @interface com.facebook.soloader.DoNotOptimize

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

# Do not strip any method/class that is annotated with @DoNotOptimize
-keep @com.facebook.soloader.DoNotOptimize class *
-keepclassmembers class * {
    @com.facebook.soloader.DoNotOptimize *;
}

# Keep native methods
-keepclassmembers class * {
    native <methods>;
}

-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**
-dontwarn com.facebook.infer.**
-keep class com.tencent.** { *; }
# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
-keep class com.tencent.** { *; }
-keep class com.google.** { *; }

-dontwarn org.**
-dontwarn com.tencent.**
-dontwarn com.google.**
-keep class org.**{*;}
-dontwarn android.net.http**
-keep class android.net.http.**{*;}

-dontwarn com.android.installreferrer
-dontwarn com.appsflyer.**

-keep class com.kubik.news.modules.shortvideotab.bean.**{ *; }
-keep class com.kubik.news.modules.detail.shortvideodetail.bean.**{ *; }
-keep class com.kubik.news.modules.detail.newshortvideodetail.bean.**{ *; }
-keep class com.kubik.news.network.**{ *; }
-keep class com.kubik.news.modules.personalcenter.kstore.bean.**{ *; }
-keep class com.kubik.news.receiver.**{ *; }

#arouter混淆
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

#UM混淆  http://dev.umeng.com/analytics/android-doc/integration
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-dontwarn com.alibaba.android.arouter.facade.model.**
-keep class com.inno.innosecure.** { *; }