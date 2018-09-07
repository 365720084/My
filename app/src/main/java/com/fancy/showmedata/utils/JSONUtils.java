package com.fancy.showmedata.utils;

import android.content.Context;

import com.fancy.showmedata.application.App;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.fancy.showmedata.utils.log.LogUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON序列化辅助类
 **/
public class JSONUtils {

    private static Gson gson;

    private JSONUtils() {
    }

    private static void init() {
        if (gson == null) {
            gson = new Gson();
        }
    }

    public static String toJSON(Object obj) {
        init();
        try {
            return gson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toObj(String json, Class<T> clz) {
        init();
        try {
            return gson.fromJson(json, clz);
        } catch (Exception e) {
            if (App.getAppContext() != null) {
                String s = "JsonException:" + e.getCause();
                LogUtils.e(s);
//                MobclickAgent.reportError(App.getAppContext()(), s);
            }
            e.printStackTrace();
        }
        return null;
    }


    public static <T> List<T> toListObj(String str, Class<T> type) {
        init();
        ArrayList<T> list = new ArrayList<>();
        JsonParser parser = new JsonParser();
        try {
            JsonArray jarray = parser.parse(str).getAsJsonArray();
            for (JsonElement obj : jarray) {
                T item = gson.fromJson(obj, type);
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getJsonFromAsset(Context context, String assetFileName) {
        /*获取到assets文件下的TExt.json文件的数据，并以输出流形式返回。*/
//        InputStream is = context.getClass().getClassLoader().getResourceAsStream("assets/" + assetFileName + ".json");
        StringBuilder stringBuilder = null;
        try {
            InputStream is =context.getAssets().open(assetFileName+".json");
            InputStreamReader streamReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // stringBuilder.append(line);
                stringBuilder.append(line);
            }
            reader.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(stringBuilder == null){
                return "";
            }else{
                return stringBuilder.toString();
            }
        }
    }

}
