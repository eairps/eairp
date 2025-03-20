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
public enum IncomeExpenseCodeEnum {

    ADD_INCOME_EXPENSE_SUCCESS(200, "成功新增收支项目"),

    ADD_INCOME_EXPENSE_SUCCESS_EN(200, "Successfully add income/expense item"),

    ADD_INCOME_EXPENSE_ERROR(500, "系统异常，新增收支项目失败"),

    ADD_INCOME_EXPENSE_ERROR_EN(500, "System exception, failed to add income/expense item"),

    UPDATE_INCOME_EXPENSE_SUCCESS(200, "成功更新收支项目"),

    UPDATE_INCOME_EXPENSE_SUCCESS_EN(200, "Successfully modify income/expense item"),

    UPDATE_INCOME_EXPENSE_ERROR(500, "系统异常，更新收支项目失败"),

    UPDATE_INCOME_EXPENSE_ERROR_EN(500, "System exception, failed to modify income/expense item"),

    DELETE_INCOME_EXPENSE_SUCCESS(200, "成功删除收支项目"),

    DELETE_INCOME_EXPENSE_SUCCESS_EN(200, "Successfully delete income/expense item"),

    DELETE_INCOME_EXPENSE_ERROR(500, "系统异常，删除收支项目失败"),

    DELETE_INCOME_EXPENSE_ERROR_EN(500, "System exception, failed to delete income/expense item"),

    ADD_INCOME_RECEIPT_SUCCESS(200, "成功新增收入单据"),

    ADD_INCOME_RECEIPT_SUCCESS_EN(200, "Successfully add income receipt"),

    ADD_INCOME_RECEIPT_ERROR(500, "系统异常，新增收入单失败"),

    ADD_INCOME_RECEIPT_ERROR_EN(500, "System exception, failed to add income receipt"),

    UPDATE_INCOME_RECEIPT_SUCCESS(200, "成功更新收入单据"),

    UPDATE_INCOME_RECEIPT_SUCCESS_EN(200, "Successfully modify income receipt"),

    UPDATE_INCOME_RECEIPT_ERROR(500, "系统异常，更新收入单失败"),

    UPDATE_INCOME_RECEIPT_ERROR_EN(500, "System exception, failed to modify income receipt"),

    DELETE_INCOME_RECEIPT_SUCCESS(200, "成功删除收入单据"),

    DELETE_INCOME_RECEIPT_SUCCESS_EN(200, "Successfully delete income receipt"),

    DELETE_INCOME_RECEIPT_ERROR(500, "系统异常，删除收入单据失败"),

    DELETE_INCOME_RECEIPT_ERROR_EN(500, "System exception, failed to delete income receipt"),

    ADD_EXPENSE_RECEIPT_SUCCESS(200, "成功新增支出单据"),

    ADD_EXPENSE_RECEIPT_SUCCESS_EN(200, "Successfully add expense receipt"),

    ADD_EXPENSE_RECEIPT_ERROR(500, "系统异常，新增支出单据失败"),

    ADD_EXPENSE_RECEIPT_ERROR_EN(500, "System exception, failed to add expense receipt"),

    UPDATE_EXPENSE_RECEIPT_SUCCESS(200, "成功更新支出单据"),

    UPDATE_EXPENSE_RECEIPT_SUCCESS_EN(200, "Successfully modify expense receipt"),

    UPDATE_EXPENSE_RECEIPT_ERROR(500, "系统异常，更新支出单据失败"),

    UPDATE_EXPENSE_RECEIPT_ERROR_EN(500, "System exception, failed to modify expense receipt"),

    DELETE_EXPENSE_RECEIPT_SUCCESS(200, "成功删除支出单据"),

    DELETE_EXPENSE_RECEIPT_SUCCESS_EN(200, "Successfully delete expense receipt"),

    DELETE_EXPENSE_RECEIPT_ERROR(500, "系统异常，删除支出单据失败"),

    DELETE_EXPENSE_RECEIPT_ERROR_EN(500, "System exception, failed to delete expense receipt");

    private final int code;

    private final String msg;

    IncomeExpenseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
