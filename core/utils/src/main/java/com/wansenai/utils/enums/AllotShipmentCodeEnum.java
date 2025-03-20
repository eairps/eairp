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
public enum AllotShipmentCodeEnum {

    ADD_ALLOT_SHIPMENT_STOCK_RECEIPT_SUCCESS(200, "成功添加调拨出库单据"),

    ADD_ALLOT_SHIPMENT_STOCK_RECEIPT_SUCCESS_EN(200, "Successfully add transfer outbound document"),

    ADD_ALLOT_SHIPMENT_STOCK_RECEIPT_ERROR(500, "系统异常，添加调拨出库单据失败"),

    ADD_ALLOT_SHIPMENT_STOCK_RECEIPT_ERROR_EN(500, "System exception, failed to add transfer outbound document"),

    UPDATE_ALLOT_SHIPMENT_STOCK_RECEIPT_SUCCESS(200, "成功修改调拨出库单据"),

    UPDATE_ALLOT_SHIPMENT_STOCK_RECEIPT_SUCCESS_EN(200, "Successfully modify transfer outbound document"),

    UPDATE_ALLOT_SHIPMENT_STOCK_RECEIPT_ERROR(500, "系统异常，修改调拨出库单据失败"),

    UPDATE_ALLOT_SHIPMENT_STOCK_RECEIPT_ERROR_EN(500, "System exception, failed to modify transfer outbound document"),

    DELETE_ALLOT_SHIPMENT_STOCK_RECEIPT_SUCCESS(200, "成功删除调拨出库单据"),

    DELETE_ALLOT_SHIPMENT_STOCK_RECEIPT_SUCCESS_EN(200, "Successfully delete transfer outbound document"),

    DELETE_ALLOT_SHIPMENT_STOCK_RECEIPT_ERROR(500, "系统异常，删除调拨出库单据失败"),

    DELETE_ALLOT_SHIPMENT_STOCK_RECEIPT_ERROR_EN(500, "System exception, failed to delete transfer outbound document");

    /**
     * 响应状态码
     */
    private final int code;

    /**
     * 响应提示
     */
    private final String msg;

    AllotShipmentCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
