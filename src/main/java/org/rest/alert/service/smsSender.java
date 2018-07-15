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
//import org.rest.alert.dao.messageData;



class smsBase{
    private Account account;
    private PostMsg postmsg;

    public void init(String name, String password, String gw_down_ip, int gw_down_port, String gw_up_ip, int gw_up_port){
        Account ac = new Account(name, password);
        PostMsg pm = new PostMsg();
        pm.getCmHost().setHost(gw_down_ip, gw_down_port);
        pm.getWsHost().setHost(gw_up_ip, gw_up_port);
        this.setAccount(ac);
        this.setPostmsg(pm);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public PostMsg getPostmsg() {
        return postmsg;
    }

    public void setPostmsg(PostMsg postmsg) {
        this.postmsg = postmsg;
    }
}

public class smsSender extends smsBase {


    public void init(){
        Account ac = new Account("admin", "123456");
        PostMsg pm = new PostMsg();
        pm.getWsHost().setHost("127.0.0.1", 8080);
        pm.getCmHost().setHost("127.0.0.1", 8080);
        this.setAccount(ac);
        this.setPostmsg(pm);
    }

    public void sms_biz_sendsms(smsSender smser ,String content, ArrayList<String> sendto){
        if (smser != null){
            smser = new smsSender();
            smser.init();
        }
        smser.doSendSMS(smser.getAccount(), smser.getPostmsg(), content, sendto);
    }

    public void doSendSMS(Account ac, PostMsg pm, String content, ArrayList<String> sendto){

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
        System.out.println(msgs);

        //		/** 组发，多号码多内容 */
//		pack.setSendType(SendType.GROUP);
//		msgs.add(new MessageData("13430258111", "短信组发测试111"));
//		msgs.add(new MessageData("13430258222", "短信组发测试222"));
//		msgs.add(new MessageData("13430258333", "短信组发测试333"));
//		pack.setMsgs(msgs);

        // 设置模板编号(静态模板将以模板内容发送; 动态模板需要发送变量值，JSON格式：[{"key":"变量名1","value":"变量值1"},{"key":"变量名2","value":"变量值2"}])
        //pack.setTemplateNo("test");

        GsmsResponse resp = null;
        try {
            resp = pm.post(ac, pack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(resp);

    }
}
