package org.rest.alert.service;

import com.csg.itms.edic.util.client.EDIClient;
import com.csg.itms.edic.util.client.MsgHandler;
import com.csg.itms.edic.util.message.Request;
import com.csg.itms.edic.util.message.Response;
import java.util.HashMap;
import java.lang.String;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.rest.alert.dao.messageBean;
import org.rest.alert.service.configure;


@Service
public class sender {
    private static Logger logger = Logger.getLogger(sender.class);
    public static int send(messageBean message, configure con) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("CLASSNAME",message.getClassName());     //约定为安全审计系统集成大类
        map.put("SCENE",message.getScene());             //以启明星辰为例
        map.put("TIME",message.getTime());               //本消息生成时间
        map.put("AREACODE", message.getAreaCode());      //单位以超高压输电公司本部为例
        map.put("MAINDATA", message.getMainData());      //告警主数据，"AlarmID=$SOC平台告警ID"
        map.put("ID", message.getId());                  //按照ITSM的配置项编码标准
        map.put("OriginalID", message.getOriginalID());     //按照ITSM的配置项编码标准
        map.put("IPAddress", message.getiPAddress());    //产生告警配置项的IP地址
        map.put("AlarmID", message.getAlarmID());        //SOC平台的告警ID
        map.put("AlarmCate", message.getAlarmCate());    //安全告警
        map.put("AlarmLevel", message.getAlarmLevel());  //重大告警
        map.put("AlarmType", message.getAlarmType());    //描述告警
        map.put("AlarmContent", message.getAlarmContent());   //告警内容描述
        map.put("AlarmStatus", message.getAlarmStatus());     //未确认状态
        map.put("FirstTime", message.getFirstTime());         //告警开始时间
        map.put("EndTime", message.getEndTime());             //告警结束时间
        map.put("AlarmCount", message.getAlarmCount());       //告警重复次数
                       //组装告警消息内容
        String app_id = System.getenv("APP_ID");
        String mq_address = System.getenv("MQ_ADDRESS");

        if (System.getenv("MQ_PORT") != null){
            int mq_port = Integer.parseInt(System.getenv("MQ_PORT"));
            if (mq_port != 0){
                con.setPort(mq_port);
            }
        }
        String app_password = System.getenv("APP_PASSWORD");
        String bus_subject = System.getenv("BUS_SUBJECT");
        try {
            if (app_id != null){
                con.setId(app_id);
            }

            if (mq_address != null){
                con.setAddress(mq_address);
            }

            if (app_password != null){
                con.setPassword(app_password);
            }
            if (bus_subject != null){
                con.setSubject(bus_subject);
            }
        }catch (Exception e){
            logger.error("there is a error occur when init configure:" + e);
        }
        try {
            EDIClient client = new EDIClient(con.getAddress(),con.getPort()) ;
//总线IP地址，端口，建议做成可配置，各个现场根据实际情况进行配置
            Response sessionId = client.authenticate(con.getId(),con.getPassword());
//authenticate方法第一个参数:总线用户名，第二个参数:总线密码，默认为空
            String sessionId_str = sessionId.toString();
            Request r = new Request();
            r.setContent(map);
            r.setSessionId(sessionId_str);
            r.setSubject(con.getSubject());//设置总线发送队列名称
            //总线队列名称，按照文档各章节约定的队列发送即可
            r = MsgHandler.sealMessage(r);
            Response response = client.sendMesssage(r);
            logger.info("sended to data bus " + response.isSuccessful());
            if (!response.isSuccessful()) {
                logger.error("失败的错误码：" + response.getMessageid());
                logger.error("失败的错误信息:" + response.getResultHint());
            }
        } catch (Exception e){
            logger.error("there is a mistake of sending.");
        }
        return 0;
    }

}
