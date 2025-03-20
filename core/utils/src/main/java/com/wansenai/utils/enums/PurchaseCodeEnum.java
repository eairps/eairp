package com.wansenai.utils.enums;

import lombok.Getter;

@Getter
public enum PurchaseCodeEnum {

    ADD_PURCHASE_ORDER_SUCCESS(200, "成功添加采购订单"),

    ADD_PURCHASE_ORDER_SUCCESS_EN(200, "Successfully add purchase order"),

    ADD_PURCHASE_ORDER_ERROR(500, "系统异常，添加采购订单失败"),

    ADD_PURCHASE_ORDER_ERROR_EN(500, "System exception, failed to add purchase order"),

    UPDATE_PURCHASE_ORDER_SUCCESS(200, "成功修改采购订单"),

    UPDATE_PURCHASE_ORDER_SUCCESS_EN(200, "Successfully modify purchase order"),

    UPDATE_PURCHASE_ORDER_ERROR(500, "系统异常，修改采购订单失败"),

    UPDATE_PURCHASE_ORDER_ERROR_EN(500, "System exception, failed to modify purchase order"),

    DELETE_PURCHASE_ORDER_SUCCESS(200, "成功删除采购订单"),

    DELETE_PURCHASE_ORDER_SUCCESS_EN(200, "Successfully delete purchase order"),

    DELETE_PURCHASE_ORDER_ERROR(500, "系统异常，删除采购订单失败"),

    DELETE_PURCHASE_ORDER_ERROR_EN(500, "System exception, failed to delete purchase order"),

    ADD_PURCHASE_RECEIPT_SUCCESS(200, "成功添加采购入库单据"),

    ADD_PURCHASE_RECEIPT_SUCCESS_EN(200, "Successfully add purchase receipt document"),

    ADD_PURCHASE_RECEIPT_ERROR(500, "系统异常，添加采购入库单据失败"),

    ADD_PURCHASE_RECEIPT_ERROR_EN(500, "System exception, failed to add purchase receipt document"),

    UPDATE_PURCHASE_RECEIPT_SUCCESS(200, "修改采购入库单据成功"),

    UPDATE_PURCHASE_RECEIPT_SUCCESS_EN(200, "Successfully modify purchase receipt document"),

    UPDATE_PURCHASE_RECEIPT_ERROR(500, "系统异常，修改采购入库单据失败"),

    UPDATE_PURCHASE_RECEIPT_ERROR_EN(500, "System exception, failed to modify purchase receipt document"),

    DELETE_PURCHASE_RECEIPT_SUCCESS(200, "成功删除采购入库单据"),

    DELETE_PURCHASE_RECEIPT_SUCCESS_EN(200, "Successfully delete purchase receipt document"),

    DELETE_PURCHASE_RECEIPT_ERROR(500, "系统异常，删除采购入库单据失败"),

    DELETE_PURCHASE_RECEIPT_ERRORS_EN(500, "System exception, failed to delete purchase receipt document"),

    ADD_PURCHASE_REFUND_SUCCESS(200, "成功添加采购退货单据"),

    ADD_PURCHASE_REFUND_SUCCESS_EN(200, "Successfully add purchase return document"),

    ADD_PURCHASE_REFUND_ERROR(500, "系统异常，添加采购退货单据失败"),

    ADD_PURCHASE_REFUND_ERROR_EN(500, "System exception, failed to add purchase return document"),

    UPDATE_PURCHASE_REFUND_SUCCESS(200, "成功修改采购退货单据"),

    UPDATE_PURCHASE_REFUND_SUCCESS_EN(200, "Successfully modify purchase return document"),

    UPDATE_PURCHASE_REFUND_ERROR(500, "系统异常，修改采购退货单据失败"),

    UPDATE_PURCHASE_REFUND_ERROR_EN(500, "System exception, failed to modify purchase return document"),

    DELETE_PURCHASE_REFUND_SUCCESS(200, "成功删除采购退货单据"),

    DELETE_PURCHASE_REFUND_SUCCESS_EN(200, "Successfully delete purchase return document"),

    DELETE_PURCHASE_REFUND_ERROR(500, "系统异常，删除采购退货单据失败"),

    DELETE_PURCHASE_REFUND_ERROR_EN(500, "System exception, failed to delete purchase return document");

    /**
     * 响应状态码
     */
    private final int code;

    /**
     * 响应提示
     */
    private final String msg;

    PurchaseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
