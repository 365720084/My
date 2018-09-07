package com.fancy.showmedata.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * User: Smile(lijianhy1990@gmail.com)
 * Date: 2016-06-07
 * Time: 15:23
 */
public class ParallaxPagerTransformer implements ViewPager.PageTransformer {

    private boolean isSpeedReverse;
    private int[] resIds;
    private float speedEffect;
    private float distanceEffect;

    public ParallaxPagerTransformer(float speed, float distance,
                                    int[] resIds, boolean isSpeedReverse) {
        this.isSpeedReverse = isSpeedReverse;
        this.resIds = resIds;
        this.speedEffect = speed;
        this.distanceEffect = distance;
        if (isSpeedReverse)
            this.speedEffect *= -1;
    }

    @Override
    public void transformPage(View page, float position) {
        float moveLength = page.getWidth() * speedEffect;
        for (int i = 0; i < resIds.length; i++) {
            View view = page.findViewById(resIds[i]);
            if (view != null) {
                view.setTranslationX(moveLength * position);
            }
            moveLength *= distanceEffect;
        }

    }


}
