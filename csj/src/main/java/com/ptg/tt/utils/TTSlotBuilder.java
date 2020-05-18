package com.ptg.tt.utils;

import com.ptg.adsdk.lib.model.AdSlot;

public class TTSlotBuilder {
    public static com.bytedance.sdk.openadsdk.AdSlot Build(AdSlot slot) {
        com.bytedance.sdk.openadsdk.AdSlot.Builder builder = new com.bytedance.sdk.openadsdk.AdSlot.Builder();
        builder.setCodeId(slot.getCodeId())
                .setImageAcceptedSize(slot.getImgAcceptedWidth(), slot.getImgAcceptedHeight())
                .setExpressViewAcceptedSize(slot.getExpressViewAcceptedWidth(), slot.getExpressViewAcceptedHeight())
                .setIsAutoPlay(slot.isAutoPlay());
        return builder.build();
    }
}
