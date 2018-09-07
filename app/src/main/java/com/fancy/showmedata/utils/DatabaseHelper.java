package com.fancy.showmedata.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Locale;

/**
 * Smile<lijian@adeaz.com>
 * 2015-2-9
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "QK";

    private static final int VERSION = 12;

    public static final String TABLE_USER_INFO = "user_info";
    public static final String TABLE_SUBS_INFO = "subs_info";
    public static final String TABLE_NEWS_READ = "news_read";
    public static final String TABLE_MAIN_POP = "main_pop";
    public static final String TABLE_WEMEDIA_CLICK = "wemedia_click";
    //修改表名，防止上个版本冲突
    public static final String TABLE_MAIN_ACTIVITY = "main_activity2";
    //修改表名，防止上个版本冲突
    public static final String TABLE_APP_USER_EVENTS = "app_statistic_user_events";
    //通知记录表,修改表名，防止上个版本冲突
    public static final String TABLE_APP_NOTIFY = "app_notify_new";
    //gif播放记录表
    public static final String TABLE_ANIM_PLAY_RECORD = "anim_play_record";
    //关注关系记录表
    public static final String TABLE_FOLLOW_LIST = "follow_list";
    //网页缓存表
    public static final String TABLE_WEB_HTML_CACHE = "web_html_cache";

    public static final String USERINFO_ID = "uid";
    public static final String USERINFO_JSON = "json";

    public static final String SUBS_ID = "sid";
    public static final String SUBS_UPDATE_TIME = "update_time";

    public static final String NEWS_READ_ID = "nid";
    public static final String NEWS_READ_IS_READ = "read";

    public static final String MAIN_POP_ID = "pid";
    public static final String MAIN_POP_CLICK = "click";
    public static final String MAIN_POP_SHOW_COUNT = "show_count";
    public static final String MAIN_POP_MAX_COUNT = "max_count";

    public static final String MAIN_ACTIVITY_ID = "aid";
    public static final String MAIN_ACTIVITY_URL = "url";
    public static final String MAIN_ACTIVITY_PIC = "pic";
    public static final String MAIN_ACTIVITY_START_TIME = "start_time";
    public static final String MAIN_ACTIVITY_SHOW_TIME = "show_time";
    public static final String MAIN_ACTIVITY_END_TIME = "end_time";
    public static final String MAIN_ACTIVITY_LOCALE_COUNT = "locale_count";
    public static final String MAIN_ACTIVITY_MAX_COUNT = "max_count";
    public static final String MAIN_ACTIVITY_HASFOCUS = "has_focus";

    public static final String APP_USER_EVENTS_ID = "e_id";
    public static final String APP_USER_EVENTS_TIME = "e_time";
    public static final String APP_USER_EVENTS_NAME = "e_name";
    public static final String APP_USER_EVENTS_DATA = "e_data";
    public static final String APP_USER_EVENTS_EXTRA = "e_extra";

    public static final String APP_NOTIFY_ID = "notify_id";
    public static final String APP_NOTIFY_LOCALE_ID = "notify_locale_id";
    public static final String APP_NOTIFY_IS_SHOW = "notify_isshow";

    public static final String ANIM_ID = "anim_id";
    public static final String ANIM_PLAY_TIME = "anim_play_time";

    public static final String WEMEDIA_CLICK_AUTHOR_ID = "author_id";
    public static final String WEMEDIA_CLICK_TIME = "click_time";

    public static final String FOLLOW_AUTHOR_ID = "author_id";
    public static final String FOLLOW_TIME = "follow_time";

    public static final String WEB_HTML_CACHE_URL = "url";
    public static final String WEB_HTML_CACHE_MD5 = "md5";
    public static final String WEB_HTML_CACHE_CONTENT = "content";

    //本地H5缓存
    public static final String H5_LOCALE_KEY = "locale_key";
    public static final String H5_LOCALE_VALUE = "locale_value";


    public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name) {
        this(context, name, VERSION);
    }

    public DatabaseHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(%s int, %s varchar)",
                TABLE_USER_INFO, USERINFO_ID, USERINFO_JSON));
        db.execSQL(String.format(Locale.getDefault(), "CREATE TABLE IF NOT EXISTS %s(%s varchar, %s varchar)",
                TABLE_SUBS_INFO, SUBS_ID, SUBS_UPDATE_TIME));
        createVersion2(db);
        createVersion3(db);
        createVersion4(db);
        createVersion5(db);
        createVersion6(db);
        createVersion10(db);
//        createVersion11(db);
        createVersion12(db);

    }

    private void createVersion2(SQLiteDatabase db) {
        db.execSQL(String.format(Locale.getDefault(), "CREATE TABLE IF NOT EXISTS %s " +
                "(%s varchar, %s int)", TABLE_NEWS_READ, NEWS_READ_ID, NEWS_READ_IS_READ));
    }

    private void createVersion3(SQLiteDatabase db) {
        //主界面气泡表
        db.execSQL(String.format(Locale.getDefault(), "CREATE TABLE IF NOT EXISTS %s(%s varchar, %s int DEFAULT 0,%s int,%s int)",
                TABLE_MAIN_POP, MAIN_POP_ID, MAIN_POP_CLICK, MAIN_POP_SHOW_COUNT, MAIN_POP_MAX_COUNT));

    }

    private void createVersion4(SQLiteDatabase db) {
        //用户行为表
        db.execSQL(String.format(Locale.getDefault(), "CREATE TABLE IF NOT EXISTS %s(%s int,%s int," +
                        "%s varchar,%s varchar,%s varchar)",
                TABLE_APP_USER_EVENTS, APP_USER_EVENTS_ID, APP_USER_EVENTS_TIME, APP_USER_EVENTS_NAME,
                APP_USER_EVENTS_DATA, APP_USER_EVENTS_EXTRA));
    }

    private void createVersion5(SQLiteDatabase db) {
        //gif播放记录表
        db.execSQL(String.format(Locale.getDefault(), "CREATE TABLE IF NOT EXISTS %s(%s varchar,%s int)",
                TABLE_ANIM_PLAY_RECORD, ANIM_ID, ANIM_PLAY_TIME));
    }

    private void createVersion6(SQLiteDatabase db) {
        //通知记录表
        db.execSQL(String.format(Locale.getDefault(), "CREATE TABLE IF NOT EXISTS %s(%s varchar,%s int)",
                TABLE_APP_NOTIFY, APP_NOTIFY_ID, APP_NOTIFY_LOCALE_ID));
        //主界面活动表
        db.execSQL(String.format(Locale.getDefault(), "CREATE TABLE IF NOT EXISTS %s(%s varchar,%s varchar," +
                        "%s varchar,%s int,%s int,%s int, %s int DEFAULT 0,%s int,%s int)",
                TABLE_MAIN_ACTIVITY, MAIN_ACTIVITY_ID, MAIN_ACTIVITY_URL, MAIN_ACTIVITY_PIC,
                MAIN_ACTIVITY_START_TIME, MAIN_ACTIVITY_SHOW_TIME, MAIN_ACTIVITY_END_TIME,
                MAIN_ACTIVITY_LOCALE_COUNT, MAIN_ACTIVITY_MAX_COUNT, MAIN_ACTIVITY_HASFOCUS));
    }

    private void createVersion10(SQLiteDatabase db) {
        //自媒体点击时间记录表
        db.execSQL(String.format(Locale.getDefault(), "CREATE TABLE IF NOT EXISTS %s " +
                "(%s int, %s int)", TABLE_WEMEDIA_CLICK, WEMEDIA_CLICK_AUTHOR_ID, WEMEDIA_CLICK_TIME));
    }

    private void createVersion11(SQLiteDatabase db) {
        //本地关注关系记录表
        db.execSQL(String.format(Locale.getDefault(), "CREATE TABLE IF NOT EXISTS %s " +
                "(%s int, %s int)", TABLE_WEMEDIA_CLICK, WEMEDIA_CLICK_AUTHOR_ID, WEMEDIA_CLICK_TIME));
    }

    private void createVersion12(SQLiteDatabase db) {
        //本地网页缓存表
        db.execSQL(String.format(Locale.getDefault(), "CREATE TABLE IF NOT EXISTS %s " +
                        "(%s varchar, %s varchar, %s varchar)",
                TABLE_WEB_HTML_CACHE,
                WEB_HTML_CACHE_URL,
                WEB_HTML_CACHE_MD5,
                WEB_HTML_CACHE_CONTENT));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //本地数据无需维护太重，版本更替时直接清理即可，防止某些奇怪的崩溃出现
        String[] tables = {TABLE_ANIM_PLAY_RECORD, TABLE_APP_NOTIFY, TABLE_APP_USER_EVENTS,
                TABLE_MAIN_ACTIVITY, TABLE_MAIN_POP, TABLE_NEWS_READ, TABLE_SUBS_INFO, TABLE_USER_INFO,TABLE_WEB_HTML_CACHE};
        for (String table : tables) {
            try {
                db.execSQL("DROP TABLE " + table);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
