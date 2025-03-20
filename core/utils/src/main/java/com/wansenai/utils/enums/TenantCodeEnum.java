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

@Getter
public enum TenantCodeEnum {

    TENANT_ADD_SUCCESS(200, "成功添加租户"),

    TENANT_ADD_SUCCESS_EN(200, "Successfully add tenant"),

    TENANT_INFO_UPDATE_SUCCESS(200, "成功修改租户资料"),

    TENANT_INFO_UPDATE_SUCCESS_EN(200, "Successfully modify tenant info"),

    TENANT_INFO_UPDATE_ERROR(500, "系统异常，租户资料修改失败"),

    TENANT_INFO_UPDATE_ERROR_EN(500, "System exception, failed to modify tenant info"),

    TENANT_ADD_ERROR(500, "系统异常，租户添加失败"),

    TENANT_ADD_ERROR_EN(500, "System exception, failed to add tenant"),

    TENANT_USER_NUM_LIMIT(403, "当前租户已达到注册用户的最大数量"),

    TENANT_USER_NUM_LIMIT_EN(403, "The current tenant has reached the maximum number of registered users"),

    TENANT_EXPIRED(403, "当前租户已过期，请联系平台管理员续费"),

    TENANT_EXPIRED_EN(403, "The current tenant has expired. Please contact the platform administrator to renew"),

    TENANT_UNEXPIRED(200, "租户未过期"),

    TENANT_UNEXPIRED_EN(200, "Tenant not expired"),

    TENANT_DELETE_SUCCESS(200, "成功删除租户"),

    TENANT_DELETE_SUCCESS_EN(200, "Successfully delete tenant"),

    TENANT_DELETE_ERROR(500, "系统异常，租户删除失败"),

    TENANT_DELETE_ERROR_EN(500, "System exception, failed to delete tenant");

    /**
     * 响应状态码
     */
    private final int code;

    /**
     * 响应提示
     */
    private final String msg;

    TenantCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
