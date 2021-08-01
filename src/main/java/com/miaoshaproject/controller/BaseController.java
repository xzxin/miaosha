package com.miaoshaproject.controller;

import com.miaoshaproject.error.BussinessException;
import com.miaoshaproject.error.EnumBussinessError;
import com.miaoshaproject.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public Object handlerException(HttpServletRequest request, Exception exception) {
//        Map<String, Object> responseData = new HashMap<>();
//        if (!(exception instanceof BussinessException)) {
//            responseData.put("errCode", EnumBussinessError.UNKNOWN_ERROR.getErrCode());
//            responseData.put("errMsg", EnumBussinessError.UNKNOWN_ERROR.getErrMsg());
//        } else {
//            BussinessException bussinessException = (BussinessException) exception;
//            responseData.put("errCode", bussinessException.getErrCode());
//            responseData.put("errMsg", bussinessException.getErrMsg());
//        }
//        return CommonReturnType.create(responseData, "fail");
//    }
}
