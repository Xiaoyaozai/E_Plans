package com.mariomz.e_plan.utils;

import android.content.Context;

/**
 * Created by MarioMz on 2017/3/15/ 0015.
 * Function：根据手机的分辨率，实时的转换像素与dp
 * ContactInfo:QQ-980390613
 */
public class DensityUtils {

    /**根据手机的分辨率，从 dip 单位转换成 px
     * @param ctx
     * @param dpValue dip
     * @return px
     */
    public static int dip2Px(Context ctx,float dpValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }

    /**根据手机的分辨率，从 px 单位转换成 dip
    * @param ctx
    * @param pxValue px
    * @return dip
     */
    public static int px2Dip(Context ctx,float pxValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }
}
