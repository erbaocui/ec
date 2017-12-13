package com.cn.constant;

import com.cn.util.PropertiesUtil;

/**
 * Created by home on 2017/8/11.
 */
public class Jpush {


    public static final String ANDROID_APP_KEY= PropertiesUtil.getStringByKey("jpush.android.appKey", "jpush.properties");
    public static final String ANDROID_MASTER_SECRET= PropertiesUtil.getStringByKey("jpush.android.masterSecret", "jpush.properties");
    public static final String IOS_APP_KEY= PropertiesUtil.getStringByKey("jpush.ios.appKey", "jpush.properties");
    public static final String IOS_MASTER_SECRET= PropertiesUtil.getStringByKey("jpush.ios.masterSecret", "jpush.properties");
}
