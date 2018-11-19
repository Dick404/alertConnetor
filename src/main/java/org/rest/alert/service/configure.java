package org.rest.alert.service;

import org.springframework.beans.factory.annotation.Value;
import org.rest.alert.dao.messageBean;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class configure {
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

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getSubject() {
        return subject;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "configure{" +
                "address='" + address + '\'' +
                ", port=" + port +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }

    public void init(String address, int port, String appId, String password, String subject){
        this.setPort(port);
        this.setSubject(subject);
        this.setPassword(password);
        this.setAddress(address);
        this.setId(appId);
    }

    public static String configuration(messageBean message){


        try{
            System.out.println( message.getPlatform().getBytes("UTF-8") );
        }catch (Exception e){
            System.out.println( e );
        }
        String content = "报警平台为： " + message.getPlatform() + "\r\n";
        content += "告警ID： " + message.getAlarmID() + "\r\n";
        content += "告警触发器： " + message.getSubject() + "\r\n";
        content += "告警主机： " + message.getHost() + "\r\n";
        content += "告警主机IP： " + message.getiPAddress() + "\r\n";
        content += "告警等级： " + message.getAlarmLevel() + "\r\n";
        content += "告警内容： " + message.getAlarmContent() + "\r\n";
        content += "告警时间： " + message.getTime() + "\r\n";
        return content;
    }

}



