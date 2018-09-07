package com.fancy.showmedata.utils.countdown;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Meng on 2018/1/22.
 */

public enum TimeUtil {
    INSTANCE;

    private SimpleDateFormat format;
    private StringBuilder builder;
    /**
     * 传入时间戳
     */
    public long getLongTime(String str){
        try{
            if (format == null){
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            }
            return format.parse(str).getTime();
        }catch (Exception e){
            Log.e("TimeUtil",e.getMessage());
        }
        return 0;
    }

    public String getStrTime(long time){
        try{
            if (builder == null){
                builder = new StringBuilder();
            }
            builder.delete(0,builder.length());
            long hours = time/1000/3600;
            long minutes = time/1000%3600/60;
            long seconds = time/1000%60;
            if (hours<=9){
                builder.append(0);
            }
            builder.append(hours);
            builder.append(":");

            if (minutes<=9){
                builder.append(0);
            }
            builder.append(minutes);
            builder.append(":");

            if (seconds<=9){
                builder.append(0);
            }
            builder.append(seconds);
            return builder.toString();
        }catch (Exception e){
            Log.e("TimeUtil",e.getMessage());
        }
        return null;
    }

    public  char[] getTimeArray(long time){
        char[] s;
        try{
            if (builder == null){
                builder = new StringBuilder();
            }
            builder.delete(0,builder.length());
            long hours = time/1000/3600;
            long minutes = time/1000%3600/60;
            long seconds = time/1000%60;
            if (hours<=9){
                builder.append(0);
            }
            builder.append(hours);
//            builder.append(":");

            if (minutes<=9){
                builder.append(0);
            }
            builder.append(minutes);
//            builder.append(":");

            if (seconds<=9){
                builder.append(0);
            }
            builder.append(seconds);
            String timeS=builder.toString();
            s=timeS.toCharArray();
            return s;
        }catch (Exception e){
            Log.e("TimeUtil",e.getMessage());
        }
        return null;
    }
}
