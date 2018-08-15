package org.rest.alert.service;

import java.net.ConnectException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;

import com.esms.common.entity.Account;
import com.esms.common.entity.BusinessType;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;
import com.esms.common.entity.MTReport;
import com.esms.common.entity.MTResponse;
import com.esms.common.util.MediaUtil;
import com.esms.PostMsg;
import com.esms.MessageData;


public class smsDemo {
    /*
    @Test
    public void test(){
        try {
            extend(String Content, String platform, String sendto); //扩展接口范例
//			compatibility(); //兼容接口范例
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
    public static void extend(String content, String platform, String sendto) throws Exception{
        Account ac = new Account("mas_dxypt", "zQC6f$7z");//
        PostMsg pm = new PostMsg();
        pm.getCmHost().setHost("10.91.1.151", 8090);//设置网关的IP和port，用于发送信息
        pm.getWsHost().setHost("10.91.1.151", 8088);//设置网关的 IP和port，用于获取账号信息、上行、状态报告等等

        content = platform + "::" + content;
//		/**代理上网设置,如果需要*/
//		HostInfo proxyHost = new HostInfo("192.168.0.47", 1080);
//		proxyHost.setType(HostInfo.ConnectionType.SOCKET4);  	//设置连接类型
//		proxyHost.setUsername("004");						//设置用户名
//		proxyHost.setPassword("123");							//设置密码
//		pm.getProxy().setProxy(proxyHost);					//设置代理

        doSendSms(pm, ac, content, sendto); //短信下行
//		doSendMms(pm, ac); //彩信下行

//		doGetAccountInfo(pm, ac); //获取账号信息
//		doModifyPwd(pm, ac); //修改密码

//		doFindResps(pm, ac); //查询提交报告
//		doFindReports(pm, ac); //查询状态报告

//		doGetMos(pm, ac); //获取上行信息
//		doGetResps(pm, ac); //获取提交报告
//		doGetReports(pm, ac); //获取状态报告
    }

    /**
     * 短信下发范例
     * @param pm
     * @param ac
     * @param content
     * @param sendto
     */
    public static void doSendSms(PostMsg pm, Account ac, String content, String sendto) throws Exception{
        MTPack pack = new MTPack();
        pack.setBatchID(UUID.randomUUID());
        pack.setBatchName("云平台告警通知");
        pack.setMsgType(MTPack.MsgType.SMS);
        pack.setBizType(0);
        pack.setDistinctFlag(false);
        ArrayList<MessageData> msgs = new ArrayList<MessageData>();

//		/** 单发，一号码一内容 */
//		msgs.add(new MessageData("13430258111", "短信单发测试"));
//		pack.setMsgs(msgs);

        /** 群发，多号码一内容 */
        pack.setSendType(MTPack.SendType.MASS);
        //String content = "短信群发测试";
        msgs.add(new MessageData(sendto, content));
        //msgs.add(new MessageData("13430258222", content));
        //msgs.add(new MessageData("13430258333", content));
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

        /** 设置模板编号(静态模板将以模板内容发送; 动态模板需要发送变量值，JSON格式：[{"key":"变量名1","value":"变量值1"},{"key":"变量名2","value":"变量值2"}]) */
        //pack.setTemplateNo("test");

        GsmsResponse resp = pm.post(ac, pack);
        System.out.println(resp);
    }

}
