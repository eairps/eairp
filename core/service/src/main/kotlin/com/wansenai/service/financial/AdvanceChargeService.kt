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
package com.wansenai.service.financial

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.IService
import com.wansenai.dto.financial.AddOrUpdateAdvanceChargeDTO
import com.wansenai.dto.financial.QueryAdvanceChargeDTO
import com.wansenai.entities.financial.FinancialMain
import com.wansenai.utils.response.Response
import com.wansenai.vo.financial.AdvanceChargeDetailVO
import com.wansenai.vo.financial.AdvanceChargeVO
import jakarta.servlet.http.HttpServletResponse

interface AdvanceChargeService : IService<FinancialMain>{

    fun addOrUpdateAdvanceCharge(advanceChargeDTO: AddOrUpdateAdvanceChargeDTO): Response<String>

    fun getAdvanceChargePageList(advanceChargeDTO: QueryAdvanceChargeDTO?): Response<Page<AdvanceChargeVO>>

    fun getAdvanceChargeDetailById(id: Long): Response<AdvanceChargeDetailVO>

    fun deleteAdvanceChargeById(ids: List<Long>): Response<String>

    fun updateAdvanceChargeStatusById(ids: List<Long>, status: Int): Response<String>

    fun exportAdvanceCharge(advanceChargeDTO: QueryAdvanceChargeDTO, response: HttpServletResponse)

    fun exportAdvanceChargeDetail(receiptNumber: String, response: HttpServletResponse)
}