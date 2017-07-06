package com.mariomz.e_plan.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by MarioMz on 2017/3/10/ 0010.
 * Function：缓存工具类
 * ContactInfo:QQ-980390613
 */
public class CacheUtils {


    public final static String IS_MAIN = "is_main";


    public static void putBooolean(Context ctx,String key,boolean value) {
        SharedPreferences sp = ctx.getSharedPreferences(IS_MAIN, Context.MODE_PRIVATE);
        sp.edit().putBoolean("IS_MAIN",value).apply();
    }

    public static boolean getBoolean(Context ctx,String key) {
        SharedPreferences sp = ctx.getSharedPreferences(IS_MAIN, Context.MODE_PRIVATE);
        return sp.getBoolean("IS_MAIN",false);
    }


    /**保存数据--String
     * @param ctx
     * @param key
     * @param value
     */
    public static void putString(Context ctx,String key,String value) {
        SharedPreferences sp = ctx.getSharedPreferences("NewsCache",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).apply();
    }

    /**获取数据--String
     * @param ctx
     * @param key
     * @return
     */
    public static String getString(Context ctx,String key){
        SharedPreferences sp = ctx.getSharedPreferences("NewsCache",Context.MODE_PRIVATE);
        //TODO 【2017-6-5】【特别注意不要返回null，防止程序崩溃】
        return sp.getString(key,"");
    }

    /**得到整型数据---播放模式
     * @param ctx
     * @param key
     * @return
     */
    public static int getInt(Context ctx,String key) {
        SharedPreferences sp = ctx.getSharedPreferences("audio_play_mode", Context.MODE_PRIVATE);
//        return sp.getInt(key, MusicService.PEPEAT_NORMAL);
        return 0;
    }

    /**得到整型数据---播放模式
     * @param ctx
     * @param key
     * @param value
     */
    public static void setInt(Context ctx,String key,int value) {
        SharedPreferences sp = ctx.getSharedPreferences("audio_play_mode", Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).apply();
    }
}
