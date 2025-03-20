package com.wansenai.utils.enums

enum class WarehouseCodeEnum(val code: Int, val msg: String) {

    ADD_WAREHOUSE_SUCCESS(200, "成功添加仓库"),

    ADD_WAREHOUSE_SUCCESS_EN(200, "Successfully add warehouse"),

    ADD_WAREHOUSE_ERROR(500, "系统异常，仓库添加失败"),

    ADD_WAREHOUSE_ERROR_EN(500, "System exception, failed to add warehouse"),

    UPDATE_WAREHOUSE_INFO_SUCCESS(200, "成功修改仓库信息"),

    UPDATE_WAREHOUSE_INFO_SUCCESS_EN(200, "Successfully modify warehouse info"),

    UPDATE_WAREHOUSE_INFO_ERROR(500, "系统异常，仓库信息修改失败"),

    UPDATE_WAREHOUSE_INFO_ERROR_EN(500, "System exception, failed to modify warehouse info"),

    DELETE_WAREHOUSE_SUCCESS(200, "成功删除仓库"),

    DELETE_WAREHOUSE_SUCCESS_EN(200, "Successfully delete warehouse"),

    DELETE_WAREHOUSE_ERROR(500, "系统异常，仓库删除失败"),

    DELETE_WAREHOUSE_ERROR_EN(500, "System exception, failed to delete warehouse"),

    UPDATE_WAREHOUSE_STATUS_SUCCESS(200, "成功修改仓库状态"),

    UPDATE_WAREHOUSE_STATUS_SUCCESS_EN(200, "Successfully modify warehouse status"),

    UPDATE_WAREHOUSE_STATUS_ERROR(500, "系统异常，仓库状态修改失败"),

    UPDATE_WAREHOUSE_STATUS_ERROR_EN(500, "System exception, failed to modify warehouse status"),
}