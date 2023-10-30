/*
 * Copyright (C) 2023-2033 WanSen AI Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wansenai.vo.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wansenai.bo.BigDecimalSerializerBO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDetailVO {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long productId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long productCategoryId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long productUnitId;

    private String productName;

    private String productStandard;

    private String productModel;

    private String productColor;

    private String productUnit;

    @JsonSerialize(using = BigDecimalSerializerBO.class)
    private BigDecimal productWeight;

    private Integer productExpiryNum;

    private String productCategoryName;

    private String remark;
    //
    // TODO
    // The @JsonFormat has been added here because the front-end is a select component,
    // and the value values they bind correspond to string types of 0 and 1
    //
    // Vue strictly compares the values bound to the v-model with the value attribute of the option,
    // which means that their types and values must match exactly.
    //
    // So we did Json conversion here, but in fact,
    // they still maintain the Integer type in the database and backend logic.
    //
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Integer enableSerialNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Integer enableBatchNumber;

    private String warehouseShelves;

    private List<ProductPriceVO> priceList;

    // Extended information
    private String productManufacturer;

    private String otherFieldOne;

    private String otherFieldTwo;

    private String otherFieldThree;

    // Product stock information
    private List<ProductStockVO> stockList;

    // Image list information
    private List<ProductImageVO> imageList;
}
