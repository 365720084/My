package com.ptg.adsdk.lib.core.util;

import android.text.TextUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class ContainerUtils {
    public static boolean isEmptyCollection(Collection<? extends Object> collection) {
        return collection == null || collection.size() <= 0;
    }

    public static boolean isEmptyMap(Map<? extends Object, ? extends Object> map) {
        return map == null || map.size() <= 0;
    }

    public static boolean isEmptyString(String str) {
        return TextUtils.isEmpty(str);
    }

    public static <T> boolean isEmptyArray(T[] array) {
        return array == null || array.length <= 0;
    }

    public static boolean isEmptyArray(Object array) {
        return array == null || !array.getClass().isArray() || Array.getLength(array) <= 0;
    }

    public static boolean isEmptyObject(Object o) {
        if (o instanceof Collections) {
            return isEmptyCollection((Collection) o);
        }
        if (o instanceof Map) {
            return isEmptyMap((Map) o);
        }
        if (o instanceof String) {
            if (((String) o).length() > 0) {
                return false;
            }
            return true;
        } else if (o.getClass().isArray()) {
            return isEmptyArray(o);
        } else {
            if (o != null) {
                return false;
            }
            return true;
        }
    }

    public static Object primitiveArrayUnboxing(Object array) {
        if (!isEmptyArray(array)) {
            Class<?> c = array.getClass().getComponentType();
            Class<?> premitiveType = null;
            if (Integer.class == c) {
                premitiveType = Integer.TYPE;
            } else if (Boolean.class == c) {
                premitiveType = Boolean.TYPE;
            } else if (Double.class == c) {
                premitiveType = Double.TYPE;
            } else if (Long.class == c) {
                premitiveType = Long.TYPE;
            }
            if (premitiveType != null) {
                int arrLen = Array.getLength(array);
                Object newInstance = Array.newInstance(premitiveType, arrLen);
                for (int counter = 0; counter < arrLen; counter++) {
                    Array.set(newInstance, counter, Array.get(array, counter));
                }
                return newInstance;
            }
        }
        return array;
    }

    public static <T> T safeArrayGet(Object array, int index, T defaultValue) {
        if (isEmptyArray(array) || !array.getClass().isArray() || Array.getLength(array) < index) {
            return defaultValue;
        }
        T o = (T) Array.get(array, index);
        if (o != null) {
            return o;
        }
        return defaultValue;
    }
}
