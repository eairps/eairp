package com.wansenai.utils.enums;

import lombok.Getter;

@Getter
public enum ProdcutCodeEnum {

    ADD_PRODUCT_CATEGORY_SUCCESS(200, "成功添加商品分类"),

    ADD_PRODUCT_CATEGORY_SUCCESS_EN(200, "Successfully add product category"),

    ADD_PRODUCT_CATEGORY_ERROR(500, "系统异常，添加商品分类失败"),

    ADD_PRODUCT_CATEGORY_ERROR_EN(500, "System exception, adding product category failed"),

    UPDATE_PRODUCT_CATEGORY_SUCCESS(200, "修改商品分类成功"),

    UPDATE_PRODUCT_CATEGORY_SUCCESS_EN(200, "Successfully modify product category"),

    UPDATE_PRODUCT_CATEGORY_ERROR(500, "系统异常，修改商品分类失败"),

    UPDATE_PRODUCT_CATEGORY_ERROR_EN(500, "System exception, failed to modify product category"),

    DELETE_PRODUCT_CATEGORY_SUCCESS(200, "成功删除商品分类"),

    DELETE_PRODUCT_CATEGORY_SUCCESS_EN(200, "Successfully delete product category"),

    DELETE_PRODUCT_CATEGORY_ERROR(500, "系统异常，删除商品分类失败"),

    DELETE_PRODUCT_CATEGORY_ERROR_EN(500, "System exception, failed to delete product category"),

    // Product Attribute Code
    PRODUCT_ATTRIBUTE_NAME_EXIST(200, "商品属性名称已存在"),

    PRODUCT_ATTRIBUTE_NAME_EXIST_EN(200, "Product attribute name already exists"),

    ADD_PRODUCT_ATTRIBUTE_SUCCESS(200, "成功添加商品属性"),

    ADD_PRODUCT_ATTRIBUTE_SUCCESS_EN(200, "Successfully add product attributes"),

    ADD_PRODUCT_ATTRIBUTE_ERROR(500, "系统异常，添加商品属性失败"),

    ADD_PRODUCT_ATTRIBUTE_ERROR_EN(500, "System exception, failed to add product attributes"),

    UPDATE_PRODUCT_ATTRIBUTE_SUCCESS(200, "成功修改商品属性"),

    UPDATE_PRODUCT_ATTRIBUTE_SUCCESS_EN(200, "Successfully modify product attributes"),

    UPDATE_PRODUCT_ATTRIBUTE_ERROR(500, "系统异常，修改商品属性失败"),

    UPDATE_PRODUCT_ATTRIBUTE_ERROR_EN(500, "System exception, failed to modify product attributes"),

    DELETE_PRODUCT_ATTRIBUTE_SUCCESS(200, "成功删除商品属性"),

    DELETE_PRODUCT_ATTRIBUTE_SUCCESS_EN(200, "Successfully delete product attributes"),

    DELETE_PRODUCT_ATTRIBUTE_ERROR(500, "系统异常，删除商品属性失败"),

    DELETE_PRODUCT_ATTRIBUTE_ERROR_EN(500, "System exception, failed to delete product attributes"),

    // Product Unit Code
    PRODUCT_COMPUTE_UNIT_EXIST(200, "商品计量单位已存在"),

    PRODUCT_COMPUTE_UNIT_EXIST_EN(200, "Product measurement unit already exists"),

    PRODUCT_UNIT_ADD_SUCCESS(200, "成功添加商品计量单位"),

    PRODUCT_UNIT_ADD_SUCCESS_EN(200, "Successfully add product measurement unit"),

    PRODUCT_UNIT_ADD_ERROR(500, "系统异常，添加商品计量单位失败"),

    PRODUCT_UNIT_ADD_ERROR_EN(500, "System exception, failed to add product measurement unit"),

    PRODUCT_UNIT_UPDATE_SUCCESS(200, "成功修改商品计量单位"),

    PRODUCT_UNIT_UPDATE_SUCCESS_EN(200, "Successfully modify product measurement unit"),

