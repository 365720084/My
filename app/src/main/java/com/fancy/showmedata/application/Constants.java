package com.fancy.showmedata.application;

import android.os.Environment;

import com.fancy.showmedata.BuildConfig;


/**
 * 常量类
 * <p/>
 * Smile<lijianhy1990@gmail.com>
 * 2015年4月25日
 */
public class Constants {

    public static final String APP_NAME = "qk";
    public static final String TEXT_SIGN = "3082032f30820217a003020102020447d432a2300d06092a864886f70d01010b05003048310a30080603550406130131310a30080603550408130131310a30080603550407130131310a3008060355040a130131310a3008060355040b130131310a30080603550403130131301e170d3138303532363037343630325a170d3433303532303037343630325a3048310a30080603550406130131310a30080603550408130131310a30080603550407130131310a3008060355040a130131310a3008060355040b130131310a3008060355040313013130820122300d06092a864886f70d01010105000382010f003082010a02820101009602f14afa1abe613b53191db27b7367993f22c69eaab9a742ea3f53f45dc81bd4ad74340d9338db7407dc06ccee5ee697008e434e9513b471fb0a6c2947d90a1048d3fb423354d55b0992376cb1241e0feed729629f476aa05506c71f836f3b01ccd83661f6fb0f11e8bfd2d4a268405fa7a0c581193779b2df0d5783a1c0d6d9af0788e67a92cc694290832f11a969d28f7b0afdb3facc4ae1cdf3004221e68f3420cce9ac24385403d04e2333867811c7515584a8071f6ff3c537d9aa2764dacdcb099972976afaabac488fa156d30808b04e4c641704aa6baa465773cb8d63b62ae998e9f8e81b5eed9927fa9899266b8599609dc43692563192687d67790203010001a321301f301d0603551d0e0416041447aea956b3fde98fc9ffc54448b476c88470fca3300d06092a864886f70d01010b050003820101002374248357483ce66fdf56c989740f6d796e2ec133bb08f68884ed2e734093daa78d4b0efcb4be4886d4d4bd9a26f036d245a6e4fcdb4241bc5f5dfc20f815d3b15b5e720edd8d2db84a280510a8d7824f3f308e9a5878a2f356afb100c8db9bcf79ee07394c2aa9a0566472018f31162427f2f4eeee5229f3657cf432c2823061a15e98a1ccab527729f305ebdb5a25059b7b5b1b7ca2bd30dd908c2555b71be67ae23f98567d01126668e2f5046dda1deae667648eebc8441c7621a0568cfc4b4f22832e8a1b21c77f4453e9a55ed269b2152253cee74b2c3b387d820de0e8350f29bd9d3570308110fea0debdeb23aecd66c20ad7f1ead583b43db667914a";
    public static final int APP_VERSION = BuildConfig.VERSION_CODE;

    // HOST
    public static final String HOST = BuildConfig.HOST;

    //URL
    public static final String URL_PROTOCOL = "";
    public static final String URL_INVITE = "";
    public static final String URL_MISSION = "";
    public static final String URL_HOW_TO_RICH = "";
    public static final String URL_REGISTER_INVITE_CODE = "";
    public static final String URL_ABOUT = "";
    public static final String URL_MALL = "";
    public static final String URL_FEEDBACK = "";
    public static final String URL_MESSAGE = "";
    public static final String URL_REPORT = "";
    public static final String URL_FSDEMO = "";
    public static final String URL_GAME = "";
    public static final String URL_SHOP = "";
    public static final String URL_WEMEDIA_TOP_LIST = "";
    public static final String URL_BIND_PHONE = "";
    public static final String URL_COVERT_HISTORY = "";
    public static final String URL_COVERT_DETAIL = "";
    public static final String URL_KSTORE_EXCHARGE = "";
    public static final String URL_TASK_LIST = "";

    public static final String SHARE_URL_EXT = "?id=%s";

