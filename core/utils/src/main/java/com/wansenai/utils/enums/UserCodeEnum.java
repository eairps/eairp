/*
 * Copyright 2023-2025 EAIRP Team, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://opensource.wansenai.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.wansenai.utils.enums;

import lombok.Getter;

/**
 * 用户类枚举代码
 */
@Getter
public enum UserCodeEnum {

    // login business
    USER_REGISTER_SUCCESS(200, "用户注册成功"),

    USER_REGISTER_SUCCESS_EN(200, "Successfully register user"),

    USER_NAME_EXISTS(200, "用户名已存在"),

    USER_NAME_EXISTS_EN(200, "Username already exists"),

    USER_LOGOUT(200, "账户注销成功"),

    USER_LOGOUT_EN(200, "Successfully Logout account"),

    USER_NOT_EXISTS(404, "用户账户不存在"),

    USER_NOT_EXISTS_EN(404, "User account does not exist"),

    USER_PASSWORD_ERROR(200, "用户旧密码错误，请检查重新输入"),

    USER_PASSWORD_ERROR_EN(200, "User old password is incorrect. Please check and reenter it"),

    USER_REGISTER_PHONE_EXISTS(200, "当前手机号已注册，请直接登陆"),

    USER_REGISTER_PHONE_EXISTS_EN(200, "Current phone number has been registered, Please login"),

    PHONE_EXISTS(200, "当前手机号已被绑定使用, 请更换新的手机号"),

    PHONE_EXISTS_EN(200, "Current phone number has been bound for use. Please replace it with a new phone number"),

    USERNAME_OR_PASSWORD_ERROR(500, "登录失败，用户名或密码错误"),

    USERNAME_OR_PASSWORD_ERROR_EN(500, "Login failed, username or password incorrect"),

    UPDATE_PASSWORD_ERROR(500, "系统异常，密码修改失败"),

    UPDATE_PASSWORD_ERROR_EN(500, "System exception, failed to modify password"),

    USER_RESET_PASSWORD_ERROR(500, "系统异常，重置用户密码失败"),

    USER_RESET_PASSWORD_ERROR_EN(500, "System exception, failed to reset user password"),

    UPDATE_PASSWORD_SUCCESS(200, "成功修改密码"),

    UPDATE_PASSWORD_SUCCESS_EN(200, "Successfully changed password"),

    USER_RESET_PASSWORD_SUCCESS(200, "成功重置用户密码"),

    USER_RESET_PASSWORD_SUCCESS_EN(200, "Successfully reset user password"),

    USER_ACCOUNT_FREEZE(200, "账户被冻结"),

    USER_ACCOUNT_FREEZE_EN(200, "Account frozen"),

    USER_ACCOUNT_INVALID(203, "账户已作废"),

    USER_ACCOUNT_INVALID_EN(203, "Account has been invalidated"),

    EMAIL_EXISTS(200, "当前邮箱已被绑定使用"),

    EMAIL_EXISTS_EN(200, "Current email has been bound for use"),

    USER_PHONE_UPDATE_SUCCESS(200, "成功换绑手机号"),

    USER_PHONE_UPDATE_SUCCESS_EN(200, "Successfully changed bound phone number"),

    USER_PHONE_UPDATE_ERROR(500, "系统异常，手机号换绑失败"),

    USER_PHONE_UPDATE_ERROR_EN(500, "System exception, failed to change phone number"),

    USER_EMAIL_UPDATE_SUCCESS(200, "成功换绑邮箱换绑"),

    USER_EMAIL_UPDATE_SUCCESS_EN(200, "Successfully changed bound email address"),

    USER_EMAIL_UPDATE_ERROR(500, "系统异常，邮箱换绑失败"),

    USER_EMAIL_UPDATE_ERROR_EN(500, "System exception, failed to change email"),

    // user list table business
    USER_ADD_SUCCESS(200, "成功添加用户"),

    USER_ADD_SUCCESS_EN(200, "Successfully add user"),

    USER_INFO_UPDATE_SUCCESS(200, "成功修改用户信息"),

    USER_INFO_UPDATE_SUCCESS_EN(200, "Successfully modify user info"),

    USER_INFO_UPDATE_ERROR(500, "系统异常，改用户资料失败"),

    USER_INFO_UPDATE_ERROR_EN(500, "System exception, failed to modify user info"),

    USER_ADD_ERROR(500, "系统异常，添加用户失败"),

    USER_ADD_ERROR_EN(500, "System exception, failed to add user"),

    USER_DELETE_SUCCESS(200, "成功删除用户"),

    USER_DELETE_SUCCESS_EN(200, "Successfully delete user"),

    USER_DELETE_ERROR(500, "系统异常，删除用户失败"),

    USER_DELETE_ERROR_EN(500, "System exception, failed to delete user"),

    // user role
    USER_NOT_PERMISSION(401, "用户没有权限"),

    USER_NOT_PERMISSION_EN(401, "User does not have permission");

    /**
     * 响应状态码
     */
    private final int code;

    /**
     * 响应提示
     */
    private final String msg;

    UserCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