    PRODUCT_UNIT_UPDATE_ERROR(500, "系统异常，修改商品计量单位失败"),

    PRODUCT_UNIT_UPDATE_ERROR_EN(500, "System exception, failed to modify product measurement unit"),

    PRODUCT_UNIT_DELETE_SUCCESS(200, "成功删除商品计量单位"),

    PRODUCT_UNIT_DELETE_SUCCESS_EN(200, "Successfully delete product measurement unit"),

    PRODUCT_UNIT_DELETE_ERROR(500, "系统异常，删除商品计量单位失败"),

    PRODUCT_UNIT_DELETE_ERROR_EN(500, "System exception, failed to delete product measurement unit"),

    UPDATE_PRODUCT_UNIT_STATUS_SUCCESS(200, "成功修改商品计量单位状态"),

    UPDATE_PRODUCT_UNIT_STATUS_SUCCESS_EN(200, "Successfully modify product measurement unit status"),

    UPDATE_PRODUCT_UNIT_STATUS_ERROR(500, "系统异常，修改商品计量单位状态失败"),

    UPDATE_PRODUCT_UNIT_STATUS_ERROR_EN(500, "System exception, failed to modify product measurement unit status"),

    // Product code
    PRODUCT_NAME_EXIST(200, "商品名称已存在，请重新输入"),

    PRODUCT_NAME_EXIST_EN(200, "Product name already exists, please reenter"),

    PRODUCT_BAR_CODE_EXIST(200, "商品条码已存在，请重新输入"),

    PRODUCT_BAR_CODE_EXIST_EN(200, "Product barcode already exists, please reenter"),

    PRODUCT_ADD_SUCCESS(200, "成功添加商品"),

    PRODUCT_ADD_SUCCESS_EN(200, "Successfully add product"),

    PRODUCT_ADD_ERROR(500, "系统异常，添加商品信息失败"),

    PRODUCT_ADD_ERROR_EN(500, "System exception, failed to add product information"),

    PRODUCT_UPDATE_SUCCESS(200, "成功修改商品信息"),

    PRODUCT_UPDATE_SUCCESS_EN(200, "Successfully modify product information"),

    PRODUCT_UPDATE_ERROR(500, "系统异常，修改商品失败"),

    PRODUCT_UPDATE_ERROR_EN(500, "System exception, failed to modify product information"),

    PRODUCT_DELETE_SUCCESS(200, "成功删除商品"),

    PRODUCT_DELETE_SUCCESS_EN(200, "Successfully delete product"),

    PRODUCT_DELETE_ERROR(500, "系统异常，删除商品失败"),

    PRODUCT_DELETE_ERROR_EN(500, "System exception, failed to delete product"),

    PRODUCT_STATUS_UPDATE_SUCCESS(200, "成功修改商品状态"),

    PRODUCT_STATUS_UPDATE_SUCCESS_EN(200, "Successfully modify product status"),

    PRODUCT_STATUS_UPDATE_ERROR(500, "系统异常，修改商品状态失败"),

    PRODUCT_STATUS_UPDATE_ERROR_EN(500, "System exception, failed to modify product status"),

    PRODUCT_BATCH_UPDATE_SUCCESS(200, "成功批量修改商品信息"),

    PRODUCT_BATCH_UPDATE_SUCCESS_EN(200, "Successfully batch modify product information"),

    PRODUCT_BATCH_UPDATE_ERROR(500, "系统异常，批量修改商品信息失败"),

    PRODUCT_BATCH_UPDATE_ERROR_EN(500, "System exception, failed to batch modify product information"),

    PRODUCT_BAR_CODE_NOT_DUPLICATED(200, "商品条码不能重复，请重新输入"),

    PRODUCT_BAR_CODE_NOT_DUPLICATED_EN(200, "Product barcode cannot be repeated, please reenter");

    private final int code;

    /**
     * 响应提示
     */
    private final String msg;

    ProdcutCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
