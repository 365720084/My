package com.ptg.ptgapi.model;

public class PtgImage {
    private int a;
    private int b;
    private String c;

    public PtgImage(int var1, int var2, String var3) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    public int getHeight() {
        return this.a;
    }

    public int getWidth() {
        return this.b;
    }

    public String getImageUrl() {
        return this.c;
    }

    public boolean isValid() {
        return this.a > 0 && this.b > 0 && this.c != null && this.c.length() > 0;
    }
}
