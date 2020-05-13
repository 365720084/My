package com.fancy.fancyapi.constants;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AdConstant {

    public static String ADTYPE = "adtype";



    public static final int FANCY_AD = 1;
    public static final int TT_AD = 0;
    public static final int GDT_AD = 2;

    public static final int FANCY_SPLASH_AD_SCROLL = 0;
    public static final int FANCY_SPLASH_AD_CLIP = 1;

    //开屏动画效果类型
    public static final String SPLASH_AD_Gallery = "Gallery";
    public static final String SPLASH_AD_Sliding = "Sliding";
    public static final String SPLASH_AD_Cube = "Cube";
    public static final String SPLASH_AD_Swipe = "Swipe";

    public static final String SPLASH_AD_NORMAL = "normal";
    public static final String SPLASH_AD_VIDEO = "video";

    public static final String SPLASH_AD_API = "api";

    public static int NEEDAPI=1;
    public static int NEEDAPI_NO=0;




    public static final int INTERACTION_TYPE_BROWSER = 2;
    public static final int INTERACTION_TYPE_LANDING_PAGE = 3;
    public static final int INTERACTION_TYPE_DOWNLOAD = 4;
    public static final int INTERACTION_TYPE_DIAL = 5;
    public static final int INTERACTION_TYPE_UNKNOWN = -1;
    public static final int FALLBACK_TYPE_LANDING_PAGE = 1;
    public static final int FALLBACK_TYPE_DOWNLOAD = 2;
    public static final int IMAGE_MODE_LARGE_IMG = 3;
    public static final int IMAGE_MODE_SMALL_IMG = 2;
    public static final int IMAGE_MODE_GROUP_IMG = 4;
    public static final int IMAGE_MODE_VERTICAL_IMG = 16;
    public static final int IMAGE_MODE_VIDEO = 5;
    public static final int IMAGE_MODE_VIDEO_VERTICAL = 15;
    public static final int IMAGE_MODE_UNKNOWN = -1;
    public static final String TAG = "TT_AD_SDK";
    public static final int TITLE_BAR_THEME_LIGHT = 0;
    public static final int TITLE_BAR_THEME_DARK = 1;
    public static final int TITLE_BAR_THEME_NO_TITLE_BAR = -1;
    public static final int NETWORK_STATE_MOBILE = 1;
    public static final int NETWORK_STATE_2G = 2;
    public static final int NETWORK_STATE_3G = 3;
    public static final int NETWORK_STATE_WIFI = 4;
    public static final int NETWORK_STATE_4G = 5;
    public static final int VERTICAL = 1;
    public static final int HORIZONTAL = 2;
    public static final String MULTI_PROCESS_DATA = "multi_process_data";
    public static final String MULTI_PROCESS_MATERIALMETA = "multi_process_materialmeta";
    public static final int AD_TYPE_UNKNOWN = -1;
    public static final int AD_TYPE_COMMON_VIDEO = 0;
    public static final int AD_TYPE_PLAYABLE_VIDEO = 1;
    public static final int AD_TYPE_PLAYABLE = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface NATIVE_AD_TYPE {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ORIENTATION_STATE {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NETWORK_STATE {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TITLE_BAR_THEME {
    }

}