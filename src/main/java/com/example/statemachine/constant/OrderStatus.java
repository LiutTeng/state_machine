package com.example.statemachine.constant;

/**
 * @author liuteng
 */
public enum  OrderStatus {



    /** 已支付 */
    PAID(20),

    /** 已发货 */
    SEND(40),

    /** 确认收货 */
    RECEIVE(60),

    /** 已退款 */
    RETURN(80);


    private Integer code;

    OrderStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static OrderStatus getOrderStatus(Integer code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}