    // REQUEST URL
    public static final String URL_START = "/app/start";
    public static final String URL_VCODE_URL = "/captcha/getImgCaptcha";
    public static final String URL_VCODE_URL_OTHER = "/captcha/getImgCaptcha2";
    public static final String URL_MEMBER_MODIFYTEL = "/member/modifyTel";
    public static final String URL_MEMBER_BINDTEL = "/member/bindTel";
    public static final String URL_USER_GET_VERIFY_CODE = "/captcha/getSmsCaptcha";
    public static final String URL_USER_TRY_IMG_CAPTCHA = "/captcha/tryImgCaptcha";
    public static final String URL_USER_GET_IMG_CAPTCHA = "/captcha/getImgCaptcha3";
    public static final String URL_USER_REGISTER = "/member/register";
    public static final String URL_USER_LOGIN = "/member/login";
    public static final String URL_USER_CHECK_VERIFY_CODE = "/captcha/checkSmsCaptcha";
    public static final String URL_USER_FIND_PWD = "/member/findPassword";
    public static final String URL_USER_MODIFY_PWD = "/member/modifyPassword";
    public static final String URL_USER_MODIFY = "/member/modify";
    public static final String URL_USER_LOGOUT = "/member/logout";
    public static final String URL_GET_RSS_TYPE_LIST = "/rss/getRssTypeList";
    public static final String URL_GET_RSS_LIST = "/rss/getRssList";
    public static final String URL_SET_RSS = "/rss/setRss";
    public static final String URL_GET_MY_RSS_LIST = "/rss/getMyRssList";
    public static final String URL_GET_DEFAULT_CHANNEL_LIST = "/content/getDefaultChannelList";
    public static final String URL_GET_USER_CHANNEL_LIST = "/content/getChannelList";
    public static final String URL_SET_USER_CHANNEL_LIST = "/content/setChannelList";
    public static final String URL_GET_CONTENT_LIST = "/content/getList";
    public static final String URL_ADD_COMMENT = "/comment/add";
    public static final String URL_GET_COMMENT_LIST = "/comment/";
    public static final String URL_GET_COMMENT_LIST_NEW = "/comment/articleCommentList";
    public static final String URL_LIKE_COMMENT = "/comment/like";
    public static final String URL_CONTENT_FAVORITE = "/content/favorite";
    public static final String URL_CONTENT_CANCEL_FAVORITE = "/content/cancelFavorite";
    public static final String URL_CONTENT_SHARE = "/content/share";
    public static final String URL_GET_RSS_CONTENT_LIST = "/rss/getRssContentList";
    public static final String URL_GET_FAVORITE_LIST = "/content/getFavoriteList";
    public static final String URL_SEARCH_TITLE = "/content/searchTitle";
    public static final String URL_GET_USER_INFO = "/member/getMemberInfo";
    public static final String URL_CONTENT_DETAIL = "/content/getContent";
    public static final String URL_SEARCH_HOTWORD_LIST = "/content/searchHotWordList";
    public static final String URL_UPLOAD_AVATAR = "/upload/avatar";
    public static final String URL_RECEIVE_GIFT = "/mission/receiveGift";
    public static final String URL_GET_RSS_DETAIL = "/rss/getRssDetail";
    public static final String URL_GET_HEART = "/heart/getHeart";
    public static final String URL_USING_REPORT = "/heart/usingReport";
    public static final String URL_CHECK_UPDATE = "/app/update";
    public static final String URL_GET_CONFIG = "/app/getConfig";
    public static final String URL_GET_SWITCH = "/app/getSwitch";
    public static final String URL_GUIDE_REPORT = "/member/guideReport";
    public static final String URL_ARTICLE_UPDOWN = "/content/articleUpDown";
    public static final String URL_SHORT_VIDEO_COMMENT_LIST = "/comment/videoCommentList";
    public static final String URL_SYNC_MEMBER_CONTACTS = "/member/syncMemberContacts";
    public static final String URL_INVITE_SHARE_REPORT = "/app/inviteShareReport";
    public static final String URL_GET_REPLY = "/comment/getReply";
    public static final String URL_OFFENCE_REPORT = "/offence/report";
    public static final String URL_SIGN_IN = "/mission/signIn";
    public static final String URL_TIME_READ = "/timing/read";
    public static final String URL_TIMING_GET_CONTENT_READ_TIME = "/timing/getContentReadTime";
    public static final String URL_TIMING_ACTIVE_TIMING = "/timing/activeTiming";
    public static final String URL_BIND_WX = "/member/bindWx";
    public static final String URL_UNFOLD_REPLY = "/comment/unfoldReply";
    public static final String URL_AD_REPORT = "http://log.kubiknews.com";
    public static final String URL_COMMENT_HOT = "/comment/hotList";
    public static final String URL_WIFI_UPLOAD = "/wifi/uploadEncrypt";
    public static final String URL_REPORT_IS_INSTALL = "/member/appInstall";
    public static final String URL_CONTENT_UNLIKE = "/content/unlike";
    public static final String URL_CONTENT_VIEW = "/content/view";
    public static final String URL_CONTENT_READ = "/content/read";
    public static final String URL_CONTENT_RECOMMEND = "/content/getRecommend";
    public static final String URL_APP_EXTEND_REPORT = "/app/extendReport";
    public static final String URL_CONVERT_REMOVE_SPOT = "/convert/removeSpot";
    public static final String URL_OPEN_SCREEN_AD = "/app/openScreenAd";
    public static final String URL_ADMIN_COMMENT_AUDIT = "/comment/audit";
    public static final String URL_ADMIN_FREEZE_MEMBER = "/member/freezeMember";
    public static final String URL_UPLOAD_FILE = "/upload/uploadFile";
    public static final String URL_ACTIVITY_CARD_LIST = "/activity/getActivityCardList";
    public static final String URL_POST_USER_INTEREST = "/member/interestPointCollect";
    public static final String URL_GET_GUEST_INFO = "/member/getGuestInfo";
    public static final String URL_GET_FIND_LIST = "/content/getFoundList";
    public static final String URL_GET_TOKEN = "/member/mcrypt";
    public static final String URL_RECEIVE_GIFT_COIN = "/mission/receiveGiftCoin";
    public static final String URL_APP_FEEDBACK = "/app/feedback";
    public static final String URL_APP_MSG_BOX = "/app/msgBox";
    public static final String URL_APP_POSITIONING = "/app/positioning";
    public static final String URL_MEMBER_INVITE = "/member/inviteCode";
    public static final String URL_GET_COMMENT_H5_LIST = "/comment/getH5List";
    public static final String URL_GET_RECOMMEND = "/content/getRecommend";
    public static final String URL_GET_COMMENT_DETAIL = "/comment/commentDetail";

    public static final String URL_GET_COMMENT_LIKE_LIST = "/comment/commentLikeList";
    public static final String URL_GET_COMMENT_REPLY_LIST = "/comment/commentReplyDetail";


    //自媒体相关
    public static final String URL_WEMEDIA_MY_FOLLOW = "/wemedia/author/getFollows";
    public static final String URL_WEMEDIA_CANCEL_FOLLOW = "/wemedia/author/cancelFollow";
    public static final String URL_WEMEDIA_FOLLOW = "/wemedia/author/batchFollow";
    public static final String URL_WEMEDIA_RECOMMEND = "/wemedia/author/recommend";
    public static final String URL_WEMEDIA_MEMBER_INFO = "/wemedia/author/memberInfo";
    public static final String URL_WEMEDIA_CATEGORY_LIST = "/wemedia/author/categoryList";
    public static final String URL_WEMEDIA_ARTICLE_LIST = "/wemedia/content/articleList";
    public static final String URL_WEMEDIA_VIDEO_LIST = "/wemedia/content/videoList";
    public static final String URL_WEMEDIA_AUTHOR_LIST = "/wemedia/author/getList";
    public static final String URL_WEMEDIA_AUTHOR_SHARE = "/wemedia/author/homeShare";
    //支付相关
    public static final String URL_PAY = "/payparams/pay";

    //other
    public static final String URL_REFRESH_FRIBASE_TOKEN = "/member/reportDeviceToken";
    public static final String URL_KSTORE_MALL_LIST = "/convert/showList";

    public static final String URL_KSTORE_SHOP_LIST = "/convert/showListV2";
    public static final String URL_KSTORE_APPLY_CONVERT = "/convert/applyConvertV2";
    public static final String URL_AUTO_BINDTEL = "/member/autoBindTel";

    /**
     * 获取新版用户签到信息
     */
    public static final String URL_GET_SIGN_INFO = "/mission/getSignInfo";
    //other
    public static final String URL_RETURN_INFO = "/messagepush/wake";

