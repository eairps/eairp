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
package com.wansenai.api.receipt;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wansenai.dto.receipt.purchase.*;
import com.wansenai.service.receipt.ReceiptPurchaseService;
import com.wansenai.utils.response.Response;
import com.wansenai.vo.receipt.purchase.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("purchase")
public class PurchaseController {

    private final ReceiptPurchaseService purchaseService;

    public PurchaseController(ReceiptPurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/order/pageList")
    public Response<Page<PurchaseOrderVO>> pageList(@ModelAttribute QueryPurchaseOrderDTO queryPurchaseOrderDTO) {
        return purchaseService.getPurchaseOrderPage(queryPurchaseOrderDTO);
    }

    @PostMapping("/order/addOrUpdate")
    public Response<String> addOrUpdate(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        return purchaseService.addOrUpdatePurchaseOrder(purchaseOrderDTO);
    }

    @GetMapping("/order/detail/{id}")
    public Response<PurchaseOrderDetailVO> detail(@PathVariable("id") Long id) {
        return purchaseService.getPurchaseOrderDetail(id);
    }

    @GetMapping("/order/getLinkOrderDetail/{receiptNumber}")
    public Response<PurchaseOrderDetailVO> getLinkPurchaseOrderDetail(@PathVariable("receiptNumber") String receiptNumber) {
        return purchaseService.getLinkPurchaseOrderDetail(receiptNumber);
    }

    @PutMapping("/order/updateStatus/{ids}/{status}")
    public Response<String> updateStatus(@PathVariable("ids") List<Long> ids, @PathVariable("status") Integer status) {
        return purchaseService.updatePurchaseOrderStatus(ids, status);
    }

    @PutMapping("/order/delete/{ids}")
    public Response<String> delete(@PathVariable("ids") List<Long> ids) {
        return purchaseService.deletePurchaseOrder(ids);
    }

    @GetMapping("/storage/pageList")
    public Response<Page<PurchaseStorageVO>> getPurchaseStoragePageList(@ModelAttribute QueryPurchaseStorageDTO queryPurchaseStorageDTO) {
        return purchaseService.getPurchaseStoragePage(queryPurchaseStorageDTO);
    }

    @PostMapping("/storage/addOrUpdate")
    public Response<String> addOrUpdatePurchaseStorage(@RequestBody PurchaseStorageDTO purchaseStorageDTO) {
        return purchaseService.addOrUpdatePurchaseStorage(purchaseStorageDTO);
    }

    @GetMapping("/storage/detail/{id}")
    public Response<PurchaseStorageDetailVO> purchaseStorageDetail(@PathVariable("id") Long id) {
        return purchaseService.getPurchaseStorageDetail(id);
    }

    @GetMapping("/storage/getLinkStorageDetail/{receiptNumber}")
    public Response<PurchaseStorageDetailVO> getLinkPurchaseStorageDetail(@PathVariable("receiptNumber") String receiptNumber) {
        return purchaseService.getLinkPurchaseStorageDetail(receiptNumber);
    }

    @PutMapping("/storage/updateStatus/{ids}/{status}")
    public Response<String> updatePurchaseStorageStatus(@PathVariable("ids") List<Long> ids, @PathVariable("status") Integer status) {
        return purchaseService.updatePurchaseStorageStatus(ids, status);
    }

    @PutMapping("/storage/delete/{ids}")
    public Response<String> deletePurchaseStorage(@PathVariable("ids") List<Long> ids) {
        return purchaseService.deletePurchaseStorage(ids);
    }

    @GetMapping("/refund/pageList")
    public Response<Page<PurchaseRefundVO>> getPurchaseRefundPageList(@ModelAttribute QueryPurchaseRefundDTO queryPurchaseRefundDTO) {
        return purchaseService.getPurchaseRefundPage(queryPurchaseRefundDTO);
    }

    @PostMapping("/refund/addOrUpdate")
    public Response<String> addOrUpdatePurchaseRefund(@RequestBody PurchaseRefundDTO purchaseRefundDTO) {
        return purchaseService.addOrUpdatePurchaseRefund(purchaseRefundDTO);
    }

    @GetMapping("/refund/detail/{id}")
    public Response<PurchaseRefundDetailVO> purchaseRefundDetail(@PathVariable("id") Long id) {
        return purchaseService.getPurchaseRefundDetail(id);
    }

    @GetMapping("/refund/getLinkRefundDetail/{receiptNumber}")
    public Response<PurchaseRefundDetailVO> getLinkPurchaseRefundDetail(@PathVariable("receiptNumber") String receiptNumber) {
        return purchaseService.getLinkPurchaseRefundDetail(receiptNumber);
    }

    @PutMapping("/refund/updateStatus/{ids}/{status}")
    public Response<String> updatePurchaseRefundStatus(@PathVariable("ids") List<Long> ids, @PathVariable("status") Integer status) {
        return purchaseService.updatePurchaseRefundStatus(ids, status);
    }

    @PutMapping("/refund/delete/{ids}")
    public Response<String> deletePurchaseRefund(@PathVariable("ids") List<Long> ids) {
        return purchaseService.deletePurchaseRefund(ids);
    }

    @PostMapping("/arrears/pageList")
    public Response<Page<PurchaseArrearsVO>> arrearsPageList(@RequestBody QueryPurchaseArrearsDTO arrearsDTO) {
        return purchaseService.getPurchaseArrearsPage(arrearsDTO);
    }

    @GetMapping("/order/export")
    public void exportOrderExcel(@ModelAttribute QueryPurchaseOrderDTO queryPurchaseOrderDTO, HttpServletResponse response) throws Exception {
        purchaseService.exportPurchaseOrderExcel(queryPurchaseOrderDTO, response);
    }

    @GetMapping("/order/exportDetail/{receiptNumber}")
    public void exportOrderDetailExcel(@PathVariable("receiptNumber") String receiptNumber, HttpServletResponse response) throws Exception {
        purchaseService.exportPurchaseOrderDetailExcel(receiptNumber, response);
    }

    @GetMapping("/storage/export")
    public void exportStorageExcel(@ModelAttribute QueryPurchaseStorageDTO queryPurchaseStorageDTO, HttpServletResponse response) throws Exception {
        purchaseService.exportPurchaseStorageExcel(queryPurchaseStorageDTO, response);
    }

    @GetMapping("/storage/exportDetail/{receiptNumber}")
    public void exportStorageDetailExcel(@PathVariable("receiptNumber") String receiptNumber, HttpServletResponse response) throws Exception {
        purchaseService.exportPurchaseStorageDetailExcel(receiptNumber, response);
    }

    @GetMapping("/refund/export")
    public void exportRefundExcel(@ModelAttribute QueryPurchaseRefundDTO queryPurchaseRefundDTO, HttpServletResponse response) throws Exception {
        purchaseService.exportPurchaseRefundExcel(queryPurchaseRefundDTO, response);
    }

    @GetMapping("/refund/exportDetail/{receiptNumber}")
    public void exportRefundDetailExcel(@PathVariable("receiptNumber") String receiptNumber, HttpServletResponse response) throws Exception {
        purchaseService.exportPurchaseRefundDetailExcel(receiptNumber, response);
    }
}
