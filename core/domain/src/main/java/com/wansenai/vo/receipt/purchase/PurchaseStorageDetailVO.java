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
import com.wansenai.bo.FileDataBO;
import com.wansenai.bo.PurchaseDataBO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseStorageDetailVO {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long supplierId;

    private String supplierName;

    private String receiptNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime receiptDate;

    private String otherReceipt;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    private BigDecimal paymentRate;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    private BigDecimal paymentAmount;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    private BigDecimal paymentLastAmount;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    private BigDecimal otherAmount;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    private BigDecimal thisPaymentAmount;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    private BigDecimal thisArrearsAmount;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long accountId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private List<Long> multipleAccountAmounts;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private List<Long> multipleAccountIds;

    private String accountName;

    private List<PurchaseDataBO> tableData;

    private List<FileDataBO> files;

    private Integer status;

    private String remark;
}