    /**
     * 小视频相关
     */
    public static final String URL_GET_SHORT_VIDEO_LIST = "/content/getSmallVideos";
    public static final String URL_CET_UP_DOWN_INFO = "/content/getUpDownInfo";
    public static final String URL_GET_SHORT_VIDEO_DETAIL_INFO = "/content/resourceDetail";


//    public static final String URL_WEMEDIA_MY_FOLLOW = "/app/msgBox";
//    public static final String URL_WEMEDIA_CANCEL_FOLLOW = "/app/msgBox";
//    public static final String URL_WEMEDIA_FOLLOW = "/app/msgBox";
//    public static final String URL_WEMEDIA_RECOMMEND = "/app/msgBox";
//    public static final String URL_WEMEDIA_MEMBER_INFO = "/app/msgBox";
//    public static final String URL_WEMEDIA_CATEGORY_LIST = "/app/msgBox";
//    public static final String URL_WEMEDIA_ARTICLE_LIST = "/app/msgBox";
//    public static final String URL_WEMEDIA_VIDEO_LIST = "/app/msgBox";
//    public static final String URL_WEMEDIA_AUTHOR_LIST = "/app/msgBox";


    // REQUEST TYPE
    public static final int REQUEST_TEST = 0x0000;
    public static final int REQUEST_START = 0x0001;
    public static final int REQUEST_WX_LOGIN = 0x0002;
    public static final int REQUEST_USER_GET_CODE = 0x0003;
    public static final int REQUEST_USER_REGISTER = 0x0004;
    public static final int REQUEST_USER_LOGIN = 0x0005;
    public static final int REQUEST_USER_CHECK_VERIFY_CODE = 0x0006;
    public static final int REQUEST_USER_FIND_PWD = 0x0007;
    public static final int REQUEST_GET_RSS_TYPE_LIST = 0x0008;
    public static final int REQUEST_GET_RSS_LIST = 0x0009;
    public static final int REQUEST_SET_RSS = 0x000A;
    public static final int REQUEST_URL_GET_MY_RSS_LIST = 0x000B;
    public static final int REQUEST_GET_DEFAULT_CHANNEL_LIST = 0x000C;
    public static final int REQUEST_GET_USER_CHANNEL_LIST = 0x000D;
    public static final int REQUEST_SET_USER_CHANNEL_LIST = 0x000E;
    public static final int REQUEST_GET_CONTENT_LIST = 0x000F;
    public static final int REQUEST_ADD_COMMENT = 0x0010;
    public static final int REQUEST_GET_COMMENT_LIST = 0x0011;
    public static final int REQUEST_LIKE_COMMENT = 0x0012;
    public static final int REQUEST_CONTENT_FAVORITE = 0x0013;
    public static final int REQUEST_CONTENT_CANCEL_FAVORITE = 0x0014;
    public static final int REQUEST_CONTENT_SHARE = 0x0015;
    public static final int REQUEST_GET_RSS_CONTENT_LIST = 0x0016;
    public static final int REQUEST_GET_FAVORITE_LIST = 0x0017;
    public static final int REQUEST_CONTENT_SEARCH_TITLE = 0x0018;
    public static final int REQUEST_GET_MEMBER_INFO = 0x0019;
    public static final int REQUEST_CONTENT_DETAIL = 0x001A;
    public static final int REQUEST_SEARCH_HOTWORD_LIST = 0x001B;
    public static final int REQUEST_MEMBER_MODIFY = 0x001C;
    public static final int REQUEST_UPLOAD_AVATAR = 0x001D;
    public static final int REQUEST_GET_WX_MEMBER_INFO = 0x001E;
    public static final int REQUEST_MEMBER_LOGOUT = 0x001F;
    public static final int REQUEST_RECEIVE_GIFT = 0x0020;
    public static final int REQUEST_GET_RSS_DETAIL = 0x0021;
    public static final int REQUEST_USER_MODIFY_PWD = 0x0022;
    public static final int REQUEST_GET_HEART = 0x0023;
    public static final int REQUEST_CHECK_UPDATE = 0x0024;
    public static final int REQUEST_GET_CONFIG = 0x0025;
    public static final int REQUEST_SYNC_MEMBER_CONTACTS = 0x0026;
    public static final int REQUEST_INVITE_SHARE_REPORT = 0x0027;
    public static final int REQUEST_GET_REPLY = 0x0028;
    public static final int REQUEST_OFFENCE_REPORT = 0x0029;
    //    public static final int REQUEST_BIND_WX = 0x002A;
    public static final int REQUEST_UNFOLD_REPLY = 0x002B;
    public static final int REQUEST_AD_REPORT = 0x002C;
    public static final int REQUEST_COMMENT_HOT = 0x002D;
    public static final int REQUEST_WIFI_UPLOAD = 0x002E;
    public static final int REQUEST_REPORT_IS_INSTALL = 0x002F;
    public static final int REQUEST_VCODE_URL_OTHER = 0x0030;
    public static final int REQUEST_CONTENT_UNLIKE = 0x0031;
    public static final int REQUEST_CONTENT_VIEW = 0x0032;
    public static final int REQUEST_CONTENT_READ = 0x0033;
    public static final int REQUEST_CONTENT_RECOMMEND = 0x0034;
    public static final int REQUEST_APP_EXTEND_REPORT = 0x0035;
    public static final int REQUEST_ADMIN_COMMENT_AUDIT = 0x0036;
    public static final int REQUEST_ADMIN_FREEZE_MEMBER = 0x0037;
    public static final int REQUEST_UPLOAD_FILE = 0x0038;
    public static final int REQUEST_ACTIVITY_CARD_LIST = 0x0039;
    public static final int REQUEST_POST_USER_INTEREST = 0x003A;
    public static final int REQUEST_GET_GUEST_INFO = 0x003B;
    public static final int REQUEST_GET_FOUND_LIST = 0x003C;
    public static final int REQUEST_GET_TOKEN = 0x003D;
    public static final int REQUEST_RECEIVE_GIFT_COIN = 0x003E;
    public static final int REQUEST_APP_FEEDBACK = 0x003F;
    public static final int REQUEST_APP_MSG_BOX = 0x0040;
    //自媒体相关
    public static final int REQUEST_WEMEDIA_MY_FOLLOW = 0x0041;
    public static final int REQUEST_WEMEDIA_CANCEL_FOLLOW = 0x0042;
    public static final int REQUEST_WEMEDIA_FOLLOW = 0x0043;
    public static final int REQUEST_WEMEDIA_RECOMMEND = 0x0044;
    public static final int REQUEST_WEMEDIA_MEMBER_INFO = 0x0045;
    public static final int REQUEST_WEMEDIA_CATEGORY_LIST = 0x0046;
    public static final int REQUEST_WEMEDIA_ARTICLE_LIST = 0x0047;
    public static final int REQUEST_WEMEDIA_VIDEO_LIST = 0x0048;
    public static final int REQUEST_WEMEDIA_AUTHOR_LIST = 0x0049;
    public static final int REQUEST_WEMEDIA_AUTHOR_SHARE = 0x004A;
    //Kubik 新增
    //数据中心上报
    public static final int REQUEST_DC_REPORT = 0x0050;
    public static final int REQUEST_APP_POSITIONING = 0x004B;
    //支付相关
    public static final int REQUEST_PAY = 0x004C;
    public static final int REQUEST_FIREBASE_TOKEN = 0x004D;
    public static final int REQUEST_GET_IMG_CAPTCHA = 0x004E;
    public static final int REQUEST_MODIFY_TEL = 0x004F;
    public static final int REQUEST_BIND_TEL = 0x0051;
    public static final int REQUEST_GET_SWITCH = 0x0052;

