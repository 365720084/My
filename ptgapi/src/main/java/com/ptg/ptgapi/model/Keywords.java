package com.ptg.ptgapi.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Keywords {
    private String channel_id;
    private String title_id;
    private List<String> keywords;

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getTitle_id() {
        return title_id;
    }

    public void setTitle_id(String title_id) {
        this.title_id = title_id;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return getJson().toString();
    }

    public JSONObject getJson() {
        JSONObject object = new JSONObject();
        try {
            object.put("channel_id", channel_id);
            object.put("title_id", title_id);
            if (keywords != null) {
                JSONArray array = new JSONArray();
                for (String keyword : keywords) {
                    array.put(keyword);
                }
                object.put("keywords", array);
            }
        } catch (JSONException ignored) {
        }
        return object;
    }
}
