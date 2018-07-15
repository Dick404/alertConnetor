package org.rest.alert.dao;


public class messageData {
    private String name;
    private String password;
    private String gw_down_ip;
    private int gw_down_port;
    private String gw_up_ip;
    private int gw_up_port;
    public String sendto;
    public String alert_content;

    public messageData(String sendto, String alert_content) {
        this.sendto = sendto;
        this.alert_content = alert_content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGw_down_ip() {
        return gw_down_ip;
    }

    public void setGw_down_ip(String gw_down_ip) {
        this.gw_down_ip = gw_down_ip;
    }

    public int getGw_down_port() {
        return gw_down_port;
    }

    public void setGw_down_port(int gw_down_port) {
        this.gw_down_port = gw_down_port;
    }

    public String getGw_up_ip() {
        return gw_up_ip;
    }

    public void setGw_up_ip(String gw_up_ip) {
        this.gw_up_ip = gw_up_ip;
    }

    public int getGw_up_port() {
        return gw_up_port;
    }

    public void setGw_up_port(int gw_up_port) {
        this.gw_up_port = gw_up_port;
    }

    public String getSendto() {
        return sendto;
    }

    public void setSendto(String sendto) {
        this.sendto = sendto;
    }

    public String getAlert_content() {
        return alert_content;
    }

    public void setAlert_content(String alert_content) {
        this.alert_content = alert_content;
    }

    @Override
    public String toString() {
        return "messageData{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gw_down_ip='" + gw_down_ip + '\'' +
                ", gw_down_port=" + gw_down_port +
                ", gw_up_ip='" + gw_up_ip + '\'' +
                ", gw_up_port=" + gw_up_port +
                ", sendto='" + sendto + '\'' +
                ", alert_content='" + alert_content + '\'' +
                '}';
    }
}