    public static final int REQUEST_GUIDE_REPORT = 0x0053;
    public static final int REQUEST_ARTICLE_UPDOWN = 0x0054;
    public static final int REQUEST_KSTORE_DATA = 0x0055;
    public static final int REQUEST_APPLY_CONVERT = 0x0056;
    public static final int REQUEST_AUTO_BINDTEL = 0x0057;
    public static final int REQUEST_CONVERT_REMOVE_SPOT = 0x0058;
    public static final int REQUEST_OPENSCREEN_AD = 0x0059;
    public static final int REQUEST_SIGN_IN = 0x0060;
    public static final int REQUEST_TIMING_READ = 0x0061;
    public static final int REQUEST_TIMING_GET_CONTENT_READ_TIME = 0x0062;
    public static final int REQUEST_TIMING_ACTIVE_TIMING = 0x0063;
    public static final int REQUEST_USING_REPORT = 0x0064;

    public static final int REQUEST_GET_SIGN_INFO = 0x0065;


    // PATH
    public static final String PATH_ROOT = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/qk";
    public static final String PATH_ROOT_N = Environment.getExternalStorageDirectory().getPath();
    public static final String PATH_IMAGE_TEMP = PATH_ROOT + "/temp/";
    public static final String PATH_IMAGE_CACHE = PATH_ROOT + "/cache/";
    public static final String PATH_CRASH = PATH_ROOT + "/crash/";
    public static final String PATH_LOG = PATH_ROOT + "/log/";
    public static final String PATH_SHARE = PATH_ROOT + "/share/";
    public static final String PATH_ICONS = PATH_ROOT + "/icons/";
    public static final String PATH_GIFS = PATH_ROOT + "/gifs/";
    public static final String PATH_IMAGES = PATH_ROOT + "/images/";
    public static final String PATH_SHARE_IMAGE = PATH_ROOT + "/share.jpg";

    // 字段值
    public static final String FIELD_FILES_PRE = "files:";
    public static final String FIELD_URL = "field_url";
    public static final String FROM_AD = "from_ad";
    public static final String NEED_GO_KSOTRE_ORDER_LIST = "need_go_kstore_order_list";
    public static final String FIELD_REQUEST_URL = "field_request_url";
    public static final String MESSAGE_ID = "message_id";
    public static final String FIELD_TARGET_TAB = "field_target_tab";
    public static final String FIELD_FIRST_START = "field_first_start";
    //    public static final String FIELD_TARGET_BIND_WX = "field_target_bin_wechat";
    public static final String FIELD_MENU_LIST_ALL = "field_menu_list_all";
    public static final String FIELD_MENU_LIST_PERSON = "field_menu_list_person";
    public static final String FIELD_CHECK_ITEM = "field_check_item";
    public static final String FIELD_MENU = "field_menu";
    public static final String FIELD_TYPE = "field_type";
    public static final String FIELD_WX_CODE = "field_wx_code";
    public static final String FIELD_IS_DIRECT = "field_is_direct";
    public static final String FIELD_IMAGE = "field_image";
    public static final String FIELD_IMAGES = "field_images";
    public static final String FIELD_TITLE = "field_title";
    public static final String FIELD_SUMMARY = "field_summary";
    public static final String FIELD_EXTRAS = "field_extras";
    public static final String FIELD_PHONE = "field_phone";
    public static final String FIELD_VERIFY_CODE = "field_verify_code";
    public static final String FIELD_NEWS_ITEM = "field_news_item";
    public static final String FIELD_COMMENT_LIKE_LIST_NUM = "field_comment_like_list_num";
    public static final String FIELD_NEWS_SHOW_POSITION = "field_news_show_position";
    public static final String FIELD_NEWS_FROM = "field_news_from";
    public static final String FIELD_HOME_PAGE_FONT_SIZE = "field_home_page_font_size";
    public static final String FIELD_NEWS_COIN_SHOW = "field_news_coin_show";
    public static final String FIELD_CONTENT_ID = "field_content_id";
    public static final String SHOW_POSITION = "show_position";
    public static final String FIELD_SHOW_POSITION = "field_show_position";
    public static final String FIELD_PUSH_ACTION = "field_push_action";
    public static final String FIELD_SHARE_INVITE = "field_share_invite";
    public static final String FIELD_PV_ID = "field_pv_id";
    public static final String FIELD_SUB_WECHAT = "field_sub_wechat";
    public static final String FIELD_SOURCE_TYPE = "field_source_type";
    //    public static final String FIELD_TARGET_IS_FIRST_LOGIN = "field_target_is_first_login";
    public static final String FIELD_TARGET_JPUSH_MODEL = "field_target_jpush_model";
    public static final String FIELD_USER_STYLE = "field_user_style";
    public static final String FIELD_USER_AGE_INDEX = "field_user_age_index";
    public static final String FIELD_USER_INTEREST_INDEX = "field_user_interest_index";
    public static final String FIELD_USER_TOKEN = "field_user_token";
    public static final String FIELD_COMMENT_ITEM = "field_comment_item";
    public static final String FIELD_COMMENT_ID = "field_comment_id";
    public static final String FIELD_REPORT_REASON = "field_report_reason";
    public static final String FIELD_APK_VERSION = "field_apk_version";
    public static final String FIELD_MD5 = "field_md5";
    public static final String FIELD_APP_LIST = "field_app_list";
    public static final String FIELD_READ_DURATION = "field_read_duration";
    public static final String FIELD_SEARCH_TITLE = "field_search_title";
    public static final String FIELD_SEARCH_TYPE = "field_search_type";
    public static final String FIELD_POSITION = "field_position";
    public static final String FIELD_CLEAN = "field_clean";
    public static final String FIELD_REPORT_REFRESH = "field_report_refresh";
    public static final String FIELD_REPORT_TYPE = "field_report_type";
    public static final String FIELD_WX_APPID = "field_wx_appid";
    public static final String FIELD_ID = "field_id";
    public static final String FIELD_HOT_LIST = "field_hot_list";
    public static final String FIELD_POP_ACTION = "field_pop_action";
    public static final String FIELD_POP_ITEM = "field_pop_item";
    public static final String FIELD_NEWS_PUSH_LIST = "field_news_push_list";
    public static final String FIELD_ACTIVITY_PUSH = "field_activity_push";
    public static final String FIELD_NAME = "field_name";
    public static final String FIELD_ERROR_URL = "field_error_url";
    public static final String FIELD_PUSH_REPORT_MODELS = "field_push_report_models";
    public static final String FIELD_PUSH_PLATFORM = "field_push_platform";
    public static final String FIELD_DESCRIPTION = "field_description";
    public static final String FIELD_HTML_CODE = "field_html_code";
    public static final String FIELD_PUSH_SHOW = "field_push_show";
    public static final String FIELD_PUSH_SHOW_JPUSH = "field_push_show_jpush";
    public static final String FIELD_OPEN_GIFT = "field_open_gift";
    public static final String FIELD_HAVE_GREENHAND_RED_BAG_ID = "field_have_greenhand_red_bag_id";
    public static final String FIRST_LOGIN_NEED_DIALOG = "first_login_need_dialog";
    public static final String FIELD_TEACHER_ID = "field_teacher_id";
    public static final String FIELD_TIPS = "field_tips";
    public static final String FIELD_CDN_FAILED_COUNT = "field_cdn_failed_count";
    public static final String FIELD_MEDIA_ITEM = "field_media_item";
    public static final String FIELD_APP_POSITIONING = "field_app_positioning";
    public static final String FIELD_REFERRER_MASTER_ID = "field_referrer_master_id";
    public static final String FIELD_INVITE_CODE = "field_invite_code";
    public static final String FIELD_CLICK_TYPE = "field_click_type";
    public static final String FIELD_SHOW_TYPE = "field_show_type";
    public static final String USER_TOKEN = "USER_TOKEN";
    public static final String USER_ID = "USER_ID";
    public static final String APP_RUNING = "app_running";
    public static final String QUIT_TIME = "quit_time";

