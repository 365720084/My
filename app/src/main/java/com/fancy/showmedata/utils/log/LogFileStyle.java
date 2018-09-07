package com.fancy.showmedata.utils.log;

import android.support.annotation.Nullable;

import org.mym.plog.Style;

/**
 * User: Smile(lijianhy1990@gmail.com)
 * Date: 2017-03-14
 * Time: 19:27
 */
public class LogFileStyle implements Style {
    @Nullable
    @Override
    public String msgPrefix() {
        return "\n";
    }

    @Nullable
    @Override
    public String msgSuffix() {
        return "\n";
    }

    @Nullable
    @Override
    public String lineHeader() {
        return null;
    }
}
