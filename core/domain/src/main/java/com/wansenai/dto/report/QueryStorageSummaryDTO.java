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
package com.wansenai.dto.report;

import lombok.Data;

@Data
public class QueryStorageSummaryDTO {

    private Long relatedPersonId;

    private String productInfo;

    private Long warehouseId;

    private String startDate;

    private String endDate;

    private Integer page;

    private Integer pageSize;
}
