package com.mariomz.e_plan.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by MarioMz on 2017/2/25/ 0025.
 * Function：吐司工具类
 * ContactInfo:QQ-980390613
 */
public class ToastUtils {


    public ToastUtils(){
        super();
    }

    public static void show(Context ctx,String str){
        Toast.makeText(ctx,str,Toast.LENGTH_SHORT).show();
    }
}
