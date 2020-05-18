package com.ptg.adsdk.lib.model;

public class PtgImage {
    private int width;
    private int height;
    private String imgUrl;

    public PtgImage(int width, int height, String imgUrl) {
        this.width = width;
        this.height = height;
        this.imgUrl = imgUrl;
    }

    public int getHeight() {
        return this.width;
    }

    public int getWidth() {
        return this.height;
    }

    public String getImageUrl() {
        return this.imgUrl;
    }

    public boolean isValid() {
        return true;
//        return this.width > 0 && this.height > 0 && this.imgUrl != null && this.imgUrl.length() > 0;
    }
}
