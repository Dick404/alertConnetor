package org.rest.alert.service;

import java.net.ConnectException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.UUID;

import com.esms.PostMsg;
import javafx.geometry.Pos;
import org.junit.Test;

import com.esms.common.entity.Account;
import com.esms.common.entity.BusinessType;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;
import com.esms.common.entity.MTReport;
import com.esms.common.entity.MTResponse;
import com.esms.common.util.MediaUtil;
import com.esms.MessageData;
import org.apache.log4j.Logger;
//import org.apache.logging.log4j.Logger;
//import org.rest.alert.dao.messageData;

public class smsSender{

    public static Logger logger = Logger.getLogger(smsSender.class);

    public void start(String content, ArrayList<String> sendto, String flag, String platform)throws Exception{
        Account ac = new Account("mas_dxypt", "zQC6f$7z");
        PostMsg pm = new PostMsg();
        pm.getWsHost().setHost("10.91.1.151", 8090);
        pm.getCmHost().setHost("10.91.1.151", 8088);
        sms_biz_sendsms(ac, pm, content, sendto, flag, platform);
    }

    public void sms_biz_sendsms(Account ac ,PostMsg pm,String content, ArrayList<String> sendto, String flag, String platform)throws Exception{
        content = platform + "::" + content;
        doSendSMS(ac, pm, content, sendto);
    }

    public void doSendSMS(Account ac, PostMsg pm, String content, ArrayList<String> sendto) throws Exception{

        MTPack pack = new MTPack();
        pack.setBatchID(UUID.randomUUID());
        pack.setBatchName("云平台告警发送");
        pack.setMsgType(MTPack.MsgType.SMS);
        pack.setBizType(0);
        pack.setDistinctFlag(false);
        ArrayList<MessageData> msgs = new ArrayList<MessageData>();

        pack.setSendType(MTPack.SendType.MASS);
        for (int i=0; i< sendto.size(); i++){
            msgs.add(new MessageData(sendto.get(i), content));
        }
//        msgs.add(new messageData("13430258111", content));
//        msgs.add(new messageData("13430258222", content));
//        msgs.add(new messageData("13430258333", content));
        pack.setMsgs(msgs);
        System.out.println("message content is " + msgs.get( 0 ));
        System.out.println(ac);
        System.out.println(pm);
        System.out.println( pack );
        //		/** 组发，多号码多内容 */
//		pack.setSendType(SendType.GROUP);
//		msgs.add(new MessageData("13430258111", "短信组发测试111"));
//		msgs.add(new MessageData("13430258222", "短信组发测试222"));
//		msgs.add(new MessageData("13430258333", "短信组发测试333"));
//		pack.setMsgs(msgs);

        // 设置模板编号(静态模板将以模板内容发送; 动态模板需要发送变量值，JSON格式：[{"key":"变量名1","value":"变量值1"},{"key":"变量名2","value":"变量值2"}])
        //pack.setTemplateNo("test");


        //GsmsResponse resp = pm.post( ac, pack);
        //System.out.println("connection result is " + resp);
    }
}
