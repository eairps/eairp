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
package com.wansenai.api.financial;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wansenai.dto.financial.AddOrUpdateAccountDTO;
import com.wansenai.dto.financial.QueryAccountDTO;
import com.wansenai.service.financial.IFinancialAccountService;
import com.wansenai.utils.response.Response;
import com.wansenai.vo.financial.AccountVO;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
@RequestMapping("/financial/account")
public class FinancialAccountController {

    private final IFinancialAccountService accountService;

    public FinancialAccountController(IFinancialAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("pageList")
    public Response<Page<AccountVO>> getAccountPageList(@RequestBody QueryAccountDTO queryAccountDTO) {
        return accountService.getAccountPageList(queryAccountDTO);
    }

    @PostMapping("addOrUpdate")
    public Response<String> addOrUpdateAccount(@RequestBody AddOrUpdateAccountDTO addOrUpdateAccountDTO) {
        return accountService.addOrUpdateAccount(addOrUpdateAccountDTO);
    }

    @DeleteMapping("delete")
    public Response<String> deleteAccount(@RequestParam("ids") List<Long> ids) {
        return accountService.deleteBatchAccount(ids);
    }

    @PostMapping("updateStatus")
    public Response<String> updateAccountStatus(@RequestParam("ids") List<Long> ids, @RequestParam("status") Integer status) {
        return accountService.updateAccountStatus(ids, status);
    }

    @GetMapping("list")
    public Response<List<AccountVO>> getAccountList() {
        return accountService.getAccountList();
    }
}
