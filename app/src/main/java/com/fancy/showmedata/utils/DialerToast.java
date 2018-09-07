package com.fancy.showmedata.utils;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.fancy.showmedata.R;
import com.fancy.showmedata.application.App;


public class DialerToast {

    private static Handler sHandler = new Handler();
    private static Runnable sRunnable = new Runnable() {
        @Override
        public void run() {
            sToast = null;
            sMessage = null;
            sIsShowing = false;
        }
    };

    private static boolean sIsShowing = false;
    private static Toast sToast = null;
    private static String sMessage;

    public static final int SHORT_DELAY = 1000;
    public static final int LONG_DELAY = 3000;

    public static void showMessage(final Context act, final int msgRes, final int len) {
        String msg = act.getString(msgRes);
        showMessage(act, msg, len);
    }

    public static void showMessage(final String msg, final  int len) {
        showMessage(App.getAppContext(), msg, len);
    }

    public static void showMessage(final Context act, final String msg, final int len) {
        showMessage(act, msg, len, Gravity.BOTTOM, 0, act.getResources().getDimensionPixelSize(
                R.dimen.toast_y_offset));
    }

    public static void showMessage(final Context act, final int msgRes, final int len,
                                   final int gravity, final int xOffset, final int yOffset) {
        String msg = act.getString(msgRes);
        showMessage(act, msg, len, gravity, xOffset, yOffset);
    }

    public static void showMessageCenter(final Context act, final String msg) {
        showMessage(act, msg, DialerToast.SHORT_DELAY, Gravity.CENTER_VERTICAL, 0, 0);
    }

    public static void showMessage(final Context act, final String msg, final int len,
                                   final int gravity, final int xOffset, final int yOffset) {
        if (sToast != null && !TextUtils.isEmpty(sMessage)) {
            if (sMessage.equals(msg)) {
                return;
            }
            sToast.cancel();
            sToast = null;
            sMessage = null;
            sIsShowing = false;
            sHandler.removeCallbacks(sRunnable);
        }
        if (!sIsShowing) {
            sMessage = msg;
            sToast = Toast.makeText(act, msg, len);
            sToast.setGravity(gravity, xOffset, yOffset);
            sToast.show();
            sIsShowing = true;
            int delay = len < Toast.LENGTH_LONG ? SHORT_DELAY : LONG_DELAY;
            sHandler.postDelayed(sRunnable, delay);
        }
    }

    public static void reset() {
        sToast = null;
        sMessage = null;
        sIsShowing = false;
    }

}