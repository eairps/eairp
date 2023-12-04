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
package com.wansenai.bo;

import com.wansenai.utils.excel.ExcelExport;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CollectionDataExportBO {

    @ExcelExport(value = "客户")
    private String customerName;

    @ExcelExport(value = "收款单号")
    private String receiptNumber;

    @ExcelExport(value = "销售单据编号")
    private String saleReceiptNumber;

    @ExcelExport(value = "应收欠款")
    private BigDecimal receivableArrears;

    @ExcelExport(value = "已收欠款")
    private BigDecimal receivedArrears;

    @ExcelExport(value = "本次收款")
    private BigDecimal thisCollectionAmount;

    @ExcelExport(value = "备注")
    private String remark;
}
