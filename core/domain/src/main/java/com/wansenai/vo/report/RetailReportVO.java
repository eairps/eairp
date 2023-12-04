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
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RetailReportVO {

    private String productBarcode;

    private String productName;

    private String warehouseName;

    private String productModel;

    private String productStandard;

    private String productExtendInfo;

    private String productUnit;

    private Integer retailNumber;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    private BigDecimal retailAmount;

    private Integer retailRefundNumber;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    private BigDecimal retailRefundAmount;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    private BigDecimal retailLastAmount;
}
