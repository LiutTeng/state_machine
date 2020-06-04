package com.example.statemachine.constant;

/**
 * @author liuteng
 */
public enum OrderEvent {

    /** 准备发货 */
    READY_SEND("ready_send","准备发货"),

    /** 确认收货 */
    CONFIRM_ACC("confirm_acc", "确认收货"),

    /**退款成功 */
    REFUND_SUC("refund_suc", "退款成功");

    private String eventCode;

    private String eventDesc;

    OrderEvent(String eventCode, String eventDesc) {
        this.eventCode = eventCode;
        this.eventDesc = eventDesc;
    }

    public String getEventCode() {
        return eventCode;
    }

    public String getEventDesc() {
        return eventDesc;
    }
}
