package com.fancy.fancyapi.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.fancy.adsdk.lib.model.AppInfo;
import com.fancy.adsdk.lib.model.FancyFilterWord;

import java.util.ArrayList;

public class FancyDislikeListView extends ListView {
    private AppInfo appInfo;
    private OnItemClickListener onItemClickListener;
    private OnItemClickListener c;

    public FancyDislikeListView(Context var1) {
        super(var1);
        this.c = new NamelessClass_1();

        this.init();
    }

    public FancyDislikeListView(Context var1, AttributeSet var2) {
        super(var1, var2);
        this.c = new NamelessClass_1();
        this.init();
    }

    public FancyDislikeListView(Context var1, AttributeSet var2, int var3) {
        super(var1, var2, var3);


        this.c = new NamelessClass_1();
        this.init();
    }

    class NamelessClass_1 implements OnItemClickListener {


        public void onItemClick(AdapterView<?> var1, View var2, int var3, long var4) {
            if (FancyDislikeListView.this.getAdapter() != null && FancyDislikeListView.this.getAdapter().getItem(var3) != null && FancyDislikeListView.this.getAdapter().getItem(var3) instanceof FancyFilterWord) {
                FancyFilterWord var6 = (FancyFilterWord) FancyDislikeListView.this.getAdapter().getItem(var3);
                if (!var6.hasSecondOptions()) {
                    ArrayList var7 = new ArrayList();
                    var7.add(var6);
                    if (FancyDislikeListView.this.appInfo != null) {
                            //估计是上报点击事件
//                            w.getAdManager(FancyDislikeListView.this.appInfo, var7);
                    }
                }

                try {
                    if (FancyDislikeListView.this.onItemClickListener != null) {
                        FancyDislikeListView.this.onItemClickListener.onItemClick(var1, var2, var3, var4);
                    }
                } catch (Throwable var8) {
                }

            } else {
                throw new IllegalArgumentException("adapter数据异常，必须为FilterWord");
            }
        }
    }
    public void setMaterialMeta(AppInfo var1) {
        this.appInfo = var1;
    }

    private void init() {
        super.setOnItemClickListener(this.c);
    }

    public void setOnItemClickListener(@Nullable OnItemClickListener var1) {
        this.onItemClickListener = var1;
    }


}
