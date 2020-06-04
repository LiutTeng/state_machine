package com.example.statemachine.bean;

import java.util.Date;

/**
 * @author liuteng
 */
public class Order {

    /** 订单号 .*/
    private String oid;

    /** 订单提交时间.*/
    private Date orderTime;

    /** 支付时间.*/
    private Date payTime;

    /** 订单状态 */
    private Integer status;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
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
