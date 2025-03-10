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
package com.wansenai.api.financial;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wansenai.dto.financial.AddOrUpdateExpenseDTO;
import com.wansenai.dto.financial.QueryExpenseDTO;
import com.wansenai.service.financial.ExpenseReceiptService;
import com.wansenai.utils.response.Response;
import com.wansenai.vo.financial.ExpenseDetailVO;
import com.wansenai.vo.financial.ExpenseVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@RestController
@RequestMapping("/financial/expense")
public class ExpenseReceiptController {

    private final ExpenseReceiptService expenseReceiptService;

    public ExpenseReceiptController(ExpenseReceiptService expenseReceiptService) {
        this.expenseReceiptService = expenseReceiptService;
    }

    @PostMapping("addOrUpdate")
    public Response<String> addOrUpdateExpenseReceipt(@RequestBody AddOrUpdateExpenseDTO addOrUpdateExpenseDTO) {
        return expenseReceiptService.addOrUpdateExpenseReceipt(addOrUpdateExpenseDTO);
    }

    @PostMapping("pageList")
    public Response<Page<ExpenseVO>> getExpenseReceiptPageList(@RequestBody QueryExpenseDTO queryExpenseDTO) {
        return expenseReceiptService.getExpenseReceiptPageList(queryExpenseDTO);
    }

    @GetMapping("getDetailById/{id}")
    public Response<ExpenseDetailVO> getExpenseReceiptDetailById(@PathVariable("id") Long id) {
        return expenseReceiptService.getExpenseReceiptDetail(id);
    }

    @PutMapping("deleteByIds")
    public Response<String> deleteExpenseReceiptByIds(@RequestParam("ids") List<Long> ids) {
        return expenseReceiptService.deleteBatchExpenseReceipt(ids);
    }

    @PutMapping("updateStatusByIds")
    public Response<String> updateExpenseReceiptStatusByIds(@RequestParam("ids") List<Long> ids, @RequestParam("status") Integer status) {
        return expenseReceiptService.updateExpenseReceiptStatus(ids, status);
    }

    @GetMapping("export")
    public void exportExpenseReceipt(@ModelAttribute QueryExpenseDTO queryExpenseDTO, HttpServletResponse response) {
        expenseReceiptService.exportExpenseReceipt(queryExpenseDTO, response);
    }

    @GetMapping("exportDetail/{receiptNumber}")
    public void exportExpenseReceiptDetail(@PathVariable("receiptNumber") String receiptNumber, HttpServletResponse response) {
        expenseReceiptService.exportExpenseReceiptDetail(receiptNumber, response);
    }
}