    //广告相关
    public static final String FIELD_AD_REPORT_TYPE = "field_ad_report_type";
    public static final String FIELD_AD_ACTION = "field_ad_action";
    public static final String FIELD_AD_POSITION = "field_ad_position";
    public static final String FIELD_AD_SOURCE = "field_ad_source";
    public static final String FIELD_AD_SLOTID = "field_ad_slotid";
    public static final String FIELD_AD_PIC = "field_ad_pic";
    public static final String FIELD_AD_BRAND = "field_ad_brand";
    public static final String FIELD_AD_TITLE = "field_ad_title";
    public static final String FIELD_AD_DESC = "field_ad_desc";
    // perfer key
    public static final String KEY_UID = "key_user_id";
    public static final String KEY_MENU_ID = "key_menu_id";
    public static final String KEY_PAGE = "key_page";
    public static final String KEY_MAX_TIME = "key_max_time";
    public static final String KEY_SHOW_TIME = "key_show_time";
    public static final String KEY_SWITCH_FORCE_BIND_NEW = "key_switch_force_bind_new";
    public static final String KEY_IS_BIND_TEL = "key_is_bind_tel";
    public static final String KEY_GREENHAND_RED_BAG_ID = "key_greenhand_red_bag_id";
    public static final String FIRST_LOGIN = "first_login";
    public static final String KEY_SHOW_COMMENT = "key_show_comment";
    public static final String KEY_ROLL_COMMENT = "key_roll_comment";
    public static final String KEY_LIKE_INFO = "key_like_info";
    public static final String KEY_BIND_TEL_DESC = "key_bind_tel_desc";
    public static final String KEY_USER_TOKEN = "key_user_token";
    public static final String KEY_INVITE_CODE = "key_invite_code";
    public static final String KEY_IS_FIRST = "key_is_first";
    public static final String KEY_IS_NEW_FIRST_RUN = "key_is_new_first_run";
    public static final String KEY_IS_UPDATE_FIRST_RUN = "key_is_update_first_run";
    public static final String KEY_GREEN_HAND_SHOWED = "key_green_hand_showed";
    public static final String KEY_GREEDHAND_RED_BAG_DONE = "key_red_bag_always_get";
    public static final String KEY_IS_FIRST_LOGIN = "key_is_first_login_1";
    public static final String KEY_PACKAGE_INVITE_AWARD = "key_package_invite_award";
    public static final String KEY_PACKAGE_INVITE_CODE = "key_package_invite_code";
    public static final String KEY_LOGIN_DESC = "key_login_desc";
    public static final String KEY_IS_FIRST_LOGIN_PHONE = "key_is_first_login_phone";
    public static final String KEY_IS_FIRST_GUI = "key_is_first_gui";
    public static final String KEY_IS_COMMENT_REPLY_GUI = "key_is_comment_reply_gui";
    public static final String KEY_IS_NEWS_TAB_REFRESH_GUI = "key_is_news_tab_refresh_gui";
    public static final String KEY_NET_IP = "key_net_ip";
    public static final String KEY_LAST_OPEN_LOGIN_TIME = "key_last_open_login_time";
    public static final String KEY_THIS_TIME_FRAME_COIN_FINISH = "key_this_time_frame_coin_finish";
    public static final String KEY_DAILY_CLEAR_LAST_TIME = "key_daily_clear_last_time";
    public static final String KEY_LAST_FRAME_COIN_TIME = "key_last_frame_coin_time";
    public static final String KEY_IS_NEWS_CHANNEL_GUI = "key_is_news_channel_gui";
    public static final String KEY_IS_CHANGE_FONT_SIZE_GUI = "key_is_change_font_size_gui";
    public static final String KEY_LATITUDE = "key_latitude";
    public static final String KEY_LONGITUDE = "key_longitude";
    public static final String KEY_LOCATION_TIME = "key_location_time";
    public static final String KEY_LOCATION_CITY = "key_location_city";
    public static final String KEY_USER_JSON = "key_user_json";
    public static final String KEY_INVITE_GONE = "key_invite_gone";
    public static final String KEY_KSTORE_LIST_JSON = "key_kstore_list_json";
    public static final String KEY_MEMBER_JSON = "key_member_json";
    public static final String KEY_NEWS_LIST_JSON = "key_news_list_json";
    public static final String KEY_NEWS_TOP = "key_news_top";
    public static final String KEY_VIDEO_LIST_JSON = "key_video_list_json";
    public static final String KEY_USER_AGE = "key_user_age";
    public static final String KEY_USER_SEX = "key_user_sex";
    public static final String KEY_API_START_BEGIN_TIME = "key_api_start_begin_time";
    public static final String KEY_MENU_LIST_ALL = "key_menu_list_all";
    public static final String KEY_MENU_LIST_VIDEOS = "key_menu_list_videos";
    public static final String KEY_MENU_LIST_NEWS = "key_menu_list_news";
    public static final String KEY_USER_MENU_LIST_ALL = "key_user_menu_list_all";
    public static final String KEY_TITLE_SEARCH_HOT = "key_title_search_hot";
    public static final String KEY_TITLE_SEARCH_HOT_VIDEO = "key_title_search_hot_video";
    public static final String KEY_TITLE_SEARCH_HISTORY = "key_title_search_history";
    public static final String KEY_REGISTER_BUTTON = "key_register_button";
    public static final String KEY_LOGIN_WARNING = "key_login_warning";
    public static final String KEY_MEMBER_TAGS = "key_member_tags";
    public static final String KEY_NEED_ACTIVE_NOTICE = "key_need_active_notice";
    public static final String KEY_UNLIKE_REASON = "key_unlike_reason";
    public static final String KEY_INDEX_CONTENT_TYPE = "key_index_content_type";
    public static final String KEY_APP_INSTALL_TIME = "key_app_install_time";
    public static final String KEY_APP_INSTALL_DATA = "key_app_install_data";
    public static final String KEY_IS_APP_INSTALL_REPORT = "key_is_app_install_report";
    public static final String KEY_COMMENT_TIPS = "key_comment_tips";
    public static final String KEY_IS_OPEN_JPUSH = "key_is_open_jpush";
    public static final String KEY_MAIN_POP = "key_main_pop";
    public static final String KEY_VIDEO_FIRST_CHANNEL = "key_video_first_channel";
    public static final String KEY_CONTENT_TYPE_COLOR = "key_content_type_color";
    public static final String KEY_IS_MINE_AUTH = "key_is_mine_auth";
    public static final String KEY_IS_INTEREST_COLLECT = "key_is_interest_collect";
    public static final String KEY_IS_AD_PUSH_SKIP = "key_is_ad_push_skip";
    public static final String KEY_IS_START_APP_FROM_PUSH = "key_is_start_app_from_push";
    public static final String KEY_IS_LICENSE_FORCE = "key_is_license_force";
    public static final String KEY_IS_AVATAR_GUI = "key_is_avatar_gui";
    public static final String KEY_IS_USER_AVATAR_URL = "key_is_user_avatar_url";

