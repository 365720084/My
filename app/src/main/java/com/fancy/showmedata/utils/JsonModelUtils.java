package com.fancy.showmedata.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public enum JsonModelUtils {
    INSTANCE;

    private Gson gson;
    private void initGson(){
        if (gson == null){
            gson = new Gson();
        }
    }
    /**
     * 获取json结果 对象中的某个属性 为对象 prefix data
     *
     * @param type
     * @param type
     * @return
     */
    public <T> T modelFrom(JSONObject jo, Class<T> type) {
        if (jo != null) {
            initGson();
            return gson.fromJson(jo.toString(), type);
        }
        return null;
    }




    public <T> T getEntityFrom(String jo, Class<? extends T> clssType) {
        if (jo != null) {
            initGson();
            return gson.fromJson(jo, clssType);
        }
        return null;
    }

    /**
     * 转换 JSON ARRAY 为 BEAN 列表
     *
     * @param jsonarray
     * @param type      获取方式 new TypeToken<clssType>() { }.getType(); 列表
     * @param <T>
     * @return
     */
    public <T> T getListEntityFrom(String jsonarray, Type type) {
        if (jsonarray != null) {
            initGson();
            return gson.fromJson(jsonarray, type);
        }
        return null;
    }

    public <T> T getEntityFrom(JSONObject jo, Class<? extends T> clssType) {
        if (jo != null) {
            initGson();
            return gson.fromJson(jo.toString(), clssType);
        }
        return null;
    }

    public Map<String, String> json2map(String str_json) {
        Map<String, String> res = null;
        try {
            initGson();
            res = gson.fromJson(str_json, new TypeToken<Map<String, String>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
        }
        return res;
    }


    /**
     * 将对象转换成Json 字符串
     *
     * @param object java 对象
     * @return
     */
    public String toJsonFromObject(Object object) {
        // 将对象编译成json
        initGson();
        return gson.toJson(object);
    }

    /**
     * 将对象转换成Json 字符串
     *
     * @param object java 对象
     * @return
     */
    public <T> String toJsonFromList(Object object, Class<? extends T> clssType) {
        // 将对象编译成json
        initGson();
        return gson.toJson(object, new TypeToken<ArrayList<T>>() {}.getType());
    }


    /**
     * 获取JsonObject
     *
     * @param json
     * @return
     */
    public JsonObject parseJson(String json) {
        JsonParser parser = new JsonParser();
        return parser.parse(json).getAsJsonObject();
    }

    /**
     * 根据json字符串返回Map对象
     *
     * @param json
     * @return
     */
    public Map<String, Object> toMap(String json) {
        return toMap(parseJson(json));
    }

    /**
     * 将JSONObjec对象转换成Map-List集合
     *
     * @param json
     * @return
     */
    public Map<String, Object> toMap(JsonObject json) {
        Map<String, Object> map = new HashMap<>();
        Set<Map.Entry<String, JsonElement>> entrySet = json.entrySet();
        for (Iterator<Map.Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ) {
            Map.Entry<String, JsonElement> entry = iter.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof JsonArray)
                map.put(key, toList((JsonArray) value));
            else if (value instanceof JsonObject)
                map.put(key, toMap((JsonObject) value));
            else
                map.put(key, value);
        }
        return map;
    }

    /**
     * 将JSONArray对象转换成List集合
     *
     * @param json
     * @return
     */
    public List<Object> toList(JsonArray json) {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            Object value = json.get(i);
            if (value instanceof JsonArray) {
                list.add(toList((JsonArray) value));
            } else if (value instanceof JsonObject) {
                list.add(toMap((JsonObject) value));
            } else {
                list.add(value);
            }
        }
        return list;
    }

    /**
     * 解析没有数据头的纯数组
     */
    public <T> ArrayList<T> parseNoHeaderJArray(String json, Class<? extends T> clazz) {

        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(json).getAsJsonArray();

        initGson();
        ArrayList<T> userBeanList = new ArrayList<>();

        //加强for循环遍历JsonArray
        for (JsonElement user : jsonArray) {
            //使用GSON，直接转成Bean对象
            T userBean = gson.fromJson(user, clazz);
            userBeanList.add(userBean);
        }
        return userBeanList;
    }

    /**
     * list转换成jsonArray字符串
     * @return
     */
    public <T> String listToJsonArray(List<? extends T> list) {
        if (list != null) {
            initGson();
            return gson.toJson(list);
        }
        return null;
    }

}

