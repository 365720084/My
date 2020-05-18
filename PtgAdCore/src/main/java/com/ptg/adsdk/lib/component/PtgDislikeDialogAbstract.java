package com.ptg.adsdk.lib.component;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.ptg.adsdk.lib.model.AppInfo;

public abstract class PtgDislikeDialogAbstract extends Dialog {
    private View view;
    private AppInfo appInfo;

    private Context context;

    public PtgDislikeDialogAbstract(@NonNull Context context) {
        super(context);
    }

    public PtgDislikeDialogAbstract(@NonNull Context context, int var2) {
        super(context, var2);
    }

    protected void onCreate(Bundle var1) {
        super.onCreate(var1);
        LayoutInflater var2 = LayoutInflater.from(this.getContext());
        this.view = var2.inflate(this.getLayoutId(), (ViewGroup)null);
        if (this.view == null) {
            throw new IllegalArgumentException("getLayoutId布局文件id可能异常，请检查");
        } else {
            ViewGroup.LayoutParams var3 = this.getLayoutParams();
            this.setContentView(this.view, var3 != null ? var3 : new ViewGroup.LayoutParams(-1, -1));
            this.init();
        }
    }

    private void init() {
        if (this.appInfo != null) {
            if (this.view != null) {
                int[] var1 = this.getPtgDislikeListViewIds();
                if (var1 != null && var1.length > 0) {
                    int[] var2 = var1;
                    int var3 = var1.length;

                    for(int var4 = 0; var4 < var3; ++var4) {
                        int var5 = var2[var4];
                        View var6 = this.view.findViewById(var5);
                        if (var6 == null) {
                            throw new IllegalArgumentException("getTTDislikeListViewIds提供的id找不到view，请检查");
                        }

                        if (!(var6 instanceof PtgDislikeListView)) {
                            throw new IllegalArgumentException("getTTDislikeListViewIds找到的view类型异常，请检查");
                        }

                        PtgDislikeListView var7 = (PtgDislikeListView)var6;
                        var7.setMaterialMeta(this.appInfo);
                    }

                } else {
                    throw new IllegalArgumentException("dislike选项列表为空，请设置TTDislikeListView");
                }
            }
        }
    }

    public void setMaterialMeta(AppInfo var1) {
        this.appInfo = var1;
        this.init();
    }

    public abstract int getLayoutId();

    public abstract int[] getPtgDislikeListViewIds();

    public abstract ViewGroup.LayoutParams getLayoutParams();
}
