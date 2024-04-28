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
package com.wansenai.api.common;

import com.wansenai.api.RateLimitException;
import com.wansenai.api.config.RateLimiter;
import com.wansenai.service.common.CommonService;
import com.wansenai.utils.ExcelUtil;
import com.wansenai.utils.enums.LimitType;
import com.wansenai.utils.response.Response;
import com.wansenai.utils.constants.ApiVersionConstants;
import com.wansenai.utils.enums.BaseCodeEnum;
import com.wansenai.vo.CaptchaVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.List;


@RestController
@RequestMapping(ApiVersionConstants.API_CLASS_VERSION_V2 + "common")
@Slf4j
public class CommonController {

    private final CommonService commonService;

    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping( "captcha")
    public Response<CaptchaVO> getCaptcha() {
        CaptchaVO captchaVo = commonService.getCaptcha();
        if(captchaVo == null) {
            return Response.responseMsg(BaseCodeEnum.ERROR);
        }
        return Response.responseData(captchaVo);
    }

    @ExceptionHandler(RateLimitException.class)
    public Response<String> handleRateLimitException(RateLimitException ex) {
        return Response.responseMsg(BaseCodeEnum.FREQUENT_SYSTEM_ACCESS);
    }

    @GetMapping("sms/{type}/{phoneNumber}")
    @RateLimiter(key = "sms", time = 120, count = 1, limitType = LimitType.IP)
    public Response<String> sendSmsCode(@PathVariable Integer type, @PathVariable String phoneNumber) {
        boolean result = commonService.sendSmsCode(type, phoneNumber);
        if(!result) {
            return Response.responseMsg(BaseCodeEnum.PHONE_NUMBER_FORMAT_ERROR);
        }
        return Response.responseMsg(BaseCodeEnum.SMS_VERIFY_SEND_SUCCESS);
    }

    @GetMapping("email/{type}/{email}")
    public Response<String> sendEmailCode(@PathVariable Integer type, @PathVariable String email) {
        return commonService.sendEmailCode(type, email);
    }

    @PostMapping("upload/excel")
    public Response<String> uploadExclsData(@RequestParam("file") MultipartFile file) {
        return commonService.uploadExclsData(file);
    }

    @PostMapping("upload/productCoverUpload")
    public Response<String> productCoverUpload(@RequestParam("file") MultipartFile file, @RequestParam("type") Integer type) {
        return commonService.productCoverUpload(file, type);
    }

    /**
     * @deprecatedjavadoc
     *
     * At present, exporting requires filtering based on query conditions, and the exportExcel method exports all data.
     * It is more of a universal export method, and the implementation class of the method needs to match the array
     * index mapping of exported columns, which is very complex. Therefore, it will be removed in the next version
     *
     * @param type export type name
     * @param response HttpServletResponse object file
     */
    @GetMapping("/export/excel")
    @Deprecated(since = "2.1.0", forRemoval = true)
    public void exportExcel(@RequestParam("type") String type, HttpServletResponse response) {
        try {
            File file = commonService.exportExcel(type);
            ExcelUtil.downloadExcel(file, file.getName(), response);
        }catch (Exception e) {
            log.error("导出excel异常", e);
        }
    }

    @PostMapping("uploadOss")
    public Response<List<String>> uploadOss(@RequestParam("files") List<MultipartFile> files) {
        return commonService.uploadOss(files);
    }

    @GetMapping("nextId/{type}")
    public Response<String> nextId(@PathVariable String type) {
        return commonService.generateSnowflakeId(type);
    }
}
