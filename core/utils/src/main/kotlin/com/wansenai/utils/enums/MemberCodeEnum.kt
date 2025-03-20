package com.wansenai.utils.enums

enum class MemberCodeEnum(val code: Int, val msg: String) {

    ADD_MEMBER_SUCCESS(200, "成功添加会员"),

    ADD_MEMBER_SUCCESS_EN(200, "Successfully add member"),

    ADD_MEMBER_ERROR(500, "系统异常，会员添加失败"),

    ADD_MEMBER_ERROR_EN(500, "System exception, failed to add member"),

    UPDATE_MEMBER_INFO_SUCCESS(200, "会员信息修改成功"),

    UPDATE_MEMBER_INFO_SUCCESS_EN(200, "Successfully modify member info"),

    UPDATE_MEMBER_INFO_ERROR(500, "系统异常，会员信息修改失败"),

    UPDATE_MEMBER_INFO_ERROR_EN(500, "System exception, failed to modify member info"),

    DELETE_MEMBER_SUCCESS(200, "会员删除成功"),

    DELETE_MEMBER_SUCCESS_EN(200, "Successfully delete member"),

    DELETE_MEMBER_ERROR(500, "系统异常，会员删除失败"),

    DELETE_MEMBER_ERROR_EN(500, "System exception, failed to delete member"),

    UPDATE_MEMBER_STATUS_SUCCESS(200, "会员状态修改成功"),

    UPDATE_MEMBER_STATUS_SUCCESS_EN(200, "Successfully modify member status"),

    UPDATE_MEMBER_STATUS_ERROR(500, "系统异常，会员状态修改失败"),

    UPDATE_MEMBER_STATUS_ERROR_EN(500, "System exception, failed to modify member status"),
}