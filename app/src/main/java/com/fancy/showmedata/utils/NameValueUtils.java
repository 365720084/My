package com.fancy.showmedata.utils;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Smile(lijianhy1990@gmail.com)
 * Date: 2015-09-23
 * Time: 14:55
 */
public class NameValueUtils implements Serializable {

    private List<NameValuePair> params;

    private NameValueUtils() {
        params = new ArrayList<>();
    }

    public static NameValueUtils init() {
        return new NameValueUtils();
    }

    public NameValueUtils append(@NonNull String name, String value) {
        if (params == null) {
            params = new ArrayList<>();
        }
        if (value == null)
            value = "";
        params.add(new NameValuePair(name, value));
        return this;
    }

    public NameValueUtils append(@NonNull String name, int value) {
        return append(name, String.valueOf(value));
    }

    public NameValueUtils append(@NonNull String name, float value) {
        return append(name, String.valueOf(value));
    }

    public NameValueUtils append(@NonNull String name, double value) {
        return append(name, String.valueOf(value));
    }

    public NameValueUtils append(@NonNull String name, long value) {
        return append(name, String.valueOf(value));
    }

    public List<NameValuePair> build() {
        return params;
    }

    public static class NameValuePair implements Comparable<NameValuePair>,Serializable {
        private String name;
        private String value;

        public NameValuePair(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            if (name == null)
                return "";
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            if (value == null)
                return "";
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public int compareTo(@NonNull NameValuePair another) {
            if (name == null)
                return 0;
            return name.compareTo(another.getName());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            NameValuePair that = (NameValuePair) o;

            return name != null ? name.equals(that.name) : that.name == null;

        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }
}
