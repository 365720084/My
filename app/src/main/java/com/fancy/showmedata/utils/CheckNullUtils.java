package com.fancy.showmedata.utils;

import android.app.Activity;
import android.content.Context;

/**
 * 各种对象的判空
 * Created by bruce on 2018/8/9.
 */
public class CheckNullUtils {

    public static boolean checkContextAvailable(Context context) {
        if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
            return false;
        }
        return true;
    }

}

