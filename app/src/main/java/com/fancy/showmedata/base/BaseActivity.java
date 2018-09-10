package com.fancy.showmedata.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.bugly.crashreport.CrashReport;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.KITKAT;


/**
 * Created by hengfengtian on 8/12/15.
 */
public abstract class BaseActivity extends RxFragmentActivity  {
    private static final String TAG = "BaseActivity";
    public static final String AMOUNT_UPDATED = "AMOUNT_UPDATED";
    private int mStartSeconds;
    protected Activity mActivity;
    private Rect rect;
    protected Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mStartSeconds = (int) System.currentTimeMillis() / 1000;
        refreshData();
    }

    protected void refreshData() {

    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        PushManager.getInstance().stopService(this);

        if (keyboardListenersAttached) {
            rootLayout.getViewTreeObserver().removeGlobalOnLayoutListener(keyboardLayoutListener);
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }




    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    public class WeakHandler extends Handler {
        private WeakReference<Context> reference;

        public WeakHandler(Context activity) {
            // 持有 Activity 的弱引用
            reference = new WeakReference<Context>(activity);
        }
    }

    public void backItemClick() {
        onBackPressed();
    }

    protected <T extends View> T findsViewById(int id) {
        //return返回view时,加上泛型T
        return (T) findViewById(id);
    }




    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            final View decorView = getWindow().getDecorView();
            Rect rect = new Rect();
            decorView.getWindowVisibleDisplayFrame(rect);
            int displayHight = rect.bottom - rect.top;
            int hight = decorView.getHeight();
            boolean visible = (double) displayHight / hight < 0.95;
            if (!visible) {
                onHideKeyboard();
            } else {
                int keyboardHeight = hight - displayHight;
                onShowKeyboard(keyboardHeight);
            }

        }
    };

    public void controlKeyboardLayout(final View root, final View editLayout) {
        // TODO Auto-generated method stub
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                if (rect == null) {
                    rect = new Rect();
                }
                //获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInVisibleHeigh = root.getRootView().getHeight() - (rect.bottom);
                int screenHeight = root.getRootView().getHeight();//屏幕高度
                //若不可视区域高度大于100，则键盘显示
                if (rootInVisibleHeigh > screenHeight / 3) {
//                    Log.v("hjb", "不可视区域高度大于100，则键盘显示");
                    int[] location = new int[2];
                    //获取editLayout在窗体的坐标
                    editLayout.getLocationInWindow(location);
                    //计算root滚动高度，使editLayout在可见区域
                    int srollHeight = (location[1] + editLayout.getHeight()) - rect.bottom;
//                    editLayout.scrollTo(0, srollHeight);
                    Log.d("hjb", "rootInVisibleHeigh" + rootInVisibleHeigh + "");
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) editLayout.getLayoutParams();
                    params.setMargins(0, 0, 0, rootInVisibleHeigh - getBottomKeyboardHeight());
                    editLayout.setLayoutParams(params);
                } else {
                    //键盘隐藏
//                    Log.v("hjb", "不可视区域高度小于100，则键盘隐藏");
//                    editLayout.scrollTo(0, 0);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) editLayout.getLayoutParams();
                    params.setMargins(0, 0, 0, 0);
                    editLayout.setLayoutParams(params);
                }
            }
        });
    }

    /**
     * 获取底部虚拟键盘的高度
     */
    public int getBottomKeyboardHeight() {
        int screenHeight = getAccurateScreenDpi()[1];
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int heightDifference = screenHeight - dm.heightPixels;
        return heightDifference;
    }

    /**
     * 获取精确的屏幕大小
     */
    public int[] getAccurateScreenDpi() {
        int[] screenWH = new int[2];
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            Class<?> c = Class.forName("android.view.Display");
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            screenWH[0] = dm.widthPixels;
            screenWH[1] = dm.heightPixels;
        } catch (Exception e) {
            CrashReport.postCatchedException(e);
            e.printStackTrace();
        }
        return screenWH;
    }

    protected void onShowKeyboard(int keyboardHeight) {
    }

    protected void onHideKeyboard() {
    }

    private boolean keyboardListenersAttached = false;
    private ViewGroup rootLayout;

    protected void attachKeyboardListeners() {
        if (keyboardListenersAttached) {
            return;
        }
//        rootLayout = (ViewGroup) findViewById(R.id.root);
        rootLayout.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);
        keyboardListenersAttached = true;
    }

    public static void fixInputMethodManagerLeak(Context destContext) {

        if (SDK_INT < KITKAT || SDK_INT > 22) {
            return;
        }

        if (destContext == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f = null;
        Object obj_get = null;
        for (int i = 0; i < arr.length; i++) {
            String param = arr[i];
            try {
                f = imm.getClass().getDeclaredField(param);
                if (f == null) {
                    return;
                }
                f.setAccessible(true);
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                        break;
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
