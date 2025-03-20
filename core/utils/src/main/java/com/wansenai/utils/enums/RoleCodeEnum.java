package com.wansenai.utils.enums;

import lombok.Getter;

@Getter
public enum RoleCodeEnum {

    ADD_ROLE_SUCCESS(200, "成功添加角色"),

    ADD_ROLE_SUCCESS_EN(200, "Successfully add role"),

    ADD_ROLE_ERROR(500, "系统异常，添加角色失败"),

    ADD_ROLE_ERROR_EN(500, "System exception, failed to add role"),

    UPDATE_ROLE_STATUS_SUCCESS(200, "成功修改角色状态"),

    UPDATE_ROLE_STATUS_SUCCESS_EN(200, "Successfully modify role status"),

    UPDATE_ROLE_STATUS_ERROR(500, "系统异常，修改角色状态失败"),

    UPDATE_ROLE_STATUS_ERROR_EN(500, "System exception, failed to modify role status"),

    UPDATE_ROLE_SUCCESS(200, "成功修改角色资料"),

    UPDATE_ROLE_SUCCESS_EN(200, "Successfully modify role info"),

    UPDATE_ROLE_ERROR(500, "系统异常，修改角色资料失败"),

    UPDATE_ROLE_ERROR_EN(500, "System exception, failed to modify role info"),

    DELETE_ROLE_SUCCESS(200, "成功删除角色"),

    DELETE_ROLE_SUCCESS_EN(200, "Successfully delete role"),

    DELETE_ROLE_ERROR(500, "系统异常，删除角色失败"),

    DELETE_ROLE_ERROR_EN(500, "System exception, failed to delete role"),

    ROLE_PERMISSION_MENU_SUCCESS(200, "赋予角色菜单权限成功"),

    ROLE_PERMISSION_MENU_SUCCESS_EN(200, "Successfully assign role menu permissions"),

    ROLE_PERMISSION_MENU_ERROR(500, "赋予角色菜单权限失败"),

    ROLE_PERMISSION_MENU_ERROR_EN(500, "Failed to assign role menu permissions");

    /**
     * 响应状态码
     */
    private final int code;

    /**
     * 响应提示
     */
    private final String msg;

    RoleCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
