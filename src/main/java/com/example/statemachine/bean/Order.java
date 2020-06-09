package com.example.statemachine.bean;

import java.util.Date;

/**
 * @author liuteng
 */
public class Order {

    /** 订单号 */
    private String oid;

    /** 订单类型 */
    private String type;

    /** 订单来源 */
    private String source;

    /** 订单渠道 */
    private String channel;

    /** 订单提交时间 */
    private Date orderTime;

    /** 支付时间 */
    private Date payTime;

    /** 订单状态 */
    private Integer status;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
