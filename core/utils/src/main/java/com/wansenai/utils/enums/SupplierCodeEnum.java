package com.wansenai.utils.enums;


import lombok.Getter;

@Getter
public enum SupplierCodeEnum {

    ADD_SUPPLIER_SUCCESS(200, "成功添加供应商"),

    ADD_SUPPLIER_SUCCESS_EN(200, "Successfully add supplier"),

    ADD_SUPPLIER_ERROR(500, "系统异常，供应商添加失败"),

    ADD_SUPPLIER_ERROR_EN(500, "System exception, failed to add supplier"),

    UPDATE_SUPPLIER_SUCCESS(200, "成功修改供应商信息"),

    UPDATE_SUPPLIER_SUCCESS_EN(200, "Successfully modify supplier information"),

    UPDATE_SUPPLIER_ERROR(500, "系统异常，供应商信息修改失败"),

    UPDATE_SUPPLIER_ERROR_EN(500, "System exception, failed to modify supplier information"),

    DELETE_SUPPLIER_SUCCESS(200, "成功删除供应商"),

    DELETE_SUPPLIER_SUCCESS_EN(200, "Successfully delete supplier"),

    DELETE_SUPPLIER_ERROR(500, "系统异常，供应商删除失败"),

    DELETE_SUPPLIER_ERROR_EN(500, "System exception, failed to delete supplier"),

    UPDATE_SUPPLIER_STATUS_SUCCESS(200, "成功修改供应商状态"),

    UPDATE_SUPPLIER_STATUS_SUCCESS_EN(200, "Successfully modify supplier status"),

    UPDATE_SUPPLIER_STATUS_ERROR(500, "系统异常，供应商状态修改失败"),

    UPDATE_SUPPLIER_STATUS_ERROR_EN(500, "System exception, failed to modify supplier status"),;

    /**
     * 响应状态码
     */
    private final int code;

    /**
     * 响应提示
     */
    private final String msg;

    SupplierCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
