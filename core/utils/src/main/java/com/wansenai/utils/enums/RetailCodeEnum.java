package com.wansenai.utils.enums;

import lombok.Getter;

@Getter
public enum RetailCodeEnum {

    ADD_RETAIL_SHIPMENTS_SUCCESS(200, "成功添加零售出库单据"),

    ADD_RETAIL_SHIPMENTS_SUCCESS_EN(200, "Successfully add retail outbound document"),

    ADD_RETAIL_SHIPMENTS_ERROR(500, "系统异常，添加零售出库单据失败"),

    ADD_RETAIL_SHIPMENTS_ERROR_EN(500, "System exception, failed to add retail outbound document"),

    UPDATE_RETAIL_SHIPMENTS_SUCCESS(200, "成功修改零售出库单据"),

    UPDATE_RETAIL_SHIPMENTS_SUCCESS_EN(200, "Successfully modify retail outbound document"),

    UPDATE_RETAIL_SHIPMENTS_ERROR(500, "系统异常，修改零售出库单据失败"),

    UPDATE_RETAIL_SHIPMENTS_ERROR_EN(500, "System exception, failed to modify retail outbound document"),

    DELETE_RETAIL_SHIPMENTS_SUCCESS(200, "成功删除零售出库单据"),

    DELETE_RETAIL_SHIPMENTS_SUCCESS_EN(200, "Successfully delete retail outbound document"),

    DELETE_RETAIL_SHIPMENTS_ERROR(500, "系统异常，删除零售出库单据失败"),

    DELETE_RETAIL_SHIPMENTS_ERROR_EN(500, "System exception, failed to delete retail outbound document"),

    ADD_RETAIL_REFUND_SUCCESS(200, "成功添加零售退货单据"),

    ADD_RETAIL_REFUND_SUCCESS_EN(200, "Successfully add retail return document"),

    ADD_RETAIL_REFUND_ERROR(500, "系统异常，添加零售退货单据失败"),

    ADD_RETAIL_REFUND_ERROR_EN(500, "System exception, failed to add retail return document"),

    UPDATE_RETAIL_REFUND_SUCCESS(200, "成功修改零售退货单据"),

    UPDATE_RETAIL_REFUND_SUCCESS_EN(200, "Successfully modify retail return document"),

    UPDATE_RETAIL_REFUND_ERROR(500, "系统异常，修改零售退货单失败"),

    UPDATE_RETAIL_REFUND_ERROR_EN(500, "System exception, failed to modify retail return document"),

    DELETE_RETAIL_REFUND_SUCCESS(200, "成功删除零售货单据"),

    DELETE_RETAIL_REFUND_SUCCESS_EN(200, "Successfully delete retail return document"),

    DELETE_RETAIL_REFUND_ERROR(500, "系统异常，删除零售货单据失败"),

    DELETE_RETAIL_REFUND_ERROR_EN(500, "System exception, failed to delete retail return document");


    /**
     * 响应状态码
     */
    private final int code;

    /**
     * 响应提示
     */
    private final String msg;

    RetailCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
