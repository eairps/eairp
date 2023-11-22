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
package com.wansenai.service.financial.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wansenai.bo.FileDataBO;
import com.wansenai.bo.IncomeExpenseBO;
import com.wansenai.dto.financial.AddOrUpdateExpenseDTO;
import com.wansenai.dto.financial.QueryExpenseDTO;
import com.wansenai.entities.financial.FinancialMain;
import com.wansenai.entities.financial.FinancialSub;
import com.wansenai.entities.system.SysFile;
import com.wansenai.mappers.financial.FinancialMainMapper;
import com.wansenai.mappers.system.SysFileMapper;
import com.wansenai.service.basic.IncomeExpenseService;
import com.wansenai.service.common.CommonService;
import com.wansenai.service.financial.ExpenseReceiptService;
import com.wansenai.service.financial.FinancialSubService;
import com.wansenai.service.financial.IFinancialAccountService;
import com.wansenai.service.user.ISysUserService;
import com.wansenai.utils.SnowflakeIdUtil;
import com.wansenai.utils.TimeUtil;
import com.wansenai.utils.constants.CommonConstants;
import com.wansenai.utils.enums.BaseCodeEnum;
import com.wansenai.utils.enums.IncomeExpenseCodeEnum;
import com.wansenai.utils.response.Response;
import com.wansenai.vo.financial.ExpenseDetailVO;
import com.wansenai.vo.financial.ExpenseVO;
import com.wansenai.vo.financial.IncomeDetailVO;
import com.wansenai.vo.financial.IncomeVO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseReceiptServiceImpl extends ServiceImpl<FinancialMainMapper, FinancialMain> implements ExpenseReceiptService {

    private final FinancialSubService financialSubService;

    private final CommonService commonService;

    private final ISysUserService userService;

    private final SysFileMapper fileMapper;

    private final IFinancialAccountService accountService;

    private final IncomeExpenseService incomeExpenseService;

    public ExpenseReceiptServiceImpl(FinancialSubService financialSubService, CommonService commonService, ISysUserService userService, SysFileMapper fileMapper, IFinancialAccountService accountService, IncomeExpenseService incomeExpenseService) {
        this.financialSubService = financialSubService;
        this.commonService = commonService;
        this.userService = userService;
        this.fileMapper = fileMapper;
        this.accountService = accountService;
        this.incomeExpenseService = incomeExpenseService;
    }

    private ArrayList<Long> processFiles(List<FileDataBO> files, Long retailId) {
        var userId = userService.getCurrentUserId();
        var fid = new ArrayList<Long>();
        if (!files.isEmpty()) {
            var receiptMain = getById(retailId);
            if (receiptMain != null && StringUtils.hasLength(receiptMain.getFileId())) {
                var ids = Arrays.stream(receiptMain.getFileId().split(","))
                        .map(Long::parseLong)
                        .collect(Collectors.toList());
                fileMapper.deleteBatchIds(ids);
            }
            files.forEach(item -> {
                var file = SysFile.builder()
                        .id(SnowflakeIdUtil.nextId())
                        .uid(item.getUid())
                        .fileName(item.getFileName())
                        .fileType(item.getFileType())
                        .fileSize(item.getFileSize())
                        .fileUrl(item.getFileUrl())
                        .createBy(userId)
                        .createTime(LocalDateTime.now())
                        .build();
                fileMapper.insert(file);
                fid.add(file.getId());
            });
        }
        return fid;
    }

    @Override
    public Response<Page<ExpenseVO>> getExpenseReceiptPageList(QueryExpenseDTO queryExpenseDTO) {
        var result = new Page<ExpenseVO>();
        var page = new Page<FinancialMain>(queryExpenseDTO.getPage(), queryExpenseDTO.getPageSize());

        var financialMainPage = lambdaQuery()
                .eq(queryExpenseDTO.getRelatedPersonId() != null, FinancialMain::getRelatedPersonId, queryExpenseDTO.getRelatedPersonId())
                .eq(queryExpenseDTO.getFinancialPersonId() != null, FinancialMain::getOperatorId, queryExpenseDTO.getFinancialPersonId())
                .eq(queryExpenseDTO.getAccountId() != null, FinancialMain::getAccountId, queryExpenseDTO.getAccountId())
                .eq(StringUtils.hasLength(queryExpenseDTO.getReceiptNumber()), FinancialMain::getReceiptNumber, queryExpenseDTO.getReceiptNumber())
                .eq(queryExpenseDTO.getStatus() != null, FinancialMain::getStatus, queryExpenseDTO.getStatus())
                .like(StringUtils.hasLength(queryExpenseDTO.getRemark()), FinancialMain::getRemark, queryExpenseDTO.getRemark())
                .ge(StringUtils.hasLength(queryExpenseDTO.getStartDate()), FinancialMain::getReceiptDate, queryExpenseDTO.getStartDate())
                .le(StringUtils.hasLength(queryExpenseDTO.getEndDate()), FinancialMain::getReceiptDate, queryExpenseDTO.getEndDate())
                .eq(FinancialMain::getType, "支出")
                .page(page);

        var expenseVOList = new ArrayList<ExpenseVO>(financialMainPage.getRecords().size() + 1);
        financialMainPage.getRecords().forEach(item -> {
            var expenseVO = ExpenseVO.builder()
                    .id(item.getId())
                    .receiptNumber(item.getReceiptNumber())
                    .name(commonService.getRelatedPersonName(item.getRelatedPersonId()))
                    .receiptDate(item.getReceiptDate())
                    .financialPerson(commonService.getOperatorName(item.getOperatorId()))
                    .expenseAccountName(commonService.getAccountName(item.getAccountId()))
                    .expenseAmount(item.getTotalAmount())
                    .status(item.getStatus())
                    .remark(item.getRemark())
                    .build();

            expenseVOList.add(expenseVO);
        });
        result.setRecords(expenseVOList);
        result.setTotal(financialMainPage.getTotal());
        return Response.responseData(result);
    }

    @Override
    public Response<ExpenseDetailVO> getExpenseReceiptDetail(Long id) {
        if (id == null) {
            return Response.responseMsg(BaseCodeEnum.PARAMETER_NULL);
        }

        var financialMain = getById(id);
        if(financialMain != null) {
            var expenseDetailVO = ExpenseDetailVO.builder()
                    .relatedPersonId(financialMain.getRelatedPersonId())
                    .relatedPersonName(commonService.getRelatedPersonName(financialMain.getRelatedPersonId()))
                    .receiptDate(financialMain.getReceiptDate())
                    .receiptNumber(financialMain.getReceiptNumber())
                    .financialPersonId(financialMain.getOperatorId())
                    .financialPersonName(commonService.getOperatorName(financialMain.getOperatorId()))
                    .expenseAccountId(financialMain.getAccountId())
                    .expenseAccountName(commonService.getAccountName(financialMain.getAccountId()))
                    .expenseAmount(financialMain.getTotalAmount())
                    .remark(financialMain.getRemark())
                    .status(financialMain.getStatus())
                    .build();

            var incomeExpenseBOList = financialSubService.lambdaQuery()
                    .eq(FinancialSub::getFinancialMainId, id)
                    .list();

            if (incomeExpenseBOList != null) {
                var incomeExpenseVOList = new ArrayList<IncomeExpenseBO>(incomeExpenseBOList.size() + 1);
                incomeExpenseBOList.forEach(sub -> {
                    var incomeExpense = incomeExpenseService.getById(sub.getIncomeExpenseId());
                    var incomeExpenseVO = IncomeExpenseBO.builder()
                            .incomeExpenseId(sub.getIncomeExpenseId())
                            .incomeExpenseName(incomeExpense.getName())
                            .incomeExpenseAmount(sub.getSingleAmount())
                            .remark(sub.getRemark())
                            .build();
                    incomeExpenseVOList.add(incomeExpenseVO);
                });
                expenseDetailVO.setTableData(incomeExpenseVOList);
            }
            var fileList = commonService.getFileList(financialMain.getFileId());
            expenseDetailVO.setFiles(fileList);
            return Response.responseData(expenseDetailVO);
        }
        return Response.responseMsg(BaseCodeEnum.QUERY_DATA_EMPTY);
    }

    @Override
    public Response<String> addOrUpdateExpenseReceipt(AddOrUpdateExpenseDTO addOrUpdateExpenseDTO) {
        var userId = userService.getCurrentUserId();
        var fid = processFiles(addOrUpdateExpenseDTO.getFiles(), addOrUpdateExpenseDTO.getId());
        var fileIds = StringUtils.collectionToCommaDelimitedString(fid);
        var isUpdate = addOrUpdateExpenseDTO.getId() != null;

        if (isUpdate) {
            var beforeReceipt = financialSubService.lambdaQuery()
                    .eq(FinancialSub::getFinancialMainId, addOrUpdateExpenseDTO.getId())
                    .list();

            financialSubService.lambdaUpdate()
                    .eq(FinancialSub::getFinancialMainId, addOrUpdateExpenseDTO.getId())
                    .remove();

            var financialSubList = addOrUpdateExpenseDTO.getTableData();
            var financialSub = financialSubList.stream()
                    .map(item -> FinancialSub.builder()
                            .id(SnowflakeIdUtil.nextId())
                            .financialMainId(addOrUpdateExpenseDTO.getId())
                            .accountId(addOrUpdateExpenseDTO.getExpenseAccountId())
                            .incomeExpenseId(item.getIncomeExpenseId())
                            .singleAmount(item.getIncomeExpenseAmount())
                            .remark(item.getRemark())
                            .createBy(userId)
                            .createTime(LocalDateTime.now())
                            .build())
                    .collect(Collectors.toList());
            var updateSubResult = financialSubService.saveBatch(financialSub);

            var updateFinancialMain = lambdaUpdate()
                    .eq(FinancialMain::getId, addOrUpdateExpenseDTO.getId())
                    .set(addOrUpdateExpenseDTO.getRelatedPersonId() != null, FinancialMain::getRelatedPersonId, addOrUpdateExpenseDTO.getRelatedPersonId())
                    .set(StringUtils.hasLength(addOrUpdateExpenseDTO.getReceiptDate()), FinancialMain::getReceiptDate, addOrUpdateExpenseDTO.getReceiptDate())
                    .set(addOrUpdateExpenseDTO.getFinancialPersonId() != null, FinancialMain::getOperatorId, addOrUpdateExpenseDTO.getFinancialPersonId())
                    .set(addOrUpdateExpenseDTO.getExpenseAccountId() != null, FinancialMain::getAccountId, addOrUpdateExpenseDTO.getExpenseAccountId())
                    .set(addOrUpdateExpenseDTO.getExpenseAmount() != null, FinancialMain::getTotalAmount, addOrUpdateExpenseDTO.getExpenseAmount())
                    .set(StringUtils.hasLength(addOrUpdateExpenseDTO.getRemark()), FinancialMain::getRemark, addOrUpdateExpenseDTO.getRemark())
                    .set(StringUtils.hasLength(fileIds), FinancialMain::getFileId, fileIds)
                    .set(FinancialMain::getUpdateBy, userId)
                    .set(FinancialMain::getUpdateTime, LocalDateTime.now())
                    .set(addOrUpdateExpenseDTO.getStatus() != null, FinancialMain::getStatus, addOrUpdateExpenseDTO.getStatus())
                    .update();

            var account = accountService.getById(addOrUpdateExpenseDTO.getExpenseAccountId());
            if (account != null) {
                var accountBalance = account.getCurrentAmount();
                var changeAmount = addOrUpdateExpenseDTO.getExpenseAmount();
                var beforeChangeAmount = beforeReceipt.stream()
                        .map(FinancialSub::getSingleAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                accountBalance = accountBalance.add(beforeChangeAmount);
                if (changeAmount != null) {
                    accountBalance = accountBalance.subtract(changeAmount);
                }
                account.setCurrentAmount(accountBalance);
                accountService.updateById(account);
            }

            if (!updateSubResult || !updateFinancialMain) {
                return Response.responseMsg(IncomeExpenseCodeEnum.UPDATE_EXPENSE_RECEIPT_ERROR);
            }
            return Response.responseMsg(IncomeExpenseCodeEnum.UPDATE_EXPENSE_RECEIPT_SUCCESS);

        } else {
            var id = SnowflakeIdUtil.nextId();
            var financialMain = FinancialMain.builder()
                    .id(id)
                    .relatedPersonId(addOrUpdateExpenseDTO.getRelatedPersonId())
                    .receiptDate(TimeUtil.parse(addOrUpdateExpenseDTO.getReceiptDate()))
                    .receiptNumber(addOrUpdateExpenseDTO.getReceiptNumber())
                    .operatorId(addOrUpdateExpenseDTO.getFinancialPersonId())
                    .accountId(addOrUpdateExpenseDTO.getExpenseAccountId())
                    .totalAmount(addOrUpdateExpenseDTO.getExpenseAmount())
                    .remark(addOrUpdateExpenseDTO.getRemark())
                    .type("支出")
                    .fileId(fileIds)
                    .createBy(userId)
                    .createTime(LocalDateTime.now())
                    .status(addOrUpdateExpenseDTO.getStatus())
                    .build();

            var saveResult = save(financialMain);
            var financialSubList = addOrUpdateExpenseDTO.getTableData();
            var financialSub = financialSubList.stream()
                    .map(item -> FinancialSub.builder()
                            .id(SnowflakeIdUtil.nextId())
                            .financialMainId(id)
                            .accountId(addOrUpdateExpenseDTO.getExpenseAccountId())
                            .incomeExpenseId(item.getIncomeExpenseId())
                            .singleAmount(item.getIncomeExpenseAmount())
                            .remark(item.getRemark())
                            .createBy(userId)
                            .createTime(LocalDateTime.now())
                            .build())
                    .collect(Collectors.toList());
            var saveSubResult = financialSubService.saveBatch(financialSub);

            var account = accountService.getById(addOrUpdateExpenseDTO.getExpenseAccountId());
            if (account != null) {
                // 更新余额 划扣相减
                var accountBalance = account.getCurrentAmount();
                var changeAmount = addOrUpdateExpenseDTO.getExpenseAmount();
                if (changeAmount != null) {
                    accountBalance = accountBalance.subtract(changeAmount);
                    account.setId(addOrUpdateExpenseDTO.getExpenseAccountId());
                    account.setCurrentAmount(accountBalance);
                    accountService.updateById(account);
                }
            }

            if (!saveResult || !saveSubResult) {
                return Response.responseMsg(IncomeExpenseCodeEnum.ADD_EXPENSE_RECEIPT_ERROR);
            }
            return Response.responseMsg(IncomeExpenseCodeEnum.ADD_EXPENSE_RECEIPT_SUCCESS);
        }
    }

    @Override
    public Response<String> deleteBatchExpenseReceipt(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Response.responseMsg(BaseCodeEnum.PARAMETER_NULL);
        }
        var deleteResult = lambdaUpdate()
                .set(FinancialMain::getDeleteFlag, CommonConstants.DELETED)
                .in(FinancialMain::getId, ids)
                .update();
        if(!deleteResult) {
            return Response.responseMsg(IncomeExpenseCodeEnum.DELETE_EXPENSE_RECEIPT_ERROR);
        }
        return Response.responseMsg(IncomeExpenseCodeEnum.DELETE_EXPENSE_RECEIPT_SUCCESS);
    }

    @Override
    public Response<String> updateExpenseReceiptStatus(List<Long> ids, Integer status) {
        if (ids == null || ids.isEmpty() || status == null) {
            return Response.responseMsg(BaseCodeEnum.PARAMETER_NULL);
        }
        var updateResult = lambdaUpdate()
                .set(FinancialMain::getStatus, status)
                .in(FinancialMain::getId, ids)
                .update();
        if(!updateResult) {
            return Response.responseMsg(IncomeExpenseCodeEnum.UPDATE_EXPENSE_RECEIPT_ERROR);
        }
        return Response.responseMsg(IncomeExpenseCodeEnum.UPDATE_EXPENSE_RECEIPT_SUCCESS);
    }
}
