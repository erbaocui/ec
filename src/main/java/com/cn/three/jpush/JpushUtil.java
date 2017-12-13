package com.cn.three.jpush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import com.cn.constant.Jpush;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by home on 2017/12/13.
 */
public class JpushUtil {

    //极光推送>>Android
    //Map<String, String> p
    public static void jpushAndroid(String title,String content,String msgid) throws Exception{
        // 设置好账号的app_key和masterSecret
        String appKey = Jpush.ANDROID_APP_KEY;
        String masterSecret = Jpush.ANDROID_MASTER_SECRET;
        //创建JPushClient
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        //推送的关键,构造一个payload
        Message msg=Message.newBuilder().setTitle(title).setMsgContent(content).addExtra("msgid",msgid).build();
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())//指定android平台的用户
                .setAudience(Audience.all())//你项目中的所有用户
                //.setOptions(Options.newBuilder().setApnsProduction(true).build())

               .setMessage(msg)//自定义信息
                .build();

       // try {
            //PushResult pu = jpushClient.sendPush(payload);
            PushResult pu = jpushClient.sendPush(payload);

      /*  } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }*/
    }
    public static void main(String[] args){
        Map<String,String > param=new HashMap<String,String>();

       /// JpushUtil.jpushAndroid(param);

    }
}
