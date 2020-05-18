package com.ptg.tt.utils;

import android.media.Image;

import com.bytedance.sdk.openadsdk.FilterWord;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTImage;
import com.ptg.adsdk.lib.constants.ImageMode;
import com.ptg.adsdk.lib.constants.InteractionType;
import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.model.PtgFilterWord;
import com.ptg.adsdk.lib.model.PtgImage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

public class Transformer {

    public static PtgImage TTImage2PtgImage(TTImage img) {
        if (img == null) {
            return null;
        }

        return new PtgImage(img.getWidth(), img.getHeight(), img.getImageUrl());
    }

    public static int PtgInteractionType(int ttInteractionType) {
        switch (ttInteractionType) {
            case 2:
            case 3:
                return InteractionType.LANDING_PAGE;
            case 4:
                return InteractionType.APP_DOWNLOAD;
            case 5:
                return InteractionType.PHONE_CALL;
            default:
                return InteractionType.UNKNOWN;
        }
    }

    public static int PtgImageMode(int ttImageMode) {
        switch (ttImageMode) {
            case 2 : return ImageMode.SMALL_IMAGE;
            case 3 : return ImageMode.BIG_IMAGE;
            case 4 : return ImageMode.GROUP_IMAGE;
            case 5 : return ImageMode.VIDEO;
            default:
                return ImageMode.UNKNOWN;
        }
    }

    public static TTAppDownloadListener TTAppDownloadListener(final PtgAppDownloadListener listener) {
        return new TTAppDownloadListener() {
            @Override
            public void onIdle() {
                listener.onIdle();
            }

            @Override
            public void onDownloadActive(long l, long l1, String s, String s1) {
                listener.onDownloadActive(l, l1, s, s1);
            }

            @Override
            public void onDownloadPaused(long l, long l1, String s, String s1) {
                listener.onDownloadPaused(l, l1, s, s1);
            }

            @Override
            public void onDownloadFailed(long l, long l1, String s, String s1) {
                listener.onDownloadFailed(l, l1, s, s1);
            }

            @Override
            public void onDownloadFinished(long l, String s, String s1) {
                listener.onDownloadFinished(l, s, s1);
            }

            @Override
            public void onInstalled(String s, String s1) {
                listener.onInstalled(s, s1);
            }
        };
    }

    public static PtgFilterWord ptgFilterWord(FilterWord filterWord) {
        PtgFilterWord ptgFilterWord = new PtgFilterWord();

        ptgFilterWord.setId(filterWord.getId());
        ptgFilterWord.setName(filterWord.getName());
        ptgFilterWord.setIsSelected(filterWord.getIsSelected());

        for (FilterWord fw : filterWord.getOptions()) {
            ptgFilterWord.addOption(ptgFilterWord(fw));
        }

        return ptgFilterWord;
    }

    public static List<PtgFilterWord> ptgFilterWordList(List<FilterWord> filterWord) {
        List<PtgFilterWord> result = new ArrayList<>();

        for (FilterWord item : filterWord) {
            result.add(ptgFilterWord(item));
        }

        return result;
    }
}
