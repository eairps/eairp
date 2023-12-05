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
package com.wansenai.vo.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wansenai.bo.BigDecimalSerializerBO;
import com.wansenai.utils.excel.ExcelExport;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CustomerBillVO {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long customerId;

    @ExcelExport(value = "客户")
    private String customerName;

    @ExcelExport(value = "联系人")
    private String contactName;

    @ExcelExport(value = "联系电话")
    private String contactPhone;

    @ExcelExport(value = "电子邮箱")
    private String email;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    @ExcelExport(value = "一季度应收账款")
    private BigDecimal firstQuarterReceivable;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    @ExcelExport(value = "二季度应收账款")
    private BigDecimal secondQuarterReceivable;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    @ExcelExport(value = "三季度应收账款")
    private BigDecimal thirdQuarterReceivable;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    @ExcelExport(value = "四季度应收账款")
    private BigDecimal fourthQuarterReceivable;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    @ExcelExport(value = "累计欠款")
    private BigDecimal totalQuarterArrears;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    @ExcelExport(value = "累计收款")
    private BigDecimal totalQuarterReceivable;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    @ExcelExport(value = "应收欠款")
    private BigDecimal remainingReceivableArrears;
}
