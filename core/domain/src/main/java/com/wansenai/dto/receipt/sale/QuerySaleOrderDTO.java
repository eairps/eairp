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
package com.wansenai.dto.receipt.sale;

import lombok.Data;

@Data
public class QuerySaleOrderDTO {

    private String receiptNumber;

    private String productInfo;

    private Long customerId;

    private Long operatorId;

    private Integer status;

    private String remark;

    private String startDate;

    private String endDate;

    private Long page;

    private Long pageSize;

    private Boolean isExportDetail;
}
