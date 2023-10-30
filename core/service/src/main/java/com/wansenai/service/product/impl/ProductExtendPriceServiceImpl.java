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
package com.wansenai.service.product.impl;

import com.wansenai.service.product.ProductExtendPriceService;
import com.wansenai.utils.response.Response;
import com.wansenai.entities.product.ProductExtendPrice;
import com.wansenai.mappers.product.ProductExtendPriceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品价格扩展 服务实现类
 * </p>
 */
@Service
public class ProductExtendPriceServiceImpl extends ServiceImpl<ProductExtendPriceMapper, ProductExtendPrice> implements ProductExtendPriceService {

    private static int currentBarcode = 1000;
    public static int generateBarcode() {
        if (currentBarcode > 9999) {
            throw new IllegalStateException("No more barcodes available.");
        }

        int barcode = currentBarcode;
        currentBarcode++;
        return barcode;
    }

    @Override
    public Response<Integer> getBarCode() {
        var data = lambdaQuery()
                .orderByDesc(ProductExtendPrice::getProductBarCode)
                .last("LIMIT 1").one();
        if(data == null){
            return Response.responseData(1000);
        }
        return Response.responseData(data.getProductBarCode() + 1);
    }

    @Override
    public Boolean checkProductCode(List<String> barCodes) {
        return lambdaQuery().in(ProductExtendPrice::getProductBarCode, barCodes).exists();
    }
}
