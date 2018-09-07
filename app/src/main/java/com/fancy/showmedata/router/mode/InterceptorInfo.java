package com.fancy.showmedata.router.mode;

import java.util.ArrayList;

/**
 * Created by bruce on 2018/3/1.
 */

public class InterceptorInfo {
    private String title;
    private String text;
    private ArrayList<SelectInfo> select;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<SelectInfo> getSelect() {
        return select;
    }

    public void setSelect(ArrayList<SelectInfo> select) {
        this.select = select;
    }

    public class SelectInfo{
        private String text;
        private String url;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
