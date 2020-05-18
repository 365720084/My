package com.ptg.adsdk.lib.model;

import android.text.TextUtils;


import java.util.ArrayList;
import java.util.List;

public class PtgFilterWord {
    private String id;
    private String name;
    private boolean selected;
    private List<PtgFilterWord> options;

    public PtgFilterWord() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = name;
    }

    public boolean getIsSelected() {
        return this.selected;
    }

    public void setIsSelected(boolean selected) {
        this.selected = selected;
    }

    public List<PtgFilterWord> getOptions() {
        return this.options;
    }

    public void addOption(PtgFilterWord filterWord) {
        if (filterWord != null) {
            if (this.options == null) {
                this.options = new ArrayList<>();
            }

            this.options.add(filterWord);
        }
    }

    public boolean isValid() {
        return !TextUtils.isEmpty(this.id) && !TextUtils.isEmpty(this.name);
    }

    public boolean hasSecondOptions() {
        return this.options != null && !this.options.isEmpty();
    }
}
