package com.fancy.fancyapi.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.IntRange;

import com.fancy.adsdk.lib.constants.AdConstant;
import com.fancy.adsdk.lib.interf.PermissionListener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


/**
 * Created by Meng on 2018/3/2.
 */

public enum ShakeUtil implements PermissionListener {
    INSTANCE;
    private SensorManager sensorManager;
    private ShakeListener shakeListener;
    AngelChangeListener angelChangeListener;
    public  static int SHAKE=5;//触发的临界角度
    /**
     * 上一次检测的角度
     */
    int mLastDegreeY;
    int fancyType;
//    public void setGameShakeListener(GameShakeListener gameShakeListener) {
//        this.gameShakeListener = gameShakeListener;
//    }

    public void setAngelChangeListener(AngelChangeListener angelChangeListener) {
        this.angelChangeListener = angelChangeListener;
    }

    private Vibrator vibrator;
    private Context context;
    private Sensor magneticSensor, accelerometerSensor;
    private float[] values, r, gravity, geomagnetic;

    public void initShakeListener(Context context, int fancyType) {
        this.context = context;
        this.fancyType=fancyType;
        if (sensorManager == null) {
            if (true) {
                sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
                vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);

                //获取Sensor
                magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                shakeListener = new ShakeListener();
//                sensorManager.registerListener(shakeListener, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
                // 为方向传感器注册监听器 　第三个参数是传感器数据更新数据的速度　有以下四个值可选，他们的速度是递增的
                sensorManager.registerListener(shakeListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
                // 为陀螺仪传感器注册监听器
                sensorManager.registerListener(shakeListener, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME);
//初始化数组
                gravity = new float[3];//用来保存加速度传感器的值
                r = new float[9];//
                geomagnetic = new float[3];//用来保存地磁传感器的值
                values = new float[3];//用来保存最终的结果
            }
        }

    }

    //摇一摇监听器
    private class ShakeListener implements SensorEventListener {
        /**
         * 检测的时间间隔
         */
        static final int UPDATE_INTERVAL = 100;
        /**
         * 上一次检测的时间
         */
        long mLastUpdateTime;

        /**
         * 上一次检测时，加速度在x、ThrowException、z方向上的分量，用于和当前加速度比较求差。
         */
        float mLastX, mLastY, mLastZ;

        /**
         * 摇晃检测阈值，决定了对摇晃的敏感程度，越小越敏感。
         */
        public int shakeThreshold = 1000;

        public long time;

        @Override
        public void onSensorChanged(SensorEvent event) {
            // -90<X<0时， 90>Y>0右斜，-90<Y<0左斜；  0<x<90,90>Y>0 左斜，-90<Y<0 右斜
            long currentTime = System.currentTimeMillis();
            long diffTime = currentTime - mLastUpdateTime;
//            if (diffTime < 10) {
//                return;
//            }
            mLastUpdateTime = currentTime;
            switch (event.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                    gravity = event.values;
                    getOritation();
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    geomagnetic = event.values;
                    break;
            }



        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }

    public void getOritation() {
        // r从这里返回
        SensorManager.getRotationMatrix(r, null, gravity, geomagnetic);
        //values从这里返回
        SensorManager.getOrientation(r, values);
        //提取数据
        double degreeZ = Math.toDegrees(values[0]);
        double degreeX = Math.toDegrees(values[1]);
        int degreeY = (int) Math.toDegrees(values[2]);
        if( degreeY==mLastDegreeY){
            return;
        }
        if( Math.abs(mLastDegreeY-degreeY)<3&&fancyType== AdConstant.FANCY_SPLASH_AD_CLIP){
            return;
        }
        mLastDegreeY= degreeY;
        if (angelChangeListener != null) {
            // -90<X<0时， 90>Y>0右斜，-90<Y<0左斜；  0<x<90,90>Y>0 左斜，-90<Y<0 右斜

            if(-90<=degreeX&degreeX<=0 &&Math.abs(degreeY)<90){
                //正常手势，用户正面拿着手机，略微草上
//                degreeX=degreeX*-1;
                if(degreeY<0){
                    //左斜
//                    gameShakeListener.onshake(degreeY);
                }else if(degreeY>0) {
                    //右斜
//                    gameShakeListener.onshake(degreeY);
                }
                Log.i("onSensorChange1", degreeY +"");

                angelChangeListener.onshake(degreeY);


            }else if(-90<degreeX&degreeX<0&&Math.abs(degreeY)>90){
                //非正常姿势，仰视手机，不处理

            }
        }
//        if (angelChangeListener != null) {
//            if(-90<=degreeX&degreeX<=0 &&Math.abs(degreeY)<90){
//                Log.checkDebugOpen("onSensorChanged2", degreeX+"|"+degreeY + "|"+degreeZ);
//
//                angelChangeListener.onshake(degreeY);
//            }
//        }
    }

    //判断当前进程是否为主进程
    private boolean isProcess() {
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        return processName.equals(packageName);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    //判断当前App是否在前台运行
    private boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = context.getApplicationContext().getPackageName();
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName) && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    private void startDebugLogService() {
//        MainActivity.getSdcardPermission(this);
    }

    public void unRegistSensor() {
//        sensorManager.unregisterListener(shakeListener);
    }


    @Override
    public void PermissionSuccess() {
//        if (Build.VERSION.SDK_INT >= 23) {
//            if (Settings.canDrawOverlays(context)) {
//                context.startService( new Intent(context, DebugLogService.class));
//            } else {
//                //若没有权限，提示获取.
//                Toast.makeText(context,"需要取得权限以使用悬浮窗", Toast.LENGTH_SHORT).show();
//                context.startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION));
//            }
//        } else {
//            //SDK在23以下，不用管.
//            context.startService(new Intent(context, DebugLogService.class));
//        }
    }

    @Override
    public void PermissionFail() {
        Toast.makeText(context, "权限申请失败", Toast.LENGTH_SHORT).show();
    }


    public interface AngelChangeListener {
        @SuppressLint("SupportAnnotationUsage")
        @IntRange(from = -90, to = 90)
        void onshake(int offset);
    }
}
