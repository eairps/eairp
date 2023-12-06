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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDataExportBO {

    @ExcelExport(value = "供应商")
    private String supplierName;

    @ExcelExport(value = "单据编号")
    private String receiptNumber;

    @ExcelExport(value = "仓库名称")
    private String warehouseName;

    @ExcelExport(value = "条码")
    private String barCode;

    @ExcelExport(value = "商品名称")
    private String productName;

    @ExcelExport(value = "规格")
    private String productStandard;

    @ExcelExport(value = "型号")
    private String productModel;

    @ExcelExport(value = "颜色")
    private String productColor;

    @ExcelExport(value = "库存")
    private Integer stock;

    @ExcelExport(value = "单位")
    private String productUnit;

    @ExcelExport(value = "数量")
    private Integer productNumber;

    @ExcelExport(value = "单价")
    private BigDecimal unitPrice;

    @ExcelExport(value = "金额")
    private BigDecimal amount;

    @ExcelExport(value = "税率(%)")
    private BigDecimal taxRate;

    @ExcelExport(value = "税额")
    private BigDecimal taxAmount;

    @ExcelExport(value = "价税合计")
    private BigDecimal taxTotalPrice;

    @ExcelExport(value = "备注")
    private String remark;
}
