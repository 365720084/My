package com.ptg.gdt.common;

import android.content.Context;

import com.ptg.adsdk.lib.constants.ImageMode;
import com.qq.e.comm.constants.AdPatternType;

public final class GdtAdapterHelper {
    public static int transformAdPatternType2ImageMode(int adPatternType) {
        // TODO make sure ImageMode ?
        switch (adPatternType) {
            case AdPatternType.NATIVE_2IMAGE_2TEXT: return ImageMode.SMALL_IMAGE;
            case AdPatternType.NATIVE_3IMAGE: return ImageMode.GROUP_IMAGE;
            case AdPatternType.NATIVE_VIDEO: return ImageMode.VIDEO;
            case AdPatternType.NATIVE_1IMAGE_2TEXT: return ImageMode.BIG_IMAGE;
            default: return ImageMode.UNKNOWN;
        }
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