    /**
     * 1.3.2 版本 KEY_TAB_MENUS 字段key更换，使旧缓存失效，原key为 key_tab_menus ，更改后为new_key_tab_menus
     */
    public static final String KEY_TAB_MENUS = "new_key_tab_menus";
    public static final String KEY_UUID = "key_uuid";
    public static final String KEY_PUSH_CLICK = "key_push_click";
    public static final String KEY_PUSH_CANCEL_CLICK = "key_push_cancel_click";
    public static final String KEY_INSTALL_VERSION = "key_install_version";
    public static final String KEY_GIF_ANIM_MODEL = "key_gif_anim_model";
    public static final String KEY_PRIVATE_DOMAINS = "key_private_domains";
    public static final String KEY_PUSH_HISTORY_LIST = "key_push_history_list";
    public static final String KEY_PUSH_EARLY_TIME = "key_early_time";
    public static final String KEY_PUSH_BOX_CLICK_TIME = "key_push_box_click_time";
    public static final String KEY_ATTENTION_COUNT = "key_attention_count";
    public static final String KEY_CONFIG_SHOW_WEMEDIA_LIST = "key_config_show_wemedia_list";
    public static final String KEY_CONFIG_CATCH_AD_RATE = "key_config_catch_ad_rate";
    public static final String KEY_PUSH_TIME = "key_push_time";
    public static final String KEY_WEMEDIA_JUMP_SWITCH = "key_wemedia_jump_switch";
    public static final String KEY_PAUSE_TIME = "key_pause_time";
    public static final String KEY_ALL_REFRESH = "key_all_refresh";
    public static final String KEY_FIRST_RED_BAG_SHOW = "key_first_red_bag_show";


    //运行时app上报
    public static final String KEY_RUNNING_APP_REPORT_TIME = "key_running_app_report_time";
    public static final String KEY_RUNNING_APP_REPORT_LIST = "key_running_app_report_list";
    public static final String KEY_NEED_REPORT_RUNNING_APP = "key_need_report_running_app";
    public static final String KEY_DNS_CONFIGS = "key_dns_configs";
    //分享文章是否调用系统分享
    public static final String KEY_SHARE_NEWS_WAY = "key_share_news_way";

    public static final String KEY_OPEN_AD = "key_open_ad";
    public static final String KEY_OPEN_AD_STATE = "key_open_ad_state";
    public static final String KEY_IS_PUSH_DETAIL_FIRST = "key_is_push_detail_first";
    public static final String KEY_OPEN_SLOT_ID = "key_open_ad_id";
    //    public static final String KEY_OPEN_APP_ID = "key_open_app_id";
    public static final String KEY_OPEN_AD_COUNTDOWN = "key_open_ad_countdown";

    public static final String KEY_SHARE_CONFIG = "key_share_config";
    public static final String KEY_SHOW_SEARCH = "key_show_search";
    public static final String KEY_SHOW_RSS = "key_show_rss";
    public static final String KEY_RSS_STATUS = "key_rss_status";

    public static final String KEY_WX_APP_ID = "key_wx_app_id";
    public static final String KEY_WX_SHARE_APP_ID = "key_wx_share_app_id";
    public static final String KEY_WX_SHARE_INFO_LIST = "key_wx_share_info_list";
    public static final String KEY_WX_SHARE_PACKAGE = "key_wx_share_package";
    public static final String KEY_WX_RESPONSE_APP_ID = "key_wx_response_app_id";
    public static final String KEY_WX_PAY_APP_ID = "key_wx_pay_app_id";//微信支付
    public static final String KEY_WX_APP_SECRET = "key_wx_app_secret";
    public static final String KEY_WX_OPENID = "key_wx_openid";
    public static final String KEY_WX_UNIONID = "key_wx_unionid";
    public static final String KEY_WX_ACCESS_TOKEN = "key_wx_access_token";

    public static final String KEY_GALLERY_CFG_PERCENT = "key_gallery_cfg_percent";
    public static final String KEY_GALLERY_CFG_DURATION = "key_gallery_cfg_duration";

