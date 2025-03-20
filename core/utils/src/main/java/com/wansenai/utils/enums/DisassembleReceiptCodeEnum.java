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
public enum DisassembleReceiptCodeEnum {

    ADD_DISASSEMBLE_RECEIPT_SUCCESS(200, "成功添加拆卸单据"),

    ADD_DISASSEMBLE_RECEIPT_SUCCESS_EN(200, "Successfully add disassembly documents"),

    ADD_DISASSEMBLE_RECEIPT_ERROR(500, "系统异常，添加拆卸单据失败"),

    ADD_DISASSEMBLE_RECEIPT_ERROR_EN(500, "System exception, failed to add disassembly documents"),

    UPDATE_DISASSEMBLE_RECEIPT_SUCCESS(200, "成功修改拆卸单据"),

    UPDATE_DISASSEMBLE_RECEIPT_SUCCESS_EN(200, "Successfully modify disassembly documents"),

    UPDATE_DISASSEMBLE_RECEIPT_ERROR(500, "系统异常，修改拆卸单据失败"),

    UPDATE_DISASSEMBLE_RECEIPT_ERROR_EN(500, "System exception, failed to modify disassembly documents"),

    DELETE_DISASSEMBLE_RECEIPT_SUCCESS(200, "成功删除拆卸单据"),

    DELETE_DISASSEMBLE_RECEIPT_SUCCESS_EN(200, "Successfully delete disassembly documents"),

    DELETE_DISASSEMBLE_RECEIPT_ERROR(500, "系统异常，删除拆卸单据失败"),

    DELETE_DISASSEMBLE_RECEIPT_ERROR_EN(500, "System exception, failed to delete disassembly documents");

    /**
     * 响应状态码
     */
    private final int code;

    /**
     * 响应提示
     */
    private final String msg;

    DisassembleReceiptCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
