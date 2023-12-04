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
package com.wansenai.vo.financial

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.wansenai.NoArg
import com.wansenai.bo.BigDecimalSerializerBO
import com.wansenai.utils.excel.ExcelExport
import lombok.Data
import java.math.BigDecimal
import java.time.LocalDateTime

@NoArg
@Data
data class AdvanceChargeVO (

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var id: Long? = null,

    @ExcelExport(value = "会员")
    var memberName: String,

    @ExcelExport(value = "单据编号")
    var receiptNumber: String,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ExcelExport(value = "单据日期")
    var receiptDate: LocalDateTime,

    @JsonSerialize(using = BigDecimalSerializerBO::class)
    @ExcelExport(value = "收款金额")
    var collectedAmount : BigDecimal,

    @JsonSerialize(using = BigDecimalSerializerBO::class)
    @ExcelExport(value = "合计金额")
    var totalAmount : BigDecimal,

    @ExcelExport(value = "财务人员")
    var financialPersonnel: String,

    @ExcelExport(value = "操作员")
    var operator: String,

    @ExcelExport(value = "备注")
    var remark: String? = null,

    @ExcelExport(value = "状态", kv = "0:未审核;1:已审核")
    var status: Int,
)
