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
package com.wansenai.api.warehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wansenai.dto.warehouse.AssembleReceiptDTO;
import com.wansenai.dto.warehouse.QueryAssembleReceiptDTO;
import com.wansenai.service.warehouse.AssembleReceiptService;
import com.wansenai.utils.response.Response;
import com.wansenai.vo.warehouse.AssembleReceiptDetailVO;
import com.wansenai.vo.warehouse.AssembleReceiptVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("warehouse/assemble")
public class AssembleController {

    private final AssembleReceiptService assembleService;

    public AssembleController(AssembleReceiptService assembleService) {
        this.assembleService = assembleService;
    }

    @PostMapping("addOrUpdate")
    public Response<String> addOrUpdateAssembleReceipt(@RequestBody AssembleReceiptDTO assembleReceiptDTO) {
        return assembleService.addOrUpdateAssembleReceipt(assembleReceiptDTO);
    }

    @PostMapping("pageList")
    public Response<Page<AssembleReceiptVO>> getAssembleReceiptPageList(@RequestBody QueryAssembleReceiptDTO queryAssembleReceiptDTO) {
        return assembleService.getAssembleReceiptPageList(queryAssembleReceiptDTO);
    }

    @GetMapping("getDetailById/{id}")
    public Response<AssembleReceiptDetailVO> getAssembleReceiptDetailById(@PathVariable("id") Long id) {
        return assembleService.getAssembleReceiptDetail(id);
    }

    @PutMapping("deleteByIds")
    public Response<String> deleteAssembleReceiptByIds(@RequestParam("ids") List<Long> ids) {
        return assembleService.deleteBatchAssembleReceipt(ids);
    }

    @PutMapping("updateStatusByIds")
    public Response<String> updateAssembleReceiptStatusByIds(@RequestParam("ids") List<Long> ids, @RequestParam("status") Integer status) {
        return assembleService.updateAssembleReceiptStatus(ids, status);
    }

    @GetMapping("export")
    public void exportAssembleReceipt(@ModelAttribute QueryAssembleReceiptDTO queryAssembleReceiptDTO, HttpServletResponse response) throws Exception {
        assembleService.exportAssembleReceipt(queryAssembleReceiptDTO, response);
    }

    @GetMapping("exportDetail/{receiptNumber}")
    public void exportAssembleReceiptDetail(@PathVariable("receiptNumber") String receiptNumber, HttpServletResponse response) throws Exception {
        assembleService.exportAssembleReceiptDetail(receiptNumber, response);
    }
}
