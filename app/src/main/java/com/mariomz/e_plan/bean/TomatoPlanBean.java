package com.mariomz.e_plan.bean;

import java.io.Serializable;

/**
 * Created by MarioMz on 2017/6/11/ 0011.
 * Function：番茄计划的Bean
 * ContactInfo:QQ-980390613
 */

public class TomatoPlanBean implements Serializable{

    //用户自定义的【番茄计划】
    private String plan_content;
    //用户自定义的【番茄计划】的时间
    private String plan_time;
//    用户自定义的【番茄计划】的图片
//    private Drawable plan_icon;


    public String getPlan_content() {
        return plan_content;
    }

    public void setPlan_content(String plan_content) {
        this.plan_content = plan_content;
    }

    public String getPlan_time() {
        return plan_time;
    }

    public void setPlan_time(String plan_time) {
        this.plan_time = plan_time;
    }

    @Override
    public String toString() {
        return "TomatoPlanBean{" +
                "plan_content='" + plan_content + '\'' +
                ", plan_time='" + plan_time + '\'' +
                '}';
    }
}
