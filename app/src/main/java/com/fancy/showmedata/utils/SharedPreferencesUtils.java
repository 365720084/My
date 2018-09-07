package com.fancy.showmedata.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.fancy.showmedata.application.App;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Smile<lijian@adeaz.com>
 * 2015年4月10日
 */
public class SharedPreferencesUtils {
    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "qk_app";
    private static SharedPreferences sp ;
    private static SharedPreferences.Editor editor ;
    static {
        sp = App.getAppContext().getSharedPreferences( FILE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }



    public static void setParam(String key, Object object) {
        setParam(App.getAppContext(), key, object);
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context inner context
     * @param key     param key
     * @param object  param value
     */
    public static void setParam(Context context, String key, Object object) {

        if (context == null || object == null || key == null) {
            return;
        }
        String type = object.getClass().getSimpleName();


        if (String.class.getSimpleName().equals(type)) {
            editor.putString(key, (String) object);
        } else if (Integer.class.getSimpleName().equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if (Boolean.class.getSimpleName().equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if (Float.class.getSimpleName().equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if (Double.class.getSimpleName().equals(type)) {
            editor.putFloat(key, Float.parseFloat(String.valueOf(object)));
        } else if (Long.class.getSimpleName().equals(type)) {
            editor.putLong(key, (Long) object);
        } else if (ArrayList.class.getSimpleName().equals(type)) {
            Gson gson = new Gson();
            String strJson = gson.toJson(object);
            editor.putString(key, strJson);
        }
        //如果对提交的结果不关心的话，建议使用apply，当然需要确保提交成功且有后续操作的话，还是需要用commit的。
        // https://blog.csdn.net/u010746364/article/details/51727355
        editor.apply();
    }


    public static Object getParam(String key, Object object) {
        //很多string类型可能没有初始化,会导致获得的结果是null 做""处理
        if(object==null){
            object="";
        }
        return getParam(App.getAppContext(), key, object);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context       inner context
     * @param key           param key
     * @param defaultObject param default value
     * @return value
     */
    public static Object getParam(Context context, String key,
                                  Object defaultObject) {
        if (context == null || defaultObject == null || key == null) {
            return defaultObject;
        }
        String type = defaultObject.getClass().getSimpleName();

        if (String.class.getSimpleName().equals(type)) {
            return sp.getString(key, (String) defaultObject);
        } else if (Integer.class.getSimpleName().equals(type)) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (Boolean.class.getSimpleName().equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (Float.class.getSimpleName().equals(type)) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (Double.class.getSimpleName().equals(type)) {
            float result = sp.getFloat(key,
                    Float.parseFloat(String.valueOf(defaultObject)));
            return Double.parseDouble(String.valueOf(result));
        } else if (Long.class.getSimpleName().equals(type)) {
            return sp.getLong(key, (Long) defaultObject);
        } else if (ArrayList.class.getSimpleName().equals(type)) {
            List datalist = new ArrayList();
            String strJson = sp.getString(key, null);
            if (null == strJson) {
                return datalist;
            }
            Gson gson = new Gson();
            datalist = gson.fromJson(strJson, new TypeToken<List>() {
            }.getType());
            return datalist;
        }
        return defaultObject;
    }
}
