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
import com.wansenai.dto.receipt.sale.*;
import com.wansenai.service.receipt.ReceiptSaleService;
import com.wansenai.utils.response.Response;
import com.wansenai.vo.receipt.sale.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sale")
public class SalesController {

    private final ReceiptSaleService receiptSaleService;

    public SalesController(ReceiptSaleService receiptSaleService) {
        this.receiptSaleService = receiptSaleService;
    }

    @GetMapping("/order/pageList")
    public Response<Page<SaleOrderVO>> pageList(@ModelAttribute QuerySaleOrderDTO querySaleOrderDTO) {
        return receiptSaleService.getSaleOrderPage(querySaleOrderDTO);
    }

    @PostMapping("/order/addOrUpdate")
    public Response<String> addOrUpdate(@RequestBody SaleOrderDTO saleOrderDTO) {
        return receiptSaleService.addOrUpdateSaleOrder(saleOrderDTO);
    }

    @GetMapping("/order/detail/{id}")
    public Response<SaleOrderDetailVO> detail(@PathVariable("id") Long id) {
        return receiptSaleService.getSaleOrderDetail(id);
    }

    @GetMapping("/order/getLinkOrderDetail/{receiptNumber}")
    public Response<SaleOrderDetailVO> getLinkOrderDetail(@PathVariable("receiptNumber") String receiptNumber) {
        return receiptSaleService.getLinkSaleOrderDetail(receiptNumber);
    }

    @PutMapping("/order/updateStatus/{ids}/{status}")
    public Response<String> updateStatus(@PathVariable("ids") List<Long> ids, @PathVariable("status") Integer status) {
        return receiptSaleService.updateSaleOrderStatus(ids, status);
    }

    @PutMapping("/order/delete/{ids}")
    public Response<String> delete(@PathVariable("ids") List<Long> ids) {
        return receiptSaleService.deleteSaleOrder(ids);
    }

    @GetMapping("/shipments/pageList")
    public Response<Page<SaleShipmentsVO>> shipmentsPageList(@ModelAttribute QuerySaleShipmentsDTO shipmentsDTO) {
        return receiptSaleService.getSaleShipmentsPage(shipmentsDTO);
    }

    @PostMapping("/shipments/addOrUpdate")
    public Response<String> addOrUpdateShipments(@RequestBody SaleShipmentsDTO shipmentsDTO) {
        return receiptSaleService.addOrUpdateSaleShipments(shipmentsDTO);
    }

    @GetMapping("/shipments/detail/{id}")
    public Response<SaleShipmentsDetailVO> shipmentsDetail(@PathVariable("id") Long id) {
        return receiptSaleService.getSaleShipmentsDetail(id);
    }

    @GetMapping("/shipments/getLinkShipmentDetail/{receiptNumber}")
    public Response<SaleShipmentsDetailVO> getLinkShipmentDetail(@PathVariable("receiptNumber") String receiptNumber) {
        return receiptSaleService.getLinkSaleShipmentsDetail(receiptNumber);
    }

    @PutMapping("/shipments/updateStatus/{ids}/{status}")
    public Response<String> updateShipmentsStatus(@PathVariable("ids") List<Long> ids, @PathVariable("status") Integer status) {
        return receiptSaleService.updateSaleShipmentsStatus(ids, status);
    }

    @PutMapping("/shipments/delete/{ids}")
    public Response<String> deleteShipments(@PathVariable("ids") List<Long> ids) {
        return receiptSaleService.deleteSaleShipments(ids);
    }

    @GetMapping("/refund/pageList")
    public Response<Page<SaleRefundVO>> refundPageList(@ModelAttribute QuerySaleRefundDTO refundDTO) {
        return receiptSaleService.getSaleRefundPage(refundDTO);
    }

    @PostMapping("/refund/addOrUpdate")
    public Response<String> addOrUpdateRefund(@RequestBody SaleRefundDTO refundDTO) {
        return receiptSaleService.addOrUpdateSaleRefund(refundDTO);
    }

    @GetMapping("/refund/detail/{id}")
    public Response<SaleRefundDetailVO> refundDetail(@PathVariable("id") Long id) {
        return receiptSaleService.getSaleRefundDetail(id);
    }

    @GetMapping("/refund/getLinkRefundDetail/{receiptNumber}")
    public Response<SaleRefundDetailVO> getLinkRefundDetail(@PathVariable("receiptNumber") String receiptNumber) {
        return receiptSaleService.getLinkSaleRefundDetail(receiptNumber);
    }

    @PutMapping("/refund/updateStatus/{ids}/{status}")
    public Response<String> updateRefundStatus(@PathVariable("ids") List<Long> ids, @PathVariable("status") Integer status) {
        return receiptSaleService.updateSaleRefundStatus(ids, status);
    }

    @PutMapping("/refund/delete/{ids}")
    public Response<String> deleteRefund(@PathVariable("ids") List<Long> ids) {
        return receiptSaleService.deleteSaleRefund(ids);
    }

    @PostMapping("/arrears/pageList")
    public Response<Page<SaleArrearsVO>> arrearsPageList(@RequestBody QuerySaleArrearsDTO arrearsDTO) {
        return receiptSaleService.getSaleArrearsPage(arrearsDTO);
    }

    @GetMapping("/order/export")
    public void exportOrderExcel(@ModelAttribute QuerySaleOrderDTO querySaleOrderDTO, HttpServletResponse response) throws Exception {
        receiptSaleService.exportSaleOrderExcel(querySaleOrderDTO, response);
    }

    @GetMapping("/order/exportDetail/{receiptNumber}")
    public void exportSaleOrderDetailExcel(@PathVariable("receiptNumber") String receiptNumber, HttpServletResponse response) throws Exception {
        receiptSaleService.exportSaleOrderDetailExcel(receiptNumber, response);
    }

    @GetMapping("/shipments/export")
    public void exportSaleShipmentsExcel(@ModelAttribute QuerySaleShipmentsDTO querySaleShipmentsDTO, HttpServletResponse response) throws Exception {
        receiptSaleService.exportSaleShipmentsExcel(querySaleShipmentsDTO, response);
    }

    @GetMapping("/shipments/exportDetail/{receiptNumber}")
    public void exportSaleShipmentsDetailExcel(@PathVariable("receiptNumber") String receiptNumber, HttpServletResponse response) throws Exception {
        receiptSaleService.exportSaleShipmentsDetailExcel(receiptNumber, response);
    }


    @GetMapping("/refund/export")
    public void exportRefundExcel(@ModelAttribute QuerySaleRefundDTO querySaleRefundDTO, HttpServletResponse response) throws Exception {
        receiptSaleService.exportSaleRefundExcel(querySaleRefundDTO, response);
    }

    @GetMapping("/refund/exportDetail/{receiptNumber}")
    public void exportSaleRefundDetailExcel(@PathVariable("receiptNumber") String receiptNumber, HttpServletResponse response) throws Exception {
        receiptSaleService.exportSaleRefundDetailExcel(receiptNumber, response);
    }

}
