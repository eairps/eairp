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
package com.wansenai.service.financial.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wansenai.bo.FileDataBO;
import com.wansenai.bo.IncomeExpenseBO;
import com.wansenai.bo.financial.IncomeExpenseDataExportBO;
import com.wansenai.bo.financial.IncomeExpenseDataExportEnBO;
import com.wansenai.bo.financial.IncomeExportBO;
import com.wansenai.bo.financial.IncomeExportEnBO;
import com.wansenai.dto.financial.AddOrUpdateIncomeDTO;
import com.wansenai.dto.financial.QueryIncomeDTO;
import com.wansenai.entities.financial.FinancialMain;
import com.wansenai.entities.financial.FinancialSub;
import com.wansenai.entities.system.SysFile;
import com.wansenai.mappers.financial.FinancialMainMapper;
import com.wansenai.mappers.system.SysFileMapper;
import com.wansenai.service.basic.IncomeExpenseService;
import com.wansenai.service.common.CommonService;
import com.wansenai.service.financial.FinancialSubService;
import com.wansenai.service.financial.IFinancialAccountService;
import com.wansenai.service.financial.IncomeReceiptService;
import com.wansenai.service.user.ISysUserService;
import com.wansenai.utils.SnowflakeIdUtil;
import com.wansenai.utils.TimeUtil;
import com.wansenai.utils.constants.CommonConstants;
import com.wansenai.utils.enums.BaseCodeEnum;
import com.wansenai.utils.enums.IncomeExpenseCodeEnum;
import com.wansenai.utils.excel.ExcelUtils;
import com.wansenai.utils.response.Response;
import com.wansenai.vo.financial.IncomeDetailVO;
import com.wansenai.vo.financial.IncomeVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class IncomeReceiptServiceImpl extends ServiceImpl<FinancialMainMapper, FinancialMain> implements IncomeReceiptService {

    private final FinancialSubService financialSubService;

    private final CommonService commonService;

    private final ISysUserService userService;

    private final SysFileMapper fileMapper;

    private final IFinancialAccountService accountService;

    private final IncomeExpenseService incomeExpenseService;

    public IncomeReceiptServiceImpl(FinancialSubService financialSubService, CommonService commonService, ISysUserService userService, SysFileMapper fileMapper, IFinancialAccountService accountService, IncomeExpenseService incomeExpenseService) {
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
    public Response<Page<IncomeVO>> getIncomeReceiptPageList(QueryIncomeDTO queryIncomeDTO) {
        var result = new Page<IncomeVO>();
        var page = new Page<FinancialMain>(queryIncomeDTO.getPage(), queryIncomeDTO.getPageSize());

        var financialMainPage = lambdaQuery()
                .eq(queryIncomeDTO.getRelatedPersonId() != null, FinancialMain::getRelatedPersonId, queryIncomeDTO.getRelatedPersonId())
                .eq(queryIncomeDTO.getFinancialPersonId() != null, FinancialMain::getOperatorId, queryIncomeDTO.getFinancialPersonId())
                .eq(queryIncomeDTO.getAccountId() != null, FinancialMain::getAccountId, queryIncomeDTO.getAccountId())
                .eq(StringUtils.hasLength(queryIncomeDTO.getReceiptNumber()), FinancialMain::getReceiptNumber, queryIncomeDTO.getReceiptNumber())
                .eq(queryIncomeDTO.getStatus() != null, FinancialMain::getStatus, queryIncomeDTO.getStatus())
                .like(StringUtils.hasLength(queryIncomeDTO.getRemark()), FinancialMain::getRemark, queryIncomeDTO.getRemark())
                .ge(StringUtils.hasLength(queryIncomeDTO.getStartDate()), FinancialMain::getReceiptDate, queryIncomeDTO.getStartDate())
                .le(StringUtils.hasLength(queryIncomeDTO.getEndDate()), FinancialMain::getReceiptDate, queryIncomeDTO.getEndDate())
                .eq(FinancialMain::getType, "收入")
                .eq(FinancialMain::getDeleteFlag, CommonConstants.NOT_DELETED)
                .orderByDesc(FinancialMain::getCreateTime)
                .page(page);

        var incomeVOList = new ArrayList<IncomeVO>(financialMainPage.getRecords().size() + 1);
        financialMainPage.getRecords().forEach(item -> {
            var incomeVO = IncomeVO.builder()
                    .id(item.getId())
                    .receiptNumber(item.getReceiptNumber())
                    .name(commonService.getRelatedPersonName(item.getRelatedPersonId()))
                    .receiptDate(item.getReceiptDate())
                    .financialPerson(commonService.getOperatorName(item.getOperatorId()))
                    .incomeAccountName(commonService.getAccountName(item.getAccountId()))
                    .incomeAmount(item.getTotalAmount())
                    .status(item.getStatus())
                    .remark(item.getRemark())
                    .build();

            incomeVOList.add(incomeVO);
        });
        result.setRecords(incomeVOList);
        result.setTotal(financialMainPage.getTotal());
        return Response.responseData(result);
    }

    private List<IncomeExportBO> getIncomeReceiptList(QueryIncomeDTO queryIncomeDTO) {
        var financialMainList = lambdaQuery()
                .eq(queryIncomeDTO.getRelatedPersonId() != null, FinancialMain::getRelatedPersonId, queryIncomeDTO.getRelatedPersonId())
                .eq(queryIncomeDTO.getFinancialPersonId() != null, FinancialMain::getOperatorId, queryIncomeDTO.getFinancialPersonId())
                .eq(queryIncomeDTO.getAccountId() != null, FinancialMain::getAccountId, queryIncomeDTO.getAccountId())
                .eq(StringUtils.hasLength(queryIncomeDTO.getReceiptNumber()), FinancialMain::getReceiptNumber, queryIncomeDTO.getReceiptNumber())
                .eq(queryIncomeDTO.getStatus() != null, FinancialMain::getStatus, queryIncomeDTO.getStatus())
                .like(StringUtils.hasLength(queryIncomeDTO.getRemark()), FinancialMain::getRemark, queryIncomeDTO.getRemark())
                .ge(StringUtils.hasLength(queryIncomeDTO.getStartDate()), FinancialMain::getReceiptDate, queryIncomeDTO.getStartDate())
                .le(StringUtils.hasLength(queryIncomeDTO.getEndDate()), FinancialMain::getReceiptDate, queryIncomeDTO.getEndDate())
                .eq(FinancialMain::getType, "收入")
                .eq(FinancialMain::getDeleteFlag, CommonConstants.NOT_DELETED)
                .list();

        var incomeExportBOList = new ArrayList<IncomeExportBO>(financialMainList.size() + 1);
        financialMainList.forEach(item -> {
            var incomeExportBO = IncomeExportBO.builder()
                    .id(item.getId())
                    .receiptNumber(item.getReceiptNumber())
                    .name(commonService.getRelatedPersonName(item.getRelatedPersonId()))
                    .receiptDate(item.getReceiptDate())
                    .financialPerson(commonService.getOperatorName(item.getOperatorId()))
                    .incomeAccountName(commonService.getAccountName(item.getAccountId()))
                    .incomeAmount(item.getTotalAmount())
                    .status(item.getStatus())
                    .remark(item.getRemark())
                    .build();

            incomeExportBOList.add(incomeExportBO);
        });
        return incomeExportBOList;
    }

    private List<IncomeExportEnBO> getIncomeReceiptEnList(QueryIncomeDTO queryIncomeDTO) {
        var financialMainList = lambdaQuery()
                .eq(queryIncomeDTO.getRelatedPersonId() != null, FinancialMain::getRelatedPersonId, queryIncomeDTO.getRelatedPersonId())
                .eq(queryIncomeDTO.getFinancialPersonId() != null, FinancialMain::getOperatorId, queryIncomeDTO.getFinancialPersonId())
                .eq(queryIncomeDTO.getAccountId() != null, FinancialMain::getAccountId, queryIncomeDTO.getAccountId())
                .eq(StringUtils.hasLength(queryIncomeDTO.getReceiptNumber()), FinancialMain::getReceiptNumber, queryIncomeDTO.getReceiptNumber())
                .eq(queryIncomeDTO.getStatus() != null, FinancialMain::getStatus, queryIncomeDTO.getStatus())
                .like(StringUtils.hasLength(queryIncomeDTO.getRemark()), FinancialMain::getRemark, queryIncomeDTO.getRemark())
                .ge(StringUtils.hasLength(queryIncomeDTO.getStartDate()), FinancialMain::getReceiptDate, queryIncomeDTO.getStartDate())
                .le(StringUtils.hasLength(queryIncomeDTO.getEndDate()), FinancialMain::getReceiptDate, queryIncomeDTO.getEndDate())
                .eq(FinancialMain::getType, "收入")
                .eq(FinancialMain::getDeleteFlag, CommonConstants.NOT_DELETED)
                .list();

        var incomeExportEnBOList = new ArrayList<IncomeExportEnBO>(financialMainList.size() + 1);
        financialMainList.forEach(item -> {
            var incomeExportEnBO = IncomeExportEnBO.builder()
                    .id(item.getId())
                    .receiptNumber(item.getReceiptNumber())
                    .name(commonService.getRelatedPersonName(item.getRelatedPersonId()))
                    .receiptDate(item.getReceiptDate())
                    .financialPerson(commonService.getOperatorName(item.getOperatorId()))
                    .incomeAccountName(commonService.getAccountName(item.getAccountId()))
                    .incomeAmount(item.getTotalAmount())
                    .status(item.getStatus())
                    .remark(item.getRemark())
                    .build();

            incomeExportEnBOList.add(incomeExportEnBO);
        });
        return incomeExportEnBOList;
    }

    @Override
    public Response<IncomeDetailVO> getIncomeReceiptDetail(Long id) {
        if (id == null) {
            return Response.responseMsg(BaseCodeEnum.PARAMETER_NULL);
        }

        var financialMain = getById(id);
        if(financialMain != null) {
            var incomeDetailVO = IncomeDetailVO.builder()
                    .relatedPersonId(financialMain.getRelatedPersonId())
                    .relatedPersonName(commonService.getRelatedPersonName(financialMain.getRelatedPersonId()))
                    .receiptDate(financialMain.getReceiptDate())
                    .receiptNumber(financialMain.getReceiptNumber())
                    .financialPersonId(financialMain.getOperatorId())
                    .financialPersonName(commonService.getOperatorName(financialMain.getOperatorId()))
                    .incomeAccountId(financialMain.getAccountId())
                    .incomeAccountName(commonService.getAccountName(financialMain.getAccountId()))
                    .incomeAmount(financialMain.getTotalAmount())
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
                incomeDetailVO.setTableData(incomeExpenseVOList);
            }
            var fileList = commonService.getFileList(financialMain.getFileId());
            incomeDetailVO.setFiles(fileList);
            return Response.responseData(incomeDetailVO);
        }
        return Response.responseMsg(BaseCodeEnum.QUERY_DATA_EMPTY);
    }

    @Override
    public Response<String> addOrUpdateIncomeReceipt(AddOrUpdateIncomeDTO addOrUpdateIncomeDTO) {
        var userId = userService.getCurrentUserId();
        var systemLanguage = userService.getUserSystemLanguage(userId);
        var fid = processFiles(addOrUpdateIncomeDTO.getFiles(), addOrUpdateIncomeDTO.getId());
        var fileIds = StringUtils.collectionToCommaDelimitedString(fid);
        var isUpdate = addOrUpdateIncomeDTO.getId() != null;

        if (isUpdate) {
            var beforeReceipt = financialSubService.lambdaQuery()
                    .eq(FinancialSub::getFinancialMainId, addOrUpdateIncomeDTO.getId())
                    .list();

            financialSubService.lambdaUpdate()
                    .eq(FinancialSub::getFinancialMainId, addOrUpdateIncomeDTO.getId())
                    .remove();

            var financialSubList = addOrUpdateIncomeDTO.getTableData();
            var financialSub = financialSubList.stream()
                    .map(item -> FinancialSub.builder()
                            .id(SnowflakeIdUtil.nextId())
                            .financialMainId(addOrUpdateIncomeDTO.getId())
                            .accountId(addOrUpdateIncomeDTO.getIncomeAccountId())
                            .incomeExpenseId(item.getIncomeExpenseId())
                            .singleAmount(item.getIncomeExpenseAmount())
                            .remark(item.getRemark())
                            .createBy(userId)
                            .createTime(LocalDateTime.now())
                            .build())
                    .collect(Collectors.toList());
            var updateSubResult = financialSubService.saveBatch(financialSub);

            var updateFinancialMain = lambdaUpdate()
                    .eq(FinancialMain::getId, addOrUpdateIncomeDTO.getId())
                    .set(addOrUpdateIncomeDTO.getRelatedPersonId() != null, FinancialMain::getRelatedPersonId, addOrUpdateIncomeDTO.getRelatedPersonId())
                    .set(StringUtils.hasLength(addOrUpdateIncomeDTO.getReceiptDate()), FinancialMain::getReceiptDate, addOrUpdateIncomeDTO.getReceiptDate())
                    .set(addOrUpdateIncomeDTO.getFinancialPersonId() != null, FinancialMain::getOperatorId, addOrUpdateIncomeDTO.getFinancialPersonId())
                    .set(addOrUpdateIncomeDTO.getIncomeAccountId() != null, FinancialMain::getAccountId, addOrUpdateIncomeDTO.getIncomeAccountId())
                    .set(addOrUpdateIncomeDTO.getIncomeAmount() != null, FinancialMain::getTotalAmount, addOrUpdateIncomeDTO.getIncomeAmount())
                    .set(StringUtils.hasLength(addOrUpdateIncomeDTO.getRemark()), FinancialMain::getRemark, addOrUpdateIncomeDTO.getRemark())
                    .set(StringUtils.hasLength(fileIds), FinancialMain::getFileId, fileIds)
                    .set(FinancialMain::getUpdateBy, userId)
                    .set(FinancialMain::getUpdateTime, LocalDateTime.now())
                    .set(addOrUpdateIncomeDTO.getStatus() != null, FinancialMain::getStatus, addOrUpdateIncomeDTO.getStatus())
                    .update();

            var account = accountService.getById(addOrUpdateIncomeDTO.getIncomeAccountId());
            if (account != null) {
                var accountBalance = account.getCurrentAmount();
                var changeAmount = addOrUpdateIncomeDTO.getIncomeAmount();
                var beforeChangeAmount = beforeReceipt.stream()
                        .map(FinancialSub::getSingleAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                accountBalance = accountBalance.subtract(beforeChangeAmount);
                if (changeAmount != null) {
                    accountBalance = accountBalance.add(changeAmount);
                }
                account.setCurrentAmount(accountBalance);
                accountService.updateById(account);
            }

            if (!updateSubResult || !updateFinancialMain) {
                if ("zh_CN".equals(systemLanguage)) {
                    return Response.responseMsg(IncomeExpenseCodeEnum.UPDATE_INCOME_RECEIPT_ERROR);
                }
                return Response.responseMsg(IncomeExpenseCodeEnum.UPDATE_INCOME_RECEIPT_ERROR_EN);
            } else {
                if ("zh_CN".equals(systemLanguage)) {
                    return Response.responseMsg(IncomeExpenseCodeEnum.UPDATE_INCOME_RECEIPT_SUCCESS);
                }
                return Response.responseMsg(IncomeExpenseCodeEnum.UPDATE_INCOME_RECEIPT_SUCCESS_EN);
            }

        } else {
            var id = SnowflakeIdUtil.nextId();
            var financialMain = FinancialMain.builder()
                    .id(id)
                    .relatedPersonId(addOrUpdateIncomeDTO.getRelatedPersonId())
                    .receiptDate(TimeUtil.parse(addOrUpdateIncomeDTO.getReceiptDate()))
                    .receiptNumber(addOrUpdateIncomeDTO.getReceiptNumber())
                    .operatorId(addOrUpdateIncomeDTO.getFinancialPersonId())
                    .accountId(addOrUpdateIncomeDTO.getIncomeAccountId())
                    .totalAmount(addOrUpdateIncomeDTO.getIncomeAmount())
                    .remark(addOrUpdateIncomeDTO.getRemark())
                    .type("收入")
                    .fileId(fileIds)
                    .createBy(userId)
                    .createTime(LocalDateTime.now())
                    .status(addOrUpdateIncomeDTO.getStatus())
                    .build();

            var saveResult = save(financialMain);
            var financialSubList = addOrUpdateIncomeDTO.getTableData();
            var financialSub = financialSubList.stream()
                    .map(item -> FinancialSub.builder()
                            .id(SnowflakeIdUtil.nextId())
                            .financialMainId(id)
                            .accountId(addOrUpdateIncomeDTO.getIncomeAccountId())
                            .incomeExpenseId(item.getIncomeExpenseId())
                            .singleAmount(item.getIncomeExpenseAmount())
                            .remark(item.getRemark())
                            .createBy(userId)
                            .createTime(LocalDateTime.now())
                            .build())
                    .collect(Collectors.toList());
            var saveSubResult = financialSubService.saveBatch(financialSub);

            var account = accountService.getById(addOrUpdateIncomeDTO.getIncomeAccountId());
            if (account != null) {
                // 更新余额
                var accountBalance = account.getCurrentAmount();
                var changeAmount = addOrUpdateIncomeDTO.getIncomeAmount();
                if (changeAmount != null) {
                    accountBalance = accountBalance.add(changeAmount);
                    account.setId(addOrUpdateIncomeDTO.getIncomeAccountId());
                    account.setCurrentAmount(accountBalance);
                    accountService.updateById(account);
                }
            }

            if (!saveResult || !saveSubResult) {
                if ("zh_CN".equals(systemLanguage)) {
                    return Response.responseMsg(IncomeExpenseCodeEnum.ADD_INCOME_RECEIPT_ERROR);
                }
                return Response.responseMsg(IncomeExpenseCodeEnum.ADD_INCOME_RECEIPT_ERROR_EN);
            } else {
                if ("zh_CN".equals(systemLanguage)) {
                    return Response.responseMsg(IncomeExpenseCodeEnum.ADD_INCOME_RECEIPT_SUCCESS);
                }
                return Response.responseMsg(IncomeExpenseCodeEnum.ADD_INCOME_RECEIPT_SUCCESS_EN);
            }
        }
    }

    @Override
    public Response<String> deleteBatchIncomeReceipt(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Response.responseMsg(BaseCodeEnum.PARAMETER_NULL);
        }
        var deleteResult = lambdaUpdate()
                .set(FinancialMain::getDeleteFlag, CommonConstants.DELETED)
                .in(FinancialMain::getId, ids)
                .update();
        var systemLanguage = userService.getUserSystemLanguage(userService.getCurrentUserId());
        if(!deleteResult) {
            if ("zh_CN".equals(systemLanguage)) {
                return Response.responseMsg(IncomeExpenseCodeEnum.DELETE_INCOME_RECEIPT_ERROR);
            }
            return Response.responseMsg(IncomeExpenseCodeEnum.DELETE_INCOME_RECEIPT_ERROR_EN);
        } else {
            if ("zh_CN".equals(systemLanguage)) {
                return Response.responseMsg(IncomeExpenseCodeEnum.DELETE_INCOME_RECEIPT_SUCCESS);
            }
            return Response.responseMsg(IncomeExpenseCodeEnum.DELETE_INCOME_RECEIPT_SUCCESS_EN);
        }
    }

    @Override
    public Response<String> updateIncomeReceiptStatus(List<Long> ids, Integer status) {
       if (ids == null || ids.isEmpty() || status == null) {
           return Response.responseMsg(BaseCodeEnum.PARAMETER_NULL);
       }
         var updateResult = lambdaUpdate()
                .set(FinancialMain::getStatus, status)
                .in(FinancialMain::getId, ids)
                .update();
        var systemLanguage = userService.getUserSystemLanguage(userService.getCurrentUserId());
        if(!updateResult) {
            if ("zh_CN".equals(systemLanguage)) {
                return Response.responseMsg(IncomeExpenseCodeEnum.UPDATE_INCOME_RECEIPT_ERROR);
            }
            return Response.responseMsg(IncomeExpenseCodeEnum.UPDATE_INCOME_RECEIPT_ERROR_EN);
        } else {
            if ("zh_CN".equals(systemLanguage)) {
                return Response.responseMsg(IncomeExpenseCodeEnum.UPDATE_INCOME_RECEIPT_SUCCESS);
            }
            return Response.responseMsg(IncomeExpenseCodeEnum.UPDATE_INCOME_RECEIPT_SUCCESS_EN);
        }
    }

    @Override
    public void exportIncomeReceipt(QueryIncomeDTO queryIncomeDTO, HttpServletResponse response) {
        var exportMap = new ConcurrentHashMap<String, List<List<Object>>>();
        var systemLanguage = userService.getUserSystemLanguage(userService.getCurrentUserId());
        if ("zh_CN".equals(systemLanguage)) {
            var mainData = getIncomeReceiptList(queryIncomeDTO);
            if (!mainData.isEmpty()) {
                exportMap.put("收入单", ExcelUtils.getSheetData(mainData));
                if (queryIncomeDTO.getIsExportDetail()) {
                    var subData = new ArrayList<IncomeExpenseDataExportBO>();
                    for (IncomeExportBO incomeExportBO : mainData) {
                        var detail = getIncomeReceiptDetail(incomeExportBO.getId()).getData().getTableData();
                        if (!detail.isEmpty()) {
                            detail.forEach(item -> {
                                var data = IncomeExpenseDataExportBO.builder()
                                        .receiptNumber(incomeExportBO.getReceiptNumber())
                                        .relatedPerson(incomeExportBO.getName())
                                        .incomeExpenseName(item.getIncomeExpenseName())
                                        .incomeExpenseAmount(item.getIncomeExpenseAmount())
                                        .remark(item.getRemark())
                                        .build();
                                subData.add(data);
                            });
                        }
                    }
                    exportMap.put("收入单明细", ExcelUtils.getSheetData(subData));
                }
                ExcelUtils.exportManySheet(response, "收入单", exportMap);
            }
        } else {
            var mainEnData = getIncomeReceiptEnList(queryIncomeDTO);
            if (!mainEnData.isEmpty()) {
                exportMap.put("Income Document", ExcelUtils.getSheetData(mainEnData));
                if (queryIncomeDTO.getIsExportDetail()) {
                    var subEnData = new ArrayList<IncomeExpenseDataExportEnBO>();
                    for (IncomeExportEnBO incomeExportEnBO : mainEnData) {
                        var detail = getIncomeReceiptDetail(incomeExportEnBO.getId()).getData().getTableData();
                        if (!detail.isEmpty()) {
                            detail.forEach(item -> {
                                var data = IncomeExpenseDataExportEnBO.builder()
                                        .receiptNumber(incomeExportEnBO.getReceiptNumber())
                                        .relatedPerson(incomeExportEnBO.getName())
                                        .incomeExpenseName(item.getIncomeExpenseName())
                                        .incomeExpenseAmount(item.getIncomeExpenseAmount())
                                        .remark(item.getRemark())
                                        .build();
                                subEnData.add(data);
                            });
                        }
                    }
                    exportMap.put("Income Document Details", ExcelUtils.getSheetData(subEnData));
                }
                ExcelUtils.exportManySheet(response, "Income Document", exportMap);
            }
        }
    }

    @Override
    public void exportIncomeReceiptDetail(String receiptNumber, HttpServletResponse response) {
        var id = lambdaQuery()
                .eq(FinancialMain::getReceiptNumber, receiptNumber)
                .eq(FinancialMain::getDeleteFlag, CommonConstants.NOT_DELETED)
                .eq(FinancialMain::getType, "收入")
                .one()
                .getId();

        var detail = getIncomeReceiptDetail(id);
        if(detail.getData() != null) {
            var data = detail.getData();
            var tableData = data.getTableData();
            var systemLanguage = userService.getUserSystemLanguage(userService.getCurrentUserId());
            if ("zh_CN".equals(systemLanguage)) {
                var exportData = new ArrayList<IncomeExpenseDataExportBO>();
                tableData.forEach(item -> {
                    var incomeDataBO = new IncomeExpenseDataExportBO();
                    incomeDataBO.setReceiptNumber(data.getReceiptNumber());
                    incomeDataBO.setRelatedPerson(data.getRelatedPersonName());
                    BeanUtils.copyProperties(item, incomeDataBO);
                    exportData.add(incomeDataBO);
                });
                var fileName = data.getReceiptNumber() + "-收入单明细";
                ExcelUtils.export(response, fileName, ExcelUtils.getSheetData(exportData));
            } else {
                var exportEnData = new ArrayList<IncomeExpenseDataExportEnBO>();
                tableData.forEach(item -> {
                    var incomeDataEnBO = new IncomeExpenseDataExportEnBO();
                    incomeDataEnBO.setReceiptNumber(data.getReceiptNumber());
                    incomeDataEnBO.setRelatedPerson(data.getRelatedPersonName());
                    BeanUtils.copyProperties(item, incomeDataEnBO);
                    exportEnData.add(incomeDataEnBO);
                });
                var fileName = data.getReceiptNumber() + "- Income Document Details";
                ExcelUtils.export(response, fileName, ExcelUtils.getSheetData(exportEnData));
            }
        }
    }
}
