package com.wansenai.utils.enums;

import lombok.Getter;

@Getter
public enum DeptCodeEnum {

    ADD_DEPARTMENT_SUCCESS(200, "成功添加部门"),

    ADD_DEPARTMENT_SUCCESS_EN(200, "Successfully add department"),

    ADD_DEPARTMENT_ERROR(500, "系统异常，添加部门失败"),

    ADD_DEPARTMENT_ERROR_EN(500, "System exception, failed to add department"),

    UPDATE_DEPARTMENT_SUCCESS(200, "成功修改部门"),

    UPDATE_DEPARTMENT_SUCCESS_EN(200, "Successfully modify department"),

    UPDATE_DEPARTMENT_ERROR(500, "系统异常，修改部门失败"),

    UPDATE_DEPARTMENT_ERROR_EN(500, "System exception, failed to modify department"),

    DELETE_DEPARTMENT_SUCCESS(200, "成功删除部门"),

    DELETE_DEPARTMENT_SUCCESS_EN(200, "Successfully delete department"),

    DELETE_DEPARTMENT_ERROR(500, "系统异常，删除部门失败"),

    DELETE_DEPARTMENT_ERROR_EN(500, "System exception, failed to delete department");

    /**
     * 响应状态码
     */
    private final int code;

    /**
     * 响应提示
     */
    private final String msg;

    DeptCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