    public static final String KEY_SHARE_INVITE_TITLE = "key_share_invite_title";
    public static final String KEY_SHARE_INVITE_DESC = "key_share_invite_desc";
    public static final String KEY_SHARE_INVITE_URL = "key_share_invite_url";
    public static final String KEY_SHARE_INVITE_ICON = "key_share_invite_icon";
    public static final String KEY_SHARE_INVITE_BG = "key_share_invite_bg";
    public static final String KEY_SHARE_INVITE_XYS = "key_share_invite_xys";
    public static final String KEY_SHARE_INVITE_SYS_WAY = "key_share_invite_sys_way";


    public static final String KEY_URL_INVITE = "key_url_invite";
    public static final String KEY_URL_PROTOCOL = "key_url_protocol";
    public static final String KEY_URL_MISSION = "key_url_mission";
    public static final String KEY_URL_HOT_TO_RICH = "key_url_hot_to_rich";
    public static final String KEY_URL_REGISTER_INVITE_CODE = "key_url_register_invite_code";
    public static final String KEY_URL_ABOUT = "key_url_about";
    public static final String KEY_URL_MALL = "key_url_mall";
    public static final String KEY_URL_FEEDBACK = "key_url_help";
    public static final String KEY_URL_MESSAGE = "key_url_message";
    public static final String KEY_URL_REPORT = "key_url_report";
    public static final String KEY_URL_FSDEMO = "key_url_fsdemo";
    public static final String KEY_URL_GAME = "key_url_game";
    public static final String KEY_URL_SHOP = "key_url_shop";
    public static final String KEY_URL_BALANCE_DETAILS = "key_url_balance_details";
    public static final String KEY_URL_COIN_DETAILS = "key_url_coin_details";
    public static final String KEY_URL_WEMEDIA_TOP_LIST = "key_url_wemedia_top_list";
    public static final String KEY_URL_BIND_PHONE = "key_url_bind_phone";
    public static final String KEY_URL_COVERT_HISTORY = "key_url_covert_history";
    public static final String KEY_URL_COVERT_DETAIL = "key_url_covert_detail";
    public static final String KEY_URL_KSTORE_EXCHARGE = "key_url_kstore_excharge";
    public static final String KEY_URL_TASK_LIST = "key_url_task_list";
    public static final String KEY_URL_SHAITU = "key_url_shaitu";
    public static final String KEY_URL_INVITE_FRIEND = "invite_friend";
    public static final String KEY_URL_ACTIVITY = "h5_activity";//活动h5页面

    public static final String KEY_TELPHONE = "key_telphone";
    public static final String KEY_PUBLISH_TIME = "key_publish_time";

    public static final String KEY_PVID = "key_pvid";
    public static final String KEY_LOGIN_JUDGE = "key_login_judge";
    public static final String KEY_CHECK_PACKAGE_NAME = "key_check_package_name";
    public static final String KEY_H5_URL_MODEL = "key_h5_url_model";


    public static final String KEY_NIGHT_MODE = "key_night_mode";
    public static final String KEY_IS_SHARE_WARN = "key_is_share_warn";

    public static final String KEY_LOGIN_TIMEOUT_MILLI_SECOND = "key_login_timeout_milli_second";
    public static final String KEY_ACT_CARD_SWITCH = "key_act_card_switch";
    public static final String KEY_HISTORY_KSTORY_PHONE = "key_history_kstory_phone";
    public static final String KEY_KSTORE_NATIVE_SWITCH = "key_kstore_native_switch";
    public static final String KEY_WELCOME_SHOW = "key_welcome_show";
    public static final String KEY_OPEN_SCREEN_GOOGLE_ID = "key_open_screen_google_id";
    public static final String KEY_FACEBOOK_OPEN_SHARE = "key_open_facebook_share";

    public static final String KEY_READ_DURATION_MAX = "key_read_duration_max";
    public static final String KEY_FRAME_COIN_DURATION = "key_frame_coin_duration";
    public static final String KEY_AWARD_COIN_README = "key_award_coin_readme";
    public static final String KEY_IS_TODAY_READ_MAX = "key_is_today_read_max";
    public static final String KEY_USER_BALANCE = "key_user_balance";

    /**
     * 1.3.4 版本 KEY_KSTORE_JSON 字段key更换，使旧缓存失效，原key为 key_kstore_json ，更改后为new_key_kstore_json
     */
    public static final String KEY_KSTORE_JSON = "new_key_kstore_json";
    public static final String KEY_GET_KSTORE_JSON_TIME = "key_get_kstore_json_time";

    public static final String KEY_SETTING_SCREEN_BRIGHTNESS_MODE = "key_setting_screen_brightness_mode";
    public static final String KEY_SETTING_SCREEN_BRIGHTNESS = "key_setting_screen_brightness";
    //测试环境host
    public static final String KEY_TEST_HOST = "key_test_host";

    //个人中心item点击时间
    public static final String KEY_PERSON_ITEM_READ_TIME = "key_person_item_click";

    //other
    public static final String KEY_PERSON_SHOW_LOGIN_DIALOG = "key_person_show_login_dialog";
    public static final String KEY_FACEBOOK_SHARE = "key_facebook_share";
    public static final String KEY_TEACHER_INVITATION_CODE = "teacher_invitation";
    public static final String KEY_MSG_DATA = "msg_data";//消息推送解析key值
    public static final String TEACHER_ID = "teacher_id";
    public static final String URL_KEY_SHARE = "share_link/inapp";
    public static final String URL_GOOGLE_STORE_SHARE = "play.google.com/store";
    public static final String URL_BLUE_PRINT = "blueprint";
    public static final String URL_KEY_PUPIL = "summon-pupil/info";
    public static final String URL_FAQ_URL = "guide/inapp/guide/index.html";

    //google ad key
//    public static final String KEY_GOOGLE_AD = "ca-app-pub-5238700575442644~5184727702";
    public static final String KEY_GOOGLE_AD = "ca-app-pub-5238700575442644~7154022098";

    //首页图片大小配置 ?x-oss-process=image/resize,h_240
    public static final String KEY_NEWS_IMAGE_SIZE = "?x-oss-process=image/resize,h_";

    /**
     * shortVideo constant value
     */
    public static final String KEY_SHORT_VIDEO_LIST = "short_video_list";
    public static final String KEY_SHORT_VIDEO_ITEM_POSITION = "short_video_item_position";
    public static final int REQUEST_CODE_SHORT_VIDEO_DETAIL = 102;
    public static final int RESULT_CODE_SHORT_VIDEO_DETAIL = 103;
    public static final String SP_KEY_IS_FIRST_TIME_OPEN_SHORT_VIDEO = "is_first_time_open_short_video";

