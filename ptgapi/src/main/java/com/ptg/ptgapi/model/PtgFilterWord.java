package com.ptg.ptgapi.model;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class PtgFilterWord {
    private String a;
    private String b;
    private boolean c;
    private List<PtgFilterWord> d;

    public PtgFilterWord() {
    }

    public String getId() {
        return this.a;
    }

    public void setId(String var1) {
        this.a = var1;
    }

    public String getName() {
        return this.b;
    }

    public void setName(String var1) {
        this.b = var1;
    }

    public boolean getIsSelected() {
        return this.c;
    }

    public void setIsSelected(boolean var1) {
        this.c = var1;
    }

    public List<PtgFilterWord> getOptions() {
        return this.d;
    }

    public void addOption(PtgFilterWord var1) {
        if (var1 != null) {
            if (this.d == null) {
                this.d = new ArrayList();
            }

            this.d.add(var1);
        }
    }

    public boolean isValid() {
        return !TextUtils.isEmpty(this.a) && !TextUtils.isEmpty(this.b);
    }

    public boolean hasSecondOptions() {
        return this.d != null && !this.d.isEmpty();
    }
}
