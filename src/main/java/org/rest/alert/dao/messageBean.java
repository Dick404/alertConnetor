package org.rest.alert.dao;

import java.io.Serializable;

public class messageBean {
    private String areaCode; //单位编码
    private String id;      //资产编号
    private String originalID;  // 原系统ID 非必填
    private String iPAddress;   // 告警IP
    private String alarmID;     // 告警编号
    private String alarmCate;   // 告警分类
    private String alarmType;   // 告警类型
    private String alarmLevel;  // 告警级别
    private String alarmContent;   // 告警内容 需转义中文
    private String alarmStatus;   // 告警状态
    private String firstTime;     // 告警首次发生时间
    private String endTime;       // 告警最后发生时间
    private String alarmCount;    // 告警重复发生次数
    private String closeTime;     // 告警关闭时间
    private String orderID;       // 告警处理工单  非必填
    private String className;     // 类型名称
    private String scene;         // 厂家英文名称
    private String time;          // 消息发送时间
    private String mainData;      // 主数据 AlarmID =$告警ID

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalID() {
        return originalID;
    }

    public void setOriginalID(String originalID) {
        this.originalID = originalID;
    }

    public String getiPAddress() {
        return iPAddress;
    }

    public void setiPAddress(String iPAddress) {
        this.iPAddress = iPAddress;
    }

    public String getAlarmID() {
        return alarmID;
    }

    public void setAlarmID(String alarmID) {
        this.alarmID = alarmID;
    }

    public String getAlarmCate() {
        return alarmCate;
    }

    public void setAlarmCate(String alarmCate) {
        this.alarmCate = alarmCate;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAlarmCount() {
        return alarmCount;
    }

    public void setAlarmCount(String alarmCount) {
        this.alarmCount = alarmCount;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMainData() {
        return mainData;
    }

    public void setMainData(String mainData) {
        this.mainData = mainData;
    }
}
