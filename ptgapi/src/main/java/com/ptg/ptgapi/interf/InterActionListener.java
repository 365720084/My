package com.ptg.ptgapi.interf;

import android.view.View;

public interface InterActionListener {


    void onAdClicked(View view, int type);

    void onAdShow(View view, int type);

    void onAdSkip();

    void onAdTimeOver();
}
