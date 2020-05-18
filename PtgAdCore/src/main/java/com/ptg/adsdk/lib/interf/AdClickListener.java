package com.ptg.adsdk.lib.interf;

import android.view.View;

import androidx.annotation.MainThread;

public interface AdClickListener  extends View.OnClickListener {
    void onClick(View v);
}
