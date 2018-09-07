package com.fancy.showmedata.utils;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by sun on 15/11/2.
 */
public class PrintScreenUtils {

    public static void printView(View view, ScreenCallBack listener) {
        view.post(new PrintScreenThread(view, listener));
    }

    public static class PrintScreenThread implements Runnable {

        private View rootView;
        private ScreenCallBack callable;

        public PrintScreenThread(View view, ScreenCallBack callable) {
            this.rootView = view;
            this.callable = callable;
        }

        @Override
        public void run() {
            if (rootView.isHardwareAccelerated()) {
                rootView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
            // 允许当前窗口保存缓存信息
            rootView.setDrawingCacheEnabled(true);
            rootView.setDrawingCacheBackgroundColor(0xffffffff);
            rootView.buildDrawingCache();
            Bitmap bitmap = rootView.getDrawingCache();
            callable.onScreenBitmapGet(bitmap);
            rootView.setDrawingCacheEnabled(false);
        }
    }

    public interface ScreenCallBack {
        void onScreenBitmapGet(Bitmap bitmap);
    }


}
