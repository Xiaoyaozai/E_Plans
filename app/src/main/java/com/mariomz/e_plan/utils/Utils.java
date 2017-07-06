package com.mariomz.e_plan.utils;

import android.content.Context;
import android.net.TrafficStats;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

public class Utils {

    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;

    private long lastTotalRxBytes = 0;
    private long lastTimeStamp = 0;

    public Utils() {
        // 转换成字符串的时间
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

    }

    /**
     * 把毫秒转换成：1:20:30这里形式
     * @param timeMs
     * @return
     */
    public String stringForTime(int timeMs) {
        int totalSeconds = timeMs / 1000;
        int seconds = totalSeconds % 60;

        int minutes = (totalSeconds / 60) % 60;

        int hours = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds)
                    .toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    /**判断是否是网络资源
     * @param uri
     * @return
     */
    public boolean isNetUri(String uri){
        boolean result = false;
        if (uri != null) {
            //常见的三种网络传输协议
            if (uri.toLowerCase().startsWith("http")||uri.toLowerCase().startsWith("rtsp")||uri.toLowerCase().startsWith("mms")) {
                result = true;
            }
        }
        return result;
    }

    /**
      *得到当前的网速
      *@param context
      *@return
      */
    public String getNetSpeed(Context context) {

        long nowTotalRxBytes = TrafficStats.getUidRxBytes(context.getApplicationInfo().uid)== TrafficStats.UNSUPPORTED?0:(TrafficStats.getTotalRxBytes()/1024);//转为KB;
        long nowTimeStamp =System.currentTimeMillis();
        long speed = ((nowTotalRxBytes- lastTotalRxBytes)* 1000/ (nowTimeStamp - lastTimeStamp));//毫秒转换

        lastTimeStamp = nowTimeStamp;
        lastTotalRxBytes = nowTotalRxBytes;

        String speedStr = String.valueOf(speed) +" kb/s";
        return speedStr;
    }

    /**获取当前系统时间
     * @return
     */
    public static String getSystemTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }


}
