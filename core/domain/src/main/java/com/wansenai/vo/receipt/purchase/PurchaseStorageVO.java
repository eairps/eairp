/*
 * Copyright 2023-2033 WanSen AI Team, Inc. All Rights Reserved.
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
package com.wansenai.vo.receipt.purchase;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wansenai.bo.BigDecimalSerializerBO;
import com.wansenai.utils.excel.ExcelExport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseStorageVO {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ExcelExport(value = "供应商")
    private String supplierName;

    @ExcelExport(value = "单据编号")
    private String receiptNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ExcelExport(value = "单据日期")
    private LocalDateTime receiptDate;

    @ExcelExport(value = "商品信息")
    private String productInfo;

    @ExcelExport(value = "操作员")
    private String operator;

    @ExcelExport(value = "商品数量")
    private Integer productNumber;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    @ExcelExport(value = "金额合计")
    private BigDecimal totalAmount;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    @ExcelExport(value = "含税合计")
    private BigDecimal taxIncludedAmount;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    @ExcelExport(value = "待付金额")
    private BigDecimal totalPaymentAmount;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    @ExcelExport(value = "本次付款")
    private BigDecimal thisPaymentAmount;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    @ExcelExport(value = "本次欠款")
    private BigDecimal thisArrearsAmount;

    @ExcelExport(value = "状态", kv = "0-未审核;1-已审核;2-审核中;3-部分采购;4-完成采购;")
    private Integer status;
}
