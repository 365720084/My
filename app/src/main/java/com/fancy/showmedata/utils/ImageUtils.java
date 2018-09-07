/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fancy.showmedata.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.text.TextUtils;


import com.fancy.showmedata.application.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {
    // public static String getThumbnailImagePath(String imagePath) {
    // String path = imagePath.substring(0, imagePath.lastIndexOf("/") + 1);
    // path += "th" + imagePath.substring(imagePath.lastIndexOf("/") + 1,
    // imagePath.length());
    // EMLog.d("msg", "original image path:" + imagePath);
    // EMLog.d("msg", "thum image path:" + path);
    // return path;
    // }

    public static String getImagePath(String remoteUrl) {
        String imageName = remoteUrl.substring(remoteUrl.lastIndexOf("/") + 1,
                remoteUrl.length());
        return Constants.PATH_IMAGE_CACHE + "/" + imageName;
    }

    public static String getThumbnailImagePath(String thumbRemoteUrl) {
        String thumbImageName = thumbRemoteUrl.substring(
                thumbRemoteUrl.lastIndexOf("/") + 1, thumbRemoteUrl.length());
        return Constants.PATH_IMAGE_CACHE + "/" + "th" + thumbImageName;
    }

    // 计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap getSmallBitmap(String filePath) {
        return getSmallBitmap(filePath, 480, 800);
    }

    public static Bitmap getSmallBitmap(String filePath, int w, int h) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, w, h);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    public static Bitmap getSmallBitmap(Resources res, int id, int w, int h) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, id, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, w, h);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(res, id, options);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static String compressImage(Context context, String filePath, int q) throws FileNotFoundException {

        Bitmap bm = getSmallBitmap(filePath);

        int degree = readPictureDegree(filePath);

        if (degree != 0) {// 旋转照片角度
            bm = rotateBitmap(bm, degree);
        }

        File imageDir = new File(Constants.PATH_IMAGE_TEMP);
        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File outputFile = new File(imageDir, fileName);

        FileOutputStream out = new FileOutputStream(outputFile);

        bm.compress(Bitmap.CompressFormat.JPEG, q, out);

        return outputFile.getPath();
    }

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int degrees) {
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(degrees);
            Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), m, true);
            bitmap.recycle();
            return newBitmap;
        }
        return null;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static File saveBitmap(Bitmap mBitmap, String bitName) {
        return saveBitmap(mBitmap, Constants.PATH_IMAGE_TEMP, bitName);
    }

    public static File saveBitmap(Bitmap mBitmap, String dir, String bitName) {
        return saveBitmap(mBitmap, dir, bitName, 70);
    }

    public static File saveBitmap(Bitmap mBitmap, String dir, String bitName, int q) {
        File imageDir = new File(dir);
        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }
        File f = new File(imageDir, bitName);
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return f;
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG, q, fOut);
        try {
            if (fOut != null) {
                fOut.flush();
                fOut.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        mBitmap.recycle();
        return f;
    }

    public static String rotateBitmap(String path, BitmapFactory.Options options) {
        if (TextUtils.isEmpty(path)) {
            return path;
        }
        File file = new File(path);
        if (!file.exists()) {
            return path;
        }
        int degree = readPictureDegree(path);
        if (degree == 0)
            return path;
        // 旋转照片角度
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            if (bitmap == null) {
                return path;
            }
            bitmap = rotateBitmap(bitmap, degree);
            if (bitmap == null)
                return path;
            File f = saveBitmap(bitmap, System.currentTimeMillis() + ".jpg");
            return f.getAbsolutePath();
        } catch (OutOfMemoryError e) {
            if (options == null) {
                options = new BitmapFactory.Options();
                options.inSampleSize = 2;
            } else {
                options.inSampleSize *= 2;
            }
            return rotateBitmap(path, options);
        }
    }

    public static String rotateBitmap(String path) {
        return rotateBitmap(path, null);
    }

    public static Bitmap scaleBitmap(Bitmap src, float xScale, float yScale) {
        Matrix matrix = new Matrix();
        matrix.postScale(xScale, yScale); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
        if (!src.isMutable() && xScale == 0 && yScale == 0 && matrix.isIdentity()) {
            return src;
        }
        src.recycle();
        return resizeBmp;
    }

    public static void updateGallery(Context context, String filename) {
        MediaScannerConnection.scanFile(context, new String[]{filename}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                    }
                });
    }

    public static final Bitmap greyBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap faceIconGreyBitmap = Bitmap
                .createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(faceIconGreyBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0f);
        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
                colorMatrix);
        paint.setColorFilter(colorMatrixFilter);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return faceIconGreyBitmap;
    }

}
