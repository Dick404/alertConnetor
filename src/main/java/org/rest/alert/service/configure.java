package org.rest.alert.service;

import org.springframework.beans.factory.annotation.Value;

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
}
