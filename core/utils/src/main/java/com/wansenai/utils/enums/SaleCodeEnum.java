package com.wansenai.utils.enums;

import lombok.Getter;

@Getter
public enum SaleCodeEnum {

    ADD_SALE_ORDER_SUCCESS(200, "成功添加销售订单"),

    ADD_SALE_ORDER_SUCCESS_EN(200, "Successfully add sales order"),

    ADD_SALE_ORDER_ERROR(500, "系统异常，添加销售订单失败"),

    ADD_SALE_ORDER_ERROR_EN(500, "System exception, failed to add sales order"),

    UPDATE_SALE_ORDER_SUCCESS(200, "成功修改销售订单"),

    UPDATE_SALE_ORDER_SUCCESS_EN(200, "Successfully modify sales order"),

    UPDATE_SALE_ORDER_ERROR(500, "系统异常，修改销售订单失败"),

    UPDATE_SALE_ORDER_ERROR_EN(500, "System exception, failed to modify sales order"),

    DELETE_SALE_ORDER_SUCCESS(200, "成功删除销售订单"),

    DELETE_SALE_ORDER_SUCCESS_EN(200, "Successfully delete sales order"),

    DELETE_SALE_ORDER_ERROR(500, "系统异常，删除销售订单失败"),

    DELETE_SALE_ORDER_ERROR_EN(500, "System exception, failed to delete sales order"),

    ADD_SALE_SHIPMENTS_SUCCESS(200, "成功添加销售出库单据"),

    ADD_SALE_SHIPMENTS_SUCCESS_EN(200, "Successfully add sales outbound document"),

    ADD_SALE_SHIPMENTS_ERROR(500, "系统异常，添加销售出库单据失败"),

    ADD_SALE_SHIPMENTS_ERROR_EN(500, "System exception, failed to add sales outbound document"),

    UPDATE_SALE_SHIPMENTS_SUCCESS(200, "成功修改销售出库单据"),

    UPDATE_SALE_SHIPMENTS_SUCCESS_EN(200, "Successfully modify sales outbound document"),

    UPDATE_SALE_SHIPMENTS_ERROR(500, "系统异常，修改销售单据单据失败"),

    UPDATE_SALE_SHIPMENTS_ERROR_EN(500, "System exception, failed to modify sales outbound document"),

    DELETE_SALE_SHIPMENTS_SUCCESS(200, "成功删除销售出库单据"),

    DELETE_SALE_SHIPMENTS_SUCCESS_EN(200, "Successfully delete sales outbound document"),

    DELETE_SALE_SHIPMENTS_ERROR(500, "系统异常，删除销售出库单据失败"),

    DELETE_SALE_SHIPMENTS_ERROR_EN(500, "System exception, failed to delete sales outbound document"),

    ADD_SALE_REFUND_SUCCESS(200, "成功添加销售退货单据"),

    ADD_SALE_REFUND_SUCCESS_EN(200, "Successfully add sales return document"),

    ADD_SALE_REFUND_ERROR(500, "系统异常，添加销售退货单据失败"),

    ADD_SALE_REFUND_ERROR_EN(500, "System exception, failed to add sales return document"),

    UPDATE_SALE_REFUND_SUCCESS(200, "成功修改销售退货单据"),

    UPDATE_SALE_REFUND_SUCCESS_EN(200, "Successfully modify sales return document"),

    UPDATE_SALE_REFUND_ERROR(500, "系统异常，修改销售退货单据失败"),

    UPDATE_SALE_REFUND_ERROR_EN(500, "System exception, failed to modify sales return document"),

    DELETE_SALE_REFUND_SUCCESS(200, "成功删除销售退货单据"),

    DELETE_SALE_REFUND_SUCCESS_EN(200, "Successfully delete sales return document"),

    DELETE_SALE_REFUND_ERROR(500, "系统异常，删除销售退货单据失败"),

    DELETE_SALE_REFUND_ERROR_EN(500, "System exception, failed to delete sales return document");

    /**
     * 响应状态码
     */
    private final int code;

    /**
     * 响应提示
     */
    private final String msg;

    SaleCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
