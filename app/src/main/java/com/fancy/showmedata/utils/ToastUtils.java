package com.fancy.showmedata.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fancy.showmedata.utils.log.LogUtils;

import java.util.Timer;
import java.util.TimerTask;


public class ToastUtils {
    private static Toast mToast = null;

    public enum Type {
        SUCCESS, WARNING, ERROR
    }

    public static void showToast(Context context, String text, int duration) {
        if ((context instanceof Activity) && ((Activity) context).isFinishing()){
            return ;
        }
        DialerToast.showMessage(context,text,duration);
    }

    public static void showToastCenter(Context context, String text) {
        if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
            return;
        }
        DialerToast.showMessageCenter(context,text);
    }

    public static void showToast(Context context, String text) {
        showToast(context, text, Type.SUCCESS);
    }

    public static void showToast(Context context, String text, Type type) {
        if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
            return;
        }
        if (context == null) {
            LogUtils.w("context is null");
            return;
        }
        DialerToast.showMessage(context,text, Toast.LENGTH_SHORT);


    }




}
