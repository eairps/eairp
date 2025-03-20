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
 * <p>
 *  通用响应状态枚举
 * </p>
 */
@Getter
public enum BaseCodeEnum {

    // 一级基本宏观状态码
    SUCCESS(200, "系统执行成功"),

    ERROR(500, "服务器错误"),

    QUERY_DATA_EMPTY(200, "查询数据不存在"),

    PARAMETER_NULL(400,"请求必填参数为空"),

    VERIFY_CODE_ERROR(200, "验证码错误"),

    VERIFY_CODE_EXPIRE(200, "验证码已过期"),

    SMS_VERIFY_CODE_EXPIRE(200, "短信校验码已过期"),

    SMS_VERIFY_SEND_SUCCESS(200, "短信验证码发送成功"),

    SMS_VERIFY_CODE_ERROR(500, "短信校验码错误"),

    EMAIL_VERIFY_CODE_EXPIRE(200, "邮箱验证码已过期"),

    EMAIL_VERIFY_SEND_SUCCESS(200, "邮箱验证码发送成功"),

    EMAIL_VERIFY_CODE_ERROR(500, "邮箱验证码错误"),

    PHONE_NUMBER_FORMAT_ERROR(500, "手机格式校验失败"),

    FILE_UPLOAD_ERROR(500, "文件上传失败"),

    FILE_UPLOAD_ERROR_EN(500, "File upload failed"),

    FILE_UPLOAD_NO_FILENAME_MATCH(500, "文件上传失败，文件名不匹配"),

    OSS_KEY_NOT_EXIST(500, "腾讯云OSS对象存储key不存在"),

    OSS_GET_INSTANCE_ERROR(500, "腾讯云OSS对象存储实例获取失败"),

    SNOWFLAKE_ID_GENERATE_ERROR(500, "雪花算法生成ID失败"),

    FREQUENT_SYSTEM_ACCESS(502, "系统请求过于频繁，请稍后再试"),

    SYSTEM_BUSY(502, "系统繁忙，请稍后再试");

    /**
     * 响应状态码
     */
    private final int code;

    /**
     * 响应提示
     */
    private final String msg;

    BaseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
