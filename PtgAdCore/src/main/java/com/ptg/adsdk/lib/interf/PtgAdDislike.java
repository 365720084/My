package com.ptg.adsdk.lib.interf;


public interface PtgAdDislike  {
    void showDislikeDialog();

    void setDislikeInteractionCallback(DislikeInteractionCallback var1);

    public interface DislikeInteractionCallback {
        void onSelected(int code, String message);

        void onCancel();
    }
}