    public static final long TIME_3_HOUR = 3 * 60 * 60 * 1000;

    public static final String SP_KEY_IS_FIRST_TIME_RECEIVE_GP_TRACKING_EVENT = "is_first_time_receive_gp_tracking_event";
    public static final String SP_KEY_HAS_UPLOAD_TRACKING_EVENT = "has_upload_tracking_event";
    public static final String SP_KEY_HAS_UPLOAD_UN_SIGN_IN_TRACKING_EVENT = "has_upload_un_sign_in_tracking_event";
    public static final String SP_KEY_TRACKING_ENTRANCE = "tracking_entrance";
    public static final String SP_KEY_TRACKING_CHANNEL = "tracking_channel";
    public static final String SP_KEY_TRACKING_FROM_SOURCE = "tracking_from_source";
    public static final String SP_KEY_TRACKING_CONTENT_ID = "tracking_content_id";

    public static final String SP_KEY_SHORT_VIDEO_DETAIL_NEED_SHOW_GOOD_FORTUNE_POCKET = "new_short_video_detail_need_show_good_fortune_pocket";
    public static final String SP_KEY_SHORT_VIDEO_DETAIL_GOOD_FORTUNE_POCKET_URL = "new_short_video_detail_good_fortune_pocket_url";

    /**
     * 1.4.9版本后台控制首页福袋显示逻辑
     * 0: 首页福袋一直显示
     * 1: 首页福袋显示且点击后消失（当天不再显示）
     * 2: 首页福袋不显示
     */
    public static final String SP_KEY_GOOD_FORTUNE_POCKET_SHOW_TYPE = "good_fortune_pocket_show_type";
    public static final String SP_KEY_GOOD_FORTUNE_POCKET_CLICK_TIME = "good_fortune_pocket_click_time";

    /**
     * 全局变量，用来存储当前H5界面的入口，在 NewWebActivity destroy() 之后会置空
     */
    public static String CURRENT_ENTRANCE;

    /**
     * 拉新分享入口
     */
    public interface EntranceType {
        /**
         * 首页福袋
         */
        String TYPE_GOOD_FORTUNE_POCKET = "good_fortune_pocket";

        /**
         * 小视频详情页福袋
         */
        String TYPE_SHORT_VIDEO_DETAIL_GOOD_FORTUNE_POCKET = "short_video_detail_good_fortune_pocket";

        /**
         * 个人中心收徒
         */
        String TYPE_PERSONAL_CENTER_INVITE = "personal_center_invite";

        /**
         * 个人中心活动
         */
        String TYPE_PERSONAL_CENTER_BANNER = "personal_center_banner";

        /**
         * 启动页广告
         */
        String TYPE_START_PAGE_AD = "start_page_ad";

        /**
         * 推送
         */
        String TYPE_PUSH_TO_ACTIVITY = "push_activity";

        String TYPE_PUSH_TO_INVITE = "push_invite";

        /**
         * 个人中心新手教程
         */
        String TYPE_PERSON_CENTER = "person_center";

        /**
         * 登录时奖励弹窗进入新手教程
         */
        String TYPE_LOGIN_DIALOG = "login_dialog";

        /**
         * 新闻详情页分享
         */
        String TYPE_NEWS_DETAIL_SHARE = "news_detail_share";

        String TYPE_VIDEO_LIST_SHARE = "video_list_share";

        String TYPE_WE_MEDIA_SHARE = "we_media_share";

        String TYPE_SHORT_VIDEO_DETAIL_SHARE = "short_video_detail_share";

        String TYPE_GIF_CHANNEL_SHARE = "gif_channel_share";

        String TYPE_TAB_EVENT = "tab_event";
    }

    /**
     * 拉新分享渠道
     */
    public interface ShareChannelType {
        String CHANNEL_FACEBOOK = "facebook";

        String CHANNEL_WHATSAPP = "whatsapp";

        String CHANNEL_WHATSAPP_1 = "whatsapp1";

        String CHANNEL_WHATSAPP_2 = "whatsapp2";

        String CHANNEL_WHATSAPP_3 = "whatsapp3";

        String CHANNEL_WHATSAPP_4 = "whatsapp4";

        String CHANNEL_INSTAGRAM = "instagram";

        String CHANNEL_LINE = "line";

        String CHANNEL_COPY_LINK = "copy_link";

        String CHANNEL_SYSTEM_SHARE = "system_share";

        String CHANNEL_BBM = "bbm";
    }

    /**
     * 拉新下载平台
     */
    public interface DownloadPlatform {
        String PLATFORM_GOOGLE_PLAY = "google_play";

        String PLATFORM_OWN_PLATFORM = "own_platform";
    }

    /**
     * 小视频详情页埋点ActionType
     */
    public interface ShortVideoDetailAction {
        String ACTION_LOAD = "load";

        String ACTION_PLAY_START = "play_start";

        String ACTION_PLAY_FINISH = "play_finish";

        String ACTION_CLICK = "click";
    }

    public interface ShortVideoDetailClickType {
        String TYPE_CLICK_CLOSE_BUTTON = "close_button";

        String TYPE_CLICK_LIKE_VIDEO = "like_video";

        String TYPE_CLICK_CANCEL_LIKE_VIDEO = "cancel_like_video";

        String TYPE_CLICK_COMMENT_ICON = "comment_icon";

        String TYPE_CLICK_INSTAGRAM_SHARE_ICON = "instagram_share_icon";

        String TYPE_CLICK_SYSTEM_SHARE_ICON = "system_share_icon";

        String TYPE_CLICK_COMMENT_BOX = "comment_box";

        String TYPE_CLICK_COMMENT_SUBMIT_CLICKABLE_BUTTON = "comment_submit_clickable_button";

        String TYPE_CLICK_COMMENT_SUBMIT_UN_CLICKABLE_BUTTON = "comment_submit_un_clickable_button";

        String TYPE_CLICK_COMMENT_LIST_CLOSE_BUTTON = "comment_list_close_button";

        String TYPE_CLICK_COMMENT_LIST_LOAD_MORE = "comment_list_load_more";

        String TYPE_CLICK_COMMENT_LIST_COMMENT_BOX = "comment_list_comment_box";

        String TYPE_CLICK_COMMENT_LIST_COMMENT_SUBMIT_CLICKABLE_BUTTON = "comment_list_comment_submit_clickable_button";

        String TYPE_CLICK_COMMENT_LIST_COMMENT_SUBMIT_UN_CLICKABLE_BUTTON = "comment_list_comment_submit_un_clickable_button";
    }
}
