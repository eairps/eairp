package com.wansenai.utils.enums;

import lombok.Getter;

@Getter
public enum MenuCodeEnum {

    ADD_MENU_SUCCESS(200, "添加菜单成功"),

    ADD_MENU_ERROR(500, "系统异常，添加菜单失败"),

    UPDATE_MENU_SUCCESS(200, "修改菜单成功"),

    UPDATE_MENU_ERROR(500, "系统异常，修改菜单失败"),

    DELETE_MENU_SUCCESS(200, "删除菜单成功"),

    DELETE_MENU_ERROR(500, "系统异常，删除菜单失败");


    private final int code;

    /**
     * 响应提示
     */
    private final String msg;

    MenuCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
