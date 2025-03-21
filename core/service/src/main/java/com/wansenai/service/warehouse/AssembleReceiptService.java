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
package com.wansenai.service.warehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wansenai.dto.warehouse.AssembleReceiptDTO;
import com.wansenai.dto.warehouse.QueryAssembleReceiptDTO;
import com.wansenai.entities.warehouse.WarehouseReceiptMain;
import com.wansenai.utils.response.Response;
import com.wansenai.vo.warehouse.AssembleReceiptDetailVO;
import com.wansenai.vo.warehouse.AssembleReceiptVO;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface AssembleReceiptService extends IService<WarehouseReceiptMain> {

    Response<Page<AssembleReceiptVO>> getAssembleReceiptPageList(QueryAssembleReceiptDTO queryAssembleReceiptDTO);

    Response<AssembleReceiptDetailVO> getAssembleReceiptDetail(Long id);

    Response<String> addOrUpdateAssembleReceipt(AssembleReceiptDTO assembleReceiptDTO);

    Response<String> deleteBatchAssembleReceipt(List<Long> ids);

    Response<String> updateAssembleReceiptStatus(List<Long> ids, Integer status);

    void exportAssembleReceipt(QueryAssembleReceiptDTO queryAssembleReceiptDTO, HttpServletResponse response) throws Exception;

    void exportAssembleReceiptDetail(String receiptNumber, HttpServletResponse response) throws Exception;
}
