package com.ptg.adsdk.lib.base;

public class PtgLocation {
    private double a = 0.0D;
    private double b = 0.0D;

    public PtgLocation(double var1, double var3) {
        this.a = var1;
        this.b = var3;
    }

    public double getLatitude() {
        return this.a;
    }

    public void setLatitude(double var1) {
        this.a = var1;
    }

    public double getLongitude() {
        return this.b;
    }

    public void setLongitude(double var1) {
        this.b = var1;
    }
}
