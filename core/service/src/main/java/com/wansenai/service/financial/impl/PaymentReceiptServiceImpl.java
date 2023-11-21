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

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wansenai.entities.financial.FinancialMain;
import com.wansenai.mappers.financial.FinancialMainMapper;
import com.wansenai.service.financial.PaymentReceiptService;
import org.springframework.stereotype.Service;

@Service
public class PaymentReceiptServiceImpl extends ServiceImpl<FinancialMainMapper, FinancialMain> implements PaymentReceiptService {
}
