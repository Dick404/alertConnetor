package org.rest.alert.controller;

import com.esms.common.entity.GsmsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.rest.alert.service.sender;
import org.rest.alert.dao.messageBean;
import org.rest.alert.service.configure;
import org.rest.alert.service.smsSender;
import org.rest.alert.service.smsDemo;

import java.util.ArrayList;

@RestController
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class baseController {
    @Value("${mq.address}")
    private String address;

    @Value("${mq.port}")
    private int port;

    @Value("${app.id}")
    private String id;

    @Value("${app.password}")
    private String password;

    @Value("${bus.subject}")
    private String subject;

    @Value("${auth.name}")
    private String name;

    @Value("${auth.password}")
    private String passwd;

    @Value("${address.down.ip}")
    private String gw_down_ip;

    @Value("${address.down.port}")
    private int gw_down_port;

    @Value("${address.up.ip}")
    private String gw_up_ip;

    @Value("${address.up.port}")
    private int gw_up_port;

    @RequestMapping("/")
    public String base(){
        return "200";
    }

    @RequestMapping(value = "/alert", method = RequestMethod.POST)
    public int connector(@RequestBody messageBean messagebean){
        //return "hello world~";
        configure con = new configure();
        con.init(address, port, id, password, subject);
        sender message = new sender();
        int value = message.send( messagebean, con);
        if (value >= 1){
            return value;
        }
        else {
            return 0;
        }
    }

    @RequestMapping(value = "/sender", method = RequestMethod.POST)
    public void smsController(@RequestBody messageBean messageBean) throws Exception{
        smsSender smser = new smsSender();
        ArrayList<String> E = new ArrayList<String>();
        E.add(messageBean.getSendto());
        smser.start( messageBean.getAlarmContent(), E, messageBean.getAlarmLevel(), messageBean.getPlatform() );
    }

    @RequestMapping(value = "/sms", method = RequestMethod.POST)
    public void demoController(@RequestBody messageBean messageBean) throws Exception {
        String content = configure.configuration(messageBean);
        System.out.println( "测试数据" + messageBean + "\n" );
        smsDemo.extend(content, messageBean.getSendto());
    }
}
