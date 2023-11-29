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
package com.wansenai.bo

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.math.BigDecimal
import java.math.RoundingMode

class BigDecimalSerializerBO : JsonSerializer<BigDecimal>() {
    override fun serialize(value: BigDecimal?, gen: JsonGenerator, serializers: SerializerProvider) {
        if (value != null) {
            val convertedValue = convertToPositiveFormatAndRound(value)
            gen.writeNumber(convertedValue)
        } else {
            gen.writeNull()
        }
    }

    private fun convertToPositiveFormatAndRound(value: BigDecimal): BigDecimal {
        val absoluteValue = value.abs()
        val roundedValue = absoluteValue.setScale(2, RoundingMode.HALF_UP)
        return roundedValue
    }
}