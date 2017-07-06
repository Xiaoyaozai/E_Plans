package com.mariomz.e_plan.utils;

/**
 * Created by MarioMz on 2017/3/29/ 0029.
 * Function：常量类，配置联网请求的地址
 * ContactInfo:QQ-980390613
 */

public class ConstantUtils {


    /**
     * 手机联WIFI 访问分段资源需要添加的地址
     */
    public static final String BASE_URL = "http://169.254.5.254:8080/web_home";


    /**
     * 本地模拟器 访问本机tomcat
     */
    public static final String BASE_URL2 = "http://10.0.2.2:8080/web_home";


    /**
     * 本地Tomcat服务器的地址
     */
    public static final String NEWSCENTER_PAGER_URL = BASE_URL + "/static/api/news/categories.json";
}
