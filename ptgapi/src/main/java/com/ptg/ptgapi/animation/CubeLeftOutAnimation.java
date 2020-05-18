package com.ptg.ptgapi.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.ptg.adsdk.lib.utils.Logger;

/**
 * https://blog.csdn.net/csdn_aiyang/article/details/56672419
 */
public class CubeLeftOutAnimation extends Animation{
    private Camera mCamera;
    private Matrix mMatrix;
    private int mWidth;
    private int mHeight;
    private static final int sFinalDegree = 90;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
        mMatrix = new Matrix();
        mWidth = width;
        mHeight = height;
    }


    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        float rotate = (sFinalDegree - sFinalDegree * interpolatedTime);
        mCamera.save();
        mCamera.translate((-mWidth * interpolatedTime), 0, 0);
        mCamera.rotateY(rotate);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();

        mMatrix.postTranslate(mWidth, mHeight / 2);
        mMatrix.preTranslate(0, -mHeight / 2);
        Logger.w("rotate",rotate+"");



        t.getMatrix().postConcat(mMatrix);
    }
}
